package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.Result;

public interface LoginService {
    Result login(String studentCode, String password);
}
