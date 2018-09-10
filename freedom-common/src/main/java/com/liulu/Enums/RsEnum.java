package com.liulu.Enums;

/**
 * Created by 刘璐 on 2018/7/20.
 */
public enum RsEnum {
    SUCCESS("0000","成功"),
    LOGIN_EXCEPTION("1001","请登录")

    ;


    private String status;

    private String msg;

    RsEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static RsEnum getEnumByKey(String key){
        for (RsEnum rsEnum : RsEnum.values()){
            if(rsEnum.getStatus().equals(key)){
                return rsEnum;
            }
        }
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
