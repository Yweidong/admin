package com.haoyong.admin.sys.vo;

import com.haoyong.admin.common.pojo.PageCondition;
import com.haoyong.admin.sys.entity.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-08 11:55
 **/
@Data
public class SysUserVo extends PageCondition implements Serializable {
    private String userId;
    private String username;
    private String phone;
    private String password;
    private Date createTime;
    private Date updateTime;
    private Set<SysRoleVo> roles;
}
