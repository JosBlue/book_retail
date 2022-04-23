package com.book.retail;

import com.book.retail.dao.UserInfoEntityMapper;
import com.book.retail.domain.entity.UserInfo;
import com.book.retail.service.UserInfoService;
import com.book.retail.web.BookRetailApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/22 下午5:50
 * @description: UserInfoServiceMockTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookRetailApplication.class)
public class UserInfoServiceMockTest {

    @Autowired
    UserInfoService userInfoService;

    @MockBean
    UserInfoEntityMapper userInfoEntityMapper;


    @Test
    public void getUserInfoByIdTest() {
        Mockito.when(userInfoEntityMapper.selectByPrimaryKey(1L)).thenReturn(
                new UserInfo(1L, 1L, LocalDateTime.now(), LocalDateTime.now(), 1, "金牌会员", 1, new BigDecimal(10)));

        UserInfo entity = userInfoService.getUserInfoById(1L);

        Assert.assertNotNull(entity);
        Assert.assertEquals(entity.getId(), new Long(1));
        Assert.assertEquals(entity.getUserName(), "金牌会员");
    }

}
