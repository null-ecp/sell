package com.weison.sell.Exception;

import com.weison.sell.Enums.ResultEnums;
import lombok.Getter;

/**
 *
 */
@Getter
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnums resultEnum) {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}