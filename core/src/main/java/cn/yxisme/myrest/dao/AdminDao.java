package cn.yxisme.myrest.dao;

import cn.yxisme.myrest.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao extends BaseMapper<Admin> {

    @Select("select * from t_admin where username = #{username}")
    Admin selectByUsername(String username);
}