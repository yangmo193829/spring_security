package fun.zyjk.spring_security.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole {
    //主键
    private Long id;
    //角色名称
    private String name;
    //角色code
    private String roleCode;
    //说明
    private String remark;
    //排序
    private Integer sort;
    //删除标识  0：未删除    1：删除
    private Integer delFlag;
    //创建者
    private String creator;
    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //
    private String updater;
    //更新时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
}