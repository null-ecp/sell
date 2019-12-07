package com.weison.sell.Services.Impl;

import com.weison.sell.Entity.OrderDetial;
import com.weison.sell.Services.OrderService;
import com.weison.sell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyyerAddress("九江职业技术学院");
        orderDto.setBuyyerName("weison");
        orderDto.setBuyyerOpenid("abc123");
        orderDto.setBuyyerPhone("18579088395");
        List<OrderDetial> list = new ArrayList<OrderDetial>();
        OrderDetial orderDetial = new OrderDetial();
        orderDetial.setProductName("卤肉");
        orderDetial.setProductId("C1-001");
        orderDetial.setProductQuantity(2);
        orderDetial.setProductPrice(new BigDecimal(15));
        OrderDetial orderDetial1 = new OrderDetial();
        orderDetial1.setProductName("卤肉");
        orderDetial1.setProductId("C1-002");
        orderDetial1.setProductQuantity(2);
        orderDetial1.setProductPrice(new BigDecimal(15));
//        list.add(orderDetial);
        list.add(orderDetial1);
        orderDto.setOrderDetialList(list);

        OrderDto orderDto1 = orderService.CreateOrder(orderDto);

        log.info("创建订单 -- {}", orderDto1);
    }

    @Test
    public void findbyOpenid() {
        PageRequest request = PageRequest.of(1,2);
        Page<OrderDto> abc123 = orderService.findbyOpenid("abc123", request);
        for (OrderDto orderDto : abc123.getContent()) {
            System.out.println("1");
            log.info("【订单信息】：{}",orderDto);
        }
    }


    @Test
    public void findOne() {
        OrderDto one = orderService.findOne("1575643148640773706");
        System.out.println(one);
    }

    @Test
    public void canel() {
        OrderDto one = orderService.findOne("1575644154637564172");
        OrderDto canel = orderService.canel(one);
        log.info("[订单信息] 订单 : {}  已被取消 , 订单状态 : {}", canel.getOrderId(),canel.getOrderStatus());
    }

    @Test
    public void finished() {
    }

    @Test
    public void payed() {
    }
}