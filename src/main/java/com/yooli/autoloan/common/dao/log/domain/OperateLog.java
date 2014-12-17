package com.yooli.autoloan.common.dao.log.domain;

import com.yooli.autoloan.common.page.Entity;


public class OperateLog extends Entity {
	private static final long serialVersionUID = -8592001234453997072L;
	
	// 1 日志ID
	private Integer logId;
	// 2 操作人ID
	private Integer logOperatorId;
	// 3 操作人姓名
	private String logOperatorName;
	// 4 操作人所在办公室
	private String logOpOffice;
	// 5 操作人角色
	private String logOpRole;
	// 6 操作对象
	private String logOpObject;
	// 7 操作类型
	private Integer logOpType;
	// 8 更新后数据
	private String logUpatedData;
	// 9 操作结果
	private Integer logOpResult;
	// 10 操作时间 
	private String logOpTime;
	
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getLogOperatorId() {
		return logOperatorId;
	}
	public void setLogOperatorId(Integer logOperatorId) {
		this.logOperatorId = logOperatorId;
	}
	public String getLogOperatorName() {
		return logOperatorName;
	}
	public void setLogOperatorName(String logOperatorName) {
		this.logOperatorName = logOperatorName;
	}
	public String getLogOpOffice() {
		return logOpOffice;
	}
	public void setLogOpOffice(String logOpOffice) {
		this.logOpOffice = logOpOffice;
	}
	public String getLogOpRole() {
		return logOpRole;
	}
	public void setLogOpRole(String logOpRole) {
		this.logOpRole = logOpRole;
	}
	public String getLogOpObject() {
		return logOpObject;
	}
	public void setLogOpObject(String logOpObject) {
		this.logOpObject = logOpObject;
	}
	public Integer getLogOpType() {
		return logOpType;
	}
	public void setLogOpType(Integer logOpType) {
		this.logOpType = logOpType;
	}
	public String getLogUpatedData() {
		return logUpatedData;
	}
	public void setLogUpatedData(String logUpatedData) {
		this.logUpatedData = logUpatedData;
	}
	public Integer getLogOpResult() {
		return logOpResult;
	}
	public void setLogOpResult(Integer logOpResult) {
		this.logOpResult = logOpResult;
	}
	public String getLogOpTime() {
		return logOpTime;
	}
	public void setLogOpTime(String logOpTime) {
		this.logOpTime = logOpTime;
	}
	
	
	
}
