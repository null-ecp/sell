package com.weison.sell.Conveter;

import com.weison.sell.Entity.OrderMaster;
import com.weison.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDto {

    /**
     * orderMaster to orderDto
     * @param orderMaster
     * @return
     */
    public static OrderDto convet(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    /**
     * ordermaster lists to orderdto lissts
     * @param list
     * @return
     */
    public static List<OrderDto> convet(List<OrderMaster> list){
        return list.stream().map(orderMaster ->
                convet(orderMaster)
                ).collect(Collectors.toList());
    }
}
