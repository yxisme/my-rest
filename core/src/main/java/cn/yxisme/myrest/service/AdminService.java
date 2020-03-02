package cn.yxisme.myrest.service;

import cn.yxisme.myrest.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Created by yangxiong on 2019/3/19.
 */
public interface AdminService extends IService<Admin> {
    Admin getByUsername(String username);
}
