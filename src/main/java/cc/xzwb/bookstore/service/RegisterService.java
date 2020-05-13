package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.Person;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.zfjw.exception.PublicKeyException;
import com.github.qcloudsms.httpclient.HTTPException;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public interface RegisterService {
    /**
     * 获取短信验证码
     * @param phoneNumber 用户手机号
     * @return
     */
    Result getSMSCode(String phoneNumber) throws HTTPException, IOException;

    /**
     * 注册service
     * @param person 需要保存到数据库中的用户信息
     * @param smsCode 短信验证码
     * @param studentPassword 学生教务系统密码
     * @return
     */
    Result register(Person person, String smsCode, String studentPassword) throws LoginException, PublicKeyException, cc.xzwb.bookstore.zfjw.exception.LoginException;

}
