package dgut.ljj.controller.test;

import dgut.ljj.commom.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangjinju
 * @date 2022/03/14/ 17:04
 */
@Api(tags = "测试控制类")
@RestController
public class TestController {

    @ApiOperation("hello测试方法")
    @GetMapping("/hello")
    public Result hello(){
        return Result.SUCCESS("游客访问成功");
    }

    @RequiresAuthentication
    @GetMapping("/hello1")
    public Result hello1(){

        System.out.println("hello1====>" + SecurityUtils.getSubject().getPrincipal());
        return Result.SUCCESS("游客访问成功");
    }

    @RequiresAuthentication
    @RequiresRoles("admin")
    @GetMapping("/hello2")
    public Result hello2(){
        return Result.SUCCESS("hell02");
    }

    // 拥有 user 或 admin 角色，且拥有 vip 权限可以访问
    @GetMapping("/hello3")
    @RequiresRoles(logical = Logical.OR, value = {"visitor", "admin"})
    @RequiresPermissions("all")
    public Result getVipMessage() {
        return Result.SUCCESS("hello3");
    }
}
