package fun.zyjk.spring_security.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private Long id;
    //
    private Long userId;
    //
    private Long roleId;
}