package cc.xzwb.bookstore.zfjw.model;

public class LoginStatus {


    private final boolean success;
    private final String errorMsg;

    private static final String DEFAULT_MSG = "login failed";

    private LoginStatus(boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public static LoginStatus success() {
        return new LoginStatus(false, null);
    }

    public static LoginStatus error(String msg) {
        if (msg == null) {
            msg = DEFAULT_MSG;
        }
        return new LoginStatus(true, msg);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public static String getDefaultMsg() {
        return DEFAULT_MSG;
    }
}
