package cn.yxisme.myrest.controller;

import cn.yxisme.myrest.common.ResultBean;
import cn.yxisme.myrest.context.Context;
import cn.yxisme.myrest.controller.bean.user.AdminLoginBean;
import cn.yxisme.myrest.core.web.GlobalHandler;
import cn.yxisme.myrest.core.web.JwtTokenUtil;
import cn.yxisme.myrest.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/auth")
public class AuthController extends GlobalHandler {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService adminUserDetailService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    Context context;

    /**
     * 登录
     * @param bean
     * @return ResultBean
     */
    @PostMapping(value = "/login")
    public Object login(@RequestBody @Validated({AdminLoginBean.LoginValid.class}) AdminLoginBean bean) {
        UsernamePasswordAuthenticationToken upToken =
                new UsernamePasswordAuthenticationToken(bean.getUsername(), bean.getPassword());
        Authentication authenticate = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetails userDetails = adminUserDetailService.loadUserByUsername(bean.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new ResultBean(token);
    }

    /**
     * 登出
     * @return ResultBean
     */
    @PostMapping(value = "/logout")
    public Object logout() {
        return new ResultBean();
    }

    /**
     * 获取当前登录的用户
     * @return ResultBean
     */
    @GetMapping(value = "/getUser")
    public Object getSessionUser() {
        Admin admin = context.adminService.getById(getAdminId());
        return new ResultBean(admin);
    }
}
