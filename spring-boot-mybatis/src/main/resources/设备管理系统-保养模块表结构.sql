CREATE TABLE ct_tmos_equipment_group(
                                        id VARCHAR2(64) NOT NULL,
                                        template_id VARCHAR2(64),
                                        group_name VARCHAR2(100) NOT NULL,
                                        unit_type VARCHAR2(30),
                                        STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                        REMARK VARCHAR2(200),
                                        CREATED_BY VARCHAR2(64),
                                        CREATED_TIME DATE,
                                        UPDATED_BY VARCHAR2(64),
                                        UPDATED_TIME DATE,
                                        PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_group IS '设备关系组表';
COMMENT ON COLUMN ct_tmos_equipment_group.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_group.template_id IS '模版id';
COMMENT ON COLUMN ct_tmos_equipment_group.group_name IS '关系组名称';
COMMENT ON COLUMN ct_tmos_equipment_group.unit_type IS '设备类型';
COMMENT ON COLUMN ct_tmos_equipment_group.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_group.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_group.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_group.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_group.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_group.UPDATED_TIME IS '更新时间';

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
                                             CREATED_BY VARCHAR2(64),
                                             CREATED_TIME DATE,
                                             UPDATED_BY VARCHAR2(64),
                                             UPDATED_TIME DATE,
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
COMMENT ON COLUMN ct_tmos_equipment_group_unit.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_group_unit.UPDATED_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_template(
                                           id VARCHAR2(64) NOT NULL,
                                           template_name VARCHAR2(100) NOT NULL,
                                           template_type VARCHAR2(10) DEFAULT  1 NOT NULL,
                                           parent_id VARCHAR2(64),
                                           group_id VARCHAR2(64),
                                           STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                           REMARK VARCHAR2(200),
                                           CREATED_BY VARCHAR2(64),
                                           CREATED_TIME DATE,
                                           UPDATED_BY VARCHAR2(64),
                                           UPDATED_TIME DATE,
                                           PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_template IS '保养模版表';
COMMENT ON COLUMN ct_tmos_equipment_template.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_template.template_name IS '模版名称';
COMMENT ON COLUMN ct_tmos_equipment_template.template_type IS '模版类型：1=by unit,2=by sub unit';
COMMENT ON COLUMN ct_tmos_equipment_template.parent_id IS '父节点id';
COMMENT ON COLUMN ct_tmos_equipment_template.group_id IS '关系组id';
COMMENT ON COLUMN ct_tmos_equipment_template.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_template.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_template.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_template.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_template.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_template.UPDATED_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_sub_unit(
                                           id VARCHAR2(64) NOT NULL,
                                           template_id VARCHAR2(64) NOT NULL,
                                           sub_unit_group VARCHAR2(32) NOT NULL,
                                           STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                           REMARK VARCHAR2(200),
                                           CREATED_BY VARCHAR2(64),
                                           CREATED_TIME DATE,
                                           UPDATED_BY VARCHAR2(64),
                                           UPDATED_TIME DATE,
                                           PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_sub_unit IS '保养模版设备腔室配置表';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.template_id IS '模版id';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.sub_unit_group IS '设备unit组别';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_sub_unit.UPDATED_TIME IS '更新时间';

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
                                            CREATED_BY VARCHAR2(64),
                                            CREATED_TIME DATE,
                                            UPDATED_BY VARCHAR2(64),
                                            UPDATED_TIME DATE,
                                            PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_stop_line IS '保养计划生成设备停线配置表';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.stop_line_type IS '停线类型：1=Line，2=Unit(不停线)';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.first_upkeep_time IS '首次保养时间';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.expect_hours IS '预计时长';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.UPDATED_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_item(
                                       id VARCHAR2(64) NOT NULL,
                                       template_id VARCHAR2(64) NOT NULL,
                                       item_content VARCHAR2(300),
                                       min_cycle VARCHAR2(10),
                                       STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                       REMARK VARCHAR2(200),
                                       CREATED_BY VARCHAR2(64),
                                       CREATED_TIME DATE,
                                       UPDATED_BY VARCHAR2(64),
                                       UPDATED_TIME DATE,
                                       PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_item IS '保养模版项目表';
COMMENT ON COLUMN ct_tmos_equipment_item.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_item.template_id IS '模板id';
COMMENT ON COLUMN ct_tmos_equipment_item.item_content IS '保养内容';
COMMENT ON COLUMN ct_tmos_equipment_item.min_cycle IS '最小周期';
COMMENT ON COLUMN ct_tmos_equipment_item.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_item.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_item.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_item.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_item.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_item.UPDATED_TIME IS '更新时间';

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
                                               CREATED_BY VARCHAR2(64),
                                               CREATED_TIME DATE,
                                               UPDATED_BY VARCHAR2(64),
                                               UPDATED_TIME DATE,
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
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_cycle_config.UPDATED_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_time_config(
                                              id VARCHAR2(64) NOT NULL,
                                              factory_name VARCHAR2(32) NOT NULL,
                                              area VARCHAR2(32) NOT NULL,
                                              eqp_id VARCHAR2(32) NOT NULL,
                                              first_upkeep_time DATE,
                                              STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                              REMARK VARCHAR2(200),
                                              CREATED_BY VARCHAR2(64),
                                              CREATED_TIME DATE,
                                              UPDATED_BY VARCHAR2(64),
                                              UPDATED_TIME DATE,
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
COMMENT ON COLUMN ct_tmos_equipment_time_config.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_time_config.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_time_config.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_time_config.UPDATED_TIME IS '更新时间';

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
                                       CREATED_BY VARCHAR2(64),
                                       CREATED_TIME DATE,
                                       UPDATED_BY VARCHAR2(64),
                                       UPDATED_TIME DATE,
                                       PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_plan IS '保养计划表';
COMMENT ON COLUMN ct_tmos_equipment_plan.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_plan.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_plan.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_plan.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_plan.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_plan.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_plan.stop_line_type IS '停线类型：1=Line，2=Unit(不停线)';
COMMENT ON COLUMN ct_tmos_equipment_plan.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_plan.sys_plan_time IS '系统计划日期';
COMMENT ON COLUMN ct_tmos_equipment_plan.adjust_time IS '调整日期';
COMMENT ON COLUMN ct_tmos_equipment_plan.expect_hours IS '预计时长';
COMMENT ON COLUMN ct_tmos_equipment_plan.plan_status IS '保养计划状态：0=新建，1=生成工单，2=保养完成，3=取消保养';
COMMENT ON COLUMN ct_tmos_equipment_plan.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_plan.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_plan.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_plan.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_plan.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_plan.UPDATED_TIME IS '更新时间';

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
                                            CREATED_BY VARCHAR2(64),
                                            CREATED_TIME DATE,
                                            UPDATED_BY VARCHAR2(64),
                                            UPDATED_TIME DATE,
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
COMMENT ON COLUMN ct_tmos_equipment_plan_item.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.UPDATED_TIME IS '更新时间';

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
                                        CREATED_BY VARCHAR2(64),
                                        CREATED_TIME DATE,
                                        UPDATED_BY VARCHAR2(64),
                                        UPDATED_TIME DATE,
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
COMMENT ON COLUMN ct_tmos_equipment_order.stop_line_type IS '停线类型：1=Line，2=Unit(不停线)';
COMMENT ON COLUMN ct_tmos_equipment_order.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_order.sys_plan_time IS '系统计划日期';
COMMENT ON COLUMN ct_tmos_equipment_order.adjust_time IS '调整日期';
COMMENT ON COLUMN ct_tmos_equipment_order.expect_hours IS '预计时长';
COMMENT ON COLUMN ct_tmos_equipment_order.real_start_time IS '实际开始时间';
COMMENT ON COLUMN ct_tmos_equipment_order.real_end_time IS '实际结束时间';
COMMENT ON COLUMN ct_tmos_equipment_order.approve_status IS '审核状态：01=待生产审核，02=待设备确认，03=待执行，04=执行中，05=待填报，06=保养完成，07=取消保养';
COMMENT ON COLUMN ct_tmos_equipment_order.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_order.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_order.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_order.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_order.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_order.UPDATED_TIME IS '更新时间';

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
                                             CREATED_BY VARCHAR2(64),
                                             CREATED_TIME DATE,
                                             UPDATED_BY VARCHAR2(64),
                                             UPDATED_TIME DATE,
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
COMMENT ON COLUMN ct_tmos_equipment_order_item.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_order_item.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_order_item.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_order_item.UPDATED_TIME IS '更新时间';

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
                                             CREATED_BY VARCHAR2(64),
                                             CREATED_TIME DATE,
                                             UPDATED_BY VARCHAR2(64),
                                             UPDATED_TIME DATE,
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
COMMENT ON COLUMN ct_tmos_equipment_order_node.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_order_node.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_order_node.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_order_node.UPDATED_TIME IS '更新时间';

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
                                             CREATED_BY VARCHAR2(64),
                                             CREATED_TIME DATE,
                                             UPDATED_BY VARCHAR2(64),
                                             UPDATED_TIME DATE,
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
COMMENT ON COLUMN ct_tmos_equipment_incomplete.stop_line_type IS '停线类型：1=Line，2=Unit(不停线)';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.item_content IS '保养项目内容';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.upkeep_result IS '保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.plan_time IS '原计划日期';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.real_time IS '实际日期';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.lock_status IS '锁定状态：0=待记录，1=审批中，2=执行中';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_incomplete.UPDATED_TIME IS '更新时间';

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
                                              CREATED_BY VARCHAR2(64),
                                              CREATED_TIME DATE,
                                              UPDATED_BY VARCHAR2(64),
                                              UPDATED_TIME DATE,
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
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.stop_line_type IS '停线类型：1=Line，2=Unit(不停线)';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_temp_upkeep.UPDATED_TIME IS '更新时间';

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
                                            CREATED_BY VARCHAR2(64),
                                            CREATED_TIME DATE,
                                            UPDATED_BY VARCHAR2(64),
                                            UPDATED_TIME DATE,
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
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.stop_line_type IS '停线类型：1=Line，2=Unit(不停线)';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.item_content IS '保养项目内容';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.upkeep_result IS '保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.plan_time IS '原计划日期';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.real_time IS '实际日期';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_upkeep_in.UPDATED_TIME IS '更新时间';

