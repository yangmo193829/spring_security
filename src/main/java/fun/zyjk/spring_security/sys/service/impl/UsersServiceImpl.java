package fun.zyjk.spring_security.sys.service.impl;

import fun.zyjk.spring_security.constant.RspCodeEnum;
import fun.zyjk.spring_security.sys.dao.SysPermissionDao;
import fun.zyjk.spring_security.sys.dao.SysUsersDao;
import fun.zyjk.spring_security.sys.entity.SysPermission;
import fun.zyjk.spring_security.sys.entity.SysUsers;
import fun.zyjk.spring_security.sys.service.UsersService;
import fun.zyjk.spring_security.user.UserDetail;
import fun.zyjk.spring_security.utils.ConvertUtils;
import fun.zyjk.spring_security.vo.ReturnData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private SysUsersDao sysUsersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysPermissionDao sysPermissionDao;

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return
     */
    @Override
    public ReturnData loadUserByUsername(String userName) {
        ReturnData ret = ReturnData.newInstance();
        SysUsers sysUsers = sysUsersDao.findByUsername(userName);
        if (sysUsers == null || sysUsers.getId() == null) {
            ret.setFailure("系统中无此用户");
            ret.setStatus(RspCodeEnum.NOTOKEN.getCode());
            return ret;
        }
        UserDetail userDetail = ConvertUtils.sourceToTarget(sysUsers, UserDetail.class);
        userDetail.setStatus(sysUsers.getIsEnable());
        // 查询此用户对应的权限（菜单）
        List<SysPermission> sysPermissions = sysPermissionDao.qryPermissionInfoByUserId(sysUsers.getId());
        Set<String> permsSet = new HashSet<>();
        sysPermissions.forEach(sysPermission -> {
            if (StringUtils.isNotBlank(sysPermission.getPermission())) {
                permsSet.add(sysPermission.getPermission());
            }
        });
        //封装权限标识
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(permsSet.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
        userDetail.setAuthorities(authorities);
        ret.setSuccess();
        ret.setInfo(userDetail);
        return ret;
    }
}
