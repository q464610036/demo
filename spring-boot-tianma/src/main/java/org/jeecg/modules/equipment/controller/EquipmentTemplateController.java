package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.common.page.PageInfo;
import org.jeecg.modules.equipment.dto.*;
import org.jeecg.modules.equipment.vo.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 保养模版表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@RestController
@Api(tags = "设备保养-项目模版管理")
@RequestMapping("/equipment/equipmentTemplate")
public class EquipmentTemplateController {

    @ApiOperation("查询模版分页")
    @PostMapping("/getPage")
    public Result<PageInfo<EquipmentTemplateListVo>> getPage(@RequestBody @Validated EquipmentTemplateQueryPageDto dto){
        return null;
    }

    @ApiOperation("查询模版列表")
    @PostMapping("/getList")
    public Result<List<EquipmentTemplateListVo>> getList(@RequestBody @Validated EquipmentTemplateQueryPageDto dto){
        return null;
    }

    @ApiOperation("查询模版详情")
    @GetMapping ("/detail/{id}")
    public Result<EquipmentTemplateDetailVo> detail(@PathVariable String id){
        return null;
    }

    @ApiOperation("查询subUnit Type")
    @GetMapping ("/getSubUnitType/{unitType}")
    public Result<List<String>> getSubUnitType(@PathVariable String unitType){
        return null;
    }

    @ApiOperation("保存模版")
    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated({EquipmentTemplateSaveDto.ICreate.class}) EquipmentTemplateSaveDto dto){
        return null;
    }

    @ApiOperation("修改模版")
    @PostMapping("/update")
    public Result<String> update(@RequestBody @Validated({EquipmentTemplateSaveDto.IUpdate.class}) EquipmentTemplateSaveDto dto){
        return null;
    }

    @ApiOperation("删除模版")
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody @Validated DeleteDto dto){
        return null;
    }

    @ApiOperation("引用模版")
    @GetMapping ("/reference/{id}")
    public Result<EquipmentTemplateDetailVo> reference(@PathVariable String id){
        return null;
    }

    @ApiOperation("导入模版项目")
    @PostMapping("/importExcel")
    public Result<Boolean> importExcel(@RequestParam("file") MultipartFile file,
                                       @ApiParam(value = "模板id", required = true) @RequestParam(value = "templateId") String templateId){
        return null;
    }

    @ApiOperation("导入模版项目V2")
    @PostMapping("/importExcelV2")
    public Result<EquipmentItemImportV2Vo> importExcelV2(@RequestParam("file") MultipartFile file){
        return null;
    }


    @ApiOperation("导出模版项目")
    @PostMapping("/exportExcel")
    public void exportExcel(@RequestBody @Validated EquipmentItemExportExcelDto dto, HttpServletResponse response){

    }

    @ApiOperation(value = "查询保养项目(按设备分组)", notes = "保养项目查询菜单用")
    @PostMapping("/getUnitItemList")
    public Result<List<EquipmentPreviewUnitVo>> getUnitItemList(@RequestBody @Validated EquipmentPreviewUnitQueryDto dto){
        return null;
    }

    @ApiOperation(value = "查询保养项目(根据设备查询)", notes = "补录保养项目查询时用")
    @PostMapping("/getItemListByUnit")
    public Result<List<EquipmentItemVo>> getItemListByUnit(@RequestBody @Validated EquipmentItemQueryDto dto){
        return null;
    }

    @ApiOperation(value = "查询保养项目(根据模版查询)", notes = "添加计划外保养项目")
    @PostMapping("/getItemListByTemplateId")
    public Result<List<EquipmentItemVo>> getItemListByTemplateId(@RequestBody @Validated EquipmentItemQueryDto dto){
        return null;
    }

    @ApiOperation("模版名称重复性校验")
    @PostMapping("/validateTemplateName")
    public Result<Boolean> validateTemplateName(@RequestBody @Validated EquipmentTemplateValidateTemplateNameDto dto){
        return null;
    }

    @ApiOperation("解绑关系组")
    @PostMapping("/unBindingGroup")
    public Result<Boolean> unBindingGroup(@RequestBody @Validated EquipmentTemplateBindingGroupDto dto){
        return null;
    }
}
