package com.springboot.cache.service;


import com.springboot.cache.mapper.UserMapper;
import com.springboot.cache.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getOne(Integer id){
      return   userMapper.getOne(id);
    }

    public int addUser(User user){
        return userMapper.addUser(user);
    }
}
