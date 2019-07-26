package com.springboot.cache.controller;

import com.springboot.cache.pojo.User;
import com.springboot.cache.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("get/{id}")
    public User getOne(@PathVariable("id") Integer id){
        return   userService.getOne(id);
    }
    @GetMapping("add")
    public int addUser(User user){
        return userService.addUser(user);
    }
}
