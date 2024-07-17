package com.example.springbootswagger.controller;

import com.example.springbootswagger.page.PageInfo;
import com.example.springbootswagger.vo.Result;
import com.example.springbootswagger.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "欢迎模块")
public class HelloWordController {

    @GetMapping("/helloWord")
    @ApiOperation("你好接口")
    public Result<PageInfo<User>> helloWord(){

        return null;
    }
}
