
CREATE TABLE `t_user` (
                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                          `name` varchar(100) DEFAULT NULL COMMENT '名称',
                          `online_status` int(11) DEFAULT NULL COMMENT '在线状态，1：在线，2：离线',
                          `sex` int(11) DEFAULT NULL COMMENT '性别',
                          `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0未删除，1已删除',
                          `roleId` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `idx_name` (`name`,`online_status`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE `t_dict` (
                          `id` bigint(20) NOT NULL COMMENT '主键id',
                          `dict_type` varchar(100) DEFAULT NULL COMMENT '字典类型',
                          PRIMARY KEY (`id`),
                          KEY `idx_dict_type` (`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;