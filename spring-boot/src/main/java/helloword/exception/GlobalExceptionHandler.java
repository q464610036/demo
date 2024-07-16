package helloword.exception;

import helloword.vo.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
 
/**
 * 描述：  处理统一异常的handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {
 
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 最后的兜底方案：处理【剩余的，没有被其他方法处理的】+【Exception异常，或者继承Exception的异常】；
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        log.error("Default Exception",e);
        return ResultUtil.isFail(100101, "服务内部错误");
    }
 
    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Object handleServiceExceptionException(ServiceException e) {
        return ResultUtil.isFail(100101, e.getMessage());
    }
 
    /**
     * 处理@Valid所引发的，参数校验失败引发的【MethodArgumentNotValidException】异常；
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return handleBindingResult(e.getBindingResult());
 
    }
 
    /**
     * 处理【MethodArgumentNotValidException】异常；提取错误信息，构建ApiRestResponse统一返回对象；
     * @param result
     * @return
     */
    private Object handleBindingResult(BindingResult result) {
        //把【MethodArgumentNotValidException异常】处理为，对应的ApiRestResponse统一返回对象；
        //这儿创建一个List集合；后面我们在MethodArgumentNotValidException中获取的错误信息，都存放在这个集合中去；
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {//如果BindingResult中，包含错误，就获取其中所有的错误信息；
            List<FieldError> allErrors = result.getFieldErrors();
            //遍历所有的错误信息；
            for (int i = 0; i < allErrors.size(); i++) {
                FieldError field = allErrors.get(i);
                String fieldName = field.getField();
                //提取具体的错误信息；
                String message = field.getDefaultMessage();
                //将错误信息，添加到list集合中
                list.add(fieldName + (message == null ? "" : message));
            }
        }
        if (list.size() == 0) {
            return ResultUtil.isFail(100101, "参数异常");
        }
        //根据MethodArgumentNotValidException异常的具体错误信息，构建统一返回对象；
        return ResultUtil.isFail(100101,list.toString());
    }
}