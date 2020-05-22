package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.BuyCarForAdd;
import cc.xzwb.bookstore.pojo.Result;

public interface UserService {
    Result searchUser(String userInformation, int page);

    Result getBookByStudentCode(String studentCode, int page);

    Result addBuyCar(BuyCarForAdd buyCar);

    Result selectBuyCar(String studentCode, int page);

    Result deleteBuyCar(String studentCode, int buyCarId);
}
