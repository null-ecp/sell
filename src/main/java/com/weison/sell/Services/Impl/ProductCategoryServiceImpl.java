package com.weison.sell.Services.Impl;

import com.weison.sell.Dao.ProductCategoryDao;
import com.weison.sell.Entity.ProductCategory;
import com.weison.sell.Services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> findall() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByTypeIn(Set<Integer> typeList) {
        return productCategoryDao.findByCategoryTypeIn(typeList);
    }

    @Override
    public ProductCategory findOne(Integer id) {
        return productCategoryDao.findById(id).get();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }

    @Override
    public void delete(Integer id) {
        productCategoryDao.deleteById(id);
    }
}
