package com.weison.sell.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@DynamicUpdate
@Entity
public class OrderDetial {

    @Id
    private String detialId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productImg;

    private Date createTime;

    private Date updateTime;

}
