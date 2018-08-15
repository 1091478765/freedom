package com.liulu.Enums;

/**
 * Created by 刘璐 on 2018/8/14.
 */
public enum BaseEnum {
    ;
    private String code;

    private String value;

    BaseEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
