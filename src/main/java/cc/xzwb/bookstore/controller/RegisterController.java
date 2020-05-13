package cc.xzwb.bookstore.controller;

import cc.xzwb.bookstore.pojo.Person;
import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.service.RegisterService;

import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

/**
 * 注册
 */
@CrossOrigin
@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;

    /**
     * 用户注册
     * @param person 用户保存在数据库中的基本信息
     * @param part 用户的头像图片上传保存在服务端
     * @param smsCode 用户的手机短信验证码
     * @param studentPassword 学生的教务系统密码
     * @return
     */
    @PostMapping("/a/register")
    public Result register(@Valid Person person,
                           @RequestPart("file") Part part,
                           @RequestParam String smsCode,
                           @RequestParam String studentPassword,
                           HttpSession session) throws Exception {
        boolean haveSrc = false;
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        String src = session.getServletContext().getRealPath("/");
        String  playSrcFile = timeInMillis + part.getSubmittedFileName();
        // 判断是否上传图片
        if (part.getSubmittedFileName() != null && !"".equals(part.getSubmittedFileName())) {
            person.setHeadSrc(playSrcFile);
//            part.write(src + playSrcFile);
            src += playSrcFile;
            haveSrc = true;
        }
        return registerService.register(person, smsCode, studentPassword, src, part, haveSrc);
    }

    @PostMapping("/a/smsCode")
    public Result getSMSCode(@RequestBody Map<String, String> phoneNumber) throws HTTPException, IOException {
        return registerService.getSMSCode(phoneNumber.get("phoneNumber"));
    }
}
