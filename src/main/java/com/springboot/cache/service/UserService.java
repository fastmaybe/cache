package com.springboot.cache.service;


import com.springboot.cache.mapper.UserMapper;
import com.springboot.cache.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    private static final Logger logger=LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Resource
    private RedisCacheManager redisCacheManager;
    /**
     * 方法结果缓存
     * cacheManager:管理多个cache组件
     *   cacheNames:cache组件的名字
     *   key:缓存数据的key 默认是方法参数的值
     *      或者spel表达式
     *              method 方法名做key  #{root.method.name}
     *              target 被调用的目标对象做 key #{root.target}
     *              targetClass被调用的目标对象类做 key #{root.target.class}
     *              args  第一个参数做key   #{root.args[0]} ; #a0 ;#p0
     *              或者直接参数名  #id
     *   keyGenerator:指定key的生存策略 （和key二选一使用）
     *   cacheManager：指定缓存管理器
     *   cacheResolver:指定缓存解析器  和cacheManager二选一
     *   condition：指定缓存的条件（满足条件缓存）
     *   unless：指定不缓存的条件（满足条件不进行缓存）
     *   sync：是否使用异步
     * @param id
     * @return
     * @Cacheable运行流程：
     *          1. 方法运行之前，先查询Cache(缓存组件)，按照CacheNames指定的名字获取
     *                 (CacheManager先获取相应的缓存)，第一次获取没有Cache组件就自动创建
     *          2. 去Cache中查找缓存的内容，使用一个key，默认为方法的参数，
     *          key是按照某种策略生成的：默认是使用keyGenerator,默认使用SimpleKeyGenerator
     *                  SimpleKeyGenerator生成key默认策略：
     *                      如果没有参数：key= new SimpleKey()
     *                      有一个参数：key=参数
     *                      多个参数 params：key= new SimpleKey(params)
     *           3 .没有查到缓存就调用方法
     *           4. 将目标方法结果存入缓存
     */
    @Cacheable(cacheNames="user",key = "#id")
    public User getOne(Integer id){
        logger.info("查询一个用户");
        redisCacheManager.getCacheConfigurations();
      return   userMapper.getOne(id);
    }
    @Caching
    public int addUser(User user){
        return userMapper.addUser(user);
    }
}
