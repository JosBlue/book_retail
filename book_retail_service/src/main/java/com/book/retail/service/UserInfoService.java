package com.book.retail.service;

import com.book.retail.domain.dto.UserInfoDTO;
import com.book.retail.domain.entity.UserInfo;

import java.math.BigDecimal;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/22 上午11:20
 * @description: 用户Service
 */
public interface UserInfoService {


    /**
     * 创建用户信息
     *
     * @param userInfoDTO 用户基础信息
     * @return 用户id
     */
    Long createUserInfo(UserInfoDTO userInfoDTO);


    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return {@link UserInfo}
     */
    UserInfo getUserInfoById(Long userId);


    /**
     * 计算用户成员点
     *
     * @param userId     用户id
     * @param totalPrice 订单中金额
     * @return 用户成员点
     */
    BigDecimal calculateUserScore(Long userId, BigDecimal totalPrice);


    /***
     * 更新用户成员点
     *
     * @param userId 用户id
     * @param score 成员点
     * @return 是否更新成功
     */
    Boolean updateUserScoreByUserId(Long userId, BigDecimal score);
}
