package dgut.ljj.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dgut.ljj.mapper.UserMapper;
import dgut.ljj.pojo.User;
import dgut.ljj.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义 shiro Realm
 * @author liangjinju
 * @date 2022/4/8 21:46
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private final UserMapper userMapper;

    @Autowired
    public UserRealm(UserMapper userMapper) {
        this.userMapper = userMapper;
    }




    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);


        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);

        User user = userMapper.selectOne(wrapper);
        if(user == null){
            throw new AuthenticationException("用户不存在！");
        }

        int status = user.getStatus();
        if (status == 1) {
            throw new AuthenticationException("该用户已被封号！");
        }
        return new SimpleAuthenticationInfo(token, token, getName());//存入user可以通过getsubject获取
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String username = JWTUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);

        //获得该用户角色
        String role = userMapper.selectOne(wrapper).getRole();
        //每个角色拥有默认的权限
        String permission = userMapper.selectOne(wrapper).getPermission();

        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
        roleSet.add(role);
        permissionSet.add(permission);
        //设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}
