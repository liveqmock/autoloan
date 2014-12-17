package com.yooli.autoloan.common.token;

import java.util.UUID;

public class Token {

	//public static final String TOKEN_STRING_NAME = "token";

	private static Object lock = new Object();

	private static void saveTokenString(String tokenStr) {
		TokenList tokenList = TokenList.getInstance();
		tokenList.add(tokenStr);
	}

	/**
	 * 生成token令牌值
	 * 
	 * @return
	 */
	private static String generateTokenString() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 获取token令牌
	 * 
	 * @return
	 */
	public static String getTokenString() {
		String tokenStr = generateTokenString();
		saveTokenString(tokenStr);
		return tokenStr;
	}

	/**
	 * 验证token是否有效
	 * 
	 * @param tokenStr
	 * @return
	 */
	public static boolean isTokenStringValid(String tokenStr) {
		boolean valid = false;
		synchronized (lock) {
			TokenList tokenList = TokenList.getInstance();
			if (tokenList.contains(tokenStr)) {
				valid = true;
				tokenList.remove(tokenStr);
			}
		}
		return valid;
	}
}