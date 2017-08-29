package com.hj.entity;

import java.util.List;

/**
 * 返回报文信息 报文头部+报文体
 * 
 * @author lenovo
 *
 */
public class Head {

	private String sqlId; // 查询的sqlId

	private String requestStr; // 请求的xml字符串

	private String sender; // 发送者

	private String receiver; // 接受者

	private String functionName; // 方法名  

	private String status; // 状态   failure、 success

	private String time; // 时间
	
	private String itemsName;  //列表节点名称    此字段如果为空，则代表无报文体，只有头部
	
	private String headName;// 头部节点名称
	
	
	private List<Object> bodyList; // 报文体集合
	
	
	/**
	 * 特殊接口报文头部的属性 
	 * 扩展：如果后续特殊接口的头部属性比较多，建议集合动态保存
	 */
	private String total;   // 属于特殊接口的属性  受影响总数
	
	private String breakStr; // 属于特殊接口的属性  受影响总数

	public Head() {
		super();
	}

	public List<Object> getBodyList() {
		return bodyList;
	}



	public String getBreakStr() {
		return breakStr;
	}
	
	
	
	public String getFunctionName() {
		return functionName;
	}
	
	public String getHeadName() {
		return headName;
	}
	
	public String getItemsName() {
		return itemsName;
	}
	
	public String getReceiver() {
		return receiver;
	}

	public String getRequestStr() {
		return requestStr;
	}
	
	public String getSender() {
		return sender;
	}
	
	
	
	
	public String getSqlId() {
		return sqlId;
	}


	public String getStatus() {
		return status;
	}


	public String getTime() {
		return time;
	}


	public String getTotal() {
		return total;
	}


	public void setBodyList(List<Object> bodyList) {
		this.bodyList = bodyList;
	}



	public void setBreakStr(String breakStr) {
		this.breakStr = breakStr;
	}


	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}


	public void setHeadName(String headName) {
		this.headName = headName;
	}


	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public void setRequestStr(String requestStr) {
		this.requestStr = requestStr;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public void setTotal(String total) {
		this.total = total;
	}
 
}
