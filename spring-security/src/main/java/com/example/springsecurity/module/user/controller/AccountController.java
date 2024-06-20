package com.example.springsecurity.module.user.controller;

import com.example.springsecurity.common.util.ResultUtil;
import com.example.springsecurity.module.user.dto.AccountDTO;
import com.example.springsecurity.module.user.dto.AccountDeleteDTO;
import com.example.springsecurity.module.user.service.IAccountService;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenmengfei
 * @since 2024-05-23
 */
@RestController
@RequestMapping("/user/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/add")
    public Object add(@Validated @RequestBody AccountDTO dto) {
        return ResultUtil.isSuccess(accountService.add(dto));
    }

    @GetMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        List<Integer> list = Arrays.asList(id);
        return ResultUtil.isSuccess(accountService.delete(list));
    }

    @GetMapping("/list")
    public Object list() {
        return ResultUtil.isSuccess(accountService.list());
    }

    @GetMapping("/test")
    public Object test() {
        return ResultUtil.isSuccess(true);
    }
}
