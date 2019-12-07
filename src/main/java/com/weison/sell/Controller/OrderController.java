package com.weison.sell.Controller;

import com.weison.sell.Conveter.OrderPojo2OrderDto;
import com.weison.sell.Enums.ResultEnums;
import com.weison.sell.Exception.SellException;
import com.weison.sell.Pojo.OrderPojo;
import com.weison.sell.Services.BuyyerService;
import com.weison.sell.Services.OrderService;
import com.weison.sell.Utils.ResultUtil;
import com.weison.sell.VO.ResultVO;
import com.weison.sell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order/")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyyerService buyyerService;

    /**
     * 创建订单
     * @param orderPojo
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderPojo orderPojo, BindingResult bindingResult){
        // 判断提交信息
        if (bindingResult.hasErrors()){
            log.error("[订单信息] 订单参数不正确 【{}】",bindingResult.getFieldError().getDefaultMessage());
            throw new SellException(ResultEnums.ORDER_PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderPojo2OrderDto.convet(orderPojo);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetialList())){
            log.error("[订单信息] 购物车为空 ， 请前去添加商品");
            throw new SellException(ResultEnums.ORDERList_NOT_HAVE_PRODUCT);
        }
        OrderDto createOrder = orderService.CreateOrder(orderDto);

        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", createOrder.getOrderId());

        return ResultUtil.success(map);
    }

    /**
     * 根据用户openid 获取订单列表
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDto>> findList(@RequestParam String openid,
                                             @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size){
        // 判断用户是否存在
        if (StringUtils.isEmpty(openid)) {
            log.error("[订单信息] 订单参数不正确 openid = {}", openid);
            throw new SellException(ResultEnums.ORDER_PARAM_ERROR);
        }
        // 获取订单列表
        Page<OrderDto> orderDtos = orderService.findbyOpenid(openid, PageRequest.of(page, size));
        return ResultUtil.success(orderDtos.getContent());
    }

    @GetMapping("/detail")
    public ResultVO<OrderDto> getOrderDetial(@RequestParam String openid,
                                             @RequestParam String orderId){
        // 判断用户是否存在
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            log.error("[订单信息] 订单参数不正确 openid = {}, orderid = {}", openid, orderId);
            throw new SellException(ResultEnums.ORDER_PARAM_ERROR);
        }

        OrderDto orderDto = buyyerService.findByOrderid(openid, orderId);
        return ResultUtil.success(orderDto);
    }

    @PostMapping("/cancel")
    public ResultVO CanelOrder(@RequestParam String openid,
                               @RequestParam String orderId){

        // 判断用户是否存在
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            log.error("[订单信息] 订单参数不正确 openid = {}, orderid = {}", openid, orderId);
            throw new SellException(ResultEnums.ORDER_PARAM_ERROR);
        }

        buyyerService.canelorder(openid, orderId);

        return ResultUtil.success();
    }
}
