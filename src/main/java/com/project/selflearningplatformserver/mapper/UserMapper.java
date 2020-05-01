package com.project.selflearningplatformserver.mapper;

import com.project.selflearningplatformserver.entity.User;

import java.util.Optional;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Optional<User> selectByUserName(String username);
}