package cn.yxisme.myrest.service;

import cn.yxisme.myrest.entity.Admin;

public interface LoginService {
    Admin checkLogin(String username, String password);
}
