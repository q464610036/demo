package org.jeecg.modules.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.common.entity.ServiceException;
import org.jeecg.modules.common.entity.LoginUser;
import org.jeecg.modules.common.enums.OrderApproveStatusEnum;
import org.jeecg.modules.common.enums.OrderOperateStatusEnum;
import org.jeecg.modules.common.enums.OrderTypeEnum;
import org.jeecg.modules.equipment.dto.EquipmentOrderApproveDto;
import org.jeecg.modules.equipment.dto.EquipmentSaveUpkeepResultDto;
import org.jeecg.modules.equipment.entity.EquipmentOrder;
import org.jeecg.modules.equipment.entity.EquipmentOrderNode;
import org.jeecg.modules.equipment.mapper.EquipmentOrderMapper;
import org.jeecg.modules.equipment.service.IEquipmentOrderNodeService;
import org.jeecg.modules.equipment.service.IEquipmentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 保养工单表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Service
public class EquipmentOrderServiceImpl extends ServiceImpl<EquipmentOrderMapper, EquipmentOrder> implements IEquipmentOrderService {

    @Autowired
    private IEquipmentOrderNodeService equipmentOrderNodeService;

    @Override
    public void approve(EquipmentOrderApproveDto dto) {
        /**
         * 生产计划：同意，节点显示”通过“，发MES工单，工单结束
         * 生产计划：取消保养，节点显示”取消保养“，设备担当审核
         * 设备担当：确定取消，节点显示”确定取消“，工单结束
         * 设备担当：驳回，节点显示”驳回“，返回生产计划
         */
        //TODO 查询工单
        EquipmentOrder equipmentOrder = null;

        //TODO 查询所有节点

        //TODO 获取当前审批节点
        EquipmentOrderNode currentNode = null;
        //权限校验
        if (this.validateApprove()) {
            throw new ServiceException("您没有审批权限");
        }
        //TODO chenmengfei 修改节点
        EquipmentOrderNode equipmentOrderNode = new EquipmentOrderNode();
        equipmentOrderNode.setAdvice(dto.getAdvice());
//        equipmentOrderNode.setOperateStage();
        equipmentOrderNode.setOperateStatus(dto.getOperateStatus());
        equipmentOrderNode.setId(currentNode.getId());
        equipmentOrderNodeService.updateById(equipmentOrderNode);
        String approveStatus = null;
        String orderType = null;
        //审核完成标志位
        boolean approveFlag = false;
        if (dto.getOperateStatus().equals(OrderOperateStatusEnum.CANCEL.getCode())) {
            //取消保养
            //TODO chenmengfei 生成设备担当审批节点，查询每一个设备的设备担当，需要去重



            //设置主表审批状态，包含工单类型（保养单，取消保养单）
            approveStatus = OrderApproveStatusEnum.UNIT.getCode();
            orderType = OrderTypeEnum.CANCEL_UPKEEP.getCode();
        } else if (dto.getOperateStatus().equals(OrderOperateStatusEnum.APPROVE.getCode())){
            //通过
            //TODO chenmengfei 设置主表审批状态，包含工单类型（保养单，取消保养单）

            //TODO chenmengfei 发送MES保养工单
        } else if (dto.getOperateStatus().equals(OrderOperateStatusEnum.CONFIRM_CANCEL.getCode())){
            //确定取消
            //TODO chenmengfei 如果没有下一节点，工单结束

            //TODO chenmengfei 设置主表审批状态
        } else if (dto.getOperateStatus().equals(OrderOperateStatusEnum.DENY_CANCEL.getCode())){
            //不同意取消
            //TODO chenmengfei 生成生产计划节点

            //TODO chenmengfei 设置主表审批状态
        } else {
            throw new ServiceException("未知的操作状态："+dto.getOperateStatus());
        }
        //TODO chenmengfei 修改主表

        this.updateById(equipmentOrder);
    }

    @Override
    public boolean updateById(EquipmentOrder entity){
        this.buildUpdateEntity(entity);
        return super.updateById(entity);
    }

    private void buildUpdateEntity(EquipmentOrder entity){
        LoginUser login = null;
        entity.setUpdateBy(login.getId());
        entity.setUpdateTime(new Date());
    }

    private boolean validateApprove(){
        boolean flag = true;
        return flag;
    }

    @Override
    public void submitResult(EquipmentSaveUpkeepResultDto dto){
        //TODO chenmengfei 修改保养项目的保养结果
        this.saveResult(dto);
        //TODO chenmengfei 修改生产计划的计划状态，实际结束时间

    }

    @Override
    public void saveResult(EquipmentSaveUpkeepResultDto dto){
        //TODO chenmengfei 查询工单状态，不是待填报不允许填报

        //TODO chenmengfei 修改保养项目的保养结果
    }
}
