package cc.xzwb.bookstore.service.impl;

import cc.xzwb.bookstore.mapper.BookMapper;
import cc.xzwb.bookstore.pojo.Book;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.ResultStatusEnum;
import cc.xzwb.bookstore.service.BookService;
import cc.xzwb.bookstore.thread.SaveFileThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;

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
}
