package com.book.retail;

import com.book.retail.dao.BookInfoEntityMapper;
import com.book.retail.domain.dto.OrderInfoDTO;
import com.book.retail.domain.dto.UserInfoDTO;
import com.book.retail.domain.entity.BookInfo;
import com.book.retail.domain.entity.UserInfo;
import com.book.retail.domain.enums.UserTypeEnum;
import com.book.retail.domain.vo.OrderDetailInfoVO;
import com.book.retail.service.OrderInfoService;
import com.book.retail.service.UserInfoService;
import com.book.retail.web.BookRetailApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/23 下午4:05
 * @description: OrderInfoServiceTest
 * 注：多线程场景下的超卖问题，已经在bookInfoService中进行了测试，此处不再进行测试。
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookRetailApplication.class)
@Slf4j
public class OrderInfoServiceTest {

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    BookInfoEntityMapper bookInfoEntityMapper;

    @Autowired
    UserInfoService userInfoService;


    @Test
    public void orderTest() throws InterruptedException {

        Integer bookNum = 10;
        Integer byBookNum = 3;

        Long bookId = saveBookInfo(bookNum);
        Assert.assertNotNull(bookId);

        UserInfoDTO userInfoDTO = new UserInfoDTO("金牌会员", 1);
        Long userId = this.userInfoService.createUserInfo(userInfoDTO);
        Assert.assertNotNull(userId);

        Assert.assertNotNull(userId);
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setBookId(bookId);
        orderInfoDTO.setNum(byBookNum);
        orderInfoDTO.setUserId(userId);
        orderInfoDTO.setUserName(userInfoDTO.getUserName());
        Boolean flag = this.orderInfoService.submitOrder(orderInfoDTO);

        Assert.assertEquals(flag, true);

        // 成员积分点
        BookInfo bookInfo = this.bookInfoEntityMapper.selectByPrimaryKey(bookId);
        UserInfo userInfo = this.userInfoService.getUserInfoById(userId);
        Assert.assertEquals(
                userInfo.getScore(),
                bookInfo.getBookPrice().multiply(new BigDecimal(byBookNum))
                        .multiply(new BigDecimal(UserTypeEnum.getUserWeightEnumById(userInfo.getUserType()))));

        // get order By id
        List<OrderDetailInfoVO> orderDetailInfoVOList = this.orderInfoService.getOrderDetailInfo();
        Assert.assertNotNull(orderDetailInfoVOList);

        OrderDetailInfoVO orderDetailInfoVO = this.orderInfoService.getOrderDetailInfoByOrderId(orderDetailInfoVOList.get(orderDetailInfoVOList.size() - 1).getOrderId());
        Assert.assertNotNull(orderDetailInfoVO);
        Assert.assertEquals(orderDetailInfoVO.getOrderId(), orderDetailInfoVOList.get(orderDetailInfoVOList.size() - 1).getOrderId());
        Assert.assertEquals(orderDetailInfoVO.getBookId(), bookId);
    }


    /**
     * 保存图书信息
     *
     * @param BookNum 图书销售数量
     * @return 图书ID
     */
    private Long saveBookInfo(int BookNum) {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBaseInfo();
        bookInfo.setBookName("java手册00" + BookNum);
        bookInfo.setBookPrice(new BigDecimal((60 + getRandom(0, 20))));
        bookInfo.setBookNum(BookNum);
        this.bookInfoEntityMapper.insert(bookInfo);

        return bookInfo.getId();
    }


    private static Integer getRandom(int min, int max) {
        Random random = new Random();
        int index = random.nextInt(max) % (max - min + 1) + min;
        return index;
    }


}
