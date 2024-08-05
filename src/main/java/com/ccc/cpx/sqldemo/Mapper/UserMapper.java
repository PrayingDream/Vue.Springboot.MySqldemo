package com.ccc.cpx.sqldemo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccc.cpx.sqldemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
