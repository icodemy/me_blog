package dgut.ljj.commom;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExcepitonHandler {
    /*
     捕捉shiro的异常
     */

    //    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseEntity handle401(ShiroException e) {
        Map<String, Object> map = new HashMap<>();

        if (e.getMessage().equals("The current Subject is not authenticated.  Access denied.")) {
            map.put("code", 401);
            map.put("message", "未登录");
            map.put("error_msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
        map.put("code", 403);
        map.put("message", "无权限");
        map.put("error_msg", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(map);
    }


    /**
     * @Validated 校验错误异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) throws IOException {
        log.error("运行时异常:-------------->", e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.FAIL(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e) throws IOException {
        log.error("运行时异常:-------------->", e);
        return Result.FAIL(e.getMessage());
    }
}
