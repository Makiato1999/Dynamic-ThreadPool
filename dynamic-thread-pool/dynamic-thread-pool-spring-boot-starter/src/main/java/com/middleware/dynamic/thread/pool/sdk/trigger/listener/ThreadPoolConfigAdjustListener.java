package com.middleware.dynamic.thread.pool.sdk.trigger.listener;

import com.middleware.dynamic.thread.pool.sdk.domain.IDynamicThreadPoolService;
import com.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import com.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @description 监听器, 动态调整线程池配置，并上报更新后的线程池状态
 * @author Xiaoran
 * @create 2024-11-18
 */
public class ThreadPoolConfigAdjustListener implements MessageListener<ThreadPoolConfigEntity> {
    private Logger logger = LoggerFactory.getLogger(ThreadPoolConfigAdjustListener.class);

    private final IDynamicThreadPoolService dynamicThreadPoolService;

    private final IRegistry registry;

    public ThreadPoolConfigAdjustListener(IDynamicThreadPoolService dynamicThreadPoolService, IRegistry registry) {
        this.dynamicThreadPoolService = dynamicThreadPoolService;
        this.registry = registry;
    }

    // 消息订阅, 依赖 Redisson 来监听 Redis 的消息频道, 一旦接收到消息，调用 onMessage 方法
    @Override
    public void onMessage(CharSequence charSequence, ThreadPoolConfigEntity threadPoolConfigEntity) {
        logger.info("动态线程池，调整线程池配置。线程池名称:{} 核心线程数:{} 最大线程数:{}", threadPoolConfigEntity.getThreadPoolName(), threadPoolConfigEntity.getPoolSize(), threadPoolConfigEntity.getMaxPoolSize());
        // 根据新收到的配置 更新线程池参数
        dynamicThreadPoolService.updateThreadPoolConfig(threadPoolConfigEntity);

        // 获取所有线程池的最新配置
        List<ThreadPoolConfigEntity> threadPoolConfigEntityList = dynamicThreadPoolService.queryThreadPoolList();
        // 上报到注册中心
        registry.reportThreadPool(threadPoolConfigEntityList);

        // 查询当前线程池的具体参数
        ThreadPoolConfigEntity threadPoolConfigEntityCurrent = dynamicThreadPoolService.queryThreadPoolConfigByName(threadPoolConfigEntity.getThreadPoolName());
        // 上报
        registry.reportThreadPoolConfigParameter(threadPoolConfigEntityCurrent);

        logger.info("动态线程池，上报线程池配置：{}", JSON.toJSONString(threadPoolConfigEntity));
    }
}
