package cn.yxisme.myrest.exception;

/**
 * Created by yangxiong on 2019/3/5.
 */
public class CodeMessageDef {

    /**
     * 正确返回
     */
    public static final CodeMessage SUCCESS = new CodeMessage(200, "操作成功");

    /**
     * 公共错误 1-100
     */
    public static final CodeMessage TOKEN_IS_NULL = new CodeMessage(0, "Token为空");
    public static final CodeMessage TOKEN_FAIL = new CodeMessage(1, "认证失败");
    public static final CodeMessage NO_PERMISSION = new CodeMessage(2, "暂无权限");
    public static final CodeMessage SYSTEM_ERROR = new CodeMessage(3, "未知系统错误");
    public static final CodeMessage PARAMETER_ERROR = new CodeMessage(4, "参数错误");
    public static final CodeMessage USER_NOT_LOGGED_IN = new CodeMessage(5, "用户未登录");
    public static final CodeMessage FILE_IS_NULL = new CodeMessage(6,"文件为空");
    public static final CodeMessage FILE_UPLOAD_FAIL = new CodeMessage(7, "文件上传失败");
    public static final CodeMessage FILE_FORMAT_ERROR = new CodeMessage(8, "文件格式错误");
    public static final CodeMessage FILE_IS_TOO_LARGE = new CodeMessage(9, "文件过大");

    /**
     * 用户相关错误 501-600
     */
    public static final CodeMessage USER_ALREADY_EXISTS_ERROR = new CodeMessage(1001,"用户已存在");
    public static final CodeMessage USERNAME_ERROR = new CodeMessage(1002,"用户名错误");
    public static final CodeMessage USER_PASSWORD_ERROR = new CodeMessage(1003,"用户密码错误");
    public static final CodeMessage USER_NAME_ERROR = new CodeMessage(1004,"用户名错误");
    public static final CodeMessage USER_LOGIN_ERROR = new CodeMessage(1005,"用户名或密码错误");
}
