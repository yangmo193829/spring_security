package fun.zyjk.spring_security.sys.dao;

import fun.zyjk.spring_security.sys.entity.SysUsers;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

@Mapper
public interface SysUsersDao{
    SysUsers findByUsername(@Param("username") String username);
}
