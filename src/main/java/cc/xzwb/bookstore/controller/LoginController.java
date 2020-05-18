package cc.xzwb.bookstore.controller;

import cc.xzwb.bookstore.pojo.Person;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * 登录
     * @param person 用户对象
     * @return
     */
    @PostMapping("/a/login")
    public Result login(Person person) {
         return loginService.login(person.getStudentCode(), person.getPassword());
    }
}
