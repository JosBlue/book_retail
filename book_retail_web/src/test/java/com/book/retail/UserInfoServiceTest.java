package com.book.retail;

import com.book.retail.domain.dto.UserInfoDTO;
import com.book.retail.domain.entity.UserInfo;
import com.book.retail.domain.enums.UserTypeEnum;
import com.book.retail.service.UserInfoService;
import com.book.retail.web.BookRetailApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/23 下午2:24
 * @description: UserInfoServiceTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookRetailApplication.class)
public class UserInfoServiceTest {

    @Autowired
    UserInfoService userInfoService;


    @Test
    public void userServiceTest() {

        // createUser
        UserInfoDTO userInfoDTO = new UserInfoDTO("银牌会员", 2);
        Long userId = userInfoService.createUserInfo(userInfoDTO);
        Assert.assertNotNull(userId);

        // getUser
        UserInfo entity = this.userInfoService.getUserInfoById(userId);
        Assert.assertEquals(entity.getId(), userId);

        // calculateScore
        BigDecimal baseScore = new BigDecimal("23.98");
        BigDecimal score = this.userInfoService.calculateUserScore(userId, baseScore);

        Integer weight = UserTypeEnum.getUserWeightEnumById(entity.getUserType());

        Assert.assertEquals(new BigDecimal(weight).multiply(baseScore).add(entity.getScore()).setScale(2, BigDecimal.ROUND_DOWN), score);

        // updateUserScore
        Boolean flag = this.userInfoService.updateUserScoreByUserId(userId, score);
        Assert.assertEquals(true, flag);
    }


    @Test
    public void calculateUserScoreTest() {

        UserInfo entity = this.userInfoService.getUserInfoById(4L);
        Assert.assertEquals(entity.getId(), new Long(4));

        BigDecimal baseScore = new BigDecimal("23.98");
        BigDecimal score = this.userInfoService.calculateUserScore(4L, baseScore);

        Integer weight = UserTypeEnum.getUserWeightEnumById(entity.getUserType());

        Assert.assertEquals(new BigDecimal(weight).multiply(baseScore).add(entity.getScore()).setScale(2, BigDecimal.ROUND_DOWN), score);
    }


    @Test
    public void createUserInfoTest() {

        UserInfoDTO userInfoDTO = new UserInfoDTO("金牌会员", 1);
        Long userId = userInfoService.createUserInfo(userInfoDTO);

        Assert.assertNotNull(userId);
        UserInfo entity = this.userInfoService.getUserInfoById(userId);
        Assert.assertNotNull(entity);
        System.out.println(entity);
    }

}
