package com.example.springboot2.dao;

public class JsonData {
    private Integer code;
    private String msg;
    private Object data;
    public JsonData() {
        super();
    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /***
     * 操作成功，没有数据返回，携带自定义描述信息
     * @param msg
     * @return
     */
    public static JsonData buildSuccess(String msg){
        return new JsonData(0, null, msg);
    }
    public static JsonData buildSuccess( ){
        return new JsonData(0, null, "success");
    }
    public static JsonData buildSuccess(Object data, String msg){
        return new JsonData(0, data, msg);
    }
    public static JsonData buildSuccess(Object data){
        return new JsonData(0, data, "success");
    }
    public static JsonData buildSuccess(Integer code, String msg){
        return new JsonData(code,null, msg);
    }

    public static JsonData buildError(String msg){
        return new JsonData(-1, null, msg);
    }public static JsonData buildError(){
        return new JsonData(-1, null, "fail");
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg +
                '}';
    }
}
