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
import java.util.Map;

@CrossOrigin
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    /**
     * 根据书的id获取书的详细信息
     * @param bookId
     * @return
     */
    @GetMapping("/u/book/{bookId}")
    public Result getBook(@PathVariable("bookId") int bookId) {
        return bookService.getBook(bookId);
    }

    /**
     * 根据种类来获取书籍的list信息
     * @param bookStyle
     * @param sort 1表示按时间排序 2表示按价格升序 3表示按价格降序
     * @return
     */
    @GetMapping("/u/book/style/{sort}/{page}")
    public Result getBooksByStyle(@RequestBody Map<String, String> bookStyle,
                                  @PathVariable("sort") int sort,
                                  @PathVariable("page") int page) {
        return bookService.getBookByStyle(bookStyle.get("bookStyle"), sort, page);
    }

    /**
     * 发布
     * @param book
     * @param part
     * @param request
     * @return
     */
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

    /**
     * 获取用户自己发布的书籍
     * @param request
     * @return
     */
    @GetMapping("/u/myBook/{page}")
    public Result getMyBook(@PathVariable("page") int page,
                            HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return bookService.getUserBook(studentCode, page);
    }

    @PostMapping("/u/delete/book/{bookId}")
    public Result deleteBook(@PathVariable("bookId") int bookId,
                             HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        String src = request.getSession().getServletContext().getRealPath("/");
        return bookService.deleteBook(studentCode, bookId, src);
    }
}
