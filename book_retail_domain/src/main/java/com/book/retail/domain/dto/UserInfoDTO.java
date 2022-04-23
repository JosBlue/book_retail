package com.book.retail.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/22 上午11:27
 * @description: 用户相关信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoDTO {

    /**
     * 用户名称
     */
    String userName;


    /**
     * 用户类型
     *
     * @see com.book.retail.domain.enums.UserTypeEnum
     */
    Integer userType;

}
