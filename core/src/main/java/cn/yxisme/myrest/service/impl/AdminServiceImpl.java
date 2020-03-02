package cn.yxisme.myrest.service.impl;

import cn.yxisme.myrest.dao.AdminDao;
import cn.yxisme.myrest.entity.Admin;
import cn.yxisme.myrest.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangxiong on 2019/3/19.
 */
@Service("userService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public Admin getByUsername(String username) {
        return adminDao.selectByUsername(username);
    }
}
