package com.yooli.autoloan.common.export;
import javax.servlet.http.HttpServletResponse;

import com.yooli.autoloan.common.page.Entity;


public interface ICommonExportService {

	/**
	 * 向页面导出数据
	 * @param statementName
	 */
	public void exportDate(String statementName,Entity entity,HttpServletResponse response,String tableHead,String field);
	
}
