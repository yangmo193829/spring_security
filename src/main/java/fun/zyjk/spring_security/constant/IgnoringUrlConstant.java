package fun.zyjk.spring_security.constant;


/**
 * <h1>忽略的url路径</h1>
 *
 * @author yzk
 */
public class IgnoringUrlConstant {
    /**
     * 不进行认证的URL
     */
    public static final String[] IGNORING_URLS = {
            "/api/v1/oauth/**",
            "/swagger-ui.html",
            "/actuator/**",
            "/v2/api-docs",
            "/webjars/**",
            "/swagger/**",
            "/swagger-resources/**",
            "/doc.html"
    };

    public static final String[] IGNORING_STATIC_URLS = {
            "/js/**",
            "/oauthlogin.html"
    };

    public static final String[] OAUTH2_URLS = {
            "/api/v1/oauth/**"
    };
}
