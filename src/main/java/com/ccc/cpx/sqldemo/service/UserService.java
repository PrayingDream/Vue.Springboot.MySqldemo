package com.ccc.cpx.sqldemo.service;

import com.ccc.cpx.sqldemo.Mapper.userMapper;
import com.ccc.cpx.sqldemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    userMapper usermapper;

    public List<User> selectall(){
        return usermapper.selectall();
    }
    public int insert(User user){
        int i;
        if(selectWithJobNumber(user.getJobNumber()) == null){
            try{
                i = usermapper.insert(user);
            } catch (Exception e) {
                i = -1;
            }
            return i;
        } else {
            return 0;
        }
    }
    public User selectWithJobNumber(String jobNumber) {
        return usermapper.selectWithJobNumber(jobNumber);
    }
    public List<User> selectWithName(String name) {
        return usermapper.selectWithName(name);
    }
    public int update(User user){
        int i;
        try{
            i = usermapper.update(user);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }
    public int updateWithoutIdNumber(User user){
        int i;
        try{
            i = usermapper.updateWithoutIdNumber(user);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }
    public int delete(int id){
        int i;
        try {
            i = usermapper.delete(id);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }
}
