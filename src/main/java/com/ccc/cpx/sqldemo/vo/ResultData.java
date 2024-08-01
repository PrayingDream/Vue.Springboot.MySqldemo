package com.ccc.cpx.sqldemo.vo;

public class ResultData {
    /*成功，且返回体有数据*/
    public static <T> ResultResponse<T> success(T date) {
        ResultResponse<T> r = new ResultResponse<>();
        r.setCode(StatusEnum.SUCCESS.getCode());
        r.setMessage(StatusEnum.SUCCESS.getMsg());
        r.setData(date);
        r.setSuccess(true);
        return r;
    }
    //成功，但返回体没数据
    public static  <T> ResultResponse<T> success(){
        return success(null);
    }
    //失败返回信息
    public static <T> ResultResponse<T> Err(Integer code,String msg){
        ResultResponse<T> r = new ResultResponse<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }
}