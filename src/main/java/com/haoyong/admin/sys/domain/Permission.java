package com.haoyong.admin.sys.domain;

import com.haoyong.admin.Enum.DelStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author
 * @Date 2020-12-09
 */

@Entity
@Table ( name ="sys_permission" )
public class Permission implements Serializable {

	private static final long serialVersionUID = 965968918630493846L;

	/**
	 * 编号
	 */
	@Id
   	@Column(name = "id" )
	private String id;

	/**
	 * 所属上级
	 */
   	@Column(name = "pid" )
	private String pid;

	/**
	 * 名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 类型(1:菜单,2:按钮)
	 */
   	@Column(name = "type" )
	private Integer type;

	/**
	 * 权限值
	 */
   	@Column(name = "permission_value" )
	private String permissionValue;

	/**
	 * 访问路径
	 */
   	@Column(name = "path" )
	private String path;

	/**
	 * 组件路径
	 */
   	@Column(name = "component" )
	private String component;

	/**
	 * 图标
	 */
   	@Column(name = "icon" )
	private String icon;

	/**
	 * 状态(0:禁止,1:正常)
	 */
   	@Column(name = "status" )
	private Integer status;

	/**
	 * 逻辑删除 1（true）已删除， 0（false）未删除
	 */
//
//   	@Column(name = "is_deleted" )
//	private Integer isDeleted;

	/**
	 * 创建时间
	 */
   	@Column(name = "gmt_create" )
	private Date gmtCreate;

	/**
	 * 更新时间
	 */
   	@Column(name = "gmt_modified" )
	private Date gmtModified;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPermissionValue() {
		return this.permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return this.gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	public String toString() {
		return "TpApiConfig{" +
				"id='" + id + '\'' +
				"pid='" + pid + '\'' +
				"name='" + name + '\'' +
				"type='" + type + '\'' +
				"permissionValue='" + permissionValue + '\'' +
				"path='" + path + '\'' +
				"component='" + component + '\'' +
				"icon='" + icon + '\'' +
				"status='" + status + '\'' +
//				"isDeleted='" + isDeleted + '\'' +
				"gmtCreate='" + gmtCreate + '\'' +
				"gmtModified='" + gmtModified + '\'' +
				'}';
	}

}
