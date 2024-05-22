
CREATE TABLE `t_order0` (
                            `id` bigint(20) NOT NULL COMMENT '主键id',
                            `user_id` int(11) DEFAULT NULL COMMENT '用户id',
                            `order_no` varchar(100) DEFAULT NULL COMMENT '名称',
                            `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0未删除，1已删除',
                            PRIMARY KEY (`id`),
                            KEY `idx_order_no` (`user_id`,`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_order1` (
                            `id` bigint(20) NOT NULL COMMENT '主键id',
                            `user_id` int(11) DEFAULT NULL COMMENT '用户id',
                            `order_no` varchar(100) DEFAULT NULL COMMENT '名称',
                            `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0未删除，1已删除',
                            PRIMARY KEY (`id`),
                            KEY `idx_order_no` (`user_id`,`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_order_item0` (
                                 `id` bigint(20) NOT NULL COMMENT '主键id',
                                 `user_id` int(11) DEFAULT NULL COMMENT '用户id',
                                 `order_no` varchar(100) DEFAULT NULL COMMENT '名称',
                                 `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
                                 `count` int(11) DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `idx_order_no` (`user_id`,`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_order_item1` (
                                 `id` bigint(20) NOT NULL COMMENT '主键id',
                                 `user_id` int(11) DEFAULT NULL COMMENT '用户id',
                                 `order_no` varchar(100) DEFAULT NULL COMMENT '名称',
                                 `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
                                 `count` int(11) DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `idx_order_no` (`user_id`,`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_dict` (
                          `id` bigint(20) NOT NULL COMMENT '主键id',
                          `dict_type` varchar(100) DEFAULT NULL COMMENT '字典类型',
                          PRIMARY KEY (`id`),
                          KEY `idx_dict_type` (`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;