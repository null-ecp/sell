package com.weison.sell.Entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;
    // 库存
    private Integer productStock;

    private String productDesc;

    private String productImg;
    // 分类编号
    private Integer categoryType;
    // 商品状态
    private Integer productStatus;

    private Date createTime;

    private Date updateTime;
}
