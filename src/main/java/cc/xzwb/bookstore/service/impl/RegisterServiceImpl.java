package cc.xzwb.bookstore.service.impl;

import cc.xzwb.bookstore.mapper.RegisterMapper;
import cc.xzwb.bookstore.pojo.Person;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.ResultStatusEnum;
import cc.xzwb.bookstore.service.RegisterService;
import cc.xzwb.bookstore.thread.SaveFileThread;
import cc.xzwb.bookstore.zfjw.exception.PublicKeyException;
import cc.xzwb.bookstore.zfjw.model.LoginStatus;
import cc.xzwb.bookstore.zfjw.model.User;
import cc.xzwb.bookstore.zfjw.service.LoginService;
import cc.xzwb.bookstore.zfjw.service.impl.LoginServiceImpl;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.security.auth.login.LoginException;
import javax.servlet.http.Part;
import java.io.IOException;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterMapper registerMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 队列模板
     */
    @Autowired
    RabbitTemplate rabbitTemplate;
//
//    private final int AppID = 1400341372;
//
//    private final String AppKey = "4f64c973f40e450534016ce13709dac0";
//
//    private final int TemplateId = 565992;
//
//    private final String SmsSign = "大小胖几的日常";

    /**
     * 发送短信验证码 不使用延时队列
     * @param phoneNumber 用户手机号
     * @return
     * @throws HTTPException
     * @throws IOException
     */
//    @Override
//    public Result getSMSCode(String phoneNumber) throws HTTPException, IOException {
//        String smsCode = "";
//        Random random = new Random();
//        for (int i = 0; i < 6; i++) {
//            smsCode += random.nextInt(10);
//        }
//        // 短信中的变量
//        String[] params = {smsCode, "5"};
//        SmsSingleSender sender = new SmsSingleSender(AppID, AppKey);
//        SmsSingleSenderResult result = sender.sendWithParam("86",
//                phoneNumber, TemplateId, params, SmsSign, "", "");
//        if (result.result == 0) {
//            /**
//             * 如果短信发送成功把得到的短信验证码存到redis中
//             * 并设置6分钟过期
//             */
//            redisTemplate.opsForValue().set(phoneNumber, smsCode, 360, TimeUnit.SECONDS);
//            return Result.build(ResultStatusEnum.SUCCESS);
//        } else {
//            return Result.build(ResultStatusEnum.SMS_CODE_FALSE);
//        }

//    }


    /**
     * 使用延时队列
     * @param phoneNumber 用户手机号
     * @return
     * @throws HTTPException
     * @throws IOException
     */
    @Override
    public Result getSMSCode(String phoneNumber) throws HTTPException, IOException {
        rabbitTemplate.convertAndSend("exchange_sms_yyf", "sms", phoneNumber);
        return Result.build(ResultStatusEnum.SUCCESS);
    }

    /**
     *
     * @param person 需要保存到数据库中的用户信息
     * @param smsCode 短信验证码
     * @param studentPassword 学生教务系统密码
     * @param src 图片路径
     * @param part Part
     * @return
     * @throws LoginException
     * @throws PublicKeyException
     * @throws cc.xzwb.bookstore.zfjw.exception.LoginException
     */
    @Override
    public Result register(Person person, String smsCode, String studentPassword, String src, Part part) throws LoginException, PublicKeyException, cc.xzwb.bookstore.zfjw.exception.LoginException {
        // 用户密码使用md5加密后保存到数据库
        person.setPassword(DigestUtils.md5DigestAsHex(person.getPassword().getBytes()));
        if (smsCodeService(person.getPhoneNumber(), smsCode)) {
            if (ZFJWService(person.getStudentCode(), studentPassword)) {
                registerMapper.insertPerson(person);
                // 使用异步保存头像文件
                System.out.println(src);
                new SaveFileThread(part, src).run();

                return Result.build(ResultStatusEnum.SUCCESS);
            } else {
                return Result.build(ResultStatusEnum.ZFJW_FALSE);
            }
        } else {
            return Result.build(ResultStatusEnum.SMS_CODE_MISTAKE);
        }
    }

    /**
     * 验证是不是本校学生
     * @param code 学号
     * @param password 教务系统密码
     * @return
     * @throws LoginException
     * @throws PublicKeyException
     * @throws cc.xzwb.bookstore.zfjw.exception.LoginException
     */
    private Boolean ZFJWService(String code, String password) throws LoginException, PublicKeyException, cc.xzwb.bookstore.zfjw.exception.LoginException {
        User user = User.builder(code, password);
        LoginService loginService = new LoginServiceImpl("www.zfjw.xupt.edu.cn", user);
        LoginStatus loginStatus = loginService.login();
        if (!loginStatus.isSuccess()) {
            return true;
        }
        return false;
    }

    /**
     * 验证短信验证码
     * @param phoneNumber 手机号
     * @param smsCode 短信验证码
     * @return
     */
    private Boolean smsCodeService(String phoneNumber, String smsCode) {
        if (smsCode.equals(redisTemplate.opsForValue().get(phoneNumber))) {
            redisTemplate.delete(phoneNumber);
            return true;
        }
        return false;
    }
}
