package com.yooli.autoloan.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	//排序字段名
	private String orderBy = null;	
	//排序方向
	private String order = null;
	//权限列表
	private List<String> autList = new ArrayList<String>();
	
	//用户权限范围内的营业部字符串，如果有多个以逗号分隔，首尾都没有逗号	
	private String authOfficeids;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getAutList() {
		return autList;
	}
	
	public void setAutList(List<String> autList) {
		this.autList = autList;
	}

	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

	public String getAuthOfficeids() {
		return authOfficeids;
	}

	public void setAuthOfficeids(String authOfficeids) {
		this.authOfficeids = authOfficeids;
	}	
}
