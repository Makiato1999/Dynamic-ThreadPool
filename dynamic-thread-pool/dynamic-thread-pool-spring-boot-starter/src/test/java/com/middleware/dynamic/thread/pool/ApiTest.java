package com.middleware.dynamic.thread.pool;

import com.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import org.junit.Test;
import org.redisson.api.RTopic;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @description Unit testing
 * @author Xiaoran
 * @create 2024-10-29 21:57
 */

public class ApiTest {
    @Resource
    private RTopic dynamicThreadPoolRedisTopic;

    @Test
    public void test_dynamicThreadPoolRedisTopic() throws InterruptedException {
        ThreadPoolConfigEntity threadPoolConfigEntity = new ThreadPoolConfigEntity("dynamic-thread-pool-test-app", "threadPoolExecutor01");
        threadPoolConfigEntity.setCorePoolSize(100);
        threadPoolConfigEntity.setMaxPoolSize(100);
        dynamicThreadPoolRedisTopic.publish(threadPoolConfigEntity);

        new CountDownLatch(1).await();
    }
}
