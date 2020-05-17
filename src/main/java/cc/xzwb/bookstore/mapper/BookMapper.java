package cc.xzwb.bookstore.mapper;

import cc.xzwb.bookstore.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookMapper {
    void insertBook(Book book);

    Book selectBookByBookId(@Param("bookId") int bookId);
}
