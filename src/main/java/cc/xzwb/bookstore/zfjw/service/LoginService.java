package cc.xzwb.bookstore.zfjw.service;

import cc.xzwb.bookstore.zfjw.exception.PublicKeyException;
import cc.xzwb.bookstore.zfjw.model.LoginStatus;
import org.apache.http.client.CookieStore;

import javax.security.auth.login.LoginException;

public interface LoginService {
    /**
     * 登录
     * @return 登录信息
     */
    LoginStatus login() throws PublicKeyException, LoginException, cc.xzwb.bookstore.zfjw.exception.LoginException;

    /**
     * 获取 cookie
     * @return cookie 值
     */
    CookieStore getCookie();
}
