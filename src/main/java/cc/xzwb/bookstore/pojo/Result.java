package cc.xzwb.bookstore.pojo;

/**
 * 返回给前端的结果封装
 */
public class Result {
    // 状态码
    private int status;
    // 详细信息
    private String message;
    // 附带的内容
    private Object data;

    /***** 构造方法 *****/
    public Result() {
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(Object data) {
        this(200, "成功");
        this.data = data;
    }

    /******* get and set方法 ********/
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
