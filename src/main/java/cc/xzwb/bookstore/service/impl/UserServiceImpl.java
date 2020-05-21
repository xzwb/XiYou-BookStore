package cc.xzwb.bookstore.service.impl;

import cc.xzwb.bookstore.mapper.BookMapper;
import cc.xzwb.bookstore.mapper.UserMapper;
import cc.xzwb.bookstore.pojo.Book;
import cc.xzwb.bookstore.pojo.Person;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.ResultStatusEnum;
import cc.xzwb.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Result searchUser(String userInformation, int page) {
        Map<String, Integer> total = new HashMap<>();
        total.put("userNumber", userMapper.getUserTotal(userInformation));
        List<Person> people = new ArrayList<>();
        page = (page - 1) * 7;
        people = userMapper.selectUser(userInformation, page);
        return Result.build(ResultStatusEnum.SUCCESS, people, total);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Result getBookByStudentCode(String studentCode, int page) {
        page = (page - 1) * 7;
        Map<String, Integer> total = new HashMap<>();
        total.put("booksNumber", bookMapper.getUserBookTotal(studentCode));
        List<Book> books = new ArrayList<>();
        books = bookMapper.getUserBook(studentCode, page);
        return Result.build(ResultStatusEnum.SUCCESS, books, total);
    }
}
