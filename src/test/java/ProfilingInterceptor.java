import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ProfilingInterceptor implements MethodInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger("ProfilingInterceptorLog");
	//打印日志限制，当方法执行时间大于该值时，记录性能日志
    public static final long  TIME_LENGTH=500L;
    //字符串最大长度
    public static final int MAX_STR_LENGTH=100;
    
	public Object invoke(MethodInvocation invocation) throws Throwable {
        // 启动一个 stop watch
        StopWatch sw = new StopWatch();
        // 运行计时器
        sw.start(invocation.getMethod().getName());
        // 执行业务方法
        Object returnValue = invocation.proceed();
        // 停止计时器
        sw.stop();
        // 垃圾信息输出
        dumpInfo(invocation, sw.getTotalTimeMillis(), returnValue);
        // 返回业务方法返回值
        return returnValue;
    }

	/**
	 * 输出方法运行的计时信息
	 * @param invocation
	 * @param ms
	 * @param returnValue
	 */
    private void dumpInfo(MethodInvocation invocation, long ms, Object returnValue) {
        // 获取被调用方法
        Method m = invocation.getMethod();
        // 获取被调用方法所属的对象
        Object target = invocation.getThis();
        // 获取被调用方法的参数
        Object[] args = invocation.getArguments();
        String classSimpleName = target.getClass().getSimpleName();
        if (classSimpleName.startsWith("$Proxy")) {
            return;
        }
        if(ms<TIME_LENGTH){
			return;
		}
        String paramString = deepToString(args);
    	if (paramString.length() > MAX_STR_LENGTH) {
    		paramString= paramString.substring(0, MAX_STR_LENGTH);
		}
    	logger.info("duration[{}ms] method:[{}.{}] params: {}",
				new Object[] { ms, classSimpleName, m.getName(), paramString});
    }
    
	public static String deepToString(Object[] a) {
		if (a == null) {
			return "null";
		}
		int bufLen = 20 * a.length;
		if ((a.length != 0) && (bufLen <= 0)) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuilder buf = new StringBuilder(bufLen);
		deepToString(a, buf, new HashSet());
		return buf.toString();
	}
	
	private static void deepToString(Object[] a, StringBuilder buf, Set<Object[]> dejaVu) {
		if (a == null) {
			buf.append("null");
			return;
		}
		dejaVu.add(a);
		buf.append('[');
		for (int i = 0; i < a.length; i++) {
			if (i != 0) {
				buf.append(", ");
			}
			Object element = a[i];
			if (element == null) {
				buf.append("null");
			} else {
				Class eClass = element.getClass();
				if (eClass.isArray()) {
					if (eClass == byte[].class) {
						buf.append("[....]"); 
					} else if (eClass == short[].class) {
						buf.append(Arrays.toString((short[]) element));
					} else if (eClass == int[].class) {
						buf.append(Arrays.toString((int[]) element));
					} else if (eClass == long[].class) {
						buf.append(Arrays.toString((long[]) element));
					} else if (eClass == char[].class) {
						buf.append(Arrays.toString((char[]) element));
					} else if (eClass == float[].class) {
						buf.append(Arrays.toString((float[]) element));
					} else if (eClass == double[].class) {
						buf.append(Arrays.toString((double[]) element));
					} else if (eClass == boolean[].class) {
						buf.append(Arrays.toString((boolean[]) element));
					} else { // element is an array of object references
						if (dejaVu.contains(element)) {
							buf.append("[...]");
						} else {
							deepToString((Object[]) element, buf, dejaVu);
						}
					}
				} else if (element instanceof Collection) {
					deepToString(((Collection) element).toArray(), buf, dejaVu);
					deepToString(((Collection) element).toArray());
				} else {
					// element is non-null and not an array
					try {
						buf.append(element.toString());
					} catch (Exception e) {
						// ignore
					}
				}
			}
		}
		buf.append("]");
		dejaVu.remove(a);
	}
    
}