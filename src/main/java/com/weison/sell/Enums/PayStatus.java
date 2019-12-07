package com.weison.sell.Enums;

/**
 * 支付状态
 */
public enum PayStatus {

    WAIT(0, "等待支付"),
    PAYED(1, "支付完成");

    private Integer code;

    private String msg;

    PayStatus(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }
}
