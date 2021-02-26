package com.haoyong.admin.sys.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-09 09:59
 **/
@Data
public class PermissionVo {
    private String id;
    private String pid;
    private String name;
    private Integer type;
    private String permissionValue;
    private String path;
    private String component;
    private String icon;
    private Integer status;
    private Integer level;

    private boolean isSelect;
    private Date gmtCreate;

    private Date gmtModified;

    private List<PermissionVo> children;//如果是父类，这里存孩子节点


}
