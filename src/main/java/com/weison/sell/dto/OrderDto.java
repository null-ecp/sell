package com.weison.sell.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weison.sell.Entity.OrderDetial;
import com.weison.sell.Enums.OrderStatus;
import com.weison.sell.Enums.PayStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private String orderId;

    private String buyyerName;

    private String buyyerPhone;

    private String buyyerOpenid;

    private String buyyerAddress;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetial> orderDetialList;
}
