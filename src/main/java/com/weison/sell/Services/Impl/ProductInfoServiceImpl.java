package com.weison.sell.Services.Impl;

import com.weison.sell.Dao.ProductInfoDao;
import com.weison.sell.Entity.ProductInfo;
import com.weison.sell.Enums.ProductStatus;
import com.weison.sell.Enums.ResultEnums;
import com.weison.sell.Exception.SellException;
import com.weison.sell.Services.ProductInfoService;
import com.weison.sell.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息 事务层
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public Page<ProductInfo> findall(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findByStatus(Integer status) {
        return productInfoDao.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public ProductInfo findone(String id) {
        return productInfoDao.findById(id).get();
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    public List<ProductInfo> findByCategoryType(Integer type) {
        return productInfoDao.findByCategoryType(type);
    }

    @Override
    @Transactional
    public void addproductStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = productInfoDao.findById(cartDto.getProductId()).get();
            // 没有查询到该商品
            if (productInfo == null){
                throw new SellException(ResultEnums.NOTFOUNT_PRODUCT);
            }
            Integer res = productInfo.getProductStock() + cartDto.getProductQuantity();
            // 保存
            productInfo.setProductStock(res);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void delproductStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = productInfoDao.findById(cartDto.getProductId()).get();
            // 没有查询到该商品
            if (productInfo == null){
                throw new SellException(ResultEnums.NOTFOUNT_PRODUCT);
            }
            Integer res = productInfo.getProductStock() - cartDto.getProductQuantity();
            // 商品超过库存数
            if (res < 0){
                throw new SellException(ResultEnums.OUTOF_PRODUCT_COUNT);
            }
            // 保存
            productInfo.setProductStock(res);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    public void upProduct(ProductInfo productInfo) {
        productInfo.setProductStatus(ProductStatus.UP.getCode());
        productInfoDao.save(productInfo);
    }
}
