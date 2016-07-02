package cn.mmb.b2b.user.aop;

import org.apache.commons.logging.Log;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.apache.shiro.cache.CacheManager;

/**
 * Created by lb on 2016/1/24.
 */
public class BaseCacheAspect implements InitializingBean {

    public final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private CacheManager cacheManager;
    private Cache cache;
    protected String cacheName;


    /**
     * 缓存池名称
     * @param cacheName
     */
    public void setCacheName(String cacheName){
        this.cacheName = cacheName;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = this.cacheManager.getCache(cacheName);
    }

    public void clear(){
        this.cache.clear();
        log.info("cacheName:{}, cache clear",cacheName);
    }


    public void evict(String key){
        log.debug("cacheName:{}, evict key:{}",cacheName,key);
        this.cache.remove(key);
    }

    public <T>T get(Object key){
        log.debug("cacheName:{}, get key:{}", cacheName, key);
        if(StringUtils.isEmpty(key)){
            return null;
        }
        Object value = this.cache.get(key);
        if(value == null){
             return null;
        }
        return (T) value;
    }

    public void put(String key, Object value){
        log.debug("cacheName:{}, put key:{}",cacheName,key);
        this.cache.put(key,value);
    }

}
