package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-08-13
 */
@RestController
@Api(tags = "文件管理")
@RequestMapping("/equipment/equipmentFile")
public class EquipmentFileController {

    @ApiOperation(value = "附件下载")
    @GetMapping("/downloadFileById/{id}")
    public void downloadFileById(@PathVariable String id, HttpServletResponse response){
    }

    @ApiOperation(value = "附件删除")
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable String id){

    }
}
