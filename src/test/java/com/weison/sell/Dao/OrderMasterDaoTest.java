package com.weison.sell.Dao;

import com.weison.sell.Entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void testsave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("O-001");
        orderMaster.setBuyyerAddress("九江职业技术学院");
        orderMaster.setBuyyerName("zwx");
        orderMaster.setBuyyerOpenid("abc123");
        orderMaster.setBuyyerPhone("18579088395");
        orderMaster.setOrderAmount(new BigDecimal(20));

        OrderMaster save = orderMasterDao.save(orderMaster);
        System.out.println(save);
    }

    @Test
    public void findbypage() throws Exception {
        PageRequest request = PageRequest.of(1, 3);
        Page<OrderMaster> res = orderMasterDao.findByBuyyerOpenid("abc123", request);
        long totalElements = res.getTotalElements();
        System.out.println(totalElements);
    }

}