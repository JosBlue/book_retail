package com.book.retail.service.impl;

import com.book.retail.common.exception.BusinessException;
import com.book.retail.common.response.RespCode;
import com.book.retail.dao.BookInfoEntityMapper;
import com.book.retail.domain.dto.BookInfoDTO;
import com.book.retail.domain.entity.BookInfo;
import com.book.retail.service.BookInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/22 上午10:32
 * @description: 图书Service
 */
@Service
@Slf4j
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    BookInfoEntityMapper bookInfoEntityMapper;


    @Override
    public Boolean checkBookNumIsEnough(Long bookId, Integer num) {

        BookInfo bookInfo = this.getBookInfoByBookId(bookId);

        if (Objects.isNull(bookInfo)) {
            throw new BusinessException(RespCode.BOOK_NOT_EXIST);
        }


        if ((bookInfo.getBookNum() == 0 && num > 0) || bookInfo.getBookNum() < num) {
            return false;
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookInfoDTO updateBookNum(Long bookId, Integer num) {

        try {
            BookInfoDTO bookInfoDTO;

            // 加锁更改库存，防止超卖
            synchronized (this) {

                // num check
                Boolean checkEnough = this.checkBookNumIsEnough(bookId, num);

                if (!checkEnough) {
                    throw new BusinessException(RespCode.BOOK_NUM_NOT_ENOUGH);
                }

                BookInfo bookInfo = this.getBookInfoByBookId(bookId);
                Integer newNum = bookInfo.getBookNum() - num;
                bookInfo.setBookNum(newNum);
                bookInfo.setModified(LocalDateTime.now());

                this.bookInfoEntityMapper.updateByPrimaryKey(bookInfo);

                bookInfoDTO = new BookInfoDTO();
                bookInfoDTO.setBookId(bookId);
                bookInfoDTO.setPrice(bookInfo.getBookPrice());
            }

            return bookInfoDTO;
        } catch (Exception e) {
            log.error("BookInfoService.updateBookNum error...bookId:{}...num:{}", bookId, num);
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException(RespCode.FAIL);
        }
    }


    /**
     * 根据图书ID获取图书信息
     *
     * @param bookId 图书ID
     * @return {@link BookInfo}
     */
    private BookInfo getBookInfoByBookId(Long bookId) {
        return this.bookInfoEntityMapper.selectByPrimaryKey(bookId);
    }
}
