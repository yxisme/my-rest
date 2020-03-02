package cn.yxisme.myrest.service.impl;

import cn.yxisme.myrest.dao.AdminDao;
import cn.yxisme.myrest.entity.Admin;
import cn.yxisme.myrest.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by yangxiong on 2020/1/6.
 */
@Service("adminUserDetailService")
public class AdminUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    AdminDao adminDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminDao.selectByUsername(username);
        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_ADMIN");
        List<Role> roles = Collections.singletonList(role);
        admin.setAuthorities(roles);

        return admin;
    }
}
