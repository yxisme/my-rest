package cn.yxisme.myrest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by yangxiong on 2020/1/6.
 */
@Data
@TableName(value = "t_role")
public class Role implements GrantedAuthority {
    private Integer id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
