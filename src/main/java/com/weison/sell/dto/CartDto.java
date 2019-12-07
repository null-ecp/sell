package com.weison.sell.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CartDto {

    private String productId;

    private Integer productQuantity;

    public CartDto(String productId, Integer productCount){
        this.productId = productId;
        this.productQuantity = productCount;
    }
}
