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
    private int currActiveCount;

    /**
     * Current number of threads in the pool
     */
    private int currPoolSize;

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

    public void setMaxPoolSize() {
        this.maxPoolSize = maxPoolSize;
    }

    public int getCurrActiveCount() {
        return currActiveCount;
    }

    public void setCurrActiveCount(int currActiveCount) {
        this.currActiveCount = currActiveCount;
    }

    public int getCurrPoolSize() {
        return currPoolSize;
    }

    public void setCurrPoolSize(int currPoolSize) {
        this.currPoolSize = currPoolSize;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }
    
    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }
}
