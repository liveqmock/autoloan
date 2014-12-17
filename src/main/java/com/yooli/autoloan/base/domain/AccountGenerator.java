package com.yooli.autoloan.base.domain;

/**
 * 系统用户账号生成器
 * @author Mike.Lin
 * @createDate 2014年10月30日 下午5:09:45
 * @version 2014年10月30日
 */
public class AccountGenerator {

	public static Long baseAccount = 100000L;
	
	/**
	 * 采取自增的策略生成用户账号
	 * @author Mike.Lin
	 * @createDate 2014年10月30日 下午5:10:01
	 * @version 2014年10月30日
	 * @return
	 */
	public static Long generate(){
		synchronized (baseAccount) {
			return baseAccount++;
		}
	}
}
