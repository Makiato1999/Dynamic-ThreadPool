package com.middleware.dynamic.thread.pool.sdk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description dynamic config entrance
 * @author Xiaoran
 * @create 2024-10-29 21:57
 */

@Configuration
public class DynamicThreadPoolAutoConfig {
    //private final
    @Bean("dynamicThreadPoolService")
    public String dynamicThreadPoolService() {
        return new String("");
    }
}
