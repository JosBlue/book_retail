package com.book.retail.dao;

import com.book.retail.domain.base.BookRetailBaseMapper;
import com.book.retail.domain.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午5:23
 * @description: 用户基础信息Mapper
 */
@Repository
public interface UserInfoEntityMapper extends BookRetailBaseMapper<UserInfo> {

    UserInfo getUserInfoById(@Param("id") Long id);
}
