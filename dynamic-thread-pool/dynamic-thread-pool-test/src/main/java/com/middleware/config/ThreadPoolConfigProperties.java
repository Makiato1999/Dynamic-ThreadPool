package com.middleware.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "thread.pool.executor.config", ignoreInvalidFields = true)
public class ThreadPoolConfigProperties {
    /**
     * Number of core threads
     */
    private Integer corePoolSize = 20;
    /**
     * Maximum number of threads
     */
    private Integer maxPoolSize = 200;
    /**
     * Maximum waiting time
     */
    private Long keepAliveTime = 10L;
    /**
     * Maximum number of queues
     */
    private Integer blockQueueSize = 5000;
    /**
     * AbortPolicy: discard the task and throw a RejectedExecutionException.
     * DiscardPolicy: directly discard the task, but no exception will be thrown
     * DiscardOldestPolicy: delete the earliest task in the queue, and the task that attempts to join the queue later will be rejected
     * CallerRunsPolicy: if the task fails to add the thread pool, the main thread executes the task itself
     */
    private String policy = "AbortPolicy";
}
