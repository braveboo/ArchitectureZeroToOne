package cn.mmb.b2b.user;

import net.sf.ehcache.Ehcache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.*;

/**
 * 包装Spring cache抽象,转换成shiro cacheManager
 */
public class SpringCacheManagerWrapper implements CacheManager {

//    private org.springframework.cache.CacheManager cacheManager;
    private Set<org.springframework.cache.CacheManager> cacheManagers = new HashSet<org.springframework.cache.CacheManager>();

    /**
     * 设置spring cache manager
     *
//     * @param cacheManager
     */
//    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
//        this.cacheManager = cacheManager;
//    }

    public void setCacheManagers(Set<org.springframework.cache.CacheManager> cacheManagers) {
        this.cacheManagers = cacheManagers;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if(name != null && name.contains("redis")){
            return getCacheByClass(RedisCacheManager.class,name);
        }
        return getCacheByClass(EhCacheCacheManager.class,name);
    }

    private SpringCacheWrapper getCacheByClass(Class classType, String name) {
        org.springframework.cache.Cache cache = null;
        for(org.springframework.cache.CacheManager cacheManager : cacheManagers){
            if(classType.isInstance(cacheManager)){
                cache = cacheManager.getCache(name);
                break;
            }
        }
        return new SpringCacheWrapper(cache);
    }

    static class SpringCacheWrapper implements Cache {
        private org.springframework.cache.Cache springCache;

        SpringCacheWrapper(org.springframework.cache.Cache springCache) {
            this.springCache = springCache;
        }

        @Override
        public Object get(Object key) throws CacheException {
            Object value = springCache.get(key);
            if (value instanceof SimpleValueWrapper) {
                return ((SimpleValueWrapper) value).get();
            }
            return value;
        }

        @Override
        public Object put(Object key, Object value) throws CacheException {
            springCache.put(key, value);
            return value;
        }

        @Override
        public Object remove(Object key) throws CacheException {
            springCache.evict(key);
            return null;
        }

        @Override
        public void clear() throws CacheException {
            springCache.clear();
        }

        @Override
        public int size() {
            if(springCache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springCache.getNativeCache();
                return ehcache.getSize();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
        }

        @Override
        public Set keys() {
            if(springCache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springCache.getNativeCache();
                return new HashSet(ehcache.getKeys());
            }
            throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
        }

        @Override
        public Collection values() {
            if(springCache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springCache.getNativeCache();
                List keys = ehcache.getKeys();
                if (!CollectionUtils.isEmpty(keys)) {
                    List values = new ArrayList(keys.size());
                    for (Object key : keys) {
                        Object value = get(key);
                        if (value != null) {
                            values.add(value);
                        }
                    }
                    return Collections.unmodifiableList(values);
                } else {
                    return Collections.emptyList();
                }
            }
            throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
        }
    }
}
