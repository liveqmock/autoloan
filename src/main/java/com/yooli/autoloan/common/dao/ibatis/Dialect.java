package com.yooli.autoloan.common.dao.ibatis;

/**
 * 方言接口
 * @author crazy
 */
public abstract class Dialect {

	public abstract boolean supportsLimit();

    public abstract boolean supportsLimitOffset();


    /**
     *
     * getLimitString(sql,offset,String.valueOf(offset),limit,String.valueOf(limit))
     */
    public String getLimitString(String sql, int offset, int limit) {
    	return getLimitString(sql,offset,Integer.toString(offset),limit,Integer.toString(limit));
    }
    /**
     *
     * <pre>
     * ysql
     * dialect.getLimitString("select * from user", 12, ":offset",0,":limit")
     * select * from user limit :offset,:limit
     * </pre>
     * @return 〉sql
     */
    public abstract String getLimitString(String sql, int offset,String offsetPlaceholder, int limit,String limitPlaceholder);

}
