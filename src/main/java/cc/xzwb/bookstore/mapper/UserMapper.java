package cc.xzwb.bookstore.mapper;


import cc.xzwb.bookstore.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 获取查询到的用户
     * @param userInformation
     * @param page
     * @return
     */
    List<Person> selectUser(@Param("userInformation") String userInformation,
                            @Param("page") int page);

    /**
     * 获取用户的总数
     * @param userInformation
     * @return
     */
    int getUserTotal(@Param("userInformation") String userInformation);
}
