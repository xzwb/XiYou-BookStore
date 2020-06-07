package cc.xzwb.bookstore.controller;

import cc.xzwb.bookstore.pojo.BuyCarForAdd;
import cc.xzwb.bookstore.pojo.OrderStatus;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.UserOrder;
import cc.xzwb.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
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

    /**
     * 添加购物车
     * @param buyCar
     * @param request
     * @return
     */
    @PostMapping("/u/add/buyCar")
    public Result addBookBuyCar(@Valid @RequestBody BuyCarForAdd buyCar,
                                HttpServletRequest request) {
        buyCar.setAddTime(new Date());
        buyCar.setStudentCode((String) request.getAttribute("studentCode"));
        return userService.addBuyCar(buyCar);
    }

    /**
     * 用户查看购物车
     * @param page
     * @param request
     * @return
     */
    @GetMapping("/u/look/buyCar/{page}")
    public Result selectBuyCar(@PathVariable("page") int page,
                               HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return userService.selectBuyCar(studentCode, page);
    }

    /**
     * 删除购物车中的商品 一件
     * @param buyCarId
     * @param request
     * @return
     */
    @PostMapping("/u/delete/buyCar/{buyCarId}")
    public Result deleteBuyCar(@PathVariable("buyCarId") int buyCarId,
                               HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return userService.deleteBuyCar(studentCode, buyCarId);
    }

    /**
     * 从购物主页保存订单
     * @param bookId
     * @param request
     * @return
     */
    @PostMapping("/u/save/order/{bookId}")
    public Result buyBook(@PathVariable("bookId") int bookId,
                          HttpServletRequest request) {
        Date buyDate = new Date();
        String studentCode = (String) request.getAttribute("studentCode");
        UserOrder userOrder = UserOrder.build(studentCode, bookId, buyDate, OrderStatus.WAIT_PAY);
        return userService.saveBookOrder(userOrder);
    }

    /**
     * 从购物主页买东西
     * @param bookId
     * @param request
     * @return
     */
    @PostMapping("/u/buy/book/{bookId}")
    public Result buyBookFromBookPage(@PathVariable("bookId") int bookId,
                                      HttpServletRequest request) {
        Date buyDate = new Date();
        String studentCode = (String) request.getAttribute("studentCode");
        UserOrder userOrder = UserOrder.build(studentCode, bookId, buyDate, OrderStatus.SUCCESS_PAY);
        return userService.buyBookFromPage(userOrder);
    }

    /**
     * 查看我的订单
     * @param page
     * @param request
     * @return
     */
    @GetMapping("/u/search/order/{page}")
    public Result searchOrder(@PathVariable("page") int page,
                              HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return userService.selectOrder(studentCode, page);
    }

    /**
     * 从订单页支付
     * @param orderId
     * @return
     */
    @PostMapping("/u/pay/{orderId}")
    public Result payFromOrderPage(@PathVariable("orderId") int orderId) {
        return userService.payOrder(orderId);
    }

    /**
     * 取消一个待支付的订单
     * @param orderId
     * @param request
     * @return
     */
    @PostMapping("/u/cancel/{orderId}")
    public Result cancelOrder(@PathVariable("orderId") int orderId,
                              HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return userService.cancelOrder(orderId, studentCode);
    }

    /**
     * 从购物车支付
     * @param buyCarIds
     * @param request
     * @return
     */
    @PostMapping("/u/pay/buyCar")
    public Result payBuyCar(@RequestBody List<Integer> buyCarIds,
                            HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return userService.payBuyCar(buyCarIds, studentCode);
    }

    /**
     * 查看自己卖出的书籍
     * @param page
     * @param request
     * @return
     */
    @GetMapping("/u/search/sell/{page}")
    public Result getSellBook(@PathVariable("page") int page,
                              HttpServletRequest request) {
        String studentCode = (String) request.getAttribute("studentCode");
        return userService.getSell(studentCode, page);
    }
}
