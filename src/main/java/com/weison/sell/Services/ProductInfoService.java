package com.weison.sell.Services;

import com.weison.sell.Entity.ProductInfo;
import com.weison.sell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    Page<ProductInfo> findall(Pageable pageable);

    List<ProductInfo> findByStatus(Integer status);

    ProductInfo findone(String id);

    ProductInfo save(ProductInfo productInfo);

    // 查询指定分类下的商品
    List<ProductInfo> findByCategoryType(Integer type);

    // 加库存
    void addproductStock(List<CartDto> cartDtoList);

    // 减库存
    void delproductStock(List<CartDto> cartDtoList);
    /**
     * 上架商品
     * @param productInfo
     */
    void upProduct(ProductInfo productInfo);
}
