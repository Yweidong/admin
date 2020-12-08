package com.haoyong.admin.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description
 * @Author
 * @Date 2020-12-08
 */

@Entity
@Table ( name ="sys_user_role" )
public class SysUserRole  implements Serializable {

	private static final long serialVersionUID = 4518610032002910313L;

	@Id
   	@Column(name = "sur_id" )
	private String surId;

	/**
	 * 用户 ID
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 角色 ID
	 */
   	@Column(name = "role_id" )
	private String roleId;

	public String getSurId() {
		return this.surId;
	}

	public void setSurId(String surId) {
		this.surId = surId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "SysUserRole{" +
				"surId='" + surId + '\'' +
				", userId='" + userId + '\'' +
				", roleId='" + roleId + '\'' +
				'}';
	}
}
