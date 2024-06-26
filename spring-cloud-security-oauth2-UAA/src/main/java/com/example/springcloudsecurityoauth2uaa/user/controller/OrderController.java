package com.example.springcloudsecurityoauth2uaa.user.controller;

import com.example.springcloudsecurityoauth2uaa.util.ResultUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ORDER_ADD')")
    public Object add() {
        return ResultUtil.isSuccess(true);
    }
}
