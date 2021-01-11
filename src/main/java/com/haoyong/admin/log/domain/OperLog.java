package com.haoyong.admin.log.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  操作日志
 * @Author
 * @Date 2020-12-21
 */

@Entity
@Table ( name ="sys_oper_log" )
@EntityListeners(AuditingEntityListener.class)
public class OperLog implements Serializable {

	private static final long serialVersionUID = 1229683983626553344L;

	/**
	 * 主键ID
	 */
	@Id
   	@Column(name = "id" )
	private String id;

	/**
	 * 功能模块
	 */
   	@Column(name = "oper_module" )
	private String operModule;

	/**
	 * 操作类型
	 */
   	@Column(name = "oper_type" )
	private String operType;

	/**
	 * 操作描述
	 */
   	@Column(name = "oper_desc" )
	private String operDesc;

	/**
	 * 请求参数
	 */
   	@Column(name = "oper_requ_param" )
	private String operRequParam;

	/**
	 * 响应参数
	 */
   	@Column(name = "oper_resp_param" )
	private String operRespParam;

   	@Column(name = "oper_user_id")
	private String operUserId;

	/**
	 * 操作员名称
	 */
   	@Column(name = "oper_user_name" )
	private String operUserName;

	public String getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}

	/**
	 * 请求方法
	 */
   	@Column(name = "oper_method" )
	private String operMethod;

	/**
	 * 请求地址
	 */
   	@Column(name = "oper_uri" )
	private String operUri;

	/**
	 * ip
	 */
   	@Column(name = "oper_ip" )
	private String operIp;

	/**
	 * 操作时间
	 */
   	@Column(name = "oper_create_time",nullable = false,updatable = false )
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
	private Date operCreateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperModule() {
		return this.operModule;
	}

	public void setOperModule(String operModule) {
		this.operModule = operModule;
	}

	public String getOperType() {
		return this.operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getOperDesc() {
		return this.operDesc;
	}

	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}

	public String getOperRequParam() {
		return this.operRequParam;
	}

	public void setOperRequParam(String operRequParam) {
		this.operRequParam = operRequParam;
	}

	public String getOperRespParam() {
		return this.operRespParam;
	}

	public void setOperRespParam(String operRespParam) {
		this.operRespParam = operRespParam;
	}



	public String getOperUserName() {
		return this.operUserName;
	}

	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}

	public String getOperMethod() {
		return this.operMethod;
	}

	public void setOperMethod(String operMethod) {
		this.operMethod = operMethod;
	}

	public String getOperUri() {
		return this.operUri;
	}

	public void setOperUri(String operUri) {
		this.operUri = operUri;
	}

	public String getOperIp() {
		return this.operIp;
	}

	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}

	public Date getOperCreateTime() {
		return this.operCreateTime;
	}

	public void setOperCreateTime(Date operCreateTime) {
		this.operCreateTime = operCreateTime;
	}

	@Override
	public String toString() {
		return "TpApiConfig{" +
				"id='" + id + '\'' +
				"operModule='" + operModule + '\'' +
				"operType='" + operType + '\'' +
				"operDesc='" + operDesc + '\'' +
				"operRequParam='" + operRequParam + '\'' +
				"operRespParam='" + operRespParam + '\'' +
				"operUserId='" + operUserId + '\'' +
				"operUserName='" + operUserName + '\'' +
				"operMethod='" + operMethod + '\'' +
				"operUri='" + operUri + '\'' +
				"operIp='" + operIp + '\'' +
				"operCreateTime='" + operCreateTime + '\'' +
				'}';
	}

}
