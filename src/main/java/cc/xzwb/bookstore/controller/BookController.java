package cc.xzwb.bookstore.controller;

import cc.xzwb.bookstore.pojo.Book;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;

@CrossOrigin
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/a/book/{bookId}")
    public Result getBook(@PathVariable("bookId") int bookId) {
        return bookService.getBook(bookId);
    }

    @PostMapping("/u/release")
    public Result release(@Valid Book book,
                          @RequestPart("file") Part part,
                          HttpServletRequest request) {
        book.setStudentCode((String) request.getAttribute("studentCode"));
        // 获取图书发布时间
        book.setBookDate(new Date());
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        book.setBookSrc(timeInMillis + part.getSubmittedFileName());
        String fileURI = request.getSession().getServletContext().getRealPath("/") + book.getBookSrc();
        return bookService.release(book, fileURI, part);
    }
}
