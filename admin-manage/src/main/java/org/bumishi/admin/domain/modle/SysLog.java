package org.bumishi.admin.domain.modle;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * 操作日志
 *
 */
@Entity
public class SysLog implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5931501519732437251L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

	/** 用户ID */
	private int uid;

	private String user;

	/** 日志内容 */
	private String content;

	/** 用户操作 */
	private String operation;

	/** 创建时间 */
	private Date createTime;


	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
