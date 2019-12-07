package com.weison.sell.Dao;

import com.weison.sell.Entity.OrderDetial;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetialDaoTest {

    @Autowired
    private OrderDetialDao orderDetialDao;

    @Test
    public void savetest(){
        OrderDetial orderDetial = new OrderDetial();
        orderDetial.setProductName("卤肉饭");
        orderDetial.setDetialId("D-001");
        orderDetial.setOrderId("O-001");
        orderDetial.setProductId("C1-001");
        orderDetial.setProductQuantity(2);
        orderDetial.setProductImg("");
        orderDetial.setProductPrice(new BigDecimal(15));

        OrderDetial save = orderDetialDao.save(orderDetial);
        System.out.println(save);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetial> byOrderId = orderDetialDao.findByOrderId("O-001");
        for (OrderDetial orderDetial : byOrderId) {
            System.out.println(orderDetial);
        }
    }
}