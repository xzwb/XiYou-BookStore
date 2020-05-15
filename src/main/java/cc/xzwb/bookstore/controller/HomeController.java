package cc.xzwb.bookstore.controller;

import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 主页
 */
@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/u/home")
    public Result home(HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return homeService.homeService(studentCode);
    }

    @GetMapping("/u/logout")
    public Result logout(HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return homeService.logout(studentCode);
    }
}
