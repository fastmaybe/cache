package com.springboot.cache.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
public class MyRedisCacheConfig {

    /**
     * 方式一  建造者  使用GenericJackson2JsonRedisSerializer
     *      value序列化后
     *      {
     *     "@class": "com.springboot.cache.pojo.User",
     *     "id": 6,
     *     "name": "瑞文",
     *     "email": "ruiwen"
     *      }
     * @param factory
     * @return
     */
//    @Bean
    public RedisCacheManager cacheManager_one(RedisConnectionFactory  factory) {
        RedisCacheConfiguration cacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(1))
                        .disableCachingNullValues()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new
                                GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(factory).cacheDefaults(cacheConfiguration).build();
    }

    /**
     * 方式二  构造器  使用Jackson2JsonRedisSerializer
     *  value序列化后格式
     *  {
     *     "id": 7,
     *     "name": "亚索",
     *     "email": "yasuo"
     * }
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager_two(RedisConnectionFactory redisConnectionFactory) {

        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(objectJackson2JsonRedisSerializer);
        RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
        return redisCacheManager;
    }

}
