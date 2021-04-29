package fun.zyjk.spring_security.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherSysUser {
    private Long id;
    // 第三方系统
    private String scope;
    // 第三方系统唯一账户
    private String uuid;
    // 关联系统用户表的id
    private Long userId;
    // 本系统登陆的用户名
    private String username;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}