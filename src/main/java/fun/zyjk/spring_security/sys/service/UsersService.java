package fun.zyjk.spring_security.sys.service;

import fun.zyjk.spring_security.vo.ReturnData;

public interface UsersService {

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return
     */
    ReturnData loadUserByUsername(String userName);




}
