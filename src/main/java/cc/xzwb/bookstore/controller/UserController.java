package cc.xzwb.bookstore.controller;

import cc.xzwb.bookstore.pojo.BuyCarForAdd;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
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

    @PostMapping("/u/add/buyCar")
    public Result addBookBuyCar(@Valid @RequestBody BuyCarForAdd buyCar,
                                HttpServletRequest request) {
        buyCar.setAddTime(new Date());
        buyCar.setStudentCode((String) request.getAttribute("studentCode"));
        return userService.addBuyCar(buyCar);
    }

    @GetMapping("/u/look/buyCar/{page}")
    public Result selectBuyCar(@PathVariable("page") int page,
                               HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return userService.selectBuyCar(studentCode, page);
    }

    @PostMapping("/u/delete/buyCar/{buyCarId}")
    public Result deleteBuyCar(@PathVariable("buyCarId") int buyCarId,
                               HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return userService.deleteBuyCar(studentCode, buyCarId);
    }
}
