package cc.xzwb.bookstore.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 购物车
 */
@Data
@Alias("buyCarForSelect")
public class BuyCarForSelect {
    private int buyCarId;

    private Date addTime;

    private Book book;
}
