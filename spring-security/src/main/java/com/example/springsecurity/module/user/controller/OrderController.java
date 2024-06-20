package com.example.springsecurity.module.user.controller;

import com.example.springsecurity.common.util.ResultUtil;
import com.example.springsecurity.module.user.dto.AccountDTO;
import com.example.springsecurity.module.user.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/order")
public class OrderController {



    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'admin'"  )
    public Object delete(@RequestParam Integer id) {
        return ResultUtil.isSuccess(true);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public Object list() {
        return ResultUtil.isSuccess(true);
    }
}
