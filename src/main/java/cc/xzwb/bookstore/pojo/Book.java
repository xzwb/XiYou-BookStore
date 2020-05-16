package cc.xzwb.bookstore.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 书籍信息
 */
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
}
