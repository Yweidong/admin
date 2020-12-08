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
@Table ( name ="sys_permission" )
public class SysPermission  implements Serializable {

	private static final long serialVersionUID = 7304521004685398828L;

	@Id
   	@Column(name = "sp_id" )
	private String spId;

	/**
	 * 父权限
	 */
   	@Column(name = "parent_id" )
	private String parentId;

	/**
	 * 权限名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 权限英文名称
	 */
   	@Column(name = "enname" )
	private String enname;

	/**
	 * 授权路径
	 */
   	@Column(name = "url" )
	private String url;

	/**
	 * 备注
	 */
   	@Column(name = "description" )
	private String description;

   	@Column(name = "created" )
	private Date created;

   	@Column(name = "updated" )
	private Date updated;

	@JsonIgnoreProperties(value = { "permissions" })
	@ManyToMany(targetEntity = SysRole.class,mappedBy = "permissions",fetch = FetchType.LAZY)
	Set<SysRole> roles = new HashSet<>();

	public String getSpId() {
		return this.spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		return "SysPermission{" +
				"spId='" + spId + '\'' +
				", parentId='" + parentId + '\'' +
				", name='" + name + '\'' +
				", enname='" + enname + '\'' +
				", url='" + url + '\'' +
				", description='" + description + '\'' +
				", created=" + created +
				", updated=" + updated +
				", roles=" + roles +
				'}';
	}
}
