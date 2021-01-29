package com.haoyong.admin.sys.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.haoyong.admin.Enum.DelStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author
 * @Date 2020-12-09
 */

@Entity
@Table ( name ="sys_user_role" )
@EntityListeners(AuditingEntityListener.class)
public class UserRole implements Serializable {

	private static final long serialVersionUID = 8055644129814971933L;

	/**
	 * 主键id
	 */
	@Id

   	@Column(name = "id" )
	private String id;

	/**
	 * 角色id
	 */
   	@Column(name = "role_id" )
	private String roleId;

	/**
	 * 用户id
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 逻辑删除 1（true）已删除， 0（false）未删除
	 */
//	@Enumerated(EnumType.STRING)
//   	@Column(name = "is_deleted" )
//	private DelStatus isDeleted;


	
	/**
	 * 创建时间
	 */
   	@Column(name = "gmt_create" ,nullable = false,updatable = false)
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
	private Date gmtCreate;

	/**
	 * 更新时间
	 */
   	@Column(name = "gmt_modified" )
	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
	private Date gmtModified;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
				"roleId='" + roleId + '\'' +
				"userId='" + userId + '\'' +
//				"isDeleted='" + isDeleted + '\'' +
				"gmtCreate='" + gmtCreate + '\'' +
				"gmtModified='" + gmtModified + '\'' +
				'}';
	}

}
