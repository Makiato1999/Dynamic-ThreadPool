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
 * @description 线程池数据上报任务
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
        List<ThreadPoolConfigEntity> threadPoolConfigEntityList = dynamicThreadPoolService.queryThreadPoolList();
        registry.reportThreadPool(threadPoolConfigEntityList);
        logger.info("Dynamic thread pool, report thread pool information: {}", JSON.toJSONString(threadPoolConfigEntityList));

        for (ThreadPoolConfigEntity threadPoolConfigEntity : threadPoolConfigEntityList) {
            registry.reportThreadPoolConfigParameter(threadPoolConfigEntity);
            logger.info("Dynamic thread pool, report thread pool configuration: {}", JSON.toJSONString(threadPoolConfigEntity));
        }
    }
}
