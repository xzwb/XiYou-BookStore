package cc.xzwb.bookstore.service.impl;

import cc.xzwb.bookstore.mapper.LoginMapper;
import cc.xzwb.bookstore.pojo.Person;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.ResultStatusEnum;
import cc.xzwb.bookstore.service.LoginService;
import cc.xzwb.bookstore.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    LoginMapper loginMapper;

    @Override
    public Result login(String studentCode, String password) {
        // 密码是加密后存入数据库的
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Person person = loginMapper.loginSelect(studentCode, password);
        if (person != null) {
            // 获取token
            String token = JWTUtil.getToken(studentCode);
            redisTemplate.opsForValue().set(studentCode, token, 1800, TimeUnit.SECONDS);
            return Result.build(ResultStatusEnum.SUCCESS, token);
        }
        return Result.build(ResultStatusEnum.LOGIN_FALSE);
    }


}
