package com.book.retail.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午5:27
 * @description: 订单详情信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailInfoVO {

    /**
     * 订单id
     */
    Long orderId;


    /**
     * 订单创建/完成时间
     */
    LocalDate orderCreateTime;


    /**
     * 用户id
     */
    Long userId;


    /**
     * 用户名称
     */
    String userName;


    /**
     * 用户类型
     */
    String userType;


    /**
     * 图书ID
     */
    Long bookId;


    /**
     * 图书名称
     */
    String bookName;


    /**
     * 图书单价
     */
    BigDecimal price;


    /**
     * 购买数量
     */
    Integer num;


    /**
     * 订单总价
     */
    BigDecimal totalPrice;

}
