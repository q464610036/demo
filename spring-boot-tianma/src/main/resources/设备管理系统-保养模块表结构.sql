CREATE TABLE ct_tmos_equipment_group(
                                        id VARCHAR2(64) NOT NULL,
                                        template_id VARCHAR2(64),
                                        group_name VARCHAR2(100) NOT NULL,
                                        unit_type VARCHAR2(30),
                                        STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                        REMARK VARCHAR2(200),
                                        CREATE_BY VARCHAR2(64),
                                        CREATE_TIME DATE,
                                        UPDATE_BY VARCHAR2(64),
                                        UPDATE_TIME DATE,
                                        PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_group IS '设备关系组表';
COMMENT ON COLUMN ct_tmos_equipment_group.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_group.template_id IS '模版id';
COMMENT ON COLUMN ct_tmos_equipment_group.group_name IS '关系组名称';
COMMENT ON COLUMN ct_tmos_equipment_group.unit_type IS '设备类型';
COMMENT ON COLUMN ct_tmos_equipment_group.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_group.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_group.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_group.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_group.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_group.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_group_unit(
                                             id VARCHAR2(64) NOT NULL,
                                             group_id VARCHAR2(64) NOT NULL,
                                             factory_name VARCHAR2(32) NOT NULL,
                                             area VARCHAR2(32) NOT NULL,
                                             eqp_id VARCHAR2(32) NOT NULL,
                                             unit_id VARCHAR2(32),
                                             unit_type VARCHAR2(30),
                                             STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                             REMARK VARCHAR2(200),
                                             CREATE_BY VARCHAR2(64),
                                             CREATE_TIME DATE,
                                             UPDATE_BY VARCHAR2(64),
                                             UPDATE_TIME DATE,
                                             PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_group_unit IS '设备关系组设备表';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.group_id IS '关系组id';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.unit_type IS '设备类型';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_template(
                                           id VARCHAR2(64) NOT NULL,
                                           template_name VARCHAR2(100) NOT NULL,
                                           template_type VARCHAR2(10) DEFAULT  1,
                                           unit_type VARCHAR2(30),
                                           parent_id VARCHAR2(64),
                                           create_user_name VARCHAR2(32),
                                           STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                           REMARK VARCHAR2(200),
                                           CREATE_BY VARCHAR2(64),
                                           CREATE_TIME DATE,
                                           UPDATE_BY VARCHAR2(64),
                                           UPDATE_TIME DATE,
                                           PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_template IS '保养模版表';
COMMENT ON COLUMN ct_tmos_equipment_template.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_template.template_name IS '模版名称';
COMMENT ON COLUMN ct_tmos_equipment_template.template_type IS '模版类型：1=by unit,2=by sub unit';
COMMENT ON COLUMN ct_tmos_equipment_template.unit_type IS '设备类型';
COMMENT ON COLUMN ct_tmos_equipment_template.parent_id IS '父节点id';
COMMENT ON COLUMN ct_tmos_equipment_template.create_user_name IS '创建名称';
COMMENT ON COLUMN ct_tmos_equipment_template.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_template.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_template.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_template.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_template.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_template.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_sub_unit(
                                           id VARCHAR2(64) NOT NULL,
                                           template_id VARCHAR2(64) NOT NULL,
                                           sub_unit_group VARCHAR2(32) NOT NULL,
                                           STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                           REMARK VARCHAR2(200),
                                           CREATE_BY VARCHAR2(64),
                                           CREATE_TIME DATE,
                                           UPDATE_BY VARCHAR2(64),
                                           UPDATE_TIME DATE,
                                           PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_sub_unit IS '保养模版设备腔室配置表';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.template_id IS '模版id';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.sub_unit_group IS '设备unit组别';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_stop_line(
                                            id VARCHAR2(64) NOT NULL,
                                            factory_name VARCHAR2(32) NOT NULL,
                                            area VARCHAR2(32) NOT NULL,
                                            eqp_id VARCHAR2(32) NOT NULL,
                                            unit_id VARCHAR2(32),
                                            sub_unit_id VARCHAR2(32),
                                            stop_line_type VARCHAR2(5) NOT NULL,
                                            first_upkeep_time DATE,
                                            expect_hours NUMBER(5,1),
                                            STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                            REMARK VARCHAR2(200),
                                            CREATE_BY VARCHAR2(64),
                                            CREATE_TIME DATE,
                                            UPDATE_BY VARCHAR2(64),
                                            UPDATE_TIME DATE,
                                            PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_stop_line IS '保养计划生成设备停线配置表';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.stop_line_type IS '停线类型：1=整线停线，2=不停线Unit';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.first_upkeep_time IS '首次保养时间';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.expect_hours IS '预计时长';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_item(
                                       id VARCHAR2(64) NOT NULL,
                                       template_id VARCHAR2(64) NOT NULL,
                                       item_content VARCHAR2(300),
                                       min_cycle VARCHAR2(10),
                                       STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                       REMARK VARCHAR2(200),
                                       CREATE_BY VARCHAR2(64),
                                       CREATE_TIME DATE,
                                       UPDATE_BY VARCHAR2(64),
                                       UPDATE_TIME DATE,
                                       PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_item IS '保养模版项目表';
COMMENT ON COLUMN ct_tmos_equipment_item.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_item.template_id IS '模板id';
COMMENT ON COLUMN ct_tmos_equipment_item.item_content IS '保养内容';
COMMENT ON COLUMN ct_tmos_equipment_item.min_cycle IS '最小周期';
COMMENT ON COLUMN ct_tmos_equipment_item.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_item.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_item.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_item.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_item.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_item.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_cycle_config(
                                               id VARCHAR2(64) NOT NULL,
                                               factory_name VARCHAR2(32) NOT NULL,
                                               area VARCHAR2(32) NOT NULL,
                                               expect_hours NUMBER(5,1),
                                               notice_days NUMBER(5),
                                               order_days NUMBER(5),
                                               week NUMBER(5),
                                               month NUMBER(5),
                                               two_months NUMBER(5),
                                               quarter NUMBER(5),
                                               half_year NUMBER(5),
                                               year NUMBER(5),
                                               delay_day NUMBER(5),
                                               STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                               REMARK VARCHAR2(200),
                                               CREATE_BY VARCHAR2(64),
                                               CREATE_TIME DATE,
                                               UPDATE_BY VARCHAR2(64),
                                               UPDATE_TIME DATE,
                                               PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_cycle_config IS '设备群组周期配置表';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.expect_hours IS '预计时长';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.notice_days IS '通知时限';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.order_days IS '开单时限';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.week IS '周';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.month IS '月';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.two_months IS '两个月';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.quarter IS '季度';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.half_year IS '半年';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.year IS '年';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.delay_day IS '延期天数';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_time_config(
                                              id VARCHAR2(64) NOT NULL,
                                              factory_name VARCHAR2(32) NOT NULL,
                                              area VARCHAR2(32) NOT NULL,
                                              eqp_id VARCHAR2(32) NOT NULL,
                                              first_upkeep_time DATE,
                                              STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                              REMARK VARCHAR2(200),
                                              CREATE_BY VARCHAR2(64),
                                              CREATE_TIME DATE,
                                              UPDATE_BY VARCHAR2(64),
                                              UPDATE_TIME DATE,
                                              PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_time_config IS '设备线体首次保养时间配置表';
COMMENT ON COLUMN ct_tmos_equipment_time_config.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_time_config.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_time_config.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_time_config.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_time_config.first_upkeep_time IS '首次保养时间';
COMMENT ON COLUMN ct_tmos_equipment_time_config.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_time_config.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_time_config.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_time_config.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_time_config.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_time_config.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_plan(
                                       id VARCHAR2(64) NOT NULL,
                                       factory_name VARCHAR2(32) NOT NULL,
                                       area VARCHAR2(32) NOT NULL,
                                       eqp_id VARCHAR2(32) NOT NULL,
                                       unit_id VARCHAR2(32),
                                       sub_unit_id VARCHAR2(32),
                                       stop_line_type VARCHAR2(5) NOT NULL,
                                       cycle VARCHAR2(10),
                                       sys_plan_time DATE,
                                       adjust_time DATE,
                                       expect_hours NUMBER(5,1),
                                       plan_status NUMBER(1) DEFAULT  0 NOT NULL,
                                       STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                       REMARK VARCHAR2(200),
                                       CREATE_BY VARCHAR2(64),
                                       CREATE_TIME DATE,
                                       UPDATE_BY VARCHAR2(64),
                                       UPDATE_TIME DATE,
                                       PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_plan IS '保养计划表';
COMMENT ON COLUMN ct_tmos_equipment_plan.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_plan.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_plan.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_plan.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_plan.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_plan.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_plan.stop_line_type IS '停线类型：1=整线停线，2=不停线Unit';
COMMENT ON COLUMN ct_tmos_equipment_plan.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_plan.sys_plan_time IS '系统计划日期';
COMMENT ON COLUMN ct_tmos_equipment_plan.adjust_time IS '调整日期';
COMMENT ON COLUMN ct_tmos_equipment_plan.expect_hours IS '预计时长';
COMMENT ON COLUMN ct_tmos_equipment_plan.plan_status IS '保养计划状态：0=新建，1=生成工单，2=保养完成，3=取消保养';
COMMENT ON COLUMN ct_tmos_equipment_plan.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_plan.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_plan.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_plan.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_plan.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_plan.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_plan_item(
                                            id VARCHAR2(64) NOT NULL,
                                            plan_id VARCHAR2(64) NOT NULL,
                                            item_id VARCHAR2(64),
                                            item_content VARCHAR2(300),
                                            min_cycle VARCHAR2(10),
                                            unit_id VARCHAR2(32) NOT NULL,
                                            sub_unit_id VARCHAR2(32) NOT NULL,
                                            STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                            REMARK VARCHAR2(200),
                                            CREATE_BY VARCHAR2(64),
                                            CREATE_TIME DATE,
                                            UPDATE_BY VARCHAR2(64),
                                            UPDATE_TIME DATE,
                                            PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_plan_item IS '保养计划项目表';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.plan_id IS '保养计划id';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.item_id IS '项目id';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.item_content IS '保养内容';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.min_cycle IS '最小周期';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_order(
                                        id VARCHAR2(64) NOT NULL,
                                        plan_id VARCHAR2(64) NOT NULL,
                                        order_no VARCHAR2(32) NOT NULL,
                                        factory_name VARCHAR2(32) NOT NULL,
                                        area VARCHAR2(32) NOT NULL,
                                        eqp_id VARCHAR2(32) NOT NULL,
                                        unit_id VARCHAR2(32),
                                        sub_unit_id VARCHAR2(32),
                                        stop_line_type VARCHAR2(5) NOT NULL,
                                        cycle VARCHAR2(10),
                                        sys_plan_time DATE,
                                        adjust_time DATE,
                                        expect_hours NUMBER(5,1),
                                        real_start_time DATE,
                                        real_end_time DATE,
                                        approve_status VARCHAR2(5) DEFAULT  01,
                                        STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                        REMARK VARCHAR2(200),
                                        CREATE_BY VARCHAR2(64),
                                        CREATE_TIME DATE,
                                        UPDATE_BY VARCHAR2(64),
                                        UPDATE_TIME DATE,
                                        PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_order IS '保养工单表';
COMMENT ON COLUMN ct_tmos_equipment_order.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_order.plan_id IS '计划id';
COMMENT ON COLUMN ct_tmos_equipment_order.order_no IS '工单号';
COMMENT ON COLUMN ct_tmos_equipment_order.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_order.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_order.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_order.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_order.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_order.stop_line_type IS '停线类型：1=整线停线，2=不停线Unit';
COMMENT ON COLUMN ct_tmos_equipment_order.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_order.sys_plan_time IS '系统计划日期';
COMMENT ON COLUMN ct_tmos_equipment_order.adjust_time IS '调整日期';
COMMENT ON COLUMN ct_tmos_equipment_order.expect_hours IS '预计时长';
COMMENT ON COLUMN ct_tmos_equipment_order.real_start_time IS '实际开始时间';
COMMENT ON COLUMN ct_tmos_equipment_order.real_end_time IS '实际结束时间';
COMMENT ON COLUMN ct_tmos_equipment_order.approve_status IS '审核状态：01=待生产审核，02=待设备确认，03=待执行，04=执行中，05=待填报，06=保养完成，07=取消保养';
COMMENT ON COLUMN ct_tmos_equipment_order.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_order.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_order.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_order.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_order.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_order.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_order_item(
                                             id VARCHAR2(64) NOT NULL,
                                             order_no VARCHAR2(32) NOT NULL,
                                             order_unit_pk_id VARCHAR2(64) NOT NULL,
                                             item_content VARCHAR2(300),
                                             min_cycle VARCHAR2(10),
                                             unit_id VARCHAR2(32) NOT NULL,
                                             sub_unit_id VARCHAR2(32) NOT NULL,
                                             upkeep_result VARCHAR2(5) DEFAULT  0,
                                             STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                             REMARK VARCHAR2(200),
                                             CREATE_BY VARCHAR2(64),
                                             CREATE_TIME DATE,
                                             UPDATE_BY VARCHAR2(64),
                                             UPDATE_TIME DATE,
                                             PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_order_item IS '保养工单项目表';
COMMENT ON COLUMN ct_tmos_equipment_order_item.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_order_item.order_no IS '工单号';
COMMENT ON COLUMN ct_tmos_equipment_order_item.order_unit_pk_id IS '保养工单设备主键';
COMMENT ON COLUMN ct_tmos_equipment_order_item.item_content IS '项目内容';
COMMENT ON COLUMN ct_tmos_equipment_order_item.min_cycle IS '最小周期';
COMMENT ON COLUMN ct_tmos_equipment_order_item.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_order_item.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_order_item.upkeep_result IS '保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败';
COMMENT ON COLUMN ct_tmos_equipment_order_item.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_order_item.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_order_item.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_order_item.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_order_item.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_order_item.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_order_node(
                                             id VARCHAR2(64) NOT NULL,
                                             order_no VARCHAR2(32) NOT NULL,
                                             node_code VARCHAR2(32) NOT NULL,
                                             operate_user_code VARCHAR2(64),
                                             operate_user_name VARCHAR2(32),
                                             operate_time DATE,
                                             operate_status VARCHAR2(5),
                                             advice VARCHAR2(300),
                                             operate_stage NUMBER(1) DEFAULT  0,
                                             seq_no NUMBER(3),
                                             STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                             REMARK VARCHAR2(200),
                                             CREATE_BY VARCHAR2(64),
                                             CREATE_TIME DATE,
                                             UPDATE_BY VARCHAR2(64),
                                             UPDATE_TIME DATE,
                                             PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_order_node IS '保养工单节点履历表';
COMMENT ON COLUMN ct_tmos_equipment_order_node.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_order_node.order_no IS '工单号';
COMMENT ON COLUMN ct_tmos_equipment_order_node.node_code IS '节点code';
COMMENT ON COLUMN ct_tmos_equipment_order_node.operate_user_code IS '操作人';
COMMENT ON COLUMN ct_tmos_equipment_order_node.operate_user_name IS '操作人名称';
COMMENT ON COLUMN ct_tmos_equipment_order_node.operate_time IS '操作时间';
COMMENT ON COLUMN ct_tmos_equipment_order_node.operate_status IS '操作状态：1=取消保养，2=通过，9=不通过';
COMMENT ON COLUMN ct_tmos_equipment_order_node.advice IS '意见';
COMMENT ON COLUMN ct_tmos_equipment_order_node.operate_stage IS '操作阶段：0=未操作，1=待操作，2=已操作';
COMMENT ON COLUMN ct_tmos_equipment_order_node.seq_no IS '序号';
COMMENT ON COLUMN ct_tmos_equipment_order_node.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_order_node.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_order_node.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_order_node.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_order_node.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_order_node.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_incomplete(
                                             id VARCHAR2(64) NOT NULL,
                                             order_no VARCHAR2(32),
                                             factory_name VARCHAR2(32) NOT NULL,
                                             area VARCHAR2(32) NOT NULL,
                                             eqp_id VARCHAR2(32) NOT NULL,
                                             unit_id VARCHAR2(32),
                                             sub_unit_id VARCHAR2(32),
                                             stop_line_type VARCHAR2(5) NOT NULL,
                                             cycle VARCHAR2(10),
                                             item_content VARCHAR2(300),
                                             upkeep_result VARCHAR2(5) DEFAULT  0,
                                             plan_time DATE,
                                             real_time DATE,
                                             lock_status VARCHAR2(10) DEFAULT  0 NOT NULL,
                                             STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                             REMARK VARCHAR2(200),
                                             CREATE_BY VARCHAR2(64),
                                             CREATE_TIME DATE,
                                             UPDATE_BY VARCHAR2(64),
                                             UPDATE_TIME DATE,
                                             PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_incomplete IS '设备残件表';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.order_no IS '工单号';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.stop_line_type IS '停线类型：1=整线停线，2=不停线Unit';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.item_content IS '保养项目内容';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.upkeep_result IS '保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.plan_time IS '原计划日期';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.real_time IS '实际日期';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.lock_status IS '锁定状态：0=待记录，1=审批中，2=执行中';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_temp_upkeep(
                                              id VARCHAR2(64) NOT NULL,
                                              order_no VARCHAR2(32),
                                              borrow_no VARCHAR2(32),
                                              factory_name VARCHAR2(32) NOT NULL,
                                              area VARCHAR2(32) NOT NULL,
                                              eqp_id VARCHAR2(32) NOT NULL,
                                              unit_id VARCHAR2(32),
                                              sub_unit_id VARCHAR2(32),
                                              stop_line_type VARCHAR2(5) NOT NULL,
                                              cycle VARCHAR2(10),
                                              STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                              REMARK VARCHAR2(200),
                                              CREATE_BY VARCHAR2(64),
                                              CREATE_TIME DATE,
                                              UPDATE_BY VARCHAR2(64),
                                              UPDATE_TIME DATE,
                                              PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_temp_upkeep IS '设备临时保养表';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.order_no IS '保养工单号';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.borrow_no IS '借机单号';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.stop_line_type IS '停线类型：1=整线停线，2=不停线Unit';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.UPDATE_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_upkeep_in(
                                            id VARCHAR2(64) NOT NULL,
                                            temp_upkeep_id VARCHAR2(64) NOT NULL,
                                            factory_name VARCHAR2(32) NOT NULL,
                                            area VARCHAR2(32) NOT NULL,
                                            eqp_id VARCHAR2(32) NOT NULL,
                                            unit_id VARCHAR2(32),
                                            sub_unit_id VARCHAR2(32),
                                            stop_line_type VARCHAR2(5) NOT NULL,
                                            cycle VARCHAR2(10),
                                            item_content VARCHAR2(300),
                                            upkeep_result VARCHAR2(5) DEFAULT  0,
                                            plan_time DATE,
                                            real_time DATE,
                                            STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                            REMARK VARCHAR2(200),
                                            CREATE_BY VARCHAR2(64),
                                            CREATE_TIME DATE,
                                            UPDATE_BY VARCHAR2(64),
                                            UPDATE_TIME DATE,
                                            PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_upkeep_in IS '设备临时保养残件记录表';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.temp_upkeep_id IS '临时保养id';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.stop_line_type IS '停线类型：1=整线停线，2=不停线Unit';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.item_content IS '保养项目内容';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.upkeep_result IS '保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.plan_time IS '原计划日期';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.real_time IS '实际日期';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.CREATE_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.UPDATE_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.UPDATE_TIME IS '更新时间';



-- modify 2024-7-25
ALTER TABLE CT_TMOS_EQUIPMENT_TEMPLATE MODIFY TEMPLATE_NAME VARCHAR2(200) NULL;
alter table CT_TMOS_EQUIPMENT_TEMPLATE add unit_id VARCHAR2(32);
comment on column CT_TMOS_EQUIPMENT_TEMPLATE.unit_id IS '设备';

-- modify 2024-7-29
alter table ct_tmos_equipment_item add standard VARCHAR2(300);
comment on column ct_tmos_equipment_item.standard IS '保养标准';
alter table ct_tmos_equipment_plan_item add standard VARCHAR2(300);
comment on column ct_tmos_equipment_plan_item.standard IS '保养标准';
alter table ct_tmos_equipment_order_item add standard VARCHAR2(300);
comment on column ct_tmos_equipment_order_item.standard IS '保养标准';
ALTER TABLE CT_TMOS_EQUIPMENT_TEMPLATE MODIFY TEMPLATE_TYPE VARCHAR2(10);

-- modify 2024-7-31
alter table ct_tmos_equipment_incomplete add standard VARCHAR2(300);
comment on column ct_tmos_equipment_incomplete.standard IS '保养标准';
alter table ct_tmos_equipment_upkeep_in add standard VARCHAR2(300);
comment on column ct_tmos_equipment_upkeep_in.standard IS '保养标准';
alter table ct_tmos_equipment_incomplete add item_id VARCHAR2(64);
comment on column ct_tmos_equipment_incomplete.item_id IS '项目id';
alter table ct_tmos_equipment_order_item add item_id VARCHAR2(64);
comment on column ct_tmos_equipment_order_item.item_id IS '项目id';
alter table ct_tmos_equipment_upkeep_in add item_id VARCHAR2(64);
comment on column ct_tmos_equipment_upkeep_in.item_id IS '项目id';
alter table ct_tmos_equipment_order add node_code VARCHAR2(32);
comment on column ct_tmos_equipment_order.node_code IS '节点code';
alter table ct_tmos_equipment_order add approve_time date ;
comment on column ct_tmos_equipment_order.approve_time IS '审批时间';
alter table ct_tmos_equipment_order add order_type VARCHAR2(10);
comment on column ct_tmos_equipment_order.order_type IS '工单类型：1=保养单，2=取消保养单';
alter table ct_tmos_equipment_order_item add upkeep_user_code VARCHAR2(64);
comment on column ct_tmos_equipment_order_item.upkeep_user_code IS '保养人code';
alter table ct_tmos_equipment_order_item add upkeep_user_name VARCHAR2(32);
comment on column ct_tmos_equipment_order_item.upkeep_user_name IS '保养人名称';

-- modify 2024-8-1
ALTER TABLE ct_tmos_equipment_order_item DROP COLUMN order_unit_pk_id;
ALTER TABLE CT_TMOS_EQUIPMENT_PLAN_ITEM MODIFY SUB_UNIT_ID VARCHAR2(32) NULL;
ALTER TABLE CT_TMOS_EQUIPMENT_PLAN ADD CYCLE_DAYS NUMBER(6,0) NOT NULL;
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN.CYCLE_DAYS IS '周期设定天数';
ALTER TABLE CT_TMOS_EQUIPMENT_PLAN MODIFY "CYCLE" VARCHAR2(10) NOT NULL;

-- modify 2024-8-2
alter table CT_TMOS_EQUIPMENT_TEMP_UPKEEP add PLAN_TIME DATE;
comment on column CT_TMOS_EQUIPMENT_TEMP_UPKEEP.PLAN_TIME IS '计划日期';
alter table CT_TMOS_EQUIPMENT_TEMP_UPKEEP add REAL_TIME DATE;
comment on column CT_TMOS_EQUIPMENT_TEMP_UPKEEP.REAL_TIME IS '实际日期';
alter table CT_TMOS_EQUIPMENT_TEMP_UPKEEP add EXPECT_HOURS NUMBER(5, 1);
comment on column CT_TMOS_EQUIPMENT_TEMP_UPKEEP.EXPECT_HOURS IS '预计时长';
alter table CT_TMOS_EQUIPMENT_TEMP_UPKEEP add REAL_HOURS NUMBER(5, 1);
comment on column CT_TMOS_EQUIPMENT_TEMP_UPKEEP.REAL_HOURS IS '实际时长';
CREATE TABLE CT_TMOS_EQUIPMENT_SVC_CONFIG(
                                             ID VARCHAR2(64) NOT NULL,
                                             CODE VARCHAR2(255) NOT NULL,
                                             NAME VARCHAR2(255) NOT NULL,
                                             WEEK NUMBER(5),
                                             MONTH NUMBER(5),
                                             TWO_MONTHS NUMBER(5),
                                             QUARTER NUMBER(5),
                                             HALF_YEAR NUMBER(5),
                                             YEAR NUMBER(5),
                                             STATUS VARCHAR2(10) DEFAULT  '0 ' NOT NULL,
                                             REMARK VARCHAR2(200),
                                             CREATE_BY VARCHAR2(64),
                                             CREATE_TIME DATE,
                                             UPDATE_BY VARCHAR2(64),
                                             UPDATE_TIME DATE DEFAULT  sysdate,
                                             PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_SVC_CONFIG IS '设备保养全局参数设定';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.CODE IS '参数code';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.NAME IS '参数名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.WEEK IS '周保养';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.MONTH IS '月保养';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.TWO_MONTHS IS '双月保';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.QUARTER IS '季保养';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.HALF_YEAR IS '半年保';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.YEAR IS '年保养';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SVC_CONFIG.UPDATE_TIME IS '更新时间';
CREATE UNIQUE INDEX idx_ctesgc_code ON CT_TMOS_EQUIPMENT_SVC_CONFIG(CODE);

INSERT INTO CT_TMOS_EQUIPMENT_SVC_CONFIG
(ID, CODE, NAME, WEEK, "MONTH", TWO_MONTHS, QUARTER, HALF_YEAR, "YEAR", STATUS, REMARK, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME)
VALUES('035e78ed0da8481fb4d6ed6c00388971', 'SVC001', '计划调整', 1, 3, 7, 7, 7, 7, '0', NULL, 'sys_001', TIMESTAMP '2024-08-02 00:00:00.000000', NULL, TIMESTAMP '2024-08-02 00:00:00.000000');
INSERT INTO CT_TMOS_EQUIPMENT_SVC_CONFIG
(ID, CODE, NAME, WEEK, "MONTH", TWO_MONTHS, QUARTER, HALF_YEAR, "YEAR", STATUS, REMARK, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME)
VALUES('035e78ed0da8481fb4d6ed6c00388972', 'SVC002', '保养工单有效期', 3, 7, 7, 7, 7, 7, '0', NULL, 'sys_001', TIMESTAMP '2024-08-02 00:00:00.000000', NULL, TIMESTAMP '2024-08-02 00:00:00.000000');
INSERT INTO CT_TMOS_EQUIPMENT_SVC_CONFIG
(ID, CODE, NAME, WEEK, "MONTH", TWO_MONTHS, QUARTER, HALF_YEAR, "YEAR", STATUS, REMARK, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME)
VALUES('035e78ed0da8481fb4d6ed6c00388973', 'SVC003', '保养时长上限设定', 5, 27, 60, 90, 180, 360, '0', NULL, 'sys_001', TIMESTAMP '2024-08-02 00:00:00.000000', NULL, TIMESTAMP '2024-08-02 00:00:00.000000');
INSERT INTO CT_TMOS_EQUIPMENT_SVC_CONFIG
(ID, CODE, NAME, WEEK, "MONTH", TWO_MONTHS, QUARTER, HALF_YEAR, "YEAR", STATUS, REMARK, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME)
VALUES('035e78ed0da8481fb4d6ed6c00388974', 'SVC004', '保养时长下限设定', 9, 33, 60, 90, 180, 360, '0', NULL, 'sys_001', TIMESTAMP '2024-08-02 00:00:00.000000', NULL, TIMESTAMP '2024-08-02 00:00:00.000000');
INSERT INTO CT_TMOS_EQUIPMENT_SVC_CONFIG
(ID, CODE, NAME, WEEK, "MONTH", TWO_MONTHS, QUARTER, HALF_YEAR, "YEAR", STATUS, REMARK, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME)
VALUES('035e78ed0da8481fb4d6ed6c00388975', 'SVC005', '延期保养设定', 0, 3, 3, 3, 3, 3, '0', NULL, 'sys_001', TIMESTAMP '2024-08-02 00:00:00.000000', NULL, TIMESTAMP '2024-08-02 00:00:00.000000');
INSERT INTO CT_TMOS_EQUIPMENT_SVC_CONFIG
(ID, CODE, NAME, WEEK, "MONTH", TWO_MONTHS, QUARTER, HALF_YEAR, "YEAR", STATUS, REMARK, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME)
VALUES('035e78ed0da8481fb4d6ed6c00388976', 'SVC006', '开单设定', 1, 1, 1, 1, 1, 1, '0', NULL, 'sys_001', TIMESTAMP '2024-08-02 00:00:00.000000', NULL, TIMESTAMP '2024-08-02 00:00:00.000000');

ALTER TABLE CT_TMOS_EQUIPMENT_ORDER_ITEM MODIFY SUB_UNIT_ID VARCHAR2(32) NULL;

-- modify 2024-8-6
ALTER TABLE CT_TMOS_EQUIPMENT_ORDER MODIFY PLAN_ID VARCHAR2(64) NULL;

-- modify 2024-8-7（未执行）
alter table CT_TMOS_EQUIPMENT_ORDER add supplement_flag VARCHAR2(5) DEFAULT '0';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER.supplement_flag IS '是否可补录：0=否，1=是';