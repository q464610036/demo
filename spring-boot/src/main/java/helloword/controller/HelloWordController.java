package helloword.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWordController {
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping("/hello")
    public String helloWord(){
        //System.out.println();
        //日志的级别；
        //由低到高trace < debug < info < warn < error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
        return "hello word";
    }
}
