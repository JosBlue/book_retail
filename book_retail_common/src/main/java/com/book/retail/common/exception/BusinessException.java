package com.book.retail.common.exception;

import com.book.retail.common.response.RespCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午6:40
 * @description: 统一异常处理
 */
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusinessException extends RuntimeException {

    /**
     * 异常状态码
     */
    String code;

    /**
     * 异常信息
     */
    String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(RespCode respCode) {
        this.code = respCode.getCode();
        this.message = respCode.getMsg();
    }

}
