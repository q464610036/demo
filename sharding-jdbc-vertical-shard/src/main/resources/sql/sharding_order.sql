
CREATE TABLE `t_order` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                           `user_id` int(11) DEFAULT NULL COMMENT '用户id',
                           `order_no` varchar(100) DEFAULT NULL COMMENT '名称',
                           `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0未删除，1已删除',
                           PRIMARY KEY (`id`),
                           KEY `idx_order_no` (`user_id`,`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;