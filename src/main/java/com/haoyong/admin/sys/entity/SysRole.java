package com.haoyong.admin.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author
 * @Date 2020-12-08
 */

@Entity
@Table ( name ="sys_role" )
public class SysRole  implements Serializable {

	private static final long serialVersionUID = 3457059794013334292L;

	@Id
   	@Column(name = "sr_id" )
	private String srId;

	/**
	 * 父角色
	 */
   	@Column(name = "parent_id" )
	private String parentId;

	/**
	 * 角色名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 角色英文名称
	 */
   	@Column(name = "enname" )
	private String enname;

	/**
	 * 备注
	 */
   	@Column(name = "description" )
	private String description;

   	@Column(name = "created" )
	private Date created;

   	@Column(name = "updated" )
	private Date updated;

	@JsonIgnoreProperties(value = { "roles" })
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "roles",fetch = FetchType.LAZY)
	Set<SysUser> users = new HashSet<>();

	@JsonIgnoreProperties(value = { "roles" })
	@ManyToMany(targetEntity = SysPermission.class,fetch = FetchType.EAGER)
	@JoinTable(name = "sys_role_permission",joinColumns = {

			@JoinColumn(name = "role_id",referencedColumnName = "sr_id")
	},inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "sp_id")})
	private Set<SysPermission>  permissions = new HashSet<>();




	public String getSrId() {
		return this.srId;
	}

	public void setSrId(String srId) {
		this.srId = srId;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnname() {
		return this.enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "SysRole{" +
				"srId='" + srId + '\'' +
				", parentId='" + parentId + '\'' +
				", name='" + name + '\'' +
				", enname='" + enname + '\'' +
				", description='" + description + '\'' +
				", created=" + created +
				", updated=" + updated +
				", permissions=" + permissions +
				'}';
	}
}
