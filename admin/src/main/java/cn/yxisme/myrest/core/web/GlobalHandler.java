package cn.yxisme.myrest.core.web;

import cn.yxisme.myrest.entity.Admin;
import cn.yxisme.myrest.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by yangxiong on 2019/3/5.
 */
public class GlobalHandler extends GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

    public static Authentication getCurrentUserAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前登录用户ID
     * @return
     * @throws MyException
     */
    public int getAdminId() throws MyException {
        return ((Admin) getCurrentUserAuthentication().getPrincipal()).getId();
    }
}
