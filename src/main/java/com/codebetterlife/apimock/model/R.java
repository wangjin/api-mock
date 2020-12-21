package com.codebetterlife.apimock.model;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2020-12-21 4:42 下午
 */
public class R<T> {

    /**
     * 响应数据
     */
    T data;
    /**
     * 响应状态
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;

    public static <T> R<T> success(T data) {
        R<T> r = new R<>();
        r.setCode(0);
        r.setMsg("请求成功");
        r.setData(data);
        return r;
    }

    public static <T> R<T> fail(Integer code, String message) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(message);
        r.setData(null);
        return r;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
