package cn.yxisme.myrest.common;

import cn.yxisme.myrest.exception.CodeMessage;
import cn.yxisme.myrest.exception.CodeMessageDef;
import cn.yxisme.myrest.exception.MyException;
import org.springframework.util.StringUtils;

/**
 * Created by yangxiong on 2019/3/2.
 */
public class ResultBean {
    private int code;
    private String message;
    private Object data;

    public ResultBean() {
        CodeMessage cm = CodeMessageDef.SUCCESS;
        this.code = cm.getCode();
        this.message = cm.getMsg();
    }

    public ResultBean(Object data) {
        CodeMessage cm = CodeMessageDef.SUCCESS;
        this.code = cm.getCode();
        this.message = cm.getMsg();
        this.data = data;
    }

    public ResultBean(MyException me) {
        this.code = me.getCode();
        this.message = me.getMsg();
    }

    public static ResultBean systemError() {
        CodeMessage cm = CodeMessageDef.SYSTEM_ERROR;
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(cm.getCode());
        resultBean.setMessage(cm.getMsg());
        return resultBean;
    }

    public static ResultBean validError(String message) {
        CodeMessage cm = CodeMessageDef.PARAMETER_ERROR;

        ResultBean resultBean = new ResultBean();
        resultBean.setCode(cm.getCode());

        resultBean.setMessage(StringUtils.isEmpty(message) ? cm.getMsg() : message);
        return resultBean;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
