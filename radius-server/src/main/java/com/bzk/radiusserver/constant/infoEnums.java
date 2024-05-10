package com.bzk.radiusserver.constant;

public enum infoEnums {
    _3GPP_Selection_Mode_0(0,"MS or network provided APN, subscribed verified"),
    _3GPP_Selection_Mode_1(1,"MS provided APN, subscription not verified"),
    _3GPP_Selection_Mode_2(2,"Network provided APN, subscription not verified"),
    _3GPP_Selection_Mode_3(3,"For future use. Shall not be sent. If received, shall be interpreted as the value \"2\""),
    ;

    int code;
    String msg;

    infoEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
