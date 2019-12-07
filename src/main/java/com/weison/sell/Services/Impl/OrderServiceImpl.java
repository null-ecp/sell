package com.weison.sell.Services.Impl;

import com.weison.sell.Conveter.OrderMaster2OrderDto;
import com.weison.sell.Dao.OrderDetialDao;
import com.weison.sell.Dao.OrderMasterDao;
import com.weison.sell.Dao.ProductInfoDao;
import com.weison.sell.Entity.OrderDetial;
import com.weison.sell.Entity.OrderMaster;
import com.weison.sell.Entity.ProductInfo;
import com.weison.sell.Enums.OrderStatus;
import com.weison.sell.Enums.PayStatus;
import com.weison.sell.Enums.ResultEnums;
import com.weison.sell.Exception.SellException;
import com.weison.sell.Services.OrderService;
import com.weison.sell.Services.ProductInfoService;
import com.weison.sell.Utils.KeyUtil;
import com.weison.sell.dto.CartDto;
import com.weison.sell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.omg.PortableInterceptor.ORBIdHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private OrderDetialDao orderDetialDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto CreateOrder(OrderDto orderDto) {
        // 生成唯一id
        String orderId = KeyUtil.genUniqueKey();

        // 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 查询订单中的商品
        for (OrderDetial orderDetial : orderDto.getOrderDetialList()) {
            ProductInfo productInfo = productInfoDao.findById(orderDetial.getProductId()).get();
            // 没有查询到该商品
            if (productInfo == null){
                throw new SellException(ResultEnums.NOTFOUNT_PRODUCT);
            }
            orderAmount =  productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetial.getProductQuantity()))
                    .add(orderAmount);

            // 订单详情写入持久层
            BeanUtils.copyProperties(productInfo, orderDetial);
            orderDetial.setOrderId(orderId);
            orderDetial.setDetialId(KeyUtil.genUniqueKey());
            orderDetialDao.save(orderDetial);
        }

        // 主订单写入持久层
        OrderMaster orderMaster = new OrderMaster();
        // 先复制 ， null也会被复制
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        OrderMaster createdorder = orderMasterDao.save(orderMaster);

        // 判断订单创建状态
        if (createdorder == null){
            log.error("[订单信息] 订单创建失败 ！！！！！！");
            throw new SellException(ResultEnums.ORDER_CREATE_ERROR);
        }
        BeanUtils.copyProperties(createdorder, orderDto);

        // 库存修改
        List<CartDto> cartDtos = orderDto.getOrderDetialList().stream().map(orderDetial ->
                new CartDto(orderDetial.getProductId(), orderDetial.getProductQuantity()))
                .collect(Collectors.toList());

        productInfoService.delproductStock(cartDtos);

        return orderDto;
    }

    /**
     * 根据用户openid查询用户订单列表
     * @param openid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDto> findbyOpenid(String openid, Pageable pageable) {
        // 查询用户订单列表
        Page<OrderMaster> infopage = orderMasterDao.findByBuyyerOpenid(openid, pageable);
        List<OrderDto> dtoList = OrderMaster2OrderDto.convet(infopage.getContent());
        // 入参为 集合 , pageable , 总条数
        Page<OrderDto> page = new PageImpl<OrderDto>(dtoList, pageable, infopage.getTotalElements());
        return page;
    }

    /**
     * 查询订单详情
     * @param OrderId
     * @return
     */
    @Override
    public OrderDto findOne(String OrderId) {
        OrderMaster orderMaster = orderMasterDao.findById(OrderId).get();
        // 订单未查询到
        if (orderMaster == null){
            throw new SellException(ResultEnums.NOTFOUNT_ORDER);
        }
        List<OrderDetial> byOrderId = orderDetialDao.findByOrderId(OrderId);
        // 没有查询到订单对应商品
        if (CollectionUtils.isEmpty(byOrderId)) {
            throw new SellException(ResultEnums.ORDER_NOT_HAVE_PRODUCT);
        }
        // 封装结果
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetialList(byOrderId);
        return orderDto;
    }

    /**
     * 取消订单
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto canel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        // 判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            log.error("[订单信息]: 订单状态异常,订单已完成或已取消, orderid = {} , 订单状态 : {}",
                    orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }
        // 修改订单状态
        orderMaster.setOrderStatus(OrderStatus.CANEL.getCode());
        OrderMaster uporderMaster = orderMasterDao.save(orderMaster);
        if (uporderMaster == null){
            log.error("[订单信息] 取消订单失败,{}",orderDto);
            throw new SellException(ResultEnums.ORDER_CANEL_ERROR);
        }
        // 返回库存
        // 判断是否有商品
        if (CollectionUtils.isEmpty(orderDto.getOrderDetialList())) {
            log.error("[订单信息] 订单中没有商品存在, orderid = {}", orderDto.getOrderId());
            throw new SellException(ResultEnums.ORDERList_NOT_HAVE_PRODUCT);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetialList().stream().map(
                orderDetial ->
                        new CartDto(orderDetial.getProductId(), orderDetial.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.addproductStock(cartDtoList);
        // 退款
        if (orderDto.getPayStatus().equals(PayStatus.PAYED.getCode())) {
            //TODO
        }

        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finished(OrderDto orderDto) {
        // 判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            log.error("[订单信息]: 订单状态异常,订单已完成或已取消, orderid = {} , 订单状态 : {}",
                    orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }
        // 调起微信支付
        // TODO

        // 修改订单状态
        orderDto.setOrderStatus(OrderStatus.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster uporderMaster = orderMasterDao.save(orderMaster);
        // 修改订单错误
        if (uporderMaster == null){
            log.error("[订单信息] 订单接单失败, orderid = {}",orderDto.getOrderId());
            throw new SellException(ResultEnums.ORDER_FINISHED_ERROR);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto payed(OrderDto orderDto) {
        // 判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatus.FINISHED.getCode())){
            log.error("[订单信息]: 订单状态异常,订单已完成或已取消, orderid = {} , 订单状态 : {}",
                    orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }
        // 判断是否完成支付
        if (!orderDto.getPayStatus().equals(PayStatus.WAIT.getCode())){
            log.error("[订单信息] 订单已经支付完成 orderid = {}", orderDto.getOrderId());
            throw new SellException(ResultEnums.ORDER_PAY_STATUS_ERROR);
        }
        // 修改支付状态
        orderDto.setPayStatus(PayStatus.PAYED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMasterDao.save(orderMaster);

        // 调起微信支付
        // TODO
        return orderDto;
    }
}
