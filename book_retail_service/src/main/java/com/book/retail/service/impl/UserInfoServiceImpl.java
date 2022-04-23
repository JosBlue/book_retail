package com.book.retail.service.impl;

import com.book.retail.common.exception.BusinessException;
import com.book.retail.common.response.RespCode;
import com.book.retail.dao.UserInfoEntityMapper;
import com.book.retail.domain.dto.UserInfoDTO;
import com.book.retail.domain.entity.UserInfo;
import com.book.retail.domain.enums.UserTypeEnum;
import com.book.retail.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/22 上午11:31
 * @description: 用户信息Service
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoEntityMapper userInfoEntityMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUserInfo(UserInfoDTO userInfoDTO) {
        try {
            if (Objects.isNull(userInfoDTO.getUserName()) || Objects.isNull(userInfoDTO.getUserType())) {
                throw new BusinessException(RespCode.USER_INFO_HAS_NULL);
            }

            UserInfo entity = new UserInfo();
            entity.setBaseInfo();
            entity.setScore(new BigDecimal(0));
            entity.setUserName(userInfoDTO.getUserName());
            entity.setUserType(userInfoDTO.getUserType());

            this.userInfoEntityMapper.insert(entity);

            return entity.getId();
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException(RespCode.FAIL);
        }
    }

    @Override
    public UserInfo getUserInfoById(Long userId) {
        return this.userInfoEntityMapper.selectByPrimaryKey(userId);
    }


    @Override
    public BigDecimal calculateUserScore(Long userId, BigDecimal totalPrice) {

        UserInfo userInfo = this.getUserInfoById(userId);

        if (Objects.isNull(userInfo)) {
            throw new BusinessException(RespCode.USER_NOT_EXIST);
        }

        BigDecimal score;

        switch (UserTypeEnum.getUserTypeEnumById(userInfo.getUserType())) {
            case GOLD: {
                score = this.calculateScore(totalPrice, UserTypeEnum.GOLD.getWeight());
                break;
            }

            case SILVER: {
                score = this.calculateScore(totalPrice, UserTypeEnum.SILVER.getWeight());
                break;
            }

            // 默认按照铜牌会员进行计算会员值
            default: {
                score = this.calculateScore(totalPrice, UserTypeEnum.COPER.getWeight());
                break;
            }
        }

        // 最终成长点
        return userInfo.getScore().add(score).setScale(2, BigDecimal.ROUND_DOWN);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUserScoreByUserId(Long userId, BigDecimal score) {
        try {
            UserInfo userInfo = this.getUserInfoById(userId);

            if (Objects.isNull(userInfo)) {
                throw new BusinessException(RespCode.USER_NOT_EXIST);
            }

            userInfo.setScore(score);
            userInfo.setModified(LocalDateTime.now());
            return this.userInfoEntityMapper.updateByPrimaryKey(userInfo) > 0;
        } catch (Exception e) {
            log.error("UserInfoService.updateUserScoreByUserId error...userId:{}...score:{}", userId, score);
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException(RespCode.FAIL);
        }
    }


    /**
     * 计算成员点
     *
     * @param totalPrice 订单总金额
     * @param type       用户会员值
     * @return 成员点
     */
    private BigDecimal calculateScore(BigDecimal totalPrice, Integer type) {
        return totalPrice.multiply(new BigDecimal(type)).setScale(2, BigDecimal.ROUND_DOWN);
    }
}
