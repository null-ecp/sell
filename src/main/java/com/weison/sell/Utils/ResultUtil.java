package com.weison.sell.Utils;

import com.weison.sell.Entity.ProductInfo;
import com.weison.sell.VO.ProductInfoVO;
import com.weison.sell.VO.ResultVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 结果集返回工具类
 */
public class ResultUtil {

    /**
     * 成功返回
     * @return
     */
    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO success(Object o){
        ResultVO res = new ResultVO();
        res.setCode(0);
        res.setMsg("成功");
        res.setData(o);
        return res;
    }

    /**
     * 错误信息返回
     * @param code
     * @param msg
     * @return
     */
    public static ResultVO error(Integer code, String msg){
        ResultVO res = new ResultVO();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }
    /**
     * 将product实体类转换为productVO
     * @param list
     * @return
     */
    public static List<ProductInfoVO> productInfo2InfoVO(List<ProductInfo> list){
        List<ProductInfoVO> infoVOList = new ArrayList<ProductInfoVO>();
        // products -> foods
        for (ProductInfo productInfo : list) {
            ProductInfoVO productInfoVO = new ProductInfoVO();

            productInfoVO.setProductId(productInfo.getProductId());
            productInfoVO.setProductName(productInfo.getProductName());
            productInfoVO.setProductPrice(productInfo.getProductPrice());
            productInfoVO.setProductDesc(productInfo.getProductDesc());
            productInfoVO.setProductImg(productInfo.getProductImg());

            infoVOList.add(productInfoVO);
        }
        return infoVOList;
    }

    /**
     * 获取商品实体集合中指定分类下的商品并转换为 VO 对象
     * @param type
     * @param list
     * @return
     */
    public static List<ProductInfoVO> getProductsByCategory(Integer type, List<ProductInfo> list){
        List<ProductInfoVO> infoVOList = new ArrayList<ProductInfoVO>();
        for (ProductInfo productInfo : list) {
            ProductInfoVO productInfoVO = new ProductInfoVO();
            // 仅添加当前分类
            if (productInfo.getCategoryType().equals(type)){
                BeanUtils.copyProperties(productInfo,productInfoVO);
            }
            infoVOList.add(productInfoVO);
        }
        return infoVOList;
    }
}
