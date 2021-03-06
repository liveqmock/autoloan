package com.yooli.autoloan.common.dao.ibatis;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.yooli.autoloan.common.page.Entity;
import com.yooli.autoloan.common.page.PageBean;

public class IBatisBaseDao<T extends Entity, PK extends Serializable> extends IBatisSqlMapClientDaoSupport {
	
	/**
	 * 分页查询
	 * @param statementName
	 * @param skipResults
	 * @param maxResults
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryForList(String statementName,int skipResults,int maxResults){
		List list = null;
		list = this.getSqlMapClientTemplate().queryForList(statementName, skipResults, maxResults);
		return list;
	}
	/**
	 * 查询总条数
	 * @param statementName
	 * @param entity
	 * @return
	 */
	public int queryForCount(String statementName,Entity entity){
		int totalRows = 0;
		Object o = this.getSqlMapClientTemplate().queryForObject(
				statementName, entity);
		if (null != o) {
			totalRows = Integer.parseInt(o.toString());
		}
		return totalRows;
	}
	
	@Autowired
	public void setSqlMapClientForAutowire(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	/**
	 * 新增对象
	 *
	 * @param statementName
	 * @param entity
	 */
	protected Object save(final String statementName, final T entity)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().insert(statementName, entity);
	}

	/**
	 * 修改对象
	 *
	 * @param statementName
	 * @param entity
	 */
	protected int update(final String statementName, final T entity)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().update(statementName, entity);
	}

	/**
	 * 根据id删除对象
	 *
	 * @param statementName
	 * @param id
	 */
	protected int delete(final String statementName, final PK id)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().delete(statementName, id);
	}

	/**
	 * 根据id获取对象
	 *
	 * @param statementName
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T get(final String statementName, final PK id)
			throws DataAccessException {
		return (T) this.getSqlMapClientTemplate().queryForObject(statementName,
				id);
	}

	/**
	 * 根据对象条件获取列表
	 *
	 * @param statementName
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List get(final String statementName, final T entity) {
		return this.getSqlMapClientTemplate().queryForList(statementName,
				entity);
	}
	
	@SuppressWarnings("unchecked")
	protected List getList(final String statementName, final PK id) {
		return this.getSqlMapClientTemplate().queryForList(statementName, id);
	}

	/**
	 * 批量插入
	 *
	 * @param objList
	 *            插入对象类表
	 * @param statment
	 *            sqlID名称
	 * @param i
	 *            一次插入数量
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	protected void batchInsert(final List<T> objList, final String statment,
			final int i) throws DataAccessException {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public T doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				int batch = 0;
				for (T obj : objList) {
					executor.insert(statment, obj);
					batch++;
					if (batch == i) {
						executor.executeBatch();
						batch = 0;
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	/**
	 * 批量更新
	 *
	 * @param objList
	 *            更新对象类表
	 * @param statment
	 *            sqlID名称
	 * @param i
	 *            一次更新数量
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	protected void batchUpdate(final List<T> objList, final String statment,
			final int i) throws DataAccessException {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public T doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				int batch = 0;
				for (T obj : objList) {
					executor.update(statment, obj);
					batch++;
					if (batch == i) {
						executor.executeBatch();
						batch = 0;
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	protected List<T> wsForPaginatedList(PageBean page,
			final String statementCountName, final String statementName,
			T entity) {
		List<T> list = null;// 结果集
		int totalRows = 0;// 总记录数
		if (null != page.getOrderfield()) {
			entity.setOrder(page.getOrderfield());
			entity.setOrderBy(page.getSortOrder());
		}
		Object o = this.getSqlMapClientTemplate().queryForObject(
				statementCountName, entity);
		if (null != o) {
			totalRows = Integer.parseInt(o.toString());
		}
		page.setTotalRows(totalRows);
		if (totalRows > 0) {
			list = this.getSqlMapClientTemplate().queryForList(statementName,
					entity, page.getRowStart(), page.getPageSize());
		}
		return list;
	}

	
	
}
