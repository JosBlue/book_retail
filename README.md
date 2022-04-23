## 简版图书管理系统

### 系统端口号
8080

### 数据库设计

数据库：H2

用户表：user_info
图书表：book_info
订单表：order_info


### 测试类

单元测试类所在地址：book_retail/book_retail_web/src/test

已考虑超卖问题，并进行了相应测试。


### http接口

返回参数：CommonResp

| 参数名 | 说明 |类型|响应值说明|
| ------- | ------- |------- |------- |
|    code |  响应码|String|0000:表示成功，其他响应码均表示失败|
|    result     |  响应码|String|success/执行成功:表示成功|
|    data     |  响应内容|T|返回值相关信息|

#### 1、创建用户
/user/create

请求参数：userInfoDTO

| 参数名   | 说明   |类型|是否必填|
| ------- | ------|------|------|
| userName|用户名  |String|是||
| userType|用户类型（1:金牌会员；2:银牌会员；3:铜牌会员）  |Integer|是|

返回参数：用户id



#### 2、获取订单列表信息
/order/get

请求参数：无

响应参数：List<OrderDetailInfoVO>

| 参数名   | 说明   |类型|是否必填|
| ------- | ------|------|------|
| orderId|订单id  |Long|是||
| orderCreateTime|订单创建完成时间  |LocalDate|是|
| userId|用户id  |Long|是|
| userName|用户名称  |String|是|
| userType|用户类型  |String|是|
| bookId|图书id  |Long|是|
| bookName|图书名称  |String|是|
| price|图书单价  |BigDecimal|是|
| num|购买数量  |Integer|是|
| totalPrice|订单总价  |BigDecimal|是|


#### 3、根据订单id获取订单列表信息
/order/get?orderId=X

请求参数：

| 参数名   | 说明   |类型|是否必填|
| ------- | ------|------|------|
| orderId|订单id  |Long|是||


响应参数：OrderDetailInfoVO

| 参数名   | 说明   |类型|是否必填|
| ------- | ------|------|------|
| orderId|订单id  |Long|是||
| orderCreateTime|订单创建完成时间  |LocalDate|是|
| userId|用户id  |Long|是|
| userName|用户名称  |String|是|
| userType|用户类型  |String|是|
| bookId|图书id  |Long|是|
| bookName|图书名称  |String|是|
| price|图书单价  |BigDecimal|是|
| num|购买数量  |Integer|是|
| totalPrice|订单总价  |BigDecimal|是|


#### 4、提交订单信息
/order/submit

请求参数：

| 参数名   | 说明   |类型|是否必填|
| ------- | ------|------|------|
| userId|用户id  |Long|是|
| userName|用户名称  |String|是|
| bookId|图书id  |Long|是|
| num|购买数量  |Integer|是|=


响应参数：Boolean(true:提交成功;false:提交失败)







