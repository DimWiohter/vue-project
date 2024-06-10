package com.coffeewx.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis缓存相关服务
 */
@Service
public class CacheService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove( key );
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set <String> keys = redisTemplate.keys( pattern );
        if (keys.size() > 0)
            redisTemplate.delete( keys );
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists( key )) {
            redisTemplate.delete( key );
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey( key );
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations <String, String> operations = redisTemplate.opsForValue();
        result = operations.get( key );
        return result;
    }

    /**
     * 缓存map
     *
     * @param k
     * @param v
     */
    public void cacheMap(String k, Map <String, String> v) {
        cacheMap( k, v, -1 );
    }

    /**
     * 缓存map
     *
     * @param k
     * @param v
     * @param time
     */
    public void cacheMap(String k, Map <String, String> v, long time) {
        redisTemplate.opsForHash().putAll( k, v );
        if (time > 0) {
            redisTemplate.expire( k, time, TimeUnit.SECONDS );
        }
    }

    /**
     * 读取map缓存
     *
     * @param key
     * @return
     */
    public Map <String, String> getMap(final String key) {
        Map <String, String> result = null;
        HashOperations <String, String, String> opsForHash = redisTemplate.opsForHash();
        result = opsForHash.entries( key );
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations <String, String> operations = redisTemplate.opsForValue();
            operations.set( key, value );
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations <String, String> operations = redisTemplate.opsForValue();
            operations.set( key, value );
            redisTemplate.expire( key, expireTime, TimeUnit.SECONDS );
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
