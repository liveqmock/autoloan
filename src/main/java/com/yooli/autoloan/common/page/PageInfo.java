package com.yooli.autoloan.common.page;

/*
 * 分页工具类
 */
public class PageInfo {

	private Integer rowStart;
	
	private Integer rowEnd;

	// 每页行数
	private Integer numPerPage;

	// 页号
	private Integer pageNum;

	// 排序字段
	private String orderField;

	// 总条数
	private Integer totalCount;

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public void setRowStart(Integer rowStart) {
		this.rowStart = rowStart;
	}

	public Integer getRowStart() {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 10;
		}
		rowStart = (pageNum - 1) * numPerPage;

		return rowStart;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getRowEnd() {
		rowEnd = this.getRowStart() + numPerPage-1;
				
		return rowEnd;
	}

	public void setRowEnd(Integer rowEnd) {
		this.rowEnd = rowEnd;
	}
}
