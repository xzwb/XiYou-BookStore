package cc.xzwb.bookstore.service.impl;

import cc.xzwb.bookstore.mapper.BookMapper;
import cc.xzwb.bookstore.pojo.Book;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.ResultStatusEnum;
import cc.xzwb.bookstore.service.BookService;
import cc.xzwb.bookstore.thread.SaveFileThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public Result release(Book book, String fileURI, Part part) {
        bookMapper.insertBook(book);
        new SaveFileThread(part, fileURI).run();
        return Result.build(ResultStatusEnum.SUCCESS, book);
    }

    @Override
    public Result getBook(int bookId) {
        Book book = bookMapper.selectBookByBookId(bookId);
        if (book == null) {
            return Result.build(ResultStatusEnum.SUCCESS);
        }
        return Result.build(ResultStatusEnum.SUCCESS, book);
    }

    /**
     *
     * @param style 种类
     * @param sort 排序方式1表示按时间排序 2表示按价格升序 3表示按价格降序
     * @param page
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Result getBookByStyle(String style, int sort, int page) {
        if ("all".equals(style)) {
            style = "";
        }
        int total = bookMapper.selectTotalByStyle(style);
        page = (page - 1) * 7;
        List<Book> books = new ArrayList<>();
        if (sort == 1) {
            books = bookMapper.selectBookByStyleByDate(style, page);
        } else if (sort == 2) {
            books = bookMapper.selectBookByStyleByPrice(style, page);
        } else if (sort == 3) {
            books = bookMapper.selectBookByStyleByDateDESC(style, page);
        }
        return Result.build(ResultStatusEnum.SUCCESS, books, total);
    }
}
