package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.common.page.PageInfo;
import org.jeecg.modules.equipment.dto.*;
import org.jeecg.modules.equipment.vo.EquipmentAnalysisUnitVo;
import org.jeecg.modules.equipment.vo.EquipmentExceptionDetailVo;
import org.jeecg.modules.equipment.vo.EquipmentExceptionListVo;
import org.jeecg.modules.equipment.vo.EquipmentExceptionVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 设备异常单表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Controller
@Api(tags = "设备异常-异常单管理")
@RequestMapping("/equipment/equipmentException")
public class EquipmentExceptionController {

    @ApiOperation("查询分页")
    @PostMapping("/getPage")
    public Result<PageInfo<EquipmentExceptionListVo>> getPage(@RequestBody @Validated EquipmentExceptionQueryPageDto dto){
        return null;
    }

    @ApiOperation("查询知识库")
    @PostMapping("/getProcessResultPage")
    public Result<PageInfo<EquipmentExceptionListVo>> getProcessResultPage(@RequestBody @Validated EquipmentExceptionProcessResultQueryPageDto dto){
        return null;
    }

    @ApiOperation("查询审核分页")
    @PostMapping("/getApprovePage")
    public Result<PageInfo<EquipmentExceptionListVo>> getApprovePage(@RequestBody @Validated EquipmentExceptionQueryPageDto dto){
        return null;
    }

    @ApiOperation("查询分析图")
    @PostMapping("/getAnalysisList")
    public Result<List<EquipmentAnalysisUnitVo>> getAnalysisList(@RequestBody @Validated EquipmentExceptionAnalysisQueryPageDto dto){
        return null;
    }




    @ApiOperation(value = "查询详情", notes = "非待审批单详情入口")
    @GetMapping("/detail/{id}")
    public Result<EquipmentExceptionDetailVo> detail(@PathVariable String id){
        return null;
    }

    @ApiOperation(value = "查询审批详情", notes = "待审批单详情入口")
    @GetMapping("/approveDetail/{id}")
    public Result<EquipmentExceptionDetailVo> approveDetail(@PathVariable String id){
        return null;
    }

    @ApiOperation(value = "查询详情", notes = "非待审批单详情入口")
    @GetMapping("/detailByNo/{exceptionNo}")
    public Result<EquipmentExceptionDetailVo> detailByNo(@PathVariable String exceptionNo){
        return null;
    }

    @ApiOperation(value = "查询审批详情", notes = "待审批单详情入口")
    @GetMapping("/approveDetailByNo/{exceptionNo}")
    public Result<EquipmentExceptionDetailVo> approveDetailByNo(@PathVariable String exceptionNo){
        return null;
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public Result<EquipmentExceptionVo> add(@RequestBody @Validated EquipmentExceptionSaveDto dto){
        return null;
    }

    @ApiOperation("处理")
    @PostMapping("/process")
    public Result<Boolean> process(@RequestBody @Validated EquipmentExceptionProcessDto dto){
        return Result.ok(true);
    }

    @ApiOperation("审批")
    @PostMapping("/approve")
    public Result<Boolean> approve(@RequestBody @Validated EquipmentExceptionApproveDto dto){
        return Result.ok(true);
    }

    @ApiOperation("撤回处理")
    @PostMapping("/cancel")
    public Result<Boolean> cancel(@RequestBody @Validated EquipmentExceptionCancelDto dto){
        return Result.ok(true);
    }
}
