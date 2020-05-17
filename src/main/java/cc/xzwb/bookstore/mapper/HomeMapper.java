package cc.xzwb.bookstore.mapper;

import cc.xzwb.bookstore.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HomeMapper {

    /**
     * 根据学号查询用户信息
     * @param studentCode
     * @return
     */
    Person selectPersonByStudentCode(@Param("studentCode") String studentCode);

}
