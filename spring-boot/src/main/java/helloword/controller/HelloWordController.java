package helloword.controller;

import helloword.dto.UserQueryDto;
import helloword.vo.ResultUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloWordController {
    @PostMapping("/hello")
    public Object hello(@Validated @RequestBody UserQueryDto dto){
        return ResultUtil.isSuccess(dto);
    }

    @GetMapping("/test")
    public void test(){

        while(true){
            System.out.println(1);
        }
    }
}
