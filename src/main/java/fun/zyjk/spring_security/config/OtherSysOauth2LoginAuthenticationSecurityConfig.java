package fun.zyjk.spring_security.config;

import fun.zyjk.spring_security.filter.OtherSysOauth2LoginFilter;
import fun.zyjk.spring_security.filter.UserBindFilter;
import fun.zyjk.spring_security.handler.UserAuthenticationFailureHandler;
import fun.zyjk.spring_security.handler.UserAuthenticationSuccessHandler;
import fun.zyjk.spring_security.provider.BindLoginProvider;
import fun.zyjk.spring_security.provider.OtherSysOauth2LoginProvider;
import fun.zyjk.spring_security.service.impl.OtherSysOauth2LoginUserDetailsServiceImpl;
import fun.zyjk.spring_security.service.impl.UserDetailsServiceImpl;
import fun.zyjk.spring_security.sys.dao.OtherSysUserDao;
import fun.zyjk.spring_security.sys.dao.SysUsersDao;
import fun.zyjk.spring_security.sys.service.AuthRequestService;
import fun.zyjk.spring_security.sys.service.OtherUsersService;
import fun.zyjk.spring_security.sys.service.UsersService;
import fun.zyjk.spring_security.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * <h1>第三方登陆的认证配置</h1>
 * <p>
 * 主要配置：
 * 1，加载第三方登陆的认证过滤器 ，主要提供路径拦截和基础判断
 * 设置认证管理器
 * 设置用户服务类
 * <p>
 * 2，加载登陆提供者
 * 设置用户详细服务实现
 * 设置登陆成功事件
 * 设置登陆失败事件
 * <p>
 * 将提供提供者和过滤器加入 HttpSecurity 中，并在 UsernamePasswordAuthenticationFilter 前执行逻辑判断
 * <p>
 * 用户过滤器配置
 *
 * @author yzk
 */
@Configuration
public class OtherSysOauth2LoginAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private OtherSysOauth2LoginUserDetailsServiceImpl extendUserDetailsService;
    // ------------ 用户认证失败处理程序 -------------
    @Autowired
    private UserAuthenticationFailureHandler userAuthenticationFailureHandler;

    // ------------ 用户认证成功处理程序 -------------
    @Autowired
    private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

    @Autowired
    private UsersService usersService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private OtherSysUserDao otherSysUserDao;

    @Autowired
    private SysUsersDao sysUsersDao;

    @Autowired
    private OtherUsersService otherUsersService;

    @Autowired
    private AuthRequestService authRequestService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        // 第三方登陆过滤器
        OtherSysOauth2LoginFilter filter = new OtherSysOauth2LoginFilter();
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        filter.setUsersService(usersService);
        filter.setRedisUtil(redisUtil);
        filter.setOtherUsersService(otherUsersService);
        filter.setAuthenticationSuccessHandler(userAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(userAuthenticationFailureHandler);
        filter.setAuthRequestService(authRequestService);
        // 认证
        OtherSysOauth2LoginProvider provider = new OtherSysOauth2LoginProvider();
        provider.setUserDetailsService(extendUserDetailsService);
        builder.authenticationProvider(provider).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        // 绑定用户Filter
        UserBindFilter userBindFilter = new UserBindFilter();
        userBindFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        userBindFilter.setRedisUtil(redisUtil);
        userBindFilter.setOtherSysUserDao(otherSysUserDao);
        userBindFilter.setSysUsersDao(sysUsersDao);
        userBindFilter.setAuthenticationFailureHandler(userAuthenticationFailureHandler);
        userBindFilter.setAuthenticationSuccessHandler(userAuthenticationSuccessHandler);
        // 认证
        BindLoginProvider bindLoginProvider = new BindLoginProvider();
        bindLoginProvider.setOtherSysUserDao(otherSysUserDao);
        bindLoginProvider.setUserDetailsService(userDetailsService);
        bindLoginProvider.setPasswordEncoder(passwordEncoder);
        builder.authenticationProvider(bindLoginProvider).addFilterBefore(userBindFilter, UsernamePasswordAuthenticationFilter.class);
    }
}