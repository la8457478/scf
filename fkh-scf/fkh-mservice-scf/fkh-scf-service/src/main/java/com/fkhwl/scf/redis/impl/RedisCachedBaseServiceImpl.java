package com.fkhwl.scf.redis.impl;

import com.fkhwl.scf.redis.RedisCachedBaseService;
import com.fkhwl.starter.redis.service.impl.SimpleRedisServiceImpl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisCachedBaseServiceImpl extends SimpleRedisServiceImpl implements RedisCachedBaseService {

    public RedisCachedBaseServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate);
    }

    /**
     * 尝试获取分布式锁
     * @param key 锁的名称
     * @param value 锁的持有对象
     * @param time 锁的过期时间(秒)
     * @return
     */
    public boolean tryGetDistributedLock(String key, String value, int time){
        try {
            if(time < 1 || time > 600){
                log.error("tryGetDistributedLock max timeout is 600s");
                // 锁定时间小于1秒，大于10分钟，默认获取失败
                return false;
            }
            return super.setIfAbsent(key, value,  time);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
        }
        return false;
    }
}
