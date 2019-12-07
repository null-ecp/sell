package com.weison.sell.Entity;

import com.weison.sell.Enums.OrderStatus;
import com.weison.sell.Enums.PayStatus;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单主表
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class OrderMaster {

    @Id
    private String orderId;

    private String buyyerName;

    private String buyyerPhone;

    private String buyyerOpenid;

    private String buyyerAddress;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatus.NEW.getCode();

    private Integer payStatus = PayStatus.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

}
