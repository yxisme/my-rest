package cn.yxisme.myrest.core.web;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangxiong on 2019/3/11.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        Admin admin = (Admin) request.getSession().getAttribute(GlobalHandler.SESSION_ADMIN);
//        if (admin == null || admin.getId() == 0) {
//            throw new MyException(CodeMessageDef.USER_NOT_LOGGED_IN);
//        }
        return true;
    }
}