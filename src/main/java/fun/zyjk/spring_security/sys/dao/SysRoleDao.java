package fun.zyjk.spring_security.sys.dao;

import fun.zyjk.spring_security.sys.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleDao extends JpaRepository<SysRole, Long> {

}