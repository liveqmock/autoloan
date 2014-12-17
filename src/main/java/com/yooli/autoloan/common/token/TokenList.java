package com.yooli.autoloan.common.token;

import java.util.LinkedList;

public class TokenList extends LinkedList<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7983405826317017175L;

	private static TokenList tokenList;

	private TokenList() {
	}

	/**
	 * 获取token列表
	 * 
	 * @return
	 */
	public synchronized static TokenList getInstance() {
		if (tokenList == null) {
			tokenList = new TokenList();
		}
		return tokenList;
	}
}
