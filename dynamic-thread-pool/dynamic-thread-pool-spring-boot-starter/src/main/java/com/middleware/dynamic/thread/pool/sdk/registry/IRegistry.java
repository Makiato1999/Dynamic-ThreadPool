package com.middleware.dynamic.thread.pool.sdk.registry;

import com.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @description registry center interface
 * @author Xiaoran
 * @create 2024-11-11 12:07
 */

public interface IRegistry {
    void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolConfigEntityList);

    void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity);
}
