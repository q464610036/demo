package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.common.page.PageInfo;
import org.jeecg.modules.equipment.dto.EquipmentTemplateQueryPageDto;
import org.jeecg.modules.equipment.dto.EquipmentTemplateSaveDto;
import org.jeecg.modules.equipment.vo.EquipmentTemplateDetailVo;
import org.jeecg.modules.equipment.vo.EquipmentTemplateListVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 保养模版表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Controller
@Api(tags = "设备保养-模版管理")
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
}
