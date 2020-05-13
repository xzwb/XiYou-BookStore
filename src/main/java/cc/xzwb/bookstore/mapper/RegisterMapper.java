package cc.xzwb.bookstore.mapper;

import cc.xzwb.bookstore.pojo.Person;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RegisterMapper {
    void insertPerson(Person person);
}
