package cc.xzwb.bookstore.mapper;

import cc.xzwb.bookstore.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 获取查询到的用户
     *
     * @param userInformation
     * @param page
     * @return
     */
    List<Person> selectUser(@Param("userInformation") String userInformation,
                            @Param("page") int page);

    /**
     * 获取用户的总数
     *
     * @param userInformation
     * @return
     */
    int getUserTotal(@Param("userInformation") String userInformation);

    /**
     * 加入购物车
     *
     * @param buyCar
     */
    void addBuyCar(BuyCarForAdd buyCar);

    /**
     * 查看购物车
     *
     * @return
     */
    List<BuyCarForSelect> selectBuyCar(@Param("studentCode") String studentCode,
                                       @Param("page") int page);

    /**
     * 购物车内的总数
     * @param studentCode
     * @return
     */
    int selectTotal(@Param("studentCode") String studentCode);

    /**
     * 删除购物车
     */
    void deleteBuyCar(@Param("studentCode") String studentCode,
                      @Param("buyCarId") int buyCarId);

    /**
     * 保存订单
     * @param userOrder
     */
    void insertBookOrder(UserOrder userOrder);

    /**
     * 通过学号查询用户的订单信息
     * @param studentCode
     * @param page
     * @return
     */
    List<UserOrder> getOrderByCode(@Param("studentCode") String studentCode,
                                   @Param("page") int page);

    /**
     * 用户订单的总数
     * @param studentCode
     * @return
     */
    int getOrderTotal(@Param("studentCode") String studentCode);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    void updateOrderStatus(@Param("orderId") int orderId,
                           @Param("status") OrderStatus orderStatus);

    /**
     * 取消订单
     * @param orderId
     * @param orderStatus
     * @param studentCode
     */
    void cancelOrder(@Param("orderId") int orderId,
                     @Param("status") OrderStatus orderStatus,
                     @Param("studentCode") String studentCode);

    /**
     * 获取bookId
     */
    int getBookIdByBuyCarId(@Param("buyCarId") int buyCarId);

    /**
     * 获取卖出的所有书籍
     * @param studentCode
     * @return
     */
    int getSellTotal(@Param("studentCode") String studentCode);

    /**
     * 获取到用户卖出的书籍
     * @param studentCode
     * @param page
     * @return
     */
    List<UserSell> getUserSell(@Param("studentCode") String studentCode,
                               @Param("page") int page);


    /**
     * 根据orderId获取bookId
     * @param orderId
     * @return
     */
    int selectBookIdByOrderId(@Param("orderId") int orderId);
}
