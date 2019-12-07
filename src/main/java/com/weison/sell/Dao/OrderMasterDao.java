package com.weison.sell.Dao;

import com.weison.sell.Entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMasterDao extends JpaRepository<OrderMaster, String>,
        JpaSpecificationExecutor<OrderMaster> {

    /**
     * 根据买家openid查询订单
     * @param openid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyyerOpenid(String openid, Pageable pageable);
}
