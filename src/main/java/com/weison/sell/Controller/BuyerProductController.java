package com.weison.sell.Controller;

import com.weison.sell.Entity.ProductCategory;
import com.weison.sell.Entity.ProductInfo;
import com.weison.sell.Enums.ProductStatus;
import com.weison.sell.Services.ProductCategoryService;
import com.weison.sell.Services.ProductInfoService;
import com.weison.sell.Utils.ResultUtil;
import com.weison.sell.VO.ProductInfoVO;
import com.weison.sell.VO.ProductVO;
import com.weison.sell.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 买家端 商品 控制层
 */
@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list(){
        Date start = new Date();

        // 返回对象内容体
        List<ProductVO> voList = new ArrayList<ProductVO>();

        // 获取上架商品
        List<ProductInfo> upproducts = productInfoService.findByStatus(ProductStatus.UP.getCode());

        // 获取已经上架商品的类目
        Set<Integer> typelist = new HashSet<>();
        for (ProductInfo info : upproducts) {
            typelist.add(info.getCategoryType());
        }
        List<ProductCategory> categoryList = productCategoryService.findByTypeIn(typelist);

        // 封装返回内容
        for (ProductCategory category : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());

              // products -> foods
//            List<ProductInfoVO> infoVOList = ResultUtil.productInfo2InfoVO(
//                    productInfoService.findByCategoryType(productVO.getCategoryType()));

            productVO.setFoods(
                    ResultUtil.getProductsByCategory(productVO.getCategoryType(),upproducts));

            voList.add(productVO);
        }

        long excutime = new Date().getTime() - start.getTime();
        log.info("执行时间{}毫秒",excutime);
        return ResultUtil.success(voList);
    }

}
