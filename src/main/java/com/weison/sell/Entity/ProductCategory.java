package com.weison.sell.Entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
