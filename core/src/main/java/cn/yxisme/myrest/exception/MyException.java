package cn.yxisme.myrest.exception;

/**
 * Created by yangxiong on 2019/3/5.
 */
public class MyException extends RuntimeException {

    private int code;
    private String msg;

    public MyException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyException(CodeMessage codeMessage) {
        this.code = codeMessage.getCode();
        this.msg = codeMessage.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
