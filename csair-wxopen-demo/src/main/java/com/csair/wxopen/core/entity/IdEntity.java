package com.csair.wxopen.core.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class IdEntity<T> extends DataEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	// JPA 主键标识, 策略为由数据库生成主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	public IdEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
