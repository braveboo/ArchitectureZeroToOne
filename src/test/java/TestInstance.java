import junit.framework.Test;

/**
 * Created by lb on 2016/7/1.
 */
public class TestInstance {
    public static void main(String[] args){
        Class cl = TestInstance.class;
        TestInstance t = new TestInstance();
        System.out.println(cl.isInstance(t));
    }
}
