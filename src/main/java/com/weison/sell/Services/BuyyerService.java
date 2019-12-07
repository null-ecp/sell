package com.weison.sell.Services;


import com.weison.sell.dto.OrderDto;

public interface BuyyerService {

    // 查询订单
    OrderDto findByOrderid(String openid, String orderId);

    // 取消订单
    OrderDto canelorder(String openid, String orderId);

}
