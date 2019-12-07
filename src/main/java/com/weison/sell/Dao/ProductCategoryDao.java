package com.weison.sell.Dao;

import com.weison.sell.Entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 类目 持久层
 */
@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer>,
        JpaSpecificationExecutor<ProductCategory> {

    /**
     * 查询所有 -- 前台
     * @param TypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(Set<Integer> TypeList);
}
