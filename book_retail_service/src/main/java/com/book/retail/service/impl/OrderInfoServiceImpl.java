package com.book.retail.service.impl;

import com.alibaba.fastjson.JSON;
import com.book.retail.common.exception.BusinessException;
import com.book.retail.common.response.RespCode;
import com.book.retail.dao.OrderInfoEntityMapper;
import com.book.retail.domain.dto.BookInfoDTO;
import com.book.retail.domain.dto.OrderInfoDTO;
import com.book.retail.domain.entity.OrderInfo;
import com.book.retail.domain.vo.OrderDetailInfoVO;
import com.book.retail.service.BookInfoService;
import com.book.retail.service.OrderInfoService;
import com.book.retail.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午6:00
 * @description: 订单Service
 */
@Service
@Slf4j
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    OrderInfoEntityMapper orderInfoEntityMapper;

    @Autowired
    BookInfoService bookInfoService;

    @Autowired
    UserInfoService userInfoService;

    @Override
    public List<OrderDetailInfoVO> getOrderDetailInfo() {
        return this.orderInfoEntityMapper.getOrderDetailInfo();
    }

    @Override
    public OrderDetailInfoVO getOrderDetailInfoByOrderId(Long orderId) {

        if (Objects.isNull(orderId)) {
            throw new BusinessException(RespCode.PARAM_ILLEGAL);
        }

        return this.orderInfoEntityMapper.getOrderDetailInfoByOrderId(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean submitOrder(OrderInfoDTO orderInfoDTO) {

        try {

            // 判断商品剩余库存是否满足用户购买数量
            Boolean checkBookNumIsEnough = this.bookInfoService.checkBookNumIsEnough(orderInfoDTO.getBookId(), orderInfoDTO.getNum());

            if (!checkBookNumIsEnough) {
                throw new BusinessException(RespCode.BOOK_NUM_NOT_ENOUGH);
            }

            // 扣减商品数量
            BookInfoDTO bookInfoDTO = this.bookInfoService.updateBookNum(orderInfoDTO.getBookId(), orderInfoDTO.getNum());

            // 创建订单
            BigDecimal totalPrice = this.createOrder(orderInfoDTO, bookInfoDTO.getPrice());

            // 计算用户成员点
            BigDecimal score = this.userInfoService.calculateUserScore(orderInfoDTO.getUserId(), totalPrice);

            // 更新用户成员点信息
            this.userInfoService.updateUserScoreByUserId(orderInfoDTO.getUserId(), score);

        } catch (Exception e) {
            log.error("OrderInfoService.submitOrder error...param:{}", JSON.toJSONString(orderInfoDTO));
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException(RespCode.FAIL);
        }

        return true;
    }


    /**
     * 记录订单信息
     *
     * @param orderInfoDTO {@link OrderInfoDTO}
     * @param price        图书价格信息
     * @return 订单id
     */
    public BigDecimal createOrder(OrderInfoDTO orderInfoDTO, BigDecimal price) {

        // 计算商品总金额(直接舍弃分后的所有小数位，并且不进位)
        BigDecimal totalPrice = price.multiply(new BigDecimal(orderInfoDTO.getNum())).setScale(2, BigDecimal.ROUND_DOWN);

        // 记录订单信息
        OrderInfo entity = new OrderInfo();

        BeanUtils.copyProperties(orderInfoDTO, entity);
        entity.setTotalPrice(totalPrice);
        entity.setBaseInfo();

        Boolean flag = this.orderInfoEntityMapper.insert(entity) > 0;

        if (!flag) {
            throw new BusinessException(RespCode.FAIL);
        }

        return totalPrice;
    }
}
