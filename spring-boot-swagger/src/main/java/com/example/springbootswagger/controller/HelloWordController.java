package com.example.springbootswagger.controller;

import com.example.springbootswagger.vo.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "欢迎模块")
public class HelloWordController {
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping
    @ApiOperation("你好接口")
    public ResultUtil helloWord(){

        return null;
    }
}
