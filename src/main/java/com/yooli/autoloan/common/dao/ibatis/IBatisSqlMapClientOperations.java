package com.yooli.autoloan.common.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientOperations;

public interface IBatisSqlMapClientOperations extends SqlMapClientOperations {

	
	public List queryForPaginatedList(final String statementName, final Object parameterObject) throws DataAccessException;
	
	public Object queryForPaginatedCount(final String statementName, final Object parameterObject) throws DataAccessException;
	
	
}
