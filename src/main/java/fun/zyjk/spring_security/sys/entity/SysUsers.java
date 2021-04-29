package fun.zyjk.spring_security.sys.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUsers {
//主键
    private Long id;
    //登陆用户名
    private String username;
    //真正用户名
    private String realname;
    //密码
    private String password;
    //性别
    private Integer gender;
    //手机号
    private String mobile;
    //
    private String email;
    //创建者
    private String createUser;
    //
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //是否可用
    private Integer isEnable;
}