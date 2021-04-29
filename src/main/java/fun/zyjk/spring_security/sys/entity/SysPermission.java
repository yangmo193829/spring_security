package fun.zyjk.spring_security.sys.entity;

import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.TemporalType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SysPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //主键
    private Long id;
    //父id
    private Long pid;
    //菜单名称
    private String name;
    //类型   0：菜单   1：按钮
    private Integer type;
    //
    private Integer sort;
    //菜单URL
    private String url;
    //权限标识，如：sys:menu:save
    private String permission;
    //菜单图标
    private String icon;
    //状态
    private Integer status;
    //说明
    private String remark;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    //创建者
    private String creator;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    //更新人
    private String updater;
}