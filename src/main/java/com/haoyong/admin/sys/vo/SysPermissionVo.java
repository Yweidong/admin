package com.haoyong.admin.sys.vo;

import com.haoyong.admin.common.pojo.PageCondition;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-08 14:02
 **/
@Data
public class SysPermissionVo extends PageCondition implements Serializable {
    private String spId;
    private String parentId;
    private String name;
    private String enname;
    private String url;
    private String description;
    private Date created;

    private Date updated;
    private List<SysPermissionVo> children;

    @Override
    public String toString() {
        return "SysPermissionVo{" +
                "spId='" + spId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", enname='" + enname + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
