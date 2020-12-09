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
@Table ( name ="sys_role" )
public class Role implements Serializable {

	private static final long serialVersionUID = 952671462985014941L;

	/**
	 * 角色id
	 */
	@Id
   	@Column(name = "id" )
	private String id;

	/**
	 * 角色名称
	 */
   	@Column(name = "role_name" )
	private String roleName;

	/**
	 * 角色编码
	 */
   	@Column(name = "role_code" )
	private String roleCode;

	/**
	 * 备注
	 */
   	@Column(name = "remark" )
	private String remark;

	/**
	 * 逻辑删除 1（true）已删除， 0（false）未删除
	 */
//	@Enumerated(EnumType.STRING)
//   	@Column(name = "is_deleted" )
//	private DelStatus isDeleted;

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

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
				"roleName='" + roleName + '\'' +
				"roleCode='" + roleCode + '\'' +
				"remark='" + remark + '\'' +
//				"isDeleted='" + isDeleted + '\'' +
				"gmtCreate='" + gmtCreate + '\'' +
				"gmtModified='" + gmtModified + '\'' +
				'}';
	}

}
