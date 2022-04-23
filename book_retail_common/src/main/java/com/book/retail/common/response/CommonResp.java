package com.book.retail.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午6:34
 * @description: 通用消息返回体
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommonResp<T extends Object> {

    /**
     * 响应码
     */
    String code;

    /**
     * 响应消息
     */
    String result;

    /**
     * 响应内容
     */
    T data;

    /**
     * 返回成功结果
     *
     * @return
     */
    public static CommonResp ok() {
        return ok(null, null);
    }

    /**
     * 成功
     *
     * @return 返回成功结果
     */
    public static CommonResp ok(Object data) {
        return ok(null, data);
    }

    /**
     * 成功
     *
     * @return 返回成功结果
     */
    public static CommonResp ok(String result, Object data) {
        if (result == null) {
            result = "执行成功";
        }
        return new CommonResp(RespCode.SUCCESS.getCode(), result, data);
    }

    /**
     * 失败
     *
     * @param code 错误码
     * @return 返回失败结果
     */
    public static CommonResp error(String code) {
        return error(code, null, null);
    }


    public static CommonResp error(RespCode respcode) {
        return error(respcode.getCode(), respcode.getMsg());
    }

    /**
     * 失败
     *
     * @param code   错误码
     * @param result 错误信息
     * @return 返回错误结果
     */
    public static CommonResp error(String code, String result) {
        return error(code, result, null);
    }

    /**
     * 失败
     *
     * @param code   错误码
     * @param result 错误信息
     * @return 返回错误结果
     */
    public static CommonResp error(String code, String result, Object data) {
        return new CommonResp(code, result, data);
    }


    /**
     * 找不到该记录
     *
     * @return 返回错误结果
     */
    public static CommonResp targetNotExistError() {
        return error(RespCode.PARAM_ILLEGAL.getCode(), "找不到该记录");
    }

    /**
     * 参数错误
     *
     * @return 返回错误结果
     */
    public static CommonResp paramError(String result) {
        return error(RespCode.PARAM_ILLEGAL.getCode(), result);
    }

}
