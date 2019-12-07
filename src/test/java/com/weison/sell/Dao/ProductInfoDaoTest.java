package com.weison.sell.Dao;


import com.weison.sell.Entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void testsave(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("C1-001");
        productInfo.setProductName("鸡排饭");
        productInfo.setProductPrice(new BigDecimal(15));
        productInfo.setProductDesc("黑椒鸡排饭 ， 味道很棒");
        productInfo.setProductStock(10);
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);

        ProductInfo save = productInfoDao.save(productInfo);
        System.out.println(save);
    }
}