package fun.zyjk.spring_security.constant;


/**
 * <h1>redis key 全局常量类</h1>
 *
 * @author yzk
 */
public class RedisKeyContants {
    // 页面随机验证码 redis 前缀
    public static final String VALIDCODE_PREFIX = "validcode:";
    // 页面随机验证码key
    public static final String VALIDCODEKEY = RedisKeyContants.VALIDCODE_PREFIX + "%s";
}
