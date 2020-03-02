package cn.yxisme.myrest.service.impl;

import cn.yxisme.myrest.dao.AdminDao;
import cn.yxisme.myrest.entity.Admin;
import cn.yxisme.myrest.exception.CodeMessageDef;
import cn.yxisme.myrest.exception.MyException;
import cn.yxisme.myrest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    AdminDao adminDao;

    @Override
    public Admin checkLogin(String username, String password) {
        Admin adminInDB = adminDao.selectByUsername(username);
        if (adminInDB == null) {
            throw new MyException(CodeMessageDef.USER_NAME_ERROR);
        }

        if (StringUtils.isEmpty(adminInDB.getPassword())
                || !adminInDB.getPassword().equals(password)) {
            throw new MyException(CodeMessageDef.USER_PASSWORD_ERROR);
        }

        return adminInDB;
    }
}
