package com.weison.sell.Services;

import com.weison.sell.Entity.OrderMaster;
import com.weison.sell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户主订单创建
 */
public interface OrderService {

    /** 创建订单 **/
    OrderDto CreateOrder(OrderDto orderDto);

    /** 查询订单 -- 用户 **/
    Page<OrderDto> findbyOpenid(String openid, Pageable pageable);

    /** 查询单个 **/
    OrderDto findOne(String OrderId);

    /** 取消订单 **/
    OrderDto canel(OrderDto orderDto);

    /** 接单 **/
    OrderDto finished(OrderDto orderDto);

    /** 订单支付 **/
    OrderDto payed(OrderDto orderDto);
}
