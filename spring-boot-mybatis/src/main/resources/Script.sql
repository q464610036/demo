DROP TABLE ct_tmos_equipment_borrow;
DROP TABLE ct_tmos_equipment_borrow_user;
DROP TABLE ct_tmos_equipment_borrow_unit;
DROP TABLE ct_tmos_equipment_borrow_node;
DROP TABLE ct_tmos_equipment_node_config;
-- 借机表
create table ct_tmos_equipment_borrow(
                                         id varchar2(64) not null,
                                         borrow_no varchar2(32) not null,				-- 借机单号
                                         borrow_user_code varchar2(64) not null,				-- 借机人
                                         borrow_user_name varchar2(32) not null,	-- 接机人名称
                                         factory_name varchar2(32) not null,
                                         area varchar2(32) not null,
                                         eqp_id varchar2(32) not null,
                                         unit_id varchar2(32),
                                         stop_line_type varchar2(5) not null,		-- 停线类型：1=Line，2=Unit(不停线)
                                         borrow_time date not null,							-- 借机时间
                                         borrow_plan_hours number(5) not null,		-- 计划借机时长
                                         shift_type varchar2(5) not null,				-- 班别：1=白班，2=夜班
                                         borrow_mode varchar2(5) not null,			-- 借机模式：1=普通借机，2=紧急借机
                                         borrow_type varchar2(10) not null,			-- 借机类型：ENG=工程借机，F_ENG=实验借机，PM=保养借机，EQ_D=宕机保养
                                         submit_time date not null,							-- 提交时间
                                         approve_status varchar2(5) not null,		-- 审批状态: 01=审核中，02=审核通过/待使用，03=使用中，04=使用完毕，05=审核不通过，06=作废
                                         borrow_real_start_time date,						-- 实际借机开始时间
                                         borrow_real_end_time date,							-- 实际借机结束时间
                                         borrow_real_hours number(5),						-- 实际借机时间
                                         borrow_reason varchar2(300),											-- 借机原因
                                         leader_user_code varchar2(64) not null,	-- 主管用户code
                                         office_name varchar2(30),								-- 组织名称
                                         office_code varchar2(30),								-- 组织code
                                         product_plan_user_code varchar2(64), 		-- 生产计划用户code

                                         STATUS VARCHAR2(10) DEFAULT  '0',
                                         CREATE_BY VARCHAR2(64),
                                         CREATE_TIME DATE,
                                         UPDATE_BY VARCHAR2(64),
                                         UPDATE_TIME DATE DEFAULT  sysdate,
                                         REMARK VARCHAR2(200),
                                         PRIMARY KEY (id)
);
comment on table ct_tmos_equipment_borrow is '借机表';
comment on column ct_tmos_equipment_borrow.id IS 'id';
comment on column ct_tmos_equipment_borrow.borrow_no IS '借机单号';
comment on column ct_tmos_equipment_borrow.borrow_user_code IS '借机人';
comment on column ct_tmos_equipment_borrow.borrow_user_name IS '接机人名称';
comment on column ct_tmos_equipment_borrow.factory_name IS '厂别';
comment on column ct_tmos_equipment_borrow.area IS '设备群组';
comment on column ct_tmos_equipment_borrow.eqp_id  IS '线体';
comment on column ct_tmos_equipment_borrow.unit_id IS '设备';
comment on column ct_tmos_equipment_borrow.stop_line_type IS '停线类型：1=Line，2=Unit(不停线)';
comment on column ct_tmos_equipment_borrow.borrow_time IS '借机时间';
comment on column ct_tmos_equipment_borrow.borrow_plan_hours IS '计划借机时长';
comment on column ct_tmos_equipment_borrow.shift_type IS '班别：1=白班，2=夜班';
comment on column ct_tmos_equipment_borrow.borrow_mode IS '借机模式：1=普通借机，2=紧急借机';
comment on column ct_tmos_equipment_borrow.borrow_type IS '借机类型：ENG=工程借机，F_ENG=实验借机，PM=保养借机，EQ_D=宕机保养';
comment on column ct_tmos_equipment_borrow.submit_time IS '提交时间';
comment on column ct_tmos_equipment_borrow.approve_status IS '审批状态: 01=审核中，02=审核通过/待使用，03=使用中，04=使用完毕，05=审核不通过，06=作废';
comment on column ct_tmos_equipment_borrow.borrow_real_start_time IS '实际借机开始时间';
comment on column ct_tmos_equipment_borrow.borrow_real_end_time IS '实际借机结束时间';
comment on column ct_tmos_equipment_borrow.borrow_real_hours IS '实际借机时间';
comment on column ct_tmos_equipment_borrow.borrow_reason IS '借机原因';
comment on column ct_tmos_equipment_borrow.leader_user_code IS '主管用户code';
comment on column ct_tmos_equipment_borrow.office_name IS '组织名称';
comment on column ct_tmos_equipment_borrow.office_code IS '组织code';
comment on column ct_tmos_equipment_borrow.product_plan_user_code IS '生产计划用户code';
comment on column ct_tmos_equipment_borrow.STATUS IS '状态 0=正常，1=删除';
comment on column ct_tmos_equipment_borrow.CREATE_BY IS '创建人';
comment on column ct_tmos_equipment_borrow.CREATE_TIME IS '创建时间';
comment on column ct_tmos_equipment_borrow.UPDATE_BY IS '修改人';
comment on column ct_tmos_equipment_borrow.UPDATE_TIME IS '修改时间';
comment on column ct_tmos_equipment_borrow.REMARK IS '备注';
-- INDEX
ALTER TABLE ct_tmos_equipment_borrow ADD CONSTRAINT un_cteb_borrow_no UNIQUE (borrow_no);
CREATE INDEX idx_cteb_borrow_user_code ON ct_tmos_equipment_borrow (borrow_user_code);

-- 借机用户关联表（加签/知会）
create table ct_tmos_equipment_borrow_user(
                                              id varchar2(64) not null,
                                              borrow_no varchar2(32) not null,				-- 借机单号
                                              uesr_type number(1) not null,						-- 用户类型：1=加签，2=知会
                                              user_code varchar2(64) not null,				-- 用户code
                                              user_name varchar2(32) not null,				-- 用户名称
                                              index_no number(2) not null,						  -- 序号
                                              email varchar2(30),											-- 邮箱
                                              STATUS VARCHAR2(10) DEFAULT  '0',
                                              CREATE_BY VARCHAR2(64),
                                              CREATE_TIME DATE,
                                              UPDATE_BY VARCHAR2(64),
                                              UPDATE_TIME DATE DEFAULT  sysdate,
                                              REMARK VARCHAR2(200),
                                              PRIMARY KEY (id)
);
comment on table ct_tmos_equipment_borrow_user is '借机用户关联表（加签/知会）';
comment on column ct_tmos_equipment_borrow_user.id IS 'id';
comment on column ct_tmos_equipment_borrow_user.borrow_no IS '借机单号';
comment on column ct_tmos_equipment_borrow_user.uesr_type IS '用户类型：1=加签，2=知会';
comment on column ct_tmos_equipment_borrow_user.user_code IS '用户code';
comment on column ct_tmos_equipment_borrow_user.user_name IS '用户名称';
comment on column ct_tmos_equipment_borrow_user.index_no IS '序号';
comment on column ct_tmos_equipment_borrow_user.email IS '邮箱';
comment on column ct_tmos_equipment_borrow_user.STATUS IS '状态 0=正常，1=删除';
comment on column ct_tmos_equipment_borrow_user.CREATE_BY IS '创建人';
comment on column ct_tmos_equipment_borrow_user.CREATE_TIME IS '创建时间';
comment on column ct_tmos_equipment_borrow_user.UPDATE_BY IS '修改人';
comment on column ct_tmos_equipment_borrow_user.UPDATE_TIME IS '修改时间';
comment on column ct_tmos_equipment_borrow_user.REMARK IS '备注';
-- INDEX
CREATE INDEX idx_ctebu_borrow_no ON ct_tmos_equipment_borrow_user (borrow_no);

-- 借机设备表
create table ct_tmos_equipment_borrow_unit(
                                              id varchar2(64) not null,
                                              borrow_no varchar2(32) not null,				-- 借机单号
                                              factory_name varchar2(32) not null,
                                              area varchar2(32) not null,
                                              eqp_id varchar2(32) not null,
                                              unit_id varchar2(32) not null,
                                              unit_user_code  varchar2(32) not null,		-- 设备担当

                                              STATUS VARCHAR2(10) DEFAULT  '0',
                                              CREATE_BY VARCHAR2(64),
                                              CREATE_TIME DATE,
                                              UPDATE_BY VARCHAR2(64),
                                              UPDATE_TIME DATE DEFAULT  sysdate,
                                              REMARK VARCHAR2(200),
                                              PRIMARY KEY (id)
);
comment on table ct_tmos_equipment_borrow_unit is '借机用户关联表（加签/知会）';
comment on column ct_tmos_equipment_borrow_unit.id IS 'id';
comment on column ct_tmos_equipment_borrow_unit.factory_name IS '厂别';
comment on column ct_tmos_equipment_borrow_unit.area IS '设备群组';
comment on column ct_tmos_equipment_borrow_unit.eqp_id  IS '线体';
comment on column ct_tmos_equipment_borrow_unit.unit_id IS '设备';
comment on column ct_tmos_equipment_borrow_unit.unit_user_code IS '设备担当';
comment on column ct_tmos_equipment_borrow_unit.STATUS IS '状态 0=正常，1=删除';
comment on column ct_tmos_equipment_borrow_unit.CREATE_BY IS '创建人';
comment on column ct_tmos_equipment_borrow_unit.CREATE_TIME IS '创建时间';
comment on column ct_tmos_equipment_borrow_unit.UPDATE_BY IS '修改人';
comment on column ct_tmos_equipment_borrow_unit.UPDATE_TIME IS '修改时间';
comment on column ct_tmos_equipment_borrow_unit.REMARK IS '备注';
-- INDEX
CREATE INDEX idx_ctebun_borrow_no ON ct_tmos_equipment_borrow_unit (borrow_no);

-- 借机节点履历表
create table ct_tmos_equipment_borrow_node(
                                              id varchar2(64) not null,
                                              borrow_no varchar2(32) not null,						-- 借机单号
                                              node_code  varchar2(32) not null,						-- 节点code
                                              operate_user_code varchar2(64) not null,		-- 操作人
                                              operate_user_name varchar2(32) not null,		-- 操作人名称
                                              operate_time date,													-- 操作时间
                                              operate_status varchar2(5),									-- 操作状态 0=提交申请，1=待审批，2=通过，9=不通过
                                              advice varchar2(300),												-- 意见
                                              urging_time date,														-- 催办时间
                                              seq_no number(3) not null,					  			-- 序号
                                              operate_stage number(1) default '0',	-- 操作阶段：0=未操作，1=待操作，2=已操作

                                              STATUS VARCHAR2(10) DEFAULT  '0',
                                              CREATE_BY VARCHAR2(64),
                                              CREATE_TIME DATE,
                                              UPDATE_BY VARCHAR2(64),
                                              UPDATE_TIME DATE DEFAULT  sysdate,
                                              REMARK VARCHAR2(200),
                                              PRIMARY KEY (id)
);
comment on table ct_tmos_equipment_borrow_node is '借机节点履历表';
comment on column ct_tmos_equipment_borrow_node.id is 'id';
comment on column ct_tmos_equipment_borrow_node.borrow_no is '借机单号';
comment on column ct_tmos_equipment_borrow_node.node_code is '节点code';
comment on column ct_tmos_equipment_borrow_node.operate_user_code is '操作人';
comment on column ct_tmos_equipment_borrow_node.operate_user_name is '操作人名称';
comment on column ct_tmos_equipment_borrow_node.operate_time is  '操作时间';
comment on column ct_tmos_equipment_borrow_node.operate_status is '操作状态 0=提交申请，1=待审批，2=通过，9=不通过';
comment on column ct_tmos_equipment_borrow_node.advice is '意见';
comment on column ct_tmos_equipment_borrow_node.urging_time is '催办时间';
comment on column ct_tmos_equipment_borrow_node.seq_no is '序号';
comment on column ct_tmos_equipment_borrow_node.operate_stage is '操作阶段：0=未操作，1=待操作，2=已操作';
comment on column ct_tmos_equipment_borrow_node.STATUS is '状态 0=正常，1=删除';
comment on column ct_tmos_equipment_borrow_node.CREATE_BY is '创建人';
comment on column ct_tmos_equipment_borrow_node.CREATE_TIME is '创建时间';
comment on column ct_tmos_equipment_borrow_node.UPDATE_BY is '修改人';
comment on column ct_tmos_equipment_borrow_node.UPDATE_TIME is '修改时间';
comment on column ct_tmos_equipment_borrow_node.REMARK is '备注';
-- INDEX
CREATE INDEX idx_ctebn_borrow_no ON ct_tmos_equipment_borrow_node (borrow_no);
CREATE INDEX idx_ctebn_operate_user_code ON ct_tmos_equipment_borrow_node (operate_user_code,operate_stage);

-- 借机节点配置表
create table ct_tmos_equipment_node_config(
  id varchar2(64) not null,
  node_code  varchar2(32) not null,						-- 节点code
  node_name  varchar2(32) not null,						-- 节点名称
  seq_no number(3) not null,					  			-- 序号

  STATUS VARCHAR2(10) DEFAULT  '0',
  CREATE_BY VARCHAR2(64),
  CREATE_TIME DATE,
  UPDATE_BY VARCHAR2(64),
  UPDATE_TIME DATE DEFAULT  sysdate,
  REMARK VARCHAR2(200),
  PRIMARY KEY (id)
);
comment on table ct_tmos_equipment_node_config is '借机节点配置表';
comment on column ct_tmos_equipment_node_config.id is 'id';
comment on column ct_tmos_equipment_node_config.node_code is '节点code';
comment on column ct_tmos_equipment_node_config.node_name is '节点名称';
comment on column ct_tmos_equipment_node_config.seq_no is '序号';
comment on column ct_tmos_equipment_node_config.STATUS is '状态 0=正常，1=删除';
comment on column ct_tmos_equipment_node_config.CREATE_BY is '创建人';
comment on column ct_tmos_equipment_node_config.CREATE_TIME is '创建时间';
comment on column ct_tmos_equipment_node_config.UPDATE_BY is '修改人';
comment on column ct_tmos_equipment_node_config.UPDATE_TIME is '修改时间';
comment on column ct_tmos_equipment_node_config.REMARK is '备注';

-- TODO 数据字典表







