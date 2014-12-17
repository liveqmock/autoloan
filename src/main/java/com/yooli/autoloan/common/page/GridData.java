package com.yooli.autoloan.common.page;

import java.util.List;

/**
 * json数据封装类
 * @author Administrator
 *
 */
public class GridData {
	private List Rows;
	private int Total;
	public List getRows() {
		return Rows;
	}
	public void setRows(List rows) {
		Rows = rows;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		Total = total;
	}
	
}
