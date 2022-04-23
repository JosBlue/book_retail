-- 基础数据
-- BOOK
INSERT INTO `book_info` (`book_name`, `book_price`, `book_num`, `created`, `modified`, `yn`, `sys_version`) VALUES ('java手册001', 77.00, 14, now(), now(), 1, 1);
INSERT INTO `book_info` (`book_name`, `book_price`, `book_num`, `created`, `modified`, `yn`, `sys_version`) VALUES ('java手册002', 123.32, 8, now(), now(), 1, 1);
INSERT INTO `book_info` (`book_name`, `book_price`, `book_num`, `created`, `modified`, `yn`, `sys_version`) VALUES ('java手册003', 122, 4, now(), now(), 1, 1);
INSERT INTO `book_info` (`book_name`, `book_price`, `book_num`, `created`, `modified`, `yn`, `sys_version`) VALUES ('java手册004', 23.5, 12, now(), now(), 1, 1);
INSERT INTO `book_info` (`book_name`, `book_price`, `book_num`, `created`, `modified`, `yn`, `sys_version`) VALUES ('java手册005', 66, 10, now(), now(), 1, 1);

-- User
INSERT INTO `user_info` (`user_name`, `user_type`, `score`, `created`, `modified`, `yn`, `sys_version`) VALUES ('Patton', 1, 120, now(), now(), 1, 1);
INSERT INTO `user_info` (`user_name`, `user_type`, `score`, `created`, `modified`, `yn`, `sys_version`) VALUES ('Nicole', 1, 230, now(), now(), 1, 1);
INSERT INTO `user_info` (`user_name`, `user_type`, `score`, `created`, `modified`, `yn`, `sys_version`) VALUES ('Anthony', 2, 80, now(), now(), 1, 1);
INSERT INTO `user_info` (`user_name`, `user_type`, `score`, `created`, `modified`, `yn`, `sys_version`) VALUES ('Zane', 2, 76, now(), now(), 1, 1);
INSERT INTO `user_info` (`user_name`, `user_type`, `score`, `created`, `modified`, `yn`, `sys_version`) VALUES ('Venus', 3, 16, now(), now(), 1, 1);
INSERT INTO `user_info` (`user_name`, `user_type`, `score`, `created`, `modified`, `yn`, `sys_version`) VALUES ('Asta', 3, 10, now(), now(), 1, 1);

-- Order
INSERT INTO `order_info` (`user_info_id`, `book_id`, `num`, `total_price`, `created`, `modified`, `yn`, `sys_version`) VALUES (1, 1, 1, 77.00, now(), now(), 1, 1);
INSERT INTO `order_info` (`user_info_id`, `book_id`, `num`, `total_price`, `created`, `modified`, `yn`, `sys_version`) VALUES (1, 3, 2, 244.00, now(), now(), 1, 1);