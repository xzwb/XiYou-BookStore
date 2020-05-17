package cc.xzwb.bookstore.mapper;

import cc.xzwb.bookstore.pojo.Person;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RegisterMapper {
    /**
     * 注册
     * @param person
     */
    void insertPerson(Person person);
}
