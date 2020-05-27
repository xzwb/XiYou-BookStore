package cc.xzwb.bookstore.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("userSell")
@Data
public class UserSell {
    // 卖出的时间
    private Date sellTime;
    // 卖出的书
    private Book book;
    // 买家学号
    private String buyer;
    // orderId
    private int orderId;
}
