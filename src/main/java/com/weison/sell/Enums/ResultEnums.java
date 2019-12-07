package com.weison.sell.Enums;

public enum ResultEnums {

    NOTFOUNT_PRODUCT(0, "没有找到该商品"),
    OUTOF_PRODUCT_COUNT(1, "超出商品库存"),
    NOTFOUNT_ORDER(2, "订单未查询到"),
    ORDER_NOT_HAVE_PRODUCT(3, "订单中未查询到商品"),
    ORDER_STATUS_ERROR(4, "订单状态异常"),
    ORDER_CANEL_ERROR(5, "取消订单失败"),
    ORDERList_NOT_HAVE_PRODUCT(6, "订单中没有商品"),
    ORDER_PAY_STATUS_ERROR(7, "订单支付状态异常"),
    ORDER_FINISHED_ERROR(8, "取消订单失败"),
    ORDER_CREATE_ERROR(9, "订单创建失败"),
    ORDER_PARAM_ERROR(10, "订单参数不正确"),
    ORDER_OPENID_EQ_ERROR_ORDERID(11, "订单与用户不匹配");

    private Integer code;

    private String msg;

    ResultEnums(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
