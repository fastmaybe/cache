package com.springboot.cache.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * springboot 1.X
 */
//@Configuration
public class RedisConfig {

//    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)  {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        System.err.println("******************************************");
        return template;
    }

    /**
     如果参数类型所对应的实例在spring容器中只有一个，则默认选择这个实例。如果有多个，
     则需要根据参数名称来选择（参数名称就相当于是spring的配置文件中的bean的id）
     *
     */
//    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate<Object, Object> redisTemplate){

      /*  RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        //  使用前缀，默认会将CacheName作为key的前缀
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;*/
        return null;
    }
}
