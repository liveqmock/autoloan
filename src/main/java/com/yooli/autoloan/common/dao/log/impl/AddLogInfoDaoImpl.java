package com.yooli.autoloan.common.dao.log.impl;

import org.springframework.stereotype.Component;

import com.yooli.autoloan.common.dao.ibatis.IBatisBaseDao;
import com.yooli.autoloan.common.dao.log.IAddLogInfoDao;
import com.yooli.autoloan.common.dao.log.domain.OperateLog;


@Component
public class AddLogInfoDaoImpl  extends IBatisBaseDao implements IAddLogInfoDao {
	
	/**
	 * 添加日志
	 * @param statmentName
	 * @param entity
	 */
	public void addLogInfo(String statementName,OperateLog entity){
		this.getSqlMapClientTemplate().insert(statementName, entity);
	}
}
