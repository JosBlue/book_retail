package com.book.retail.service;

import com.book.retail.domain.dto.OrderInfoDTO;
import com.book.retail.domain.vo.OrderDetailInfoVO;

import java.util.List;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午5:24
 * @description: 订单Service
 */
public interface OrderInfoService {


    /**
     * 获取有效（未删除）订单详情列表
     *
     * @return {@link List} {@link OrderDetailInfoVO}
     */
    List<OrderDetailInfoVO> getOrderDetailInfo();


    /**
     * 根据订单id获取有效（未删除）订单详情
     *
     * @param orderId 订单id
     * @return {@link OrderDetailInfoVO}
     */
    OrderDetailInfoVO getOrderDetailInfoByOrderId(Long orderId);


    /**
     * 提交订单信息
     *
     * @param orderInfoDTO {@link OrderInfoDTO}
     * @return 是否提交成功
     */
    Boolean submitOrder(OrderInfoDTO orderInfoDTO);



}
