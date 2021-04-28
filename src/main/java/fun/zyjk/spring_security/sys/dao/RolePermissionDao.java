package fun.zyjk.spring_security.sys.dao;

import fun.zyjk.spring_security.sys.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionDao extends JpaRepository<RolePermission, Long> {

}