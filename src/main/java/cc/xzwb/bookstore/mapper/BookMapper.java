package cc.xzwb.bookstore.mapper;

import cc.xzwb.bookstore.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    /**
     * 添加书籍
     * @param book
     */
    void insertBook(Book book);

    /**
     * 根据书的id查询书籍
     * @param bookId
     * @return
     */
    Book selectBookByBookId(@Param("bookId") int bookId);

    /**
     * 模糊查询
     * @param bookStyle
     * @param page
     * @return
     */
    List<Book> selectBookByStyleByPrice(@Param("bookStyle") String bookStyle,
                                        @Param("page") int page);

    List<Book> selectBookByStyleByDate(@Param("bookStyle") String bookStyle,
                                       @Param("page") int page);

    List<Book> selectBookByStyleByDateDESC(@Param("bookStyle") String bookStyle,
                                           @Param("page") int page);

    /**
     * 模糊查询的总数
     * @param bookStyle
     * @return
     */
    int selectTotalByStyle(@Param("bookStyle") String bookStyle);

    /**
     * 查询当前用户发布的书籍总数
     * @param studentCode
     * @return
     */
    int getUserBookTotal(@Param("studentCode") String studentCode);

    /**
     * 获取当前用户发布的书籍信息
     * @param studentCode
     * @param page
     * @return
     */
    List<Book> getUserBook(@Param("studentCode") String studentCode,
                           @Param("page") int page);

    void deleteBook(@Param("studentCode") String studentCode,
                    @Param("bookId") int bookId);
}
