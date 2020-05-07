package com.fkhwl.scf.web.shiro;

import com.fkhwl.scf.web.interceptor.ExceptionHandler;
import com.fkhwl.starter.core.util.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisSessionDao extends AbstractSessionDAO {
    private Logger log = LoggerFactory.getLogger(RedisSessionDao.class);
    /**
     * key前缀
     */
    private static final String SHIRO_REDIS_SESSION_KEY_PREFIX = "shiro.redis.session_";

    private RedisTemplate redisTemplate;

    private ValueOperations valueOperations;

    public RedisSessionDao(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
//        if (LOGGER.isDebugEnabled()) {
        log.debug("shiro redis session create. sessionId={}", sessionId);
//        }
        this.assignSessionId(session, sessionId);
        valueOperations.set(generateKey(sessionId), session, session.getTimeout(), TimeUnit.SECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
            log.debug("shiro redis session read. sessionId={}", sessionId);
        return (Session) valueOperations.get(generateKey(sessionId));
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
            log.debug("shiro redis session update. sessionId={}", session.getId());
        valueOperations.set(generateKey(session.getId()), session, session.getTimeout(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void delete(Session session) {
        log.debug("shiro redis session delete. sessionId={}", session.getId());
        redisTemplate.delete(generateKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Object> keySet = redisTemplate.keys(generateKey("*"));
        Set<Session> sessionSet = new HashSet<>();
        if (CollectionUtils.isEmpty(keySet)) {
            return Collections.emptySet();
        }
        for (Object key : keySet) {
            sessionSet.add((Session) valueOperations.get(key));
        }
            log.debug("shiro redis session all. size={}", sessionSet.size());
        return sessionSet;
    }

    /**
     * 重组key
     * 区别其他使用环境的key
     *
     * @param key
     * @return
     */
    private String generateKey(Object key) {
        return SHIRO_REDIS_SESSION_KEY_PREFIX + this.getClass().getName() +"_"+ key;
    }
}
