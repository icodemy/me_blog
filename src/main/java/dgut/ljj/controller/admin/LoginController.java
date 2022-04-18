package dgut.ljj.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dgut.ljj.commom.Result;
import dgut.ljj.mapper.UserMapper;
import dgut.ljj.pojo.User;
import dgut.ljj.utils.JWTUtil;
import dgut.ljj.vo.requestVo.LoginVo;
import dgut.ljj.vo.response.UserVo;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author liangjinju
 * @date 2022/4/8 23:02
 */
@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation("用户登录方法")
    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginVo loginVo) {
        //获取前端传过来的用户名以及密码
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();

        //通过用户名以及密码 查询数据库是否存在该用户
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("username",username).eq("password",password);
        User user = userMapper.selectOne(wrapper);


        if (user == null) {
            return Result.FAIL(-1,"用户名或密码错误",null);
        } else {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            Map<String,Object> result = new LinkedHashMap<>();
            result.put("user",userVo);
            result.put("token",JWTUtil.createToken(username));
            return Result.SUCCESS(200,"",result);
        }
    }

    @ApiOperation("用户退出登录")
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.SUCCESS(200,"用户已退出",null);
    }


    @RequestMapping("/unauthorized")
    public Result unauthorized(@RequestParam("msg") String message) throws UnsupportedEncodingException {
        return Result.FAIL(401,message,null);
    }
}
