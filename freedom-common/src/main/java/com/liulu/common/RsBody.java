package com.liulu.common;

import com.liulu.Enums.RsEnum;

/**
 * 响应实体
 * Created by 刘璐 on 2018/7/20.
 */
public class RsBody {
    private String status;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public RsBody success(){
        RsBody rsBody = new RsBody();
        rsBody.setStatus(RsEnum.success.getStatus());
        rsBody.setStatus(RsEnum.success.getMsg());
        return rsBody;
    }

    @Override
    public String toString() {
        return "RsBody{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
