package com.middleware.dynamic.thread.pool.sdk.domain.model.valobj;

public enum RegistryEnumVO {
    THREAD_POOL_CONFIG_LIST_KEEY("THREAD_POOL_CONFIG_LIST_KEY", "Pooling Configuration List"),
    THREAD_POOL_CONFIG_PARAMETER_LIST_KEY("THREAD_POOL_CONFIG_PARAMETER_LIST_KEY", "Pooling Configuration Parameters"),
    DYNAMIC_THREAD_POOL_REDIS_TOPIC("DYNAMIC_THREAD_POOL_REDIS_TOPIC", "\n" +
            "Dynamic Thread Pool Listener Topic Configuration");

    private final String key;
    private final String desc;

    RegistryEnumVO(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
