package fun.zyjk.spring_security.sys.dao;

import fun.zyjk.spring_security.sys.entity.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUsersDao extends JpaRepository<SysUsers, Long> {

    SysUsers findByUsername(String username);




}
