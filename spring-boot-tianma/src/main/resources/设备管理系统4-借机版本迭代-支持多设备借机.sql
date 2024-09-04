-- modify 2024-9-3
ALTER TABLE ct_tmos_equipment_borrow ADD BORROW_USER_PHONE VARCHAR2(30) NULL;
COMMENT ON COLUMN ct_tmos_equipment_borrow.BORROW_USER_PHONE IS '申请人联系方式';

ALTER TABLE ct_tmos_equipment_borrow_unit ADD sub_unit_id VARCHAR2(500) NULL;
COMMENT ON COLUMN ct_tmos_equipment_borrow_unit.sub_unit_id IS '子设备，多个用逗号隔开';

ALTER TABLE ct_tmos_equipment_borrow MODIFY unit_id VARCHAR2(500) NULL;
COMMENT ON COLUMN ct_tmos_equipment_borrow.unit_id IS '设备，多个用逗号隔开';

ALTER TABLE ct_tmos_equipment_order MODIFY unit_id VARCHAR2(500) NULL;
COMMENT ON COLUMN ct_tmos_equipment_order.unit_id IS '设备，多个用逗号隔开';

ALTER TABLE CT_TMOS_EQUIPMENT_TEMP_UPKEEP MODIFY unit_id VARCHAR2(500) NULL;
COMMENT ON COLUMN CT_TMOS_EQUIPMENT_TEMP_UPKEEP.unit_id IS '设备，多个用逗号隔开';