package com.haoyong.admin.sys.domain;

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
@Table ( name ="sys_user" )
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

	private static final long serialVersionUID = 7398247899940005740L;

	/**
	 * 会员id
	 */
	@Id
   	@Column(name = "id" )
	private String id;

	/**
	 * 微信openid
	 */
   	@Column(name = "username" )
	private String username;

	/**
	 * 密码
	 */
   	@Column(name = "password" )
	private String password;

	/**
	 * 昵称
	 */
   	@Column(name = "nick_name" )
	private String nickName;

	/**
	 * 用户头像
	 */
   	@Column(name = "salt" )
	private String salt;

	/**
	 * 用户签名
	 */
   	@Column(name = "token" )
	private String token;

	/**
	 * 逻辑删除 1（true）已删除， 0（false）未删除
	 */
	@Enumerated(EnumType.STRING)
   	@Column(name = "is_deleted" )
	private DelStatus isDeleted;

	/**
	 * 创建时间
	 */
   	@Column(name = "gmt_create" )
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

	public User() {
	}


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public DelStatus getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(DelStatus isDeleted) {
		this.isDeleted = isDeleted;
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
				"username='" + username + '\'' +
				"password='" + password + '\'' +
				"nickName='" + nickName + '\'' +
				"salt='" + salt + '\'' +
				"token='" + token + '\'' +
				"isDeleted='" + isDeleted + '\'' +
				"gmtCreate='" + gmtCreate + '\'' +
				"gmtModified='" + gmtModified + '\'' +
				'}';
	}

}
