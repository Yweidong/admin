package com.haoyong.admin.sys.vo;

import com.haoyong.admin.common.pojo.PageCondition;
import com.haoyong.admin.sys.entity.SysPermission;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-08 13:59
 **/
@Data
public class SysRoleVo extends PageCondition implements Serializable {
    private String srId;
    private String parentId;
    private String name;
    private String enname;
    private String description;
    private Date created;

    private Date updated;
    private Set<SysPermissionVo> permissions;
}
