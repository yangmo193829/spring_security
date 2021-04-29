package fun.zyjk.spring_security.sys.dao;

import fun.zyjk.spring_security.sys.entity.SysPermission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionDao  {

    List<SysPermission> qryPermissionInfoByUserId(Long userId);

}