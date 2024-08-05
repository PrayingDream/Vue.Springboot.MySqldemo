package com.ccc.cpx.sqldemo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("test")
public class User {
    private Integer id;
    private String jobNumber;
    private String name;
    private int age;
    private String idNumber;
    private String phoneNumber;
    private String department;
    private double salary;
    private boolean deleteSign;
}
