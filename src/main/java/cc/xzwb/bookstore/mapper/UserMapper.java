package cc.xzwb.bookstore.mapper;

import cc.xzwb.bookstore.pojo.BuyCarForAdd;
import cc.xzwb.bookstore.pojo.BuyCarForSelect;
import cc.xzwb.bookstore.pojo.Person;
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
}
