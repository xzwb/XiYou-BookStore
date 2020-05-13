package cc.xzwb.bookstore.exception;

import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.ResultStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result except(HttpServletRequest request, Exception e) {
        /* 处理jsr303校验抛出的异常 */
        if (e instanceof MethodArgumentNotValidException) {
            e.printStackTrace();
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            FieldError fieldError = fieldErrors.get(0);
            String errorMsg = fieldError.getDefaultMessage();
            return Result.build(ResultStatusEnum.BIND_EXCEPTION(errorMsg));
        } else if (e instanceof BindException) {
            e.printStackTrace();
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String errorMsg = error.getDefaultMessage();
            return Result.build(ResultStatusEnum.BIND_EXCEPTION(errorMsg));
          /* 针对短信验证码的时候,传入的电话号码为空 */
        } else if (e instanceof HttpMessageNotReadableException) {
            return Result.build(ResultStatusEnum.NULL_EXCEPTION);
          /* 保存文件的时候无法保存 */
        } else if (e instanceof IOException) {
            return Result.build(ResultStatusEnum.IO_EXCEPTION);
          /* 学号被重复注册问题 */
        } else if (e instanceof DuplicateKeyException) {
            return Result.build(ResultStatusEnum.CODE_HAVE);
        }
        e.printStackTrace();
        return Result.build(ResultStatusEnum.EXCEPTION);
    }
}
