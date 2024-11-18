package com.middleware.dynamic.thread.pool.sdk.trigger.job;

import com.alibaba.fastjson.JSON;
import com.middleware.dynamic.thread.pool.sdk.domain.IDynamicThreadPoolService;
import com.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import com.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @description 线程池数据定时上报任务，用于报告线程池及其配置参数信息
 * @author Xiaoran
 * @create 2024-11-17
 */

public class ThreadPoolDataReportJob {
    private final Logger logger = LoggerFactory.getLogger(ThreadPoolDataReportJob.class);

    private final IDynamicThreadPoolService dynamicThreadPoolService;

    private final IRegistry registry;

    public ThreadPoolDataReportJob(IDynamicThreadPoolService iDynamicThreadPoolService, IRegistry registry) {
        this.dynamicThreadPoolService = iDynamicThreadPoolService;
        this.registry = registry;
    }

    @Scheduled(cron = "0/20 * * * * ?")
    public void execReportThreadPoolList() {
        // 获取所有动态线程池的配置信息列表, 列表包含了多个 ThreadPoolConfigEntity 对象，每个对象对应一个线程池的配置
        List<ThreadPoolConfigEntity> threadPoolConfigEntityList = dynamicThreadPoolService.queryThreadPoolList();
        // 将获取到的列表上报到注册中心
        registry.reportThreadPool(threadPoolConfigEntityList);
        logger.info("Dynamic thread pool, report thread pool information: {}", JSON.toJSONString(threadPoolConfigEntityList));

        // 遍历每个线程池的配置信息
        for (ThreadPoolConfigEntity threadPoolConfigEntity : threadPoolConfigEntityList) {
            // 上报每个线程池的详细配置参数
            registry.reportThreadPoolConfigParameter(threadPoolConfigEntity);
            logger.info("Dynamic thread pool, report thread pool configuration: {}", JSON.toJSONString(threadPoolConfigEntity));
        }
    }
}
