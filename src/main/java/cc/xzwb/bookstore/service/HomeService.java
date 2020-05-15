package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.Result;

/**
 * 用户主页service层
 */
public interface HomeService {
    Result homeService(String studentCode);

    Result logout(String studentCode);
}
