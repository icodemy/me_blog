package dgut.ljj.shiro;

import org.apache.shiro.authc.AuthenticationToken;


/**
 *
 * @author liangjinju
 * @date 2022/4/8 21:52
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
