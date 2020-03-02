package cn.yxisme.myrest.core.web;

import cn.yxisme.myrest.common.ResultBean;
import cn.yxisme.myrest.exception.CodeMessageDef;
import cn.yxisme.myrest.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * Created by yangxiong on 2020/1/15.
 */
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

    /**
     * 逻辑和系统异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    protected Object ExceptionHandler(Exception ex) {
        logger.error("", ex);
        ex.printStackTrace();

        // 自定义逻辑异常
        if (ex instanceof MyException) {
            return new ResultBean((MyException) ex);
        }

        // security登录异常
        if (ex instanceof BadCredentialsException) {
            MyException myException = new MyException(CodeMessageDef.USER_LOGIN_ERROR);
            return new ResultBean(myException);
        }

        // 系统异常
        return ResultBean.systemError();
    }

    /**
     * Bean Validate异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    protected Object MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        logger.error("", ex);
        ex.printStackTrace();

        String message = ex.getBindingResult().getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));

        return ResultBean.validError(message);
    }

    /**
     * Method Validate异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    protected Object ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        logger.error("", ex);
        ex.printStackTrace();
        String message = ex.getMessage();
        if (message.contains(":")) {
            message = message.split(":")[1].trim();
        }
        return ResultBean.validError(message);
    }
}
