package com.ccc.cpx.sqldemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ccc.cpx.sqldemo.Mapper.UserMapper;
import com.ccc.cpx.sqldemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

    public List<User> selectall(){
        wrapper.clear();
        wrapper.ne("delete_sign",1);
        return userMapper.selectList(wrapper);
    }
    public int insert(User user){
        wrapper.clear();
        wrapper.eq("job_number",user.getJobNumber());
        if (userMapper.selectOne(wrapper) == null){
            return userMapper.insert(user);
        } else {
            return 0;
        }

    }
    public User selectWithJobNumber(String jobNumber) {
        wrapper.clear();
        wrapper.eq("job_number",jobNumber).ne("delete_sign",1);
        return userMapper.selectOne(wrapper);
    }
    public List<User> selectWithName(String name) {
        wrapper.clear();
        wrapper.eq("name",name).ne("delete_sign",1);
        return userMapper.selectList(wrapper);
    }
    public int update(User user){
        return userMapper.updateById(user);
    }
    public int updateWithoutIdNumber(User user){
        user.setIdNumber(userMapper.selectById(user.getId()).getIdNumber());
        return userMapper.updateById(user);
    }
    public int delete(int id){
        updateWrapper.clear();
        updateWrapper.eq("id",id);
        User updateUser = new User();
        updateUser = userMapper.selectById(id);
        updateUser.setDeleteSign(true);
        return userMapper.update(updateUser,updateWrapper);
    }
}
