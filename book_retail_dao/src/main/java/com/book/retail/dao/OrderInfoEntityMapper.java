package com.book.retail.dao;

import com.book.retail.domain.base.BookRetailBaseMapper;
import com.book.retail.domain.entity.OrderInfo;
import com.book.retail.domain.vo.OrderDetailInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午5:19
 * @description: 订单基础信息Mapper
 */
@Repository
public interface OrderInfoEntityMapper extends BookRetailBaseMapper<OrderInfo> {


    /**
     * 获取订单详情
     *
     * @return {@link List}
     */
    List<OrderDetailInfoVO> getOrderDetailInfo();


    /**
     * 根据订单id获取订单详情
     *
     * @param orderId 订单id
     * @return {@link OrderDetailInfoVO}
     */
    OrderDetailInfoVO getOrderDetailInfoByOrderId(@Param("orderId") Long orderId);


}
