package cn.mmb.b2b.user.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

/**
 * Created by lb on 2016/1/24.
 */
@Component
@Aspect
public class UserRoleAspect extends BaseCacheAspect {

    private String roleKeyPrefix = "roleKey-";
    public UserRoleAspect(){
        this.setCacheName("redis-roleCache");
    }
    @Pointcut(value = "target(cn.mmb.b2b.user.domain.service.RoleService)")
    public void roleServicePointcut(){

    }

    @Pointcut(value = "(execution(* getRoleListByUserName(*))) && args(arg)",argNames = "arg")
    public void roleCachePointcut(String arg){

    }

    @Around(value = "roleServicePointcut() && roleCachePointcut(arg)",argNames = "pjp,arg")
    public Object getRoleCacheAdvice(ProceedingJoinPoint pjp, String arg) throws Throwable {
        String key = roleKeyPrefix+arg;
        Object retValue = get(key);
        if(retValue != null){
            log.info("cacheName:{}, method:findRolesCacheableAdvice, hit key:{}",cacheName,key);
            return retValue;
        }
        log.info("cacheName:{}, method:findRolesCacheableAdvice, miss key:{}",cacheName,key);
        retValue = pjp.proceed();
        put(key,retValue);
        log.info("cacheName:{}, method:findRolesCacheableAdvice, put key:{}",cacheName,key);
        return retValue;
    }

}
