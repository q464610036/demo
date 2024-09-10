
CREATE TABLE CT_TMOS_EQUIPMENT_EXCEPTION(
                                            ID VARCHAR2(64) NOT NULL,
                                            EXCEPTION_NO VARCHAR2(32) NOT NULL,
                                            FACTORY_NAME VARCHAR2(32) NOT NULL,
                                            AREA VARCHAR2(32) NOT NULL,
                                            EQP_ID VARCHAR2(32) NOT NULL,
                                            UNIT_ID VARCHAR2(32),
                                            SUB_UNIT_ID VARCHAR2(32),
                                            START_TIME DATE,
                                            END_TIME DATE,
                                            EXCEPTION_TYPE VARCHAR2(10) DEFAULT  '1',
                                            REASON VARCHAR2(500),
                                            excessive_hours NUMBER(6,1),
                                            EXCEPTION_3_STAGE VARCHAR2(300),
                                            EXCEPTION_4_STAGE VARCHAR2(300),
                                            temp_strategy VARCHAR2(500),
                                            long_strategy VARCHAR2(500),
                                            upkeep_flag NUMBER(1),
                                            UPKEEP_ORDER_NO VARCHAR2(32),
                                            PROCESS_USER_CODE VARCHAR2(64),
                                            PROCESS_USER_NAME VARCHAR2(32),
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

COMMENT ON TABLE CT_TMOS_EQUIPMENT_EXCEPTION IS '设备异常单表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.EXCEPTION_NO IS '异常单号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.FACTORY_NAME IS '厂别';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.AREA IS '设备群组';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.EQP_ID IS '线体';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.UNIT_ID IS '设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.SUB_UNIT_ID IS '子设备';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.START_TIME IS '发生开始时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.END_TIME IS '发生结束时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.EXCEPTION_TYPE IS '异常类型：1=一般异常，2=重大异常';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.REASON IS '原因';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.excessive_hours IS '超标值，单位h';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.EXCEPTION_3_STAGE IS '异常3阶';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.EXCEPTION_4_STAGE IS '异常4阶';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.temp_strategy IS '临时对策';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.long_strategy IS '长期对策';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.upkeep_flag IS '是否进行了设备保养或备件更换：0=否，1=是';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.UPKEEP_ORDER_NO IS '保养工单号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.PROCESS_USER_CODE IS '处理人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.PROCESS_USER_NAME IS '处理人名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.APPROVE_STATUS IS '审核状态：01=待处理，02=主管审核，03=生产审核，04=加签审核，05=结束，06=驳回';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.APPROVE_TIME IS '审批时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.LEADER_USER_CODE IS '主管用户code';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.LEADER_USER_NAME IS '主管用户名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.PRODUCT_PLAN_USER_CODE IS '生产计划用户code';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.PRODUCT_PLAN_USER_NAME IS '生产计划用户名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXCEPTION.UPDATE_TIME IS '更新时间';


CREATE TABLE CT_TMOS_EQUIPMENT_EXC_USER(
                                           ID VARCHAR2(64) NOT NULL,
                                           EXCEPTION_NO VARCHAR2(32) NOT NULL,
                                           USER_TYPE NUMBER(1) NOT NULL,
                                           USER_CODE VARCHAR2(64) NOT NULL,
                                           USER_NAME VARCHAR2(32) NOT NULL,
                                           INDEX_NO NUMBER(2) NOT NULL,
                                           STATUS VARCHAR2(10) DEFAULT  '0',
                                           CREATE_BY VARCHAR2(64),
                                           CREATE_TIME DATE,
                                           UPDATE_BY VARCHAR2(64),
                                           UPDATE_TIME DATE DEFAULT  sysdate,
                                           REMARK VARCHAR2(200),
                                           PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_EXC_USER IS '设备异常单用户关联表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.ID IS 'id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.EXCEPTION_NO IS '异常单号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.USER_TYPE IS '用户类型：1=加签';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.USER_CODE IS '用户code';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.USER_NAME IS '用户名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.INDEX_NO IS '序号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.UPDATE_BY IS '修改人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.UPDATE_TIME IS '修改时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_USER.REMARK IS '备注';

CREATE TABLE CT_TMOS_EQUIPMENT_EXC_NODE(
                                           ID VARCHAR2(64) NOT NULL,
                                           EXCEPTION_NO VARCHAR2(32) NOT NULL,
                                           NODE_CODE VARCHAR2(32) NOT NULL,
                                           OPERATE_USER_CODE VARCHAR2(64),
                                           OPERATE_USER_NAME VARCHAR2(32),
                                           OPERATE_TIME DATE,
                                           OPERATE_STATUS VARCHAR2(5),
                                           ADVICE VARCHAR2(300),
                                           OPERATE_STAGE NUMBER(1) DEFAULT  0,
                                           SEQ_NO NUMBER(3),
                                           STATUS VARCHAR2(10) DEFAULT  '0'  NOT NULL,
                                           REMARK VARCHAR2(200),
                                           CREATE_BY VARCHAR2(64),
                                           CREATE_TIME DATE,
                                           UPDATE_BY VARCHAR2(64),
                                           UPDATE_TIME DATE,
                                           PRIMARY KEY (ID)
);

COMMENT ON TABLE CT_TMOS_EQUIPMENT_EXC_NODE IS '设备异常单审批节点表';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.ID IS '主键id';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.EXCEPTION_NO IS '工单号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.NODE_CODE IS '节点code';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.OPERATE_USER_CODE IS '操作人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.OPERATE_USER_NAME IS '操作人名称';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.OPERATE_TIME IS '操作时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.OPERATE_STATUS IS '操作状态 0=提交申请，1=待审批，2=通过，9=不通过';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.ADVICE IS '意见';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.OPERATE_STAGE IS '操作阶段：0=未操作，1=待操作，2=已操作';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.SEQ_NO IS '序号';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.STATUS IS '状态 0=正常，1=删除';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.REMARK IS '备注';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.CREATE_BY IS '创建人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.UPDATE_BY IS '更新人';
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_EXC_NODE.UPDATE_TIME IS '更新时间';

