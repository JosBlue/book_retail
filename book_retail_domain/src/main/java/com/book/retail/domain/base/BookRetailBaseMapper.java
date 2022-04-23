package com.book.retail.domain.base;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午5:11
 * @description: tkMapper基础接口
 */
public interface BookRetailBaseMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
}
