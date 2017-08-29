/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.csair.wxopen.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public abstract class DataEntity<T>implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String remarks;	// 备注
//	protected User createBy;	// 创建者
	protected Date createDate;// 创建日期
//	protected User updateBy;	// 更新者
	protected Date updateDate;// 更新日期
	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）

	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	@PrePersist
	public void prePersist(){
		/*User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
			this.createBy = user;
		}*/
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	@PreUpdate
	public void preUpdate(){
		this.updateDate = new Date();
	}
	
	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

 
	// 删除标记（0：正常；1：删除；2：审核；）
		public static final String FIELD_DEL_FLAG = "delFlag";
		public static final String DEL_FLAG_NORMAL = "0";
		public static final String DEL_FLAG_DELETE = "1";
		public static final String DEL_FLAG_AUDIT = "2";
}
