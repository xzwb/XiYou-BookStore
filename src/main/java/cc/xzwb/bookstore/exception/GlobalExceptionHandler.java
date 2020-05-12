package cc.xzwb.bookstore.exception;

import cc.xzwb.bookstore.pojo.Result;
import cc.xzwb.bookstore.pojo.ResultStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
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
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            FieldError fieldError = fieldErrors.get(0);
            String errorMsg = fieldError.getDefaultMessage();
            return Result.build(ResultStatusEnum.BIND_EXCEPTION(errorMsg));
        } else if (e instanceof BindException) {
          BindException ex = (BindException) e;
          List<ObjectError> errors = ex.getAllErrors();
          ObjectError error = errors.get(0);
          String errorMsg = error.getDefaultMessage();
          return Result.build(ResultStatusEnum.BIND_EXCEPTION(errorMsg));
        } else if (e instanceof HttpMessageNotReadableException) {
            return Result.build(ResultStatusEnum.NULL_EXCEPTION);
        }
        return Result.build(ResultStatusEnum.EXCEPTION);
    }
}
