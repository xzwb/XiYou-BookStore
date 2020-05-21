package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.Book;
import cc.xzwb.bookstore.pojo.Result;

import javax.servlet.http.Part;

public interface BookService {
    Result release(Book book, String fileURI, Part part);

    Result getBook(int BookId);

    /**
     * 按照书的中的种类查找
     * @param style 种类
     * @param sort 排序方式1表示按时间排序 2表示按价格升序 3表示按价格降序
     * @param page 分页
     * @return
     */
    Result getBookByStyle(String style, int sort, int page);

    /**
     * 获取当前学号用户发布的书籍
     * @param studentCode
     * @return
     */
    Result getUserBook(String studentCode, int page);

    Result deleteBook(String studentCode, int bookId, String src);
}
