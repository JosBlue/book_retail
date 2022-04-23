package com.book.retail.web.controller;

import com.book.retail.common.response.CommonResp;
import com.book.retail.domain.dto.OrderInfoDTO;
import com.book.retail.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/22 下午2:29
 * @description: 订单相关Controller
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController extends BaseController {

    @Autowired
    OrderInfoService orderInfoService;


    @RequestMapping("/get")
    public CommonResp getOrderList() {
        return CommonResp.ok(this.orderInfoService.getOrderDetailInfo());
    }


    @RequestMapping("/getById")
    public CommonResp getOrderById(Long orderId) {
        return CommonResp.ok(this.orderInfoService.getOrderDetailInfoByOrderId(orderId));
    }


    @RequestMapping("/submit")
    public CommonResp submitOrder(OrderInfoDTO orderInfoDTO) {
        return CommonResp.ok(this.orderInfoService.submitOrder(orderInfoDTO));
    }
}
