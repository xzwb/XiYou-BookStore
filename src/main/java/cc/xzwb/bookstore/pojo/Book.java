package cc.xzwb.bookstore.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 书籍信息
 */
@Alias("book")
@Data
public class Book {
    // 书籍名称
    @NotNull
    private String bookName;
    // 书籍编号
    private int bookId;
    // 发布者的学号
    private String studentCode;
    // 发布者的昵称
    private String userName;
    // 书的类型
    private List<String> bookStyle = new ArrayList<>();
    // 书的简介
    @NotNull
    private String bookIntroduction;
    // 书籍的主页图片
    private String bookSrc;
    // 发布日期
    private Date bookDate;
    // 书的价格
    @NotNull
    private float price;
    // 库存
    @NotNull
    private int stock;
}
