package com.weison.sell.Enums;

/**
 * 商品状态
 */
public enum ProductStatus {

    /**
     * 上架
     */
    UP(0, "上架"),
    /**
     * 下架
     */
    DOWN(1, "下架");

    private Integer code;

    private String msg;

    ProductStatus(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
