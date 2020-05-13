package cc.xzwb.bookstore.pojo;

public enum ResultStatusEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 服务器异常
     */
    EXCEPTION(500, "服务器异常"),

    /**
     * 短信验证码未能成功发出
     */
    SMS_CODE_FALSE(-2, "输入正确的手机号"),

    /**
     * 传入的电话号码为空
     */
    NULL_EXCEPTION(500, "手机号不能为空"),

    /**
     * 保存图片文件时错误
     */
    IO_EXCEPTION(500, "保存图片文件错误"),

    /**
     * 正方教务系统登录失败
     */
    ZFJW_FALSE(-3, "用户名或密码错误"),

    /**
     * 短信验证码错误
     */
    SMS_CODE_MISTAKE(-4, "短信验证码错误"),

    /**
     * 请求错误
     */
    BIND_EXCEPTION(400);

    private int status;
    private String message;

    ResultStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    ResultStatusEnum(int status) {
        this.status = status;
    }

    public static ResultStatusEnum BIND_EXCEPTION(String errMsg) {
        BIND_EXCEPTION.message = errMsg;
        return BIND_EXCEPTION;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
