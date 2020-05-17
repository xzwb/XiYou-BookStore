package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.Book;
import cc.xzwb.bookstore.pojo.Result;

import javax.servlet.http.Part;

public interface BookService {
    Result release(Book book, String fileURI, Part part);

    Result getBook(int BookId);
}
