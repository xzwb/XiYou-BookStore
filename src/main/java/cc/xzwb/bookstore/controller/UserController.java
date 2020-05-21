package cc.xzwb.bookstore.controller;

import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 按照名字或者学号查询用户
     * @param userInformation
     * @param page
     * @return
     */
    @GetMapping("/u/search/user/{page}")
    public Result searchUser(@RequestBody Map<String, String> userInformation,
                             @PathVariable("page") int page) {
        return userService.searchUser(userInformation.get("userInformation"), page);
    }

    /**
     * 查看用户发布的书籍信息
     * @param studentCode
     * @param page
     * @return
     */
    @GetMapping("/u/search/book/{studentCode}/{page}")
    public Result getBook(@PathVariable("studentCode") String studentCode,
                          @PathVariable("page") int page) {
        return userService.getBookByStudentCode(studentCode, page);
    }
}
