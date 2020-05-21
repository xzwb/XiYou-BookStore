package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.Result;

public interface UserService {
    Result searchUser(String userInformation, int page);

    Result getBookByStudentCode(String studentCode, int page);
}
