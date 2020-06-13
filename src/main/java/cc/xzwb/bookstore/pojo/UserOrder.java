package cc.xzwb.bookstore.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.lang.Nullable;

import java.util.Date;

@Alias("userOrder")
@Data
public class UserOrder {
    // 订单的唯一标识
    private int orderId;

    // 买家学号
    private String studentCode;

    // 商品编号
    private int bookId;

    // 购买的时间
    private Date buyDate;

    // 订单状态
    private OrderStatus orderStatus;

    // 订单结束的时间
    private Date orderEndTime;


    // 书籍的详细信息
    private Book book;

    private UserOrder() {
    }

    public static UserOrder build(String studentCode, int bookId, Date buyDate, OrderStatus orderStatus, Date orderEndTime) {
        UserOrder userOrder = new UserOrder();
        userOrder.setStudentCode(studentCode);
        userOrder.setBookId(bookId);
        userOrder.setBuyDate(buyDate);
        userOrder.setOrderStatus(orderStatus);
        userOrder.setOrderEndTime(orderEndTime);
        return userOrder;
    }
}
