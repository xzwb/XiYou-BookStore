package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.Result;



/**
 * 用户主页service层
 */
public interface HomeService {
    /**
     * 根据用户学号获取用户信息
     * @param studentCode
     * @return
     */
    Result homeService(String studentCode);

    /**
     * 注销
     * @param studentCode
     * @return
     */
    Result logout(String studentCode);

}
