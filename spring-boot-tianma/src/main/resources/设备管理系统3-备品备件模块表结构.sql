ALTER TABLE CT_TMOS_EQUIPMENT_GROUP ADD POINT_TEMPLATE_ID VARCHAR2(64) NULL;
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_GROUP.POINT_TEMPLATE_ID IS '备品模版id';

ALTER TABLE CT_TMOS_EQUIPMENT_ORDER ADD PMS_STUFF_ORDER_NO VARCHAR2(64) NULL;
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER.PMS_STUFF_ORDER_NO IS 'PMS领料单号';
ALTER TABLE CT_TMOS_EQUIPMENT_ORDER ADD AUTO_OPEN_ORDER_TIME DATE NULL;
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER.AUTO_OPEN_ORDER_TIME IS '自动开领料单时间';
ALTER TABLE CT_TMOS_EQUIPMENT_ORDER ADD AUTO_OPEN_ORDER_USER VARCHAR2(64) NULL;
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER.AUTO_OPEN_ORDER_USER IS '自动开领料单人';


CREATE TABLE CT_TMOS_EQUIPMENT_DEPT_STUFF(
                                             ID VARCHAR2(64) NOT NULL,
                                             STUFF_NO VARCHAR2(64) NOT NULL,
                                             SAFETY_STOCK NUMBER(20),
                                             INPORTANCE_LEVEL VARCHAR2(2) NOT NULL,
                                             LIFETIME_DAYS NUMBER(10),
                                             LIFETIME_WARN_DAYS NUMBER(10),
                                             STATUS VARCHAR2(10) DEFAULT  '0' NOT NULL,
                                             CREATE_BY VARCHAR2(64) NOT NULL,
                                             CREATE_TIME DATE NOT NULL,
                                             UPDATE_BY VARCHAR2(64),
                                             UPDATE_TIME DATE DEFAULT  sysdate,
                                             REMARK VARCHAR2(200),
                                             PRIMARY KEY (ID)
);
COMMENT ON TABLE CT_TMOS_EQUIPMENT_DEPT_STUFF IS '部门物料信息';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.ID IS '唯一标识';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.STUFF_NO IS '料号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.SAFETY_STOCK IS '安全库存';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.INPORTANCE_LEVEL IS '重要程度 0-一般 1-重要';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.LIFETIME_DAYS IS '通用寿命（天）';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.LIFETIME_WARN_DAYS IS '寿命预警（天）';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.STATUS IS '状态 0-正常 1-删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_DEPT_STUFF.REMARK IS '备注信息';


CREATE TABLE CT_TMOS_EQUIPMENT_STUFF_POINT(
                                              ID VARCHAR2(64) NOT NULL,
                                              STUFF_NO VARCHAR2(64) NOT NULL,
                                              POINT_ID VARCHAR2(64) NOT NULL,
                                              STATUS VARCHAR2(10) DEFAULT  '0' NOT NULL,
                                              CREATE_BY VARCHAR2(64) NOT NULL,
                                              CREATE_TIME DATE NOT NULL,
                                              UPDATE_BY VARCHAR2(64),
                                              UPDATE_TIME DATE DEFAULT  sysdate,
                                              REMARK VARCHAR2(200),
                                              PRIMARY KEY (ID)
);
COMMENT ON TABLE CT_TMOS_EQUIPMENT_STUFF_POINT IS '料号点位关系表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_STUFF_POINT.ID IS '唯一标识';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_STUFF_POINT.STUFF_NO IS '料号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_STUFF_POINT.POINT_ID IS '点位id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_STUFF_POINT.STATUS IS '状态 0-正常 1-删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_STUFF_POINT.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_STUFF_POINT.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_STUFF_POINT.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_STUFF_POINT.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_STUFF_POINT.REMARK IS '备注信息';



CREATE TABLE CT_TMOS_EQUIPMENT_SPARES_MONIT(
                                               ID VARCHAR2(64) NOT NULL,
                                               FACTORY_NAME VARCHAR2(32) NOT NULL,
                                               AREA VARCHAR2(32) NOT NULL,
                                               EQP_ID VARCHAR2(32) NOT NULL,
                                               UNIT_ID VARCHAR2(32) NOT NULL,
                                               SUB_UNIT_ID VARCHAR2(32),
                                               POINT_ID VARCHAR2(64) NOT NULL,
                                               POINT_NAME VARCHAR2(100),
                                               STUFF_NO VARCHAR2(300),
                                               SINGLE_GOODS_ID VARCHAR2(300),
                                               NUM NUMBER(10),
                                               START_TIME DATE,
                                               LIFE NUMBER(5),
                                               EXPECT_REPLACE_TIME DATE,
                                               FDC VARCHAR2(255),
                                               SPARES_STATUS VARCHAR2(255) DEFAULT  '1',
                                               STATUS VARCHAR2(10) DEFAULT  '0' NOT NULL,
                                               REMARK VARCHAR2(200),
                                               CREATE_BY VARCHAR2(64),
                                               CREATE_TIME DATE,
                                               UPDATE_BY VARCHAR2(64),
                                               UPDATE_TIME DATE,
                                               PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_SPARES_MONIT IS '备件监控表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.FACTORY_NAME IS '厂别';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.AREA IS '设备群组';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.EQP_ID IS '线体';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.UNIT_ID IS '设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.SUB_UNIT_ID IS '子设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.POINT_ID IS '点位id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.POINT_NAME IS '点位名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.STUFF_NO IS '料号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.SINGLE_GOODS_ID IS '单品id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.NUM IS '数量';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.START_TIME IS '上机时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.LIFE IS '寿命/天';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.EXPECT_REPLACE_TIME IS '预计更换时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.FDC IS 'FDC参数';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.SPARES_STATUS IS '备件状态 1-在机 2-待更换 3-下机';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SPARES_MONIT.UPDATE_TIME IS '更新时间';

CREATE TABLE CT_TMOS_EQUIPMENT_PLAN_SPARES(
                                              ID VARCHAR2(64) NOT NULL,
                                              PLAN_ID VARCHAR2(64) NOT NULL,
                                              UNIT_ID VARCHAR2(32) NOT NULL,
                                              SUB_UNIT_ID VARCHAR2(32),
                                              POINT_ID VARCHAR2(64) NOT NULL,
                                              POINT_NAME VARCHAR2(100),
                                              MIN_CYCLE VARCHAR2(10),
                                              LIFE NUMBER(5),
                                              STUFF_NO VARCHAR2(300),
                                              SINGLE_GOODS_ID VARCHAR2(300),
                                              NUM NUMBER(10),
                                              EXPECT_REPLACE_TIME VARCHAR2(10),
                                              STATUS VARCHAR2(10) DEFAULT  '0' NOT NULL,
                                              REMARK VARCHAR2(200),
                                              CREATE_BY VARCHAR2(64),
                                              CREATE_TIME DATE,
                                              UPDATE_BY VARCHAR2(64),
                                              UPDATE_TIME DATE,
                                              PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_PLAN_SPARES IS '保养计划备件表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.PLAN_ID IS '计划id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.UNIT_ID IS '设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.SUB_UNIT_ID IS '子设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.POINT_ID IS '点位id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.POINT_NAME IS '点位名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.MIN_CYCLE IS '最小周期';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.LIFE IS '寿命/天';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.STUFF_NO IS '料号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.SINGLE_GOODS_ID IS '单品id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.NUM IS '数量';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.EXPECT_REPLACE_TIME IS '预计更换时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_PLAN_SPARES.UPDATE_TIME IS '更新时间';

CREATE TABLE CT_TMOS_EQUIPMENT_P_TEMPLATE(
                                             ID VARCHAR2(64) NOT NULL,
                                             TEMPLATE_NAME VARCHAR2(200),
                                             TEMPLATE_TYPE VARCHAR2(10) DEFAULT  '1',
                                             UNIT_TYPE VARCHAR2(30),
                                             PARENT_ID VARCHAR2(64),
                                             SUB_UNIT_GROUP VARCHAR2(300),
                                             CREATE_USER_NAME VARCHAR2(32),
                                             STATUS VARCHAR2(10) DEFAULT  '0' NOT NULL,
                                             REMARK VARCHAR2(200),
                                             CREATE_BY VARCHAR2(64),
                                             CREATE_TIME DATE,
                                             UPDATE_BY VARCHAR2(64),
                                             UPDATE_TIME DATE,
                                             UNIT_ID VARCHAR2(32),
                                             PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_P_TEMPLATE IS '点位模版表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.TEMPLATE_NAME IS '模版名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.TEMPLATE_TYPE IS '模版类型：1=by unit,2=by sub unit';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.UNIT_TYPE IS '设备类型';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.PARENT_ID IS '父节点id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.SUB_UNIT_GROUP IS '子设备关系组';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.CREATE_USER_NAME IS '创建人名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE.UNIT_ID IS '设备';

CREATE TABLE CT_TMOS_EQUIPMENT_P_TEMPLATE_P(
                                               ID VARCHAR2(64) NOT NULL,
                                               TEMPLATE_ID VARCHAR2(64) NOT NULL,
                                               point_name VARCHAR2(100),
                                               num NUMBER(10),
                                               cycle VARCHAR2(10),
                                               STANDARD VARCHAR2(300),
                                               STATUS VARCHAR2(10) DEFAULT  '0' NOT NULL,
                                               REMARK VARCHAR2(200),
                                               CREATE_BY VARCHAR2(64),
                                               CREATE_TIME DATE,
                                               UPDATE_BY VARCHAR2(64),
                                               UPDATE_TIME DATE,
                                               PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_P_TEMPLATE_P IS '点位模版点位表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.TEMPLATE_ID IS '模板id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.point_name IS '点位名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.num IS '数量';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.cycle IS '最小周期';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.STANDARD IS '作业标准';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_P.UPDATE_TIME IS '更新时间';

CREATE TABLE CT_TMOS_EQUIPMENT_P_TEMPLATE_S(
                                               ID VARCHAR2(64) NOT NULL,
                                               TEMPLATE_ID VARCHAR2(64) NOT NULL,
                                               point_id VARCHAR2(64) NOT NULL,
                                               stuff_no VARCHAR2(64) NOT NULL,
                                               add_plan NUMBER(1),
                                               life NUMBER(5),
                                               warning NUMBER(5),
                                               STATUS VARCHAR2(10) DEFAULT  '0' NOT NULL,
                                               REMARK VARCHAR2(200),
                                               CREATE_BY VARCHAR2(64),
                                               CREATE_TIME DATE,
                                               UPDATE_BY VARCHAR2(64),
                                               UPDATE_TIME DATE,
                                               PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_P_TEMPLATE_S IS '点位模版点位物料表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.TEMPLATE_ID IS '模板id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.point_id IS '点位id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.stuff_no IS '料号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.add_plan IS '是否自动加入计划：0=不加入，1=加入';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.life IS '寿命/天';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.warning IS '预警设定/天';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_P_TEMPLATE_S.UPDATE_TIME IS '更新时间';

CREATE TABLE CT_TMOS_EQUIPMENT_ORDER_P(
                                          ID VARCHAR2(64) NOT NULL,
                                          ORDER_NO VARCHAR2(32) NOT NULL,
                                          UNIT_ID VARCHAR2(32) NOT NULL,
                                          SUB_UNIT_ID VARCHAR2(32),
                                          UPKEEP_RESULT VARCHAR2(5) DEFAULT  '0',
                                          UPKEEP_USER_CODE VARCHAR2(64),
                                          UPKEEP_USER_NAME VARCHAR2(32),
                                          point_id VARCHAR2(64) NOT NULL,
                                          point_name VARCHAR2(100),
                                          MIN_CYCLE VARCHAR2(10),
                                          life NUMBER(5),
                                          num NUMBER(10),
                                          stuff_no VARCHAR2(64),
                                          single_goods_id VARCHAR2(64),
                                          expect_replace_time DATE,
                                          STATUS VARCHAR2(10) DEFAULT  '0' NOT NULL,
                                          REMARK VARCHAR2(200),
                                          CREATE_BY VARCHAR2(64),
                                          CREATE_TIME DATE,
                                          UPDATE_BY VARCHAR2(64),
                                          UPDATE_TIME DATE,
                                          PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_ORDER_P IS '保养工单点位列表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.ORDER_NO IS '工单号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.UNIT_ID IS '设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.SUB_UNIT_ID IS '子设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.UPKEEP_RESULT IS '保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.UPKEEP_USER_CODE IS '保养人code';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.UPKEEP_USER_NAME IS '保养人名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.point_id IS '点位id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.point_name IS '点位名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.MIN_CYCLE IS '最小周期';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.life IS '寿命/天';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.num IS '数量';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.stuff_no IS '料号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.single_goods_id IS '单品id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.expect_replace_time IS '预计更换时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_ORDER_P.UPDATE_TIME IS '更新时间';

CREATE TABLE CT_TMOS_EQUIPMENT_INCOMPLETE_P(
                                               ID VARCHAR2(64) NOT NULL,
                                               ORDER_NO VARCHAR2(32),
                                               FACTORY_NAME VARCHAR2(32) NOT NULL,
                                               AREA VARCHAR2(32) NOT NULL,
                                               EQP_ID VARCHAR2(32) NOT NULL,
                                               UNIT_ID VARCHAR2(32),
                                               SUB_UNIT_ID VARCHAR2(32),
                                               CYCLE VARCHAR2(10),
                                               point_id VARCHAR2(64) NOT NULL,
                                               point_name VARCHAR2(100),
                                               UPKEEP_RESULT VARCHAR2(5) DEFAULT  '0',
                                               PLAN_TIME DATE,
                                               REAL_TIME DATE,
                                               LOCK_STATUS VARCHAR2(10) DEFAULT  '0'  NOT NULL,
                                               STATUS VARCHAR2(10) DEFAULT  '0'  NOT NULL,
                                               REMARK VARCHAR2(200),
                                               CREATE_BY VARCHAR2(64),
                                               CREATE_TIME DATE,
                                               UPDATE_BY VARCHAR2(64),
                                               UPDATE_TIME DATE,
                                               PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_INCOMPLETE_P IS '点位残件表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.ORDER_NO IS '工单号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.FACTORY_NAME IS '厂别';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.AREA IS '设备群组';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.EQP_ID IS '线体';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.UNIT_ID IS '设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.SUB_UNIT_ID IS '子设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.CYCLE IS '周期';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.point_id IS '点位id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.point_name IS '点位名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.UPKEEP_RESULT IS '保养结果：0=待保养，1=完成，2=未保养，3=残件，4=寿命上限，5=FDC，9=保养失败';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.PLAN_TIME IS '原计划日期';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.REAL_TIME IS '实际日期';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.LOCK_STATUS IS '锁定状态：0=待记录，1=审批中，2=执行中';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.UPDATE_TIME IS '更新时间';

CREATE TABLE CT_TMOS_EQUIPMENT_UPKEEP_P(
                                           ID VARCHAR2(64) NOT NULL,
                                           TEMP_UPKEEP_ID VARCHAR2(64) NOT NULL,
                                           FACTORY_NAME VARCHAR2(32) NOT NULL,
                                           AREA VARCHAR2(32) NOT NULL,
                                           EQP_ID VARCHAR2(32) NOT NULL,
                                           UNIT_ID VARCHAR2(32),
                                           SUB_UNIT_ID VARCHAR2(32),
                                           point_id VARCHAR2(64) NOT NULL,
                                           point_name VARCHAR2(100),
                                           num NUMBER(10),
                                           STATUS VARCHAR2(10) DEFAULT  '0'  NOT NULL,
                                           REMARK VARCHAR2(200),
                                           CREATE_BY VARCHAR2(64),
                                           CREATE_TIME DATE,
                                           UPDATE_BY VARCHAR2(64),
                                           UPDATE_TIME DATE,
                                           PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_UPKEEP_P IS '设备临时保养点位记录表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.TEMP_UPKEEP_ID IS '临时保养id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.FACTORY_NAME IS '厂别';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.AREA IS '设备群组';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.EQP_ID IS '线体';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.UNIT_ID IS '设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.SUB_UNIT_ID IS '子设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.point_id IS '点位id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.point_name IS '点位名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.num IS '数量';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_UPKEEP_P.UPDATE_TIME IS '更新时间';

-- modify 2024-8-21
ALTER TABLE CT_TMOS_EQUIPMENT_P_TEMPLATE_S DROP COLUMN TEMPLATE_ID;

-- modify 2024-8-22
DROP TABLE CT_TMOS_EQUIPMENT_STUFF_POINT;
ALTER TABLE CT_TMOS_EQUIPMENT_P_TEMPLATE DROP COLUMN UNIT_ID;
ALTER TABLE CT_TMOS_EQUIPMENT_SUB_UNIT ADD DATA_SOURCE VARCHAR2(64) DEFAULT 'ITEM';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_SUB_UNIT.DATA_SOURCE IS '模板来源:ITEM=保养项目，POINT=点位';
ALTER TABLE CT_TMOS_EQUIPMENT_SPARES_MONIT DROP COLUMN EXPECT_REPLACE_TIME;

-- modify 2024-8-23
ALTER TABLE CT_TMOS_EQUIPMENT_INCOMPLETE_P ADD BORROW_NO VARCHAR2(64) NULL;
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_INCOMPLETE_P.BORROW_NO IS '借机单号';