package com.yooli.autoloan.common.dao.log;

import com.yooli.autoloan.common.dao.log.domain.OperateLog;


public interface IAddLogInfoDao{

	/**
	 * 添加日志
	 * @param statmentName
	 * @param entity
	 */
	public void addLogInfo(String statementName,OperateLog entity);
}
