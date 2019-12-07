package com.weison.sell.Pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderPojo {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "电话必填")
    private String phone;

    @NotEmpty(message = "住址必填")
    private String address;

    @NotEmpty(message = "用户id必填")
    private String openid;

    @NotEmpty(message = "购物车必填")
    private String items;
}
