package cc.xzwb.bookstore.controller;

import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



/**
 * 主页
 */
@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    HomeService homeService;

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @GetMapping("/u/home")
    public Result home(HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return homeService.homeService(studentCode);
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @GetMapping("/u/logout")
    public Result logout(HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return homeService.logout(studentCode);
    }

}
