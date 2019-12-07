package com.weison.sell.Services.Impl;

import com.weison.sell.Enums.ResultEnums;
import com.weison.sell.Exception.SellException;
import com.weison.sell.Services.BuyyerService;
import com.weison.sell.Services.OrderService;
import com.weison.sell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyyerServiceImpl implements BuyyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findByOrderid(String openid, String orderId) {
        return checkorder(openid, orderId);
    }

    @Override
    public OrderDto canelorder(String openid, String orderId) {
        OrderDto orderDto = checkorder(openid, orderId);
        // 取消订单
        OrderDto dto = orderService.canel(orderDto);
        return dto;
    }

    private OrderDto checkorder(String openid, String orderId){
        OrderDto orderDto = orderService.findOne(orderId);
        // 判断订单
        if (orderDto == null){
            log.error("[订单信息] 订单查询不到，  orderid = {}", orderDto);
            throw new SellException(ResultEnums.NOTFOUNT_ORDER);
        }
        // 匹配用户与订单
        if (! orderDto.getBuyyerOpenid().equalsIgnoreCase(openid)){
            log.error("[订单信息] 订单不是该用户的 orderid = {}", orderId);
            throw new SellException(ResultEnums.ORDER_OPENID_EQ_ERROR_ORDERID);
        }
        return orderDto;
    }
}
