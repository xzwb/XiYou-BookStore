package cc.xzwb.bookstore.interceptor;

import cc.xzwb.bookstore.exception.LoginTokenException;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.ResultStatusEnum;
import cc.xzwb.bookstore.util.JWTUtil;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * token检权拦截器
 */
public class JWTInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null) {
            throw new LoginTokenException();
        }
        Date expire = JWT.decode(token).getExpiresAt();
        // 学号
        String studentCode = JWT.decode(token).getClaim("studentCode").asString();
        // 多设备登录只能登录在一个设备
        if (!token.equals(redisTemplate.opsForValue().get(studentCode))) {
            throw new LoginTokenException();
        }
        request.setAttribute("studentCode", studentCode);
        // token校验
        if (JWTUtil.verifierToken(token)) {
            if (expire.before(new Date())) {
                if (redisTemplate.opsForValue().get(studentCode).equals(token)) {
                    token = JWTUtil.getToken(studentCode);
                    redisTemplate.opsForValue().set(studentCode, token, 1800, TimeUnit.SECONDS);
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    Result result = Result.build(ResultStatusEnum.TOKEN_CHANGE, token);
                    out.write(JSON.toJSONString(result));
                    out.flush();
                    out.close();
                    return true;
                }
            }
            redisTemplate.opsForValue().set(studentCode, token, 1800, TimeUnit.SECONDS);
        }

        return true;
    }
}
