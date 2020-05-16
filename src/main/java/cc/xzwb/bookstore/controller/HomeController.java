package cc.xzwb.bookstore.controller;

import cc.xzwb.bookstore.pojo.Book;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;


/**
 * 主页
 */
@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/u/home")
    public Result home(HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return homeService.homeService(studentCode);
    }

    @GetMapping("/u/logout")
    public Result logout(HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return homeService.logout(studentCode);
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
        return homeService.release(book, fileURI, part);
    }
}
