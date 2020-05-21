package cc.xzwb.bookstore.service.impl;

import cc.xzwb.bookstore.mapper.HomeMapper;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.ResultStatusEnum;
import cc.xzwb.bookstore.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeMapper homeMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public Result homeService(String studentCode) {
        return Result.build(ResultStatusEnum.SUCCESS, homeMapper.selectPersonByStudentCode(studentCode));
    }

    @Override
    public Result logout(String studentCode) {
        redisTemplate.delete(studentCode);
        return Result.build(ResultStatusEnum.SUCCESS);
    }
}
