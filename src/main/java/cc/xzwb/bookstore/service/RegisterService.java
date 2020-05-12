package cc.xzwb.bookstore.service;

import cc.xzwb.bookstore.pojo.Result;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;

public interface RegisterService {
    /**
     * 获取短信验证码
     * @param phoneNumber 用户手机号
     * @return
     */
    Result getSMSCode(String phoneNumber) throws HTTPException, IOException;
}
