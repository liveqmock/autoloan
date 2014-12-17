package com.yooli.autoloan.common.page;

import java.io.Serializable;

public class PageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int totalRows = 0;// 总记录数

	private int rowStart;// 起始位置
	
	private int pageSize = 10;// 每页面大小
	
	private String orderfield;//排序字段名
	
	public String getOrderfield() {
		return orderfield;
	}

	public void setOrderfield(String orderfield) {
		this.orderfield = orderfield;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	private String sortOrder;//排序标识


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getRowStart() {
		return rowStart;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

}
