package cc.xzwb.bookstore;

import cc.xzwb.bookstore.zfjw.exception.PublicKeyException;
import cc.xzwb.bookstore.zfjw.model.LoginStatus;
import cc.xzwb.bookstore.zfjw.model.User;
import cc.xzwb.bookstore.zfjw.service.LoginService;
import cc.xzwb.bookstore.zfjw.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.LoginException;

public class ZFJWTest {
    @Test
    public void test() throws LoginException, PublicKeyException, cc.xzwb.bookstore.zfjw.exception.LoginException {
        // 设置用户信息
        User user = User.builder("05181018", "520520cw...");
        // 设置登录信息
        // 域名类似：www.zfjw.xupt.edu.cn，需符合 url 要求，且不能以 '/' 开头
        LoginService loginService = new LoginServiceImpl("www.zfjw.xupt.edu.cn", user);
        // 登录，并获取返回的登录状态信息
        LoginStatus loginStatus = loginService.login();
        // 若登录成功
        if (!loginStatus.isSuccess()) {
            // 获取登录成功的cookie
//            CookieStore cookieStore = loginService.getCookie();
            // 设置cookie，并使用 HttpClient 去发送响应请求
//            CloseableHttpClient httpClient = HttpClients.custom()
//                    .setDefaultCookieStore(cookieStore)
//                    .build();
//            HttpGet httpGet = new HttpGet("url");
//            CloseableHttpResponse response = httpClient.execute(httpGet);
//            System.out.println(EntityUtils.toString(response.getEntity()));
            System.out.println("登陆成功");
        } else {
            // 登录失败：打印错误信息(教务系统网页上的tips)
            System.out.println(loginStatus.getErrorMsg());
        }
    }

}
