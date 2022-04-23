package com.book.retail.web.controller;

import com.book.retail.common.response.CommonResp;
import com.book.retail.domain.dto.UserInfoDTO;
import com.book.retail.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/22 下午2:22
 * @description: 用户相关Controller
 */
@RestController
@RequestMapping("/user")
public class UserInfoController extends BaseController {

    @Autowired
    UserInfoService userInfoService;


    @RequestMapping("/create")
    public CommonResp createUserInfo(UserInfoDTO userInfoDTO) {
        return CommonResp.ok(this.userInfoService.createUserInfo(userInfoDTO));
    }

}
