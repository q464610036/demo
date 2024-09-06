
CREATE TABLE CT_TMOS_EQUIPMENT_EXECEPTION(
                                             ID VARCHAR2(64) NOT NULL,
                                             EXECEPTION_NO VARCHAR2(32) NOT NULL,
                                             FACTORY_NAME VARCHAR2(32) NOT NULL,
                                             AREA VARCHAR2(32) NOT NULL,
                                             EQP_ID VARCHAR2(32) NOT NULL,
                                             UNIT_ID VARCHAR2(32),
                                             SUB_UNIT_ID VARCHAR2(32),
                                             START_TIME DATE,
                                             END_TIME DATE,
                                             EXECEPTION_TYPE VARCHAR2(10) DEFAULT  '1',
                                             REASON VARCHAR2(500),
                                             excessive_hours NUMBER(6,1),
                                             EXECEPTION_3_STAGE VARCHAR2(20),
                                             EXECEPTION_4_STAGE VARCHAR2(20),
                                             temp_strategy VARCHAR2(500),
                                             long_strategy VARCHAR2(500),
                                             upkeep_flag NUMBER(1),
                                             UPKEEP_ORDER_NO VARCHAR2(32),
                                             HANDLE_USER_CODE VARCHAR2(64),
                                             HANDLE_USER_NAME VARCHAR2(32),
                                             APPROVE_STATUS VARCHAR2(5) DEFAULT  '01',
                                             APPROVE_TIME DATE,
                                             LEADER_USER_CODE VARCHAR2(64),
                                             LEADER_USER_NAME VARCHAR2(32),
                                             PRODUCT_PLAN_USER_CODE VARCHAR2(64),
                                             PRODUCT_PLAN_USER_NAME VARCHAR2(32),
                                             STATUS VARCHAR2(10) DEFAULT  '0'  NOT NULL,
                                             REMARK VARCHAR2(200),
                                             CREATE_BY VARCHAR2(64),
                                             CREATE_TIME DATE,
                                             UPDATE_BY VARCHAR2(64),
                                             UPDATE_TIME DATE,
                                             PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_EXECEPTION IS '设备异常单表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.EXECEPTION_NO IS '异常单号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.FACTORY_NAME IS '厂别';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.AREA IS '设备群组';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.EQP_ID IS '线体';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.UNIT_ID IS '设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.SUB_UNIT_ID IS '子设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.START_TIME IS '发生开始时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.END_TIME IS '发生结束时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.EXECEPTION_TYPE IS '异常类型：1=一般异常，2=重大异常';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.REASON IS '原因';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.excessive_hours IS '超标值，单位h';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.EXECEPTION_3_STAGE IS '异常3阶';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.EXECEPTION_4_STAGE IS '异常4阶';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.temp_strategy IS '临时对策';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.long_strategy IS '长期对策';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.upkeep_flag IS '是否进行了设备保养或备件更换：0=否，1=是';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.UPKEEP_ORDER_NO IS '保养工单号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.HANDLE_USER_CODE IS '处理人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.HANDLE_USER_NAME IS '处理人名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.APPROVE_STATUS IS '审核状态：01=待处理，02=主管审核，03=生产审核，04=加签审核，05=结束，06=驳回';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.APPROVE_TIME IS '审批时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.LEADER_USER_CODE IS '主管用户code';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.LEADER_USER_NAME IS '主管用户名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.PRODUCT_PLAN_USER_CODE IS '生产计划用户code';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.PRODUCT_PLAN_USER_NAME IS '生产计划用户名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXECEPTION.UPDATE_TIME IS '更新时间';
