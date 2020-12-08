package com.haoyong.admin.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haoyong.admin.Enum.DelStatus;

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
@Table ( name ="sys_user" )
public class SysUser  implements Serializable {

	private static final long serialVersionUID = 3814361084052500478L;

	/**
	 * 用户id
	 */
	@Id
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 用户名称
	 */
   	@Column(name = "username" )
	private String username;

	/**
	 * 手机号
	 */
   	@Column(name = "phone" )
	private String phone;

	/**
	 * 登录密码
	 */
   	@Column(name = "password" )
	private String password;

	/**
	 * 软删除标识，Y/N
	 */
	@Enumerated(EnumType.STRING)
   	@Column(name = "valid" )
	private DelStatus valid;

   	@Column(name = "openid" )
	private String openid;

   	@Column(name = "unionid" )
	private String unionid;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 修改时间
	 */
   	@Column(name = "update_time" )
	private Date updateTime;


	@JsonIgnoreProperties(value = { "users" })
	@ManyToMany(targetEntity = SysRole.class,fetch = FetchType.EAGER)
	@JoinTable(name = "sys_user_role",joinColumns = {

			@JoinColumn(name = "user_id",referencedColumnName = "user_id")
	},inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "sr_id")})
	private Set<SysRole> roles = new HashSet<>();


	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DelStatus getValid() {
		return valid;
	}

	public void setValid(DelStatus valid) {
		this.valid = valid;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "SysUser{" +
				"userId='" + userId + '\'' +
				", username='" + username + '\'' +
				", phone='" + phone + '\'' +
				", password='" + password + '\'' +
				", valid=" + valid +
				", openid='" + openid + '\'' +
				", unionid='" + unionid + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", roles=" + roles +
				'}';
	}
}
