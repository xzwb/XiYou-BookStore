package cc.xzwb.bookstore.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Alias("buyCarForAdd")
@Data
public class BuyCarForAdd {
    @NotNull
    private int bookId;

    private String studentCode;

    private Date addTime;
}
