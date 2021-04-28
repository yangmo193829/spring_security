package fun.zyjk.spring_security.service.impl;

import fun.zyjk.spring_security.constant.RspCodeEnum;
import fun.zyjk.spring_security.constant.UserStatusEnum;
import fun.zyjk.spring_security.exception.RrException;
import fun.zyjk.spring_security.sys.service.UsersService;
import fun.zyjk.spring_security.user.UserDetail;
import fun.zyjk.spring_security.vo.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * <h1>用户详细服务impl</h1>
 * <p>
 * 用于返回根据用户名返回用户详细信息，以便于供 security 使用
 *
 * @author yzk
 */
@Service("otherSysOauth2LoginUserDetailsServiceImpl")
public class OtherSysOauth2LoginUserDetailsServiceImpl {

    @Autowired
    private UsersService usersService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ReturnData result = usersService.loadUserByUsername(username);
        if (RspCodeEnum.NOTOKEN.getCode() == result.getStatus()) {
            throw new RrException(RspCodeEnum.ACCOUNT_NOT_EXIST.getDesc());
        }
        UserDetail userDetail = (UserDetail) result.getInfo();
        if (userDetail == null) {
            throw new RrException(RspCodeEnum.ACCOUNT_NOT_EXIST.getDesc());
        }
        //账号不可用
        if (userDetail.getStatus() == UserStatusEnum.DISABLE.getValue()) {
            userDetail.setEnabled(false);
        }
        return userDetail;
    }
}
