package com.ccc.cpx.sqldemo.Mapper;

import com.ccc.cpx.sqldemo.pojo.User;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface userMapper {
    List<User> selectall();
    User selectWithJobNumber(String jobNumber);
    List<User> selectWithName(String name);
    int insert(User user);
    int delete(int id);
    int update(User user);
    int updateWithoutIdNumber(User user);
}
