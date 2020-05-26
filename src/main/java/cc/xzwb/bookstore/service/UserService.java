package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.BuyCarForAdd;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.UserOrder;

import java.util.List;

public interface UserService {
    /**
     * 查找用户
     * @param userInformation
     * @param page
     * @return
     */
    Result searchUser(String userInformation, int page);

    /**
     * 通过学号 查询该用户发布的书籍
     * @param studentCode
     * @param page
     * @return
     */
    Result getBookByStudentCode(String studentCode, int page);

    /**
     * 加入购物车
     * @param buyCar
     * @return
     */
    Result addBuyCar(BuyCarForAdd buyCar);

    /**
     * 查看购物车
     * @param studentCode
     * @param page
     * @return
     */
    Result selectBuyCar(String studentCode, int page);

    /**
     * 删除购物车
     * @param studentCode
     * @param buyCarId
     * @return
     */
    Result deleteBuyCar(String studentCode, int buyCarId);

    /**
     * 保存用户订单
     * @param userOrder
     * @return
     */
    Result saveBookOrder(UserOrder userOrder);

    /**
     * 从商品主页买书
     * @param userOrder
     * @return
     */
    Result buyBookFromPage(UserOrder userOrder);

    /**
     * 查看订单
     * @param studentCode
     * @param page
     * @return
     */
    Result selectOrder(String studentCode, int page);

    /**
     * 支付订单
     * @param orderId
     * @return
     */
    Result payOrder(int orderId);

    /**
     * 取消订单
     * @param orderId
     * @param studentCode
     * @return
     */
    Result cancelOrder(int orderId, String studentCode);

    Result payBuyCar(List<Integer> buyCarIds, String studentCode);
}
