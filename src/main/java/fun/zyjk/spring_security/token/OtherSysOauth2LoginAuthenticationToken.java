package fun.zyjk.spring_security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * <h1>第三方登陆认证令牌</h1>
 * <p>
 * 参考： UsernamePasswordAuthenticationToken 实现
 * <p>
 * 将登录信息构造一个成认证令牌，传递数据
 *
 * @author yzk
 */
public class OtherSysOauth2LoginAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;


    public OtherSysOauth2LoginAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public OtherSysOauth2LoginAuthenticationToken(Object authUser) {
        super(null);
        this.principal = authUser;
        setAuthenticated(false);
    }

    /**
     * 使用提供的权限数组创建令牌。
     *
     * @param authorities 权限集合
     * @param principal   用户名
     * @param credentials 密码
     */
    public OtherSysOauth2LoginAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override

    }


    // 密码
    @Override
    public Object getCredentials() {
        return this.credentials;
    }


    // 用户名
    @Override
    public Object getPrincipal() {
        return this.principal;
    }

}
