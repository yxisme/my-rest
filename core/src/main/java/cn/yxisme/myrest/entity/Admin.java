package cn.yxisme.myrest.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * Created by yangxiong on 2019/12/4.
 */
@Data
@TableName(value = "t_admin")
public class Admin implements UserDetails {
    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private String password;
    private String salt;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    @TableField(exist = false)
    private List<Role> authorities;

    @Override
    public List<Role> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
