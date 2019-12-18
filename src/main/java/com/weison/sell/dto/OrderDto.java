package com.weison.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.weison.sell.Entity.OrderDetial;
import com.weison.sell.Enums.OrderStatus;
import com.weison.sell.Enums.PayStatus;
import com.weison.sell.Serizlieable.Date2LongSerizlieable;
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

    @JsonSerialize(using = Date2LongSerizlieable.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerizlieable.class)
    private Date updateTime;

    private List<OrderDetial> orderDetialList;
}
