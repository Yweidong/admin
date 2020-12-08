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
@Table ( name ="sys_role_permission" )
public class SysRolePermission  implements Serializable {

	private static final long serialVersionUID = 6396423245672646058L;

	@Id
   	@Column(name = "srp_id" )
	private String srpId;

	/**
	 * 角色 ID
	 */
   	@Column(name = "role_id" )
	private String roleId;

	/**
	 * 权限 ID
	 */
   	@Column(name = "permission_id" )
	private String permissionId;

	public String getSrpId() {
		return this.srpId;
	}

	public void setSrpId(String srpId) {
		this.srpId = srpId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public String toString() {
		return "SysRolePermission{" +
				"srpId='" + srpId + '\'' +
				", roleId='" + roleId + '\'' +
				", permissionId='" + permissionId + '\'' +
				'}';
	}
}
