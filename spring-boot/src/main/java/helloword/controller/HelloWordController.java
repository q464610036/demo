package helloword.controller;

import helloword.dto.UserQueryDto;
import helloword.vo.ResultUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloWordController {
    @PostMapping("/hello")
    public Object test(@Validated @RequestBody UserQueryDto dto){
        return ResultUtil.isSuccess(dto);
    }
}
