package com.middleware.dynamic.thread.pool.sdk.domain.model.entity;

public class ThreadPoolConfigEntity {
    /**
     * App name
     */
    private String appName;

    /**
     * ThreadPool name
     */
    private String threadPoolName;

    /**
     * Number of core threads
     */
    private int corePoolSize;

    /**
     * Maximum number of threads
     */
    private int maxPoolSize;

    /**
     * Current count of active threads
     */
    private int activeCount;

    /**
     * Current number of threads in the pool
     */
    private int poolSize;

    /**
     * Queue type
     */
    private String queueType;

    /**
     * Current number of tasks in the queue
     */
    private int queueSize;

    /**
     * Number of tasks remaining in the queue
     */
    private int remainingCapacity;

    public ThreadPoolConfigEntity() {
    }

    public ThreadPoolConfigEntity(String appName, String threadPoolName) {
        this.appName = appName;
        this.threadPoolName = threadPoolName;
    }

    public String getAppName() {
        return appName;
    }

    public String getThreadPoolName() {
        return threadPoolName;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }
}
