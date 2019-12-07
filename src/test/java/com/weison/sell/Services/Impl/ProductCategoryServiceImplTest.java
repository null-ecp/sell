package com.weison.sell.Services.Impl;

import com.weison.sell.Entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired ProductCategoryServiceImpl productCategoryService;

    @Test
    public void findall() {
        List<ProductCategory> findall = productCategoryService.findall();
        for (ProductCategory productCategory : findall) {
            System.out.println(productCategory);
        }
    }
//
//    @Test
//    public void findByTypeIn() {
//        List<Integer> list = Arrays.as(1, 2, 3);
//        List<ProductCategory> byTypeIn = productCategoryService.findByTypeIn(list);
//        for (ProductCategory productCategory : byTypeIn) {
//            System.out.println(productCategory);
//        }
//    }
//
    @Test
    public void findOne() {
        ProductCategory one = productCategoryService.findOne(1);
        System.out.println(one);
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("冷饮");
        productCategory.setCategoryType(3);
        ProductCategory save = productCategoryService.save(productCategory);
    }

    @Test
    public void delete() {
        productCategoryService.delete(2);
    }
}