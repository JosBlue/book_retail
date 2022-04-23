package com.book.retail.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午5:45
 * @description: 订单信息DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderInfoDTO {

    /**
     * 用户表id
     */
    Long userInfoId;

    /**
     * 用户名称
     */
    String userName;


    /**
     * 图书id
     */
    Long bookId;


    /**
     * 购买数量
     */
    Integer num;
}
