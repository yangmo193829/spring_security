package fun.zyjk.spring_security.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {
    private Long id;
    //
    private Long roleId;
    //
    private Long permissionId;
}