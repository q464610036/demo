CREATE TABLE ct_tmos_equipment_group(
                                        id VARCHAR2(64) NOT NULL,
                                        group_name VARCHAR2(100) NOT NULL,
                                        unit_type VARCHAR2(30),
                                        template_id VARCHAR2(64),
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
COMMENT ON COLUMN ct_tmos_equipment_group.group_name IS '关系组名称';
COMMENT ON COLUMN ct_tmos_equipment_group.unit_type IS '设备类型';
COMMENT ON COLUMN ct_tmos_equipment_group.template_id IS '保养模版id';
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
                                            unit_id VARCHAR2(32),
                                            stop_line_type VARCHAR2(5) NOT NULL,
                                            STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                            REMARK VARCHAR2(200),
                                            CREATED_BY VARCHAR2(64),
                                            CREATED_TIME DATE,
                                            UPDATED_BY VARCHAR2(64),
                                            UPDATED_TIME DATE,
                                            PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_stop_line IS '保养计划生成设备停线记录表';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_stop_line.stop_line_type IS '停线类型：1=Line，2=Unit(不停线)';
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

CREATE TABLE ct_tmos_equipment_time_config(
                                              id VARCHAR2(64) NOT NULL,
                                              factory_name VARCHAR2(32) NOT NULL,
                                              area VARCHAR2(32) NOT NULL,
                                              expect_hours NUMBER(5,1),
                                              notice_days NUMBER(5),
                                              order_days NUMBER(5),
                                              gap_warn_days NUMBER(5),
                                              week NUMBER(5),
                                              month NUMBER(5),
                                              two_months NUMBER(5),
                                              quarter NUMBER(5),
                                              half_year NUMBER(5),
                                              year NUMBER(5),
                                              STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                              REMARK VARCHAR2(200),
                                              CREATED_BY VARCHAR2(64),
                                              CREATED_TIME DATE,
                                              UPDATED_BY VARCHAR2(64),
                                              UPDATED_TIME DATE,
                                              PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_time_config IS '设备群组时间配置表';
COMMENT ON COLUMN ct_tmos_equipment_time_config.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_time_config.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_time_config.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_time_config.expect_hours IS '预计时长';
COMMENT ON COLUMN ct_tmos_equipment_time_config.notice_days IS '通知时限';
COMMENT ON COLUMN ct_tmos_equipment_time_config.order_days IS '开单时限';
COMMENT ON COLUMN ct_tmos_equipment_time_config.gap_warn_days IS '达成GAP预警';
COMMENT ON COLUMN ct_tmos_equipment_time_config.week IS '周';
COMMENT ON COLUMN ct_tmos_equipment_time_config.month IS '月';
COMMENT ON COLUMN ct_tmos_equipment_time_config.two_months IS '两个月';
COMMENT ON COLUMN ct_tmos_equipment_time_config.quarter IS '季度';
COMMENT ON COLUMN ct_tmos_equipment_time_config.half_year IS '半年';
COMMENT ON COLUMN ct_tmos_equipment_time_config.year IS '年';
COMMENT ON COLUMN ct_tmos_equipment_time_config.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_time_config.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_time_config.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_time_config.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_time_config.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_time_config.UPDATED_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_plan_config(
                                              id VARCHAR2(64) NOT NULL,
                                              factory_name VARCHAR2(32) NOT NULL,
                                              area VARCHAR2(32) NOT NULL,
                                              eqp_id VARCHAR2(32) NOT NULL,
                                              unit_id VARCHAR2(32),
                                              sub_unit_id VARCHAR2(32),
                                              stop_line_type VARCHAR2(5) NOT NULL,
                                              cycle VARCHAR2(10),
                                              first_maintain_time DATE,
                                              STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                              REMARK VARCHAR2(200),
                                              CREATED_BY VARCHAR2(64),
                                              CREATED_TIME DATE,
                                              UPDATED_BY VARCHAR2(64),
                                              UPDATED_TIME DATE,
                                              PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_plan_config IS '保养计划生成配置表';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.sub_unit_id IS '子设备';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.stop_line_type IS '停线类型：1=Line，2=Unit(不停线)';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.first_maintain_time IS '首次保养时间';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_plan_config.UPDATED_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_plan(
                                       id VARCHAR2(64) NOT NULL,
                                       factory_name VARCHAR2(32) NOT NULL,
                                       area VARCHAR2(32) NOT NULL,
                                       eqp_id VARCHAR2(32) NOT NULL,
                                       unit_id VARCHAR2(32),
                                       stop_line_type VARCHAR2(5) NOT NULL,
                                       cycle VARCHAR2(10),
                                       sys_plan_time DATE,
                                       adjust_time DATE,
                                       expect_hours NUMBER(5,1),
                                       maintain_status NUMBER(1) DEFAULT  0 NOT NULL,
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
COMMENT ON COLUMN ct_tmos_equipment_plan.stop_line_type IS '停线类型：1=Line，2=Unit(不停线)';
COMMENT ON COLUMN ct_tmos_equipment_plan.cycle IS '周期';
COMMENT ON COLUMN ct_tmos_equipment_plan.sys_plan_time IS '系统计划日期';
COMMENT ON COLUMN ct_tmos_equipment_plan.adjust_time IS '调整日期';
COMMENT ON COLUMN ct_tmos_equipment_plan.expect_hours IS '预计时长';
COMMENT ON COLUMN ct_tmos_equipment_plan.maintain_status IS '保养状态：0=未保养，1=已保养';
COMMENT ON COLUMN ct_tmos_equipment_plan.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_plan.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_plan.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_plan.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_plan.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_plan.UPDATED_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_plan_unit(
                                            id VARCHAR2(64) NOT NULL,
                                            plan_id VARCHAR2(64) NOT NULL,
                                            template_id VARCHAR2(64) NOT NULL,
                                            unit_id VARCHAR2(32) NOT NULL,
                                            STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                            REMARK VARCHAR2(200),
                                            CREATED_BY VARCHAR2(64),
                                            CREATED_TIME DATE,
                                            UPDATED_BY VARCHAR2(64),
                                            UPDATED_TIME DATE,
                                            PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_plan_unit IS '保养计划设备表';
COMMENT ON COLUMN ct_tmos_equipment_plan_unit.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_plan_unit.plan_id IS '计划id';
COMMENT ON COLUMN ct_tmos_equipment_plan_unit.template_id IS '模板id';
COMMENT ON COLUMN ct_tmos_equipment_plan_unit.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_plan_unit.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_plan_unit.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_plan_unit.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_plan_unit.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_plan_unit.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_plan_unit.UPDATED_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_plan_item(
                                            id VARCHAR2(64) NOT NULL,
                                            plan_unit_id VARCHAR2(64) NOT NULL,
                                            item_id VARCHAR2(6),
                                            min_cycle VARCHAR2(10),
                                            sub_unit_id_list VARCHAR2(500),
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
COMMENT ON COLUMN ct_tmos_equipment_plan_item.plan_unit_id IS '保养计划设备id';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.item_id IS '项目id';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.min_cycle IS '最小周期';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.sub_unit_id_list IS '子设备列表';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_plan_item.UPDATED_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_order(
                                        id VARCHAR2(64) NOT NULL,
                                        order_no VARCHAR2(32) NOT NULL,
                                        factory_name VARCHAR2(32) NOT NULL,
                                        area VARCHAR2(32) NOT NULL,
                                        eqp_id VARCHAR2(32) NOT NULL,
                                        unit_id VARCHAR2(32),
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
COMMENT ON COLUMN ct_tmos_equipment_order.order_no IS '工单号';
COMMENT ON COLUMN ct_tmos_equipment_order.factory_name IS '厂别';
COMMENT ON COLUMN ct_tmos_equipment_order.area IS '设备群组';
COMMENT ON COLUMN ct_tmos_equipment_order.eqp_id IS '线体';
COMMENT ON COLUMN ct_tmos_equipment_order.unit_id IS '设备';
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

CREATE TABLE ct_tmos_equipment_order_unit(
                                             id VARCHAR2(64) NOT NULL,
                                             order_no VARCHAR2(32) NOT NULL,
                                             unit_id VARCHAR2(32) NOT NULL,
                                             STATUS VARCHAR2(10) DEFAULT  0 NOT NULL,
                                             REMARK VARCHAR2(200),
                                             CREATED_BY VARCHAR2(64),
                                             CREATED_TIME DATE,
                                             UPDATED_BY VARCHAR2(64),
                                             UPDATED_TIME DATE,
                                             PRIMARY KEY (id)
);

COMMENT ON TABLE ct_tmos_equipment_order_unit IS '保养工单设备表';
COMMENT ON COLUMN ct_tmos_equipment_order_unit.id IS '主键id';
COMMENT ON COLUMN ct_tmos_equipment_order_unit.order_no IS '工单号';
COMMENT ON COLUMN ct_tmos_equipment_order_unit.unit_id IS '设备';
COMMENT ON COLUMN ct_tmos_equipment_order_unit.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN ct_tmos_equipment_order_unit.REMARK IS '备注';
COMMENT ON COLUMN ct_tmos_equipment_order_unit.CREATED_BY IS '创建人';
COMMENT ON COLUMN ct_tmos_equipment_order_unit.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN ct_tmos_equipment_order_unit.UPDATED_BY IS '更新人';
COMMENT ON COLUMN ct_tmos_equipment_order_unit.UPDATED_TIME IS '更新时间';

CREATE TABLE ct_tmos_equipment_order_item(
                                             id VARCHAR2(64) NOT NULL,
                                             order_no VARCHAR2(32) NOT NULL,
                                             order_unit_id VARCHAR2(64) NOT NULL,
                                             item_content VARCHAR2(300),
                                             min_cycle VARCHAR2(10),
                                             sub_unit_id_list VARCHAR2(500),
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
COMMENT ON COLUMN ct_tmos_equipment_order_item.order_unit_id IS '保养工单设备主键';
COMMENT ON COLUMN ct_tmos_equipment_order_item.item_content IS '项目内容';
COMMENT ON COLUMN ct_tmos_equipment_order_item.min_cycle IS '最小周期';
COMMENT ON COLUMN ct_tmos_equipment_order_item.sub_unit_id_list IS '子设备列表';
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

