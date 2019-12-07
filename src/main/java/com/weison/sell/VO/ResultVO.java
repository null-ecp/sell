package com.weison.sell.VO;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 接口返回结果封装对象
 * @param <T>
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

}
