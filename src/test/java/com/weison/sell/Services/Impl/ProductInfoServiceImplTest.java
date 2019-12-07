package com.weison.sell.Services.Impl;

import com.weison.sell.Entity.ProductInfo;
import com.weison.sell.Enums.ProductStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findall() {
    }

    @Test
    public void findByStatus() {
        List<ProductInfo> byStatus = productInfoService.findByStatus(ProductStatus.UP.getCode());
        for (ProductInfo status : byStatus) {
            System.out.println(status);
        }
    }

    @Test
    public void findone() {
        productInfoService.findone("C1-001");
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("C1-002");
        productInfo.setProductName("卤肉饭");
        productInfo.setProductPrice(new BigDecimal(15));
        productInfo.setProductDesc("卤肉饭 ， 分量很足");
        productInfo.setProductStock(10);
        productInfo.setProductStatus(ProductStatus.DOWN.getCode());
        productInfo.setCategoryType(1);

        ProductInfo save = productInfoService.save(productInfo);
        System.out.println(save);
    }

    @Test
    public void upProduct() {
        ProductInfo findone = productInfoService.findone("C1-002");
        productInfoService.upProduct(findone);
    }
}