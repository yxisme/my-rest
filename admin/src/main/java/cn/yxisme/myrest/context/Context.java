package cn.yxisme.myrest.context;

import cn.yxisme.myrest.service.AdminService;
import cn.yxisme.myrest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yangxiong on 2019/3/23.
 */
@Component
public class Context {

    @Autowired
    public AdminService adminService;

    @Autowired
    public LoginService loginService;
}
