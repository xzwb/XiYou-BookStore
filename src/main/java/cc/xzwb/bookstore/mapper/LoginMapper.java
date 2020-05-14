package cc.xzwb.bookstore.mapper;

import cc.xzwb.bookstore.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    Person loginSelect(@Param("studentCode") String studentCode,
                       @Param("password") String password);
}
