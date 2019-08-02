package com.springboot.cache;

import com.springboot.cache.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
  private   RedisTemplate<Object,Object> redisTemplate;
    @Test
    public void contextLoads() {
//      stringRedisTemplate.opsForValue().set("cache2","2019/8/1");
        User user = new User(1,"chche2","email");
        redisTemplate.opsForValue().set(user,user);
    }

}
