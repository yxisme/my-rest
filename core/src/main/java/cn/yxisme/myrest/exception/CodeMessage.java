package cn.yxisme.myrest.exception;

/**
 * Created by yangxiong on 2019/3/5.
 */
public class CodeMessage {
    private int code;

    private String msg;

    public CodeMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    protected void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    protected void setMsg(String msg) {
        this.msg = msg;
    }
}
