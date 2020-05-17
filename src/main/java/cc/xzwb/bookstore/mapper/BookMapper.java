package cc.xzwb.bookstore.mapper;

import cc.xzwb.bookstore.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    void insertBook(Book book);

    Book selectBookByBookId(@Param("bookId") int bookId);

    List<Book> selectBookByStyleByPrice(@Param("bookStyle") String bookStyle,
                                        @Param("page") int page);

    List<Book> selectBookByStyleByDate(@Param("bookStyle") String bookStyle,
                                       @Param("page") int page);

    List<Book> selectBookByStyleByDateDESC(@Param("bookStyle") String bookStyle,
                                           @Param("page") int page);

    int selectTotalByStyle(@Param("bookStyle") String bookStyle);
}
