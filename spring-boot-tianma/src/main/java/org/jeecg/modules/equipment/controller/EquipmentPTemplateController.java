package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.common.page.PageInfo;
import org.jeecg.modules.equipment.dto.*;
import org.jeecg.modules.equipment.vo.EquipmentPTemplateDetailVo;
import org.jeecg.modules.equipment.vo.EquipmentPTemplateListVo;
import org.jeecg.modules.equipment.vo.EquipmentPTemplatePByUnitVo;
import org.jeecg.modules.equipment.vo.EquipmentPTemplatePVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 点位模版表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@RestController
@Api(tags = "设备保养-点位模版管理")
@RequestMapping("/equipment/equipmentPTemplate")
public class EquipmentPTemplateController {
    @ApiOperation("查询模版分页")
    @PostMapping("/getPage")
    public Result<PageInfo<EquipmentPTemplateListVo>> getPage(@RequestBody @Validated EquipmentPTemplateQueryPageDto dto){
        return null;
    }

    @ApiOperation("查询模版列表")
    @PostMapping("/getList")
    public Result<List<EquipmentPTemplateListVo>> getList(@RequestBody @Validated EquipmentPTemplateQueryListDto dto){
        return null;
    }

    @ApiOperation("保存模版")
    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated({EquipmentPTemplateSaveDto.ICreate.class}) EquipmentPTemplateSaveDto dto){
        return null;
    }

    @ApiOperation(value = "查询点位(根据设备查询)")
    @PostMapping("/getPointListByUnit")
    public Result<List<EquipmentPTemplatePByUnitVo>> getPointListByUnit(@RequestBody @Validated EquipmentPointQueryDto dto){
        return null;
    }

    @ApiOperation(value = "查询点位(根据模板查询)")
    @GetMapping("/getPointListByUnitById/{id}")
    public Result<List<EquipmentPTemplatePVo>> getPointListByUnitById(@PathVariable String id){
        return null;
    }

    @ApiOperation(value = "查询点位(根据料号查询)")
    @PostMapping("/getPointListByStuffNo")
    public Result<List<EquipmentPTemplatePVo>> getPointListByStuffNo(@RequestBody @Validated EquipmentPTemplatePQueryDto dto){
        return null;
    }

    @ApiOperation("查询模版详情")
    @GetMapping("/detail/{id}")
    public Result<EquipmentPTemplateDetailVo> detail(@PathVariable String id){
        return null;
    }

    @ApiOperation("删除模版")
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody @Validated DeleteDto dto){
        return null;
    }

    @ApiOperation("解绑关系组")
    @PostMapping("/unBindingGroup")
    public Result<Boolean> unBindingGroup(@RequestBody @Validated EquipmentTemplateBindingGroupDto dto){
        return null;
    }



    @ApiOperation("模版名称重复性校验")
    @PostMapping("/validateTemplateName")
    public Result<Boolean> validateTemplateName(@RequestBody @Validated EquipmentTemplateValidateTemplateNameDto dto){
        return null;
    }

//    @ApiOperation("点位名称重复性校验")
//    @PostMapping("/validatePointName")
//    public Result<Boolean> validatePointName(@RequestBody @Validated ValidatePointNameDto dto){
//        return null;
//    }

    @ApiOperation("修改模版")
    @PostMapping("/update")
    public Result<String> update(@RequestBody @Validated({EquipmentPTemplateSaveDto.IUpdate.class}) EquipmentPTemplateSaveDto dto){
        return null;
    }

    @ApiOperation("引用模版")
    @GetMapping ("/reference/{id}")
    public Result<EquipmentPTemplateDetailVo> reference(@PathVariable String id){
        return null;
    }
}
