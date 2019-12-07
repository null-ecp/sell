package com.weison.sell.Conveter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weison.sell.Entity.OrderDetial;
import com.weison.sell.Enums.ResultEnums;
import com.weison.sell.Exception.SellException;
import com.weison.sell.Pojo.OrderPojo;
import com.weison.sell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderPojo2OrderDto {

    public static OrderDto convet(OrderPojo orderPojo){
        OrderDto orderDto = new OrderDto();

        orderDto.setBuyyerName(orderPojo.getName());
        orderDto.setBuyyerPhone(orderPojo.getPhone());
        orderDto.setBuyyerAddress(orderPojo.getAddress());
        orderDto.setBuyyerOpenid(orderPojo.getOpenid());

        List<OrderDetial> list = new ArrayList<OrderDetial>();
        Gson gson = new Gson();
        try {
            list = gson.fromJson(orderPojo.getItems(),
                    new TypeToken<List<OrderDetial>>(){}.getType());
        }catch (Exception e){
            log.error("[对象转换] 对象转换失败");
            throw new SellException(ResultEnums.ORDER_PARAM_ERROR);
        }
        orderDto.setOrderDetialList(list);

        return orderDto;
    }
}
