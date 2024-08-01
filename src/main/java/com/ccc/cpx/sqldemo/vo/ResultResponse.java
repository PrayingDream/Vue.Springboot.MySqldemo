package com.ccc.cpx.sqldemo.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ResultResponse<T> implements Serializable {
    /**
     * 接口响应状态码
     */
    private Integer code;

    /**
     * 接口响应信息
     */
    private String message;

    /**
     * 接口响应的数据
     */
    private T data;

    /**
     * 接口相应是否成功
     */
    private boolean isSuccess;
}