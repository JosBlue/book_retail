package com.book.retail;

import com.alibaba.fastjson.JSON;
import com.book.retail.dao.BookInfoEntityMapper;
import com.book.retail.domain.entity.BookInfo;
import com.book.retail.service.BookInfoService;
import com.book.retail.web.BookRetailApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/23 下午2:45
 * @description: BookInfoServiceTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookRetailApplication.class)
@Slf4j
public class BookInfoServiceTest {

    @Autowired
    BookInfoService bookInfoService;

    @Autowired
    BookInfoEntityMapper bookInfoEntityMapper;

    public static Integer getRandom(int min, int max) {
        Random random = new Random();
        int index = random.nextInt(max) % (max - min + 1) + min;
        return index;
    }


    /**
     * 测试数据
     */
    @Test
    public void addBookInfoForTest() {
        List<BookInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setBaseInfo();
            bookInfo.setBookName("java手册00" + i);
            bookInfo.setBookPrice(new BigDecimal((60 + getRandom(0, 20))));
            bookInfo.setBookNum(getRandom(10, 100));
            list.add(bookInfo);
        }
        this.bookInfoEntityMapper.insertList(list);
    }


    @Test
    public void updateBookNumByMoreThreadTest() throws InterruptedException {

        int threadLength = 10;

        Thread[] threads = new Thread[threadLength];

        Long bookId = saveBookInfo(threadLength);

        CountDownLatch countDownLatch = new CountDownLatch(threadLength);

        for (int i = 0; i < threadLength; i++) {
            threads[i] = new Thread(() -> {
                Integer num = getRandom(1, 5);
                try {
                    this.bookInfoService.updateBookNum(bookId, num);
                } catch (Exception e) {
                    log.error(Thread.currentThread().getName() + "=购买失败...购买数量：" + num);
                }
                countDownLatch.countDown();
            });
        }

        for (int i = 0; i < threadLength; i++) {
            threads[i].start();
        }

        countDownLatch.await();

        BookInfo entity = this.bookInfoEntityMapper.selectByPrimaryKey(bookId);
        System.out.println(JSON.toJSONString(entity));
        Assert.assertEquals(entity.getBookNum(), new Integer(0));
    }


    @Test
    public void checkBookNumIsEnoughTest() {
        // save Book
        Long bookId = saveBookInfo(9);

        Boolean enough = this.bookInfoService.checkBookNumIsEnough(bookId, 9);
        Assert.assertEquals(enough, true);

        Boolean notEnough = this.bookInfoService.checkBookNumIsEnough(bookId, 10);
        Assert.assertEquals(notEnough, false);

        // buy 2
        this.bookInfoService.updateBookNum(bookId, 2);

        Boolean notEnough2 = this.bookInfoService.checkBookNumIsEnough(bookId, 9);
        Assert.assertEquals(notEnough2, false);
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


}
