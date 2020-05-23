package cc.xzwb.bookstore.pojo;

public enum OrderStatus {
    /**
     * 代付款
     */
    WAIT_PAY(1, "等待支付"),

    /**
     * 支付完成
     */
    SUCCESS_PAY(2, "成功支付"),

    /**
     * 已取消
     */
    CANCEL(3, "订单已取消"),

    /**
     * 已退款
     */
    REFUND(4, "已退款");

    int status;

    String message;

    OrderStatus(int status, String message) {
        this.status = status;
        this.message = message;
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
