package com.weison.sell.Dao;

import com.weison.sell.Entity.OrderDetial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderDetialDao extends JpaRepository<OrderDetial, String>, JpaSpecificationExecutor<OrderDetial> {

    List<OrderDetial> findByOrderId(String orderid);

}
