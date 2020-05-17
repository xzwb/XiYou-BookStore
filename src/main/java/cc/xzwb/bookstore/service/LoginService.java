package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.Result;

public interface LoginService {
    /**
     * 登录
     * @param studentCode
     * @param password
     * @return
     */
    Result login(String studentCode, String password);
}
