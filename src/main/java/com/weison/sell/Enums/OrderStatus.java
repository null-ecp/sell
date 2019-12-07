package com.weison.sell.Enums;

/**
 * 订单状态
 */
public enum OrderStatus {

    NEW(0, "新订单"),
    FINISHED(1, "完成"),
    CANEL(2, "已取消");


    private Integer code;

    private String msg;

    OrderStatus(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }
}
