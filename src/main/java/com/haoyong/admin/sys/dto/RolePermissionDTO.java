package com.haoyong.admin.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-09 14:20
 **/
@Data
@ApiModel
public class RolePermissionDTO {
    @ApiModelProperty(name = "roleid",value = "角色id",required = true)
    private String roleid;

    @ApiModelProperty(name = "permissionid",value = "权限id集合，用逗号隔开")
    private String permissionid;
}
