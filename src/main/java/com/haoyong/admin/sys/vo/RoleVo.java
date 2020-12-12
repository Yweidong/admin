package com.haoyong.admin.sys.vo;

import lombok.Data;

import java.util.Date;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-11 14:48
 **/
@Data
public class RoleVo {
    private String id;
    private String roleName;
    private Date gmtCreate;
    private Date gmtModified;
}
