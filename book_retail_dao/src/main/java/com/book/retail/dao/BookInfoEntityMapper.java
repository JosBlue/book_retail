package com.book.retail.dao;

import com.book.retail.domain.base.BookRetailBaseMapper;
import com.book.retail.domain.entity.BookInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午5:10
 * @description: 图书基础信息Mapper
 */
@Repository
public interface BookInfoEntityMapper extends BookRetailBaseMapper<BookInfo> {

    /**
     * 扣减图书在售数量
     *
     * @param id  图书ID
     * @param num 扣减后的图书数量
     * @return 更新条数
     */
    Integer updateBookNumInfo(@Param("id") Long id, @Param("num") Integer num);

}
