package com.middleware.dynamic.thread.pool.sdk.registry.redis;

import com.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import com.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import org.redisson.api.RedissonClient;

import java.util.List;

/**
 * @description Redis registry center
 * @author Xiaoran
 * @create 2024-11-11 12:07
 */

public class RedisRegistry implements IRegistry {
    private final RedissonClient redissonClient;

    public RedisRegistry(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolConfigEntityList) {

    }

    @Override
    public void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity) {

    }
}
