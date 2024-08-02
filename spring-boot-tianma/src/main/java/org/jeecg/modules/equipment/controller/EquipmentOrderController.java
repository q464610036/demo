package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.common.page.PageInfo;
import org.jeecg.modules.equipment.dto.EquipmentOrderApproveDto;
import org.jeecg.modules.equipment.dto.EquipmentOrderQueryPageDto;
import org.jeecg.modules.equipment.dto.EquipmentSaveUpkeepResultDto;
import org.jeecg.modules.equipment.service.IEquipmentOrderService;
import org.jeecg.modules.equipment.vo.EquipmentOrderDetailVo;
import org.jeecg.modules.equipment.vo.EquipmentOrderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 保养工单表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@RestController
@Api(tags = "设备保养-工单管理")
@RequestMapping("/equipment/equipmentOrder")
public class EquipmentOrderController {

    @Autowired
    private IEquipmentOrderService equipmentOrderService;

    @ApiOperation("查询分页")
    @PostMapping("/getPage")
    public Result<PageInfo<EquipmentOrderListVo>> getPage(@RequestBody @Validated EquipmentOrderQueryPageDto dto){
        return null;
    }

    @ApiOperation(value = "查询详情", notes = "非待审批单详情入口")
    @GetMapping("/detail/{id}")
    public Result<EquipmentOrderDetailVo> detail(@PathVariable String id){
        return null;
    }

    @ApiOperation(value = "查询审批详情", notes = "待审批单详情入口")
    @GetMapping("/approveDetail/{id}")
    public Result<EquipmentOrderDetailVo> approveDetail(@PathVariable String id){
        return null;
    }

    @ApiOperation("审批")
    @PostMapping("/approve")
    public Result<Boolean> approve(@RequestBody @Validated EquipmentOrderApproveDto dto){
        equipmentOrderService.approve(dto);
        return Result.ok(true);
    }

    @ApiOperation("保养结果暂存")
    @PostMapping("/saveResult")
    public Result<Boolean> saveResult(@RequestBody @Validated EquipmentSaveUpkeepResultDto dto){
        equipmentOrderService.saveResult(dto);
        return Result.ok(true);
    }

    @ApiOperation("保养结果提交")
    @PostMapping("/submitResult")
    public Result<Boolean> submitResult(@RequestBody @Validated EquipmentSaveUpkeepResultDto dto){
        equipmentOrderService.submitResult(dto);
        return Result.ok(true);
    }
}
