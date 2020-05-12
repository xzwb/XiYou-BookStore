package cc.xzwb.bookstore.service.impl;

import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.ResultStatusEnum;
import cc.xzwb.bookstore.service.RegisterService;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final int AppID = 1400341372;

    private final String AppKey = "4f64c973f40e450534016ce13709dac0";

    private final int TemplateId = 565992;

    private final String SmsSign = "大小胖几的日常";

    @Override
    public Result getSMSCode(String phoneNumber) throws HTTPException, IOException {
        String smsCode = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            smsCode += random.nextInt(10);
        }
        // 短信中的变量
        String[] params = {smsCode, "5"};
        SmsSingleSender sender = new SmsSingleSender(AppID, AppKey);
        SmsSingleSenderResult result = sender.sendWithParam("86",
                phoneNumber, TemplateId, params, SmsSign, "", "");
        if (result.result == 0) {
            /**
             * 如果短信发送成功把得到的短信验证码存到redis中
             */
            return Result.build(ResultStatusEnum.SUCCESS);
        } else {
            return Result.build(ResultStatusEnum.SMS_CODE_FALSE);
        }
    }
}
