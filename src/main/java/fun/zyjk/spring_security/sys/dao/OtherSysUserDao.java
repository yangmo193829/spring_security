package fun.zyjk.spring_security.sys.dao;

import fun.zyjk.spring_security.sys.entity.OtherSysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherSysUserDao extends JpaRepository<OtherSysUser, Long> {

    OtherSysUser findByUuidAndScope(String uuid, String scope);

}