package cc.xzwb.bookstore.service.impl;

import cc.xzwb.bookstore.exception.BuyCarException;
import cc.xzwb.bookstore.mapper.BookMapper;
import cc.xzwb.bookstore.mapper.UserMapper;
import cc.xzwb.bookstore.pojo.*;
import cc.xzwb.bookstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Result searchUser(String userInformation, int page) {
        Map<String, Integer> total = new HashMap<>();
        total.put("userNumber", userMapper.getUserTotal(userInformation));
        List<Person> people = new ArrayList<>();
        page = (page - 1) * 7;
        people = userMapper.selectUser(userInformation, page);
        return Result.build(ResultStatusEnum.SUCCESS, people, total);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Result getBookByStudentCode(String studentCode, int page) {
        page = (page - 1) * 7;
        Map<String, Integer> total = new HashMap<>();
        total.put("booksNumber", bookMapper.getUserBookTotal(studentCode));
        List<Book> books = new ArrayList<>();
        books = bookMapper.getUserBook(studentCode, page);
        return Result.build(ResultStatusEnum.SUCCESS, books, total);
    }

    @Override
    public Result addBuyCar(BuyCarForAdd buyCar) {
        userMapper.addBuyCar(buyCar);
        return Result.build(ResultStatusEnum.SUCCESS);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Result selectBuyCar(String studentCode, int page) {
        page = (page - 1) * 7;
        Map<String, Integer> map = new HashMap<>();
        map.put("total", userMapper.selectTotal(studentCode));
        List<BuyCarForSelect> list = new ArrayList<>();
        list = userMapper.selectBuyCar(studentCode, page);
        return Result.build(ResultStatusEnum.SUCCESS, list, map);
    }

    @Override
    public Result deleteBuyCar(String studentCode, int buyCarId) {
        userMapper.deleteBuyCar(studentCode, buyCarId);
        return Result.build(ResultStatusEnum.SUCCESS);
    }

    @Override
    public Result saveBookOrder(UserOrder userOrder) {
        if (haveBook(userOrder.getBookId())) {
            userMapper.insertBookOrder(userOrder);
            bookMapper.supStock(userOrder.getBookId());
            Map<String, Integer> map = new HashMap<>();
            map.put("orderId", userOrder.getOrderId());
            return Result.build(ResultStatusEnum.SUCCESS, map);
        }
        return Result.build(ResultStatusEnum.NOT_HAVE_STOCK);
        // 对于没有支付的订单在redis中保存2400s
//        redisTemplate.opsForValue().set(userOrder.getOrderId()+"", "等待支付", 2400, TimeUnit.SECONDS);

    }

    @Override
    public Result buyBookFromPage(UserOrder userOrder) {
        if (haveBook(userOrder.getBookId())) {
            userMapper.insertBookOrder(userOrder);
            bookMapper.supStock(userOrder.getBookId());
            Map<String, Integer> map = new HashMap<>();
            map.put("orderId", userOrder.getOrderId());
            return Result.build(ResultStatusEnum.SUCCESS, map);
        }
        return Result.build(ResultStatusEnum.NOT_HAVE_STOCK);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Result selectOrder(String studentCode, int page) {
        page = (page - 1) * 7;
        List<UserOrder> userOrders = new ArrayList<>();
        userOrders = userMapper.getOrderByCode(studentCode, page);
        Map<String, Integer> map = new HashMap<>();
        map.put("total", userMapper.getOrderTotal(studentCode));
        // 在redis中判断等待支付的订单是否过期
//        for (UserOrder userOrder : userOrders) {
//            if (userOrder.getOrderStatus().equals(OrderStatus.WAIT_PAY)) {
//                if (redisTemplate.opsForValue().get(userOrder.getOrderId()+"") == null) {
//                    userOrder.setOrderStatus(OrderStatus.END_TIME);
//                    userMapper.updateOrderStatus(userOrder.getOrderId(), OrderStatus.END_TIME);
//                }
//            }
//        }
        return Result.build(ResultStatusEnum.SUCCESS, userOrders, map);
    }

    @Override
    public Result payOrder(int orderId) {
        userMapper.updateOrderStatus(orderId, OrderStatus.SUCCESS_PAY);
        return Result.build(ResultStatusEnum.SUCCESS);
    }

    @Transactional
    @Override
    public Result cancelOrder(int orderId, String studentCode) {
        userMapper.cancelOrder(orderId, OrderStatus.CANCEL, studentCode);
        int bookId = userMapper.selectBookIdByOrderId(orderId);
        bookMapper.addStock(bookId);
        return Result.build(ResultStatusEnum.SUCCESS);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Result payBuyCar(List<Integer> buyCarIds, String studentCode) {
        for (Integer buyCarId : buyCarIds) {
            if (!payBuyCar(buyCarId, studentCode)) {
                throw new BuyCarException();
            }
        }
        return Result.build(ResultStatusEnum.SUCCESS);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public boolean payBuyCar(int buyCarId, String studentCode) {
        Date date = new Date();
        // 获取bookId
        int bookId = userMapper.getBookIdByBuyCarId(buyCarId);
        // 删除购物车
        userMapper.deleteBuyCar(studentCode, buyCarId);
        // 构建订单
        UserOrder userOrder = UserOrder.build(studentCode, bookId, date, OrderStatus.SUCCESS_PAY, null);
        if (haveBook(bookId)) {
            System.out.println(bookId);
            userMapper.insertBookOrder(userOrder);
            bookMapper.supStock(bookId);
            return true;
        }
        return false;
    }

    @Override
    public Result getSell(String studentCode, int page) {
        page = (page - 1) * 7;
        Map<String, Integer> map = new HashMap<>();
        map.put("total", userMapper.getSellTotal(studentCode));
        List<UserSell> list = new ArrayList<>();
        list = userMapper.getUserSell(studentCode, page);
        return Result.build(ResultStatusEnum.SUCCESS, list, map);
    }

    /**
     * 检测是否有货
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean haveBook(int bookId) {
        int stock = bookMapper.selectStock(bookId);
        if (stock > 0) {
            return true;
        }
        return false;
    }
}
