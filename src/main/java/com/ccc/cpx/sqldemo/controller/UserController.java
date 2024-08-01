package com.ccc.cpx.sqldemo.controller;

import com.ccc.cpx.sqldemo.pojo.User;
import com.ccc.cpx.sqldemo.service.UserService;
import com.ccc.cpx.sqldemo.vo.ResultData;
import com.ccc.cpx.sqldemo.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/selectAll")
    public ResultResponse<List<User>> selectAll() {
        List<User> all = userService.selectall();
        if (all != null) {
            for (User user : all) {
                user.setIdNumber(user.getIdNumber().substring(0, 2) + "**************" + user.getIdNumber().substring(16, 18));
            }
            return ResultData.success(all);
        } else {
            return ResultData.Err(500, "无法查询到可用数据");
        }
    }

    @PostMapping("/insert")
    public ResultResponse<String> insert(@RequestBody User user) {
        int i = userService.insert(user);
        if (i == 1) {
            return ResultData.success();
        } else if(i == 0){
            return ResultData.Err(500, "添加信息失败,工号不能重复");
        } else{
            return ResultData.Err(500, "服务器内部错误");
        }

    }

    @PostMapping("/select")
    public ResultResponse<List<User>> selectUser(@RequestBody User user) {
        List<User> returnUser = new ArrayList<>();
        if (user.getJobNumber() != null) {
            System.out.println("JobNumber");
            returnUser.add(userService.selectWithJobNumber(user.getJobNumber()));
        } else {
            System.out.println("name");
            returnUser = userService.selectWithName(user.getName());
        }
        if (returnUser != null) {
            return ResultData.success(returnUser);
        } else {
            return ResultData.Err(500, "服务器无此人信息");
        }
    }

    @PostMapping("/delete")
    public ResultResponse<String> delete(@RequestParam int id) {
        int i = userService.delete(id);
        if (i == 1) {
            return ResultData.success();
        } else if (i == 0) {
            return ResultData.Err(500, "服务器无此人信息");
        } else {
            return ResultData.Err(500, "删除错误");
        }
    }

    @PostMapping("/update")
    public ResultResponse<String> update(@RequestBody User user) {
        boolean updateIdNumber = true;
        String idNumber = user.getIdNumber();
        for (int i = 0; i < idNumber.length(); i++) {
            if (idNumber.charAt(i) == '*') {
                updateIdNumber = false;
                break;
            }
        }
        int i;
        if (updateIdNumber) {
            System.out.println("带身份证号");
            i = userService.update(user);
        } else {
            System.out.println("不带身份证号");
            i = userService.updateWithoutIdNumber(user);
        }

        if (i == 1) {
            return ResultData.success("更新信息成功");
        } else if (i == 0) {
            return ResultData.Err(500, "服务器无此人信息");
        } else {
            return ResultData.Err(500, "更新错误");
        }
    }
}
