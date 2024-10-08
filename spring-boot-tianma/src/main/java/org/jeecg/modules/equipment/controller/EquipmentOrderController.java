package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.common.page.PageInfo;
import org.jeecg.modules.equipment.dto.*;
import org.jeecg.modules.equipment.service.IEquipmentOrderService;
import org.jeecg.modules.equipment.vo.EquipmentOrderBaseDataVo;
import org.jeecg.modules.equipment.vo.EquipmentOrderDetailVo;
import org.jeecg.modules.equipment.vo.EquipmentOrderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("修改")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Validated EquipmentOrderUpdateDto dto){
        return Result.ok(true);
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

    @ApiOperation(value = "创建补录保养工单")
    @PostMapping("/addSupplement")
    public Result<String> addSupplement(@RequestBody @Validated EquipmentOrderSaveDto dto){
        return null;
    }

    @ApiOperation(value = "保养结果补录")
    @PostMapping("/supplementResult")
    public Result<Boolean> supplementResult(@RequestBody @Validated EquipmentSaveUpkeepResultDto dto){
        return null;
    }

    @ApiOperation(value = "查询基础数据列表")
    @GetMapping("/getBaseData")
    public Result<EquipmentOrderBaseDataVo> getBaseData(){
        return null;
    }

    @ApiOperation(value = "查询根据料号查询库存单品")
    @GetMapping("/getSingleGoodsIdList/{stuffNo}")
    public Result<List<String>> getSingleGoodsIdList(String stuffNo){
        return null;
    }

    @ApiOperation(value = "查询根据料号查询库存单品（可排除指定单品）")
    @PostMapping("/getDeptSingleGoodsIdList")
    public Result<List<String>> getDeptSingleGoodsIdList(EquipmentStuffQueryDto dto){
        return null;
    }
}
