package cc.xzwb.bookstore.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

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
