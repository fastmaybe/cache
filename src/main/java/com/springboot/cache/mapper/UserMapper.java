package com.springboot.cache.mapper;

import com.springboot.cache.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {

    @Select("select * from user where id = #{id}")
    public User getOne(Integer id);

    @Insert("insert into user (name,email) values (#{name},#{email}) ")
    public int addUser(User user);
}
