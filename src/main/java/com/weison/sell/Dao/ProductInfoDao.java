package com.weison.sell.Dao;

import com.weison.sell.Entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductInfoDao extends JpaSpecificationExecutor<ProductInfo>,
        JpaRepository<ProductInfo, String> {

    /**
     * 查询上架商品
     * @param status
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer status);

    /**
     * 查询指定分类下的商品
     * @param type
     * @return
     */
    List<ProductInfo> findByCategoryType(Integer type);

    /**
     * 查询id集合中的所有商品
     * @param idlist
     * @return
     */
    List<ProductInfo> findByProductIdIn(List<String> idlist);
}
