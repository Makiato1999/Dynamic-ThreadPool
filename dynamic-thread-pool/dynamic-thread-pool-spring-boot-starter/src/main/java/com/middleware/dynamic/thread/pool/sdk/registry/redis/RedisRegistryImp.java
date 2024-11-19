package com.middleware.dynamic.thread.pool.sdk.registry.redis;

import com.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import com.middleware.dynamic.thread.pool.sdk.domain.model.valobj.RegistryEnumVO;
import com.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import java.time.Duration;
import java.util.List;

/**
 * @description Redis 注册中心，支持线程池配置的批量上报，支持存储单个配置参数上报
 * @author Xiaoran
 * @create 2024-11-11 12:07
 */

public class RedisRegistryImp implements IRegistry {
    private final RedissonClient redissonClient;

    public RedisRegistryImp(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolConfigEntityList) {
        // redissonClient.getList() 不会立即与 Redis 交互，而是返回一个操作 Redis 的代理对象。
        RList<ThreadPoolConfigEntity> list = redissonClient.getList(RegistryEnumVO.THREAD_POOL_CONFIG_LIST_KEEY.getKey());
        // 实际的数据操作延迟到你调用 addAll() 或其他方法时才发生。
        list.addAll(threadPoolConfigEntityList);
    }

    @Override
    public void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity) {
        // 获取配置前缀, 拼接线程池名称, 生成唯一的 Redis 键
        String cacheKey = RegistryEnumVO.THREAD_POOL_CONFIG_PARAMETER_LIST_KEY.getKey() + "_" + threadPoolConfigEntity.getThreadPoolName();
        // RBucket 是 Redisson 封装的 Redis String 数据结构的接口, 提供了键值对存储
        // 值可以是任意 Java 对象，Redisson 会自动将其序列化为 Redis 可存储的格式（例如 JSON 或二进制）。
        // 如果 Redis 中还没有这个键，这一步不会抛出异常，RBucket 会自动初始化。
        RBucket<ThreadPoolConfigEntity> bucket = redissonClient.getBucket(cacheKey);
        bucket.set(threadPoolConfigEntity, Duration.ofDays(30));
    }
}
