package com.book.retail.service;

import com.book.retail.domain.dto.BookInfoDTO;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/22 上午10:22
 * @description: 图书信息Service
 */
public interface BookInfoService {

    /**
     * 校验当前商品库存是否充足
     *
     * @param bookId 书籍ID
     * @param num    购买数量
     * @return 是否充足
     */
    Boolean checkBookNumIsEnough(Long bookId, Integer num);


    /**
     * 更新图书被购买后剩余商品数
     *
     * @param bookId 图书ID
     * @param num    图书购买数量
     * @return 图书信息 {@link BookInfoDTO}
     */
    BookInfoDTO updateBookNum(Long bookId, Integer num);

}
