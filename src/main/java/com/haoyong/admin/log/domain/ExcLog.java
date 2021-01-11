package com.haoyong.admin.log.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 错误日志
 * @Author
 * @Date 2020-12-21
 */

@Entity
@Table ( name ="sys_exc_log" )
@EntityListeners(AuditingEntityListener.class)
public class ExcLog implements Serializable {

	private static final long serialVersionUID = 807871192510105355L;

	/**
	 * 异常日志主键ID
	 */
	@Id
   	@Column(name = "id" )
	private String id;

	/**
	 * 请求参数
	 */
   	@Column(name = "exc_requ_param" )
	private String excRequParam;

	/**
	 * 异常名称
	 */
   	@Column(name = "exc_name" )
	private String excName;

	/**
	 * 异常信息
	 */
   	@Column(name = "exc_message" )
	private String excMessage;

	public String getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}

	@Column(name = "oper_user_id")
	private String operUserId;

	/**
	 * 操作员名称
	 */
   	@Column(name = "oper_user_name" )
	private String operUserName;

	/**
	 * 操作方法
	 */
   	@Column(name = "oper_method" )
	private String operMethod;

	/**
	 * 请求url
	 */
   	@Column(name = "oper_uri" )
	private String operUri;

	/**
	 * ip
	 */
   	@Column(name = "oper_ip" )
	private String operIp;

   	@Column(name = "oper_create_time" ,nullable = false,updatable = false)
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
	private Date operCreateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExcRequParam() {
		return this.excRequParam;
	}

	public void setExcRequParam(String excRequParam) {
		this.excRequParam = excRequParam;
	}

	public String getExcName() {
		return this.excName;
	}

	public void setExcName(String excName) {
		this.excName = excName;
	}

	public String getExcMessage() {
		return this.excMessage;
	}

	public void setExcMessage(String excMessage) {
		this.excMessage = excMessage;
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
				"excRequParam='" + excRequParam + '\'' +
				"excName='" + excName + '\'' +
				"excMessage='" + excMessage + '\'' +
				"operUserId='" + operUserId + '\'' +
				"operUserName='" + operUserName + '\'' +
				"operMethod='" + operMethod + '\'' +
				"operUri='" + operUri + '\'' +
				"operIp='" + operIp + '\'' +
				"operCreateTime='" + operCreateTime + '\'' +
				'}';
	}

}
