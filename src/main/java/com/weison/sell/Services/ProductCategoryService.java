package com.weison.sell.Services;


import com.weison.sell.Entity.ProductCategory;

import java.util.List;
import java.util.Set;

/**
 * 类目 事务层
 */
public interface ProductCategoryService {

    /**
     * 查询所有 -- 后台
     * @return
     */
    List<ProductCategory> findall();

    /**
     * 查询所有 -- 前台
     * @param TypeList
     * @return
     */
    List<ProductCategory> findByTypeIn(Set<Integer> TypeList);

    /**
     * 查询单一类目
     * @param id
     * @return
     */
    ProductCategory findOne(Integer id);

    /**
     * 新增/更新
     * @param productCategory 需要更新或者新增的类目
     * @return
     */
    ProductCategory save(ProductCategory productCategory);

    /**
     * 删除类目
     * @param id
     */
    void delete(Integer id);
}
