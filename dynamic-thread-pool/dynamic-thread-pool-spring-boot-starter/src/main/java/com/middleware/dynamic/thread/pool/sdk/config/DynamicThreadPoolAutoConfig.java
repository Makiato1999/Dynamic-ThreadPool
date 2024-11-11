package com.middleware.dynamic.thread.pool.sdk.config;

import com.alibaba.fastjson.JSON;
import com.middleware.dynamic.thread.pool.sdk.domain.DynamicThreadPoolServiceImp;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description dynamic config entrance
 * @author Xiaoran
 * @create 2024-10-29 21:57
 */

@Configuration
public class DynamicThreadPoolAutoConfig {
    private final Logger logger = LoggerFactory.getLogger(DynamicThreadPoolAutoConfig.class);
    @Bean("dynamicThreadPoolService")
    public DynamicThreadPoolServiceImp dynamicThreadPoolService(ApplicationContext applicationContext, Map<String, ThreadPoolExecutor> threadPoolExecutorMap) {
        String applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");

        if (StringUtils.isBlank(applicationName)) {
            applicationName = "NameIsNotFound";
            logger.warn("Dynamic thread pool startup prompt: SpringBoot application is not configured with spring.application.name, unable to obtain the application name!");
        }

        return new DynamicThreadPoolServiceImp(applicationName, threadPoolExecutorMap);
    }
}
