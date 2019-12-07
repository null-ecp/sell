package com.weison.sell.Dao;

import com.weison.sell.Entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void savetest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("小吃");
        productCategory.setCategoryType(1);
        ProductCategory save = productCategoryDao.save(productCategory);
    }

    @Test
    public void findtest(){
        ProductCategory productCategory = productCategoryDao.findById(1).get();
        System.out.println(productCategory);
    }

    @Test
    public void update(){
        ProductCategory productCategory = productCategoryDao.findById(1).get();
        productCategory.setCategoryName("热销");
        productCategoryDao.save(productCategory);
    }
}