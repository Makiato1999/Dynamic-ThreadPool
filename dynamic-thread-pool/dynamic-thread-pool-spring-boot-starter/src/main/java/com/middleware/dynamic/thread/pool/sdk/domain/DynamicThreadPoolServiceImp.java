package com.middleware.dynamic.thread.pool.sdk.domain;

import com.alibaba.fastjson.JSON;
import com.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;

public class DynamicThreadPoolServiceImp implements IDynamicThreadPoolService {

    private final Logger logger = LoggerFactory.getLogger(DynamicThreadPoolServiceImp.class);

    private final Map<String, ThreadPoolExecutor> threadPoolExecutorMap;

    private final String applicationName;

    public DynamicThreadPoolServiceImp(String applicationName, Map<String, ThreadPoolExecutor> threadPoolExecutorMap) {
        this.applicationName = applicationName;
        this.threadPoolExecutorMap = threadPoolExecutorMap;
    }

    @Override
    public List<ThreadPoolConfigEntity> queryThreadPoolList() {
        Set<String> threadPoolBeanNames = threadPoolExecutorMap.keySet();
        List<ThreadPoolConfigEntity> threadPoolVOS = new ArrayList<>(threadPoolBeanNames.size());
        for (String beanName : threadPoolBeanNames) {
            ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(beanName);
            ThreadPoolConfigEntity threadPoolConfigVO = new ThreadPoolConfigEntity(applicationName, beanName);
            threadPoolConfigVO.setCorePoolSize(threadPoolExecutor.getCorePoolSize());
            threadPoolConfigVO.setMaxPoolSize(threadPoolExecutor.getMaximumPoolSize());
            threadPoolConfigVO.setActiveCount(threadPoolExecutor.getActiveCount());
            threadPoolConfigVO.setPoolSize(threadPoolExecutor.getPoolSize());
            threadPoolConfigVO.setQueueType(threadPoolExecutor.getQueue().getClass().getSimpleName());
            threadPoolConfigVO.setQueueSize(threadPoolExecutor.getQueue().size());
            threadPoolConfigVO.setRemainingCapacity(threadPoolExecutor.getQueue().remainingCapacity());
            threadPoolVOS.add(threadPoolConfigVO);
        }
        return threadPoolVOS;
    }

    @Override
    public ThreadPoolConfigEntity queryThreadPoolConfigByName(String threadPoolName) {
        ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(threadPoolName);
        if (threadPoolExecutor == null) {
            return new ThreadPoolConfigEntity(applicationName, threadPoolName);
        }

        ThreadPoolConfigEntity threadPoolConfigVO = new ThreadPoolConfigEntity(applicationName, threadPoolName);
        threadPoolConfigVO.setCorePoolSize(threadPoolExecutor.getCorePoolSize());
        threadPoolConfigVO.setMaxPoolSize(threadPoolExecutor.getMaximumPoolSize());
        threadPoolConfigVO.setActiveCount(threadPoolExecutor.getActiveCount());
        threadPoolConfigVO.setPoolSize(threadPoolExecutor.getPoolSize());
        threadPoolConfigVO.setQueueType(threadPoolExecutor.getQueue().getClass().getSimpleName());
        threadPoolConfigVO.setQueueSize(threadPoolExecutor.getQueue().size());
        threadPoolConfigVO.setRemainingCapacity(threadPoolExecutor.getQueue().remainingCapacity());

        if (logger.isDebugEnabled()) {
            logger.info("Dynamic Thread Pool - Configuration Query | Application Name: {} | Thread Name: {} | Pool Configuration: {}.", applicationName, threadPoolName, JSON.toJSONString(threadPoolConfigVO));
        }

        return null;
    }

    @Override
    public void updateThreadPoolConfig(ThreadPoolConfigEntity threadPoolConfigEntity) {
        if (threadPoolConfigEntity == null || !applicationName.equals(threadPoolConfigEntity.getAppName())) {
            return;
        }
        ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(threadPoolConfigEntity.getAppName());
        if (threadPoolExecutor == null) {
            return;
        }

        // Set parameters to adjust core thread count and maximum thread count
        threadPoolExecutor.setCorePoolSize(threadPoolConfigEntity.getCorePoolSize());
        threadPoolExecutor.setMaximumPoolSize(threadPoolConfigEntity.getMaxPoolSize());
    }
}
