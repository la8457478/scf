package com.fkhwl.scf.redis;

import com.fkhwl.starter.redis.service.RedisService;

/**
 * @author liaohong
 * @description: 缓存层顶级接口
 * @date: 17:28 2017/7/3
 */
public interface RedisCachedBaseService extends RedisService {


    /**
     * 尝试获取分布式锁，自动释放连接
     * @param key 锁的名称
     * @param value 持有锁的对象Id
     * @param time 锁的过期时间(秒)
     * @return
     */
    boolean tryGetDistributedLock(String key, String value, int time);
}
