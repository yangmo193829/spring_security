package fun.zyjk.spring_security.sys.service;


import fun.zyjk.spring_security.sys.entity.OtherSysUser;

/**
 * <h1>第三方系统用户服务</h1>
 *
 * @author yzk
 */
public interface OtherUsersService {


    /**
     * 根据 id 获取用户信息
     *
     * @param uuid  第三方系统的id
     * @param scope 那个第三方系统
     * @return
     */
    OtherSysUser getOtherSysUserByUUIDAndScope(String scope, String uuid);


}
