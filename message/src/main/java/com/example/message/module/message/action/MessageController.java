package com.example.message.module.message.action;

import com.example.message.module.message.dto.MessageSendDTO;
import com.example.message.module.message.service.IMessageService;
import com.example.message.module.message.vo.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 * 文件与文件夹表 前端控制器
 * </p>
 *
 * @author chenmengfei
 * @since 2023-04-23
 */

@RestController
@RequestMapping("/api/message/message/")
public class MessageController {

    @Resource
    private IMessageService messageService;

    @PostMapping("v1/send")
    public R<Boolean> send(@Validated @RequestBody MessageSendDTO dto) throws Exception {
        messageService.send(dto);
        return R.status(true);
    }
}

