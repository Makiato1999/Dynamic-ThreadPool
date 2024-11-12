package com.middleware.dynamic.thread.pool.sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dynamic.thread.pool.config", ignoreInvalidFields = true)
public class DynamicThreadPoolAutoProperties {
    /**
     * Status: open, close
     */
    private boolean enable;

    /**
     * Redis host
     */
    private String host;

    /**
     * Redis port
     */
    private int port;

    /**
     * Password
     */
    private String password;

    /**
     * Set the connection pool size.
     * The default is 64
     */
    private int poolSize = 64;

    /**
     * Set the minimum number of idle connections in the connection pool.
     * The default is 10
     */
    private int minIdleSize = 10;

    /**
     * Set the maximum idle time of the connection (unit: milliseconds).
     * Idle connections exceeding this time will be closed.
     * The default value is 10000
     */
    private int idleTimeout = 10000;

    /**
     * Set the connection timeout (unit: milliseconds).
     * The default value is 10000
     */
    private int connectTimeout = 10000;

    /**
     * Set the number of connection retries.
     * The default value is 3
     */
    private int retryAttempts = 3;

    /**
     * Set the interval time for connection retries (unit: milliseconds).
     * The default value is 1000
     */
    private int retryInterval = 1000;

    /**
     * Set the time interval for periodic checks on whether the connection is available (unit: milliseconds).
     * The default value is 0, indicating that no periodic checks are performed
     */
    private int pingInterval = 0;

    /**
     * Set whether to maintain a long connection.
     * The default value is true
     */
    private boolean keepAlive = true;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public int getMinIdleSize() {
        return minIdleSize;
    }

    public void setMinIdleSize(int minIdleSize) {
        this.minIdleSize = minIdleSize;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }

    public void setRetryAttempts(int retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public int getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
    }

    public int getPingInterval() {
        return pingInterval;
    }

    public void setPingInterval(int pingInterval) {
        this.pingInterval = pingInterval;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}
