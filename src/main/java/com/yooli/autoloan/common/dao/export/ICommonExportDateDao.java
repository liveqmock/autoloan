package com.yooli.autoloan.common.dao.export;

import java.util.List;

import com.yooli.autoloan.common.page.Entity;


public interface ICommonExportDateDao {
	
	/**
	 * 分批查询导出数据
	 * @param statementName
	 * @param skipResults
	 * @param maxResults
	 * @return
	 */
	public List getExportDate(String statementName,Entity entity,int skipResults,int maxResults);
	
}
