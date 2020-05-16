package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.Book;
import cc.xzwb.bookstore.pojo.Result;

import javax.servlet.http.Part;

/**
 * 用户主页service层
 */
public interface HomeService {
    Result homeService(String studentCode);

    Result logout(String studentCode);

    Result release(Book book, String fileURI, Part part);
}
