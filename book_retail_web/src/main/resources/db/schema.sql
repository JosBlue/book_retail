CREATE TABLE IF NOT EXISTS user_info (
  `id` bigint(20)  NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `user_type` tinyint(1) DEFAULT NULL COMMENT '用户类型（1:金牌会员；2:银牌会员；3:铜牌会员）',
  `score` DECIMAL(10,2) DEFAULT NULL COMMENT '成员点（订单总价*会员值），单位精确到分',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `yn` int(2) NOT NULL DEFAULT '1' COMMENT '删除标志位',
  `sys_version` int(2) DEFAULT NULL  COMMENT '版本号',
)COMMENT='用户信息表';




CREATE TABLE IF NOT EXISTS `order_info` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_info_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `book_id` bigint(20) DEFAULT NULL COMMENT '图书表id',
  `num` int(10) DEFAULT NULL COMMENT '购买数量',
  `total_price` DECIMAL(10,2) DEFAULT NULL COMMENT '订单总价，单位精确到分',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `yn` int(2) NOT NULL DEFAULT '1' COMMENT '删除标志位',
  `sys_version` int(2) DEFAULT NULL  COMMENT '版本号',
)COMMENT='订单信息表';


CREATE TABLE IF NOT EXISTS `book_info` (
      `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
      `book_name` varchar(100) DEFAULT NULL COMMENT '图书名称',
      `book_price` DECIMAL(10,2) DEFAULT NULL COMMENT '图书单价，单位精确到分',
      `book_num` int(20) DEFAULT NULL COMMENT '图书总数量',
      `created` datetime DEFAULT NULL,
      `modified` datetime DEFAULT NULL,
      `yn` int(2) NOT NULL DEFAULT '1' COMMENT '删除标志位',
      `sys_version` int(2) DEFAULT NULL  COMMENT '版本号',
) COMMENT='图书信息表';
