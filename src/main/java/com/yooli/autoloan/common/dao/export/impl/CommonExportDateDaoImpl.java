package com.yooli.autoloan.common.dao.export.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.yooli.autoloan.common.dao.export.ICommonExportDateDao;
import com.yooli.autoloan.common.dao.ibatis.IBatisBaseDao;
import com.yooli.autoloan.common.page.Entity;


@Component
public class CommonExportDateDaoImpl extends IBatisBaseDao implements ICommonExportDateDao {
	
	/**
	 * 分批查询导出数据
	 * @param statementName
	 * @param skipResults
	 * @param maxResults
	 * @return
	 */
	public List getExportDate(String statementName,Entity entity,int skipResults,int maxResults){
		List list = null;
		list = this.getSqlMapClientTemplate().queryForList("accountMgt." + statementName,entity,skipResults,maxResults);
		return list;
	}

}
