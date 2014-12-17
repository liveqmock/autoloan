package com.yooli.autoloan.common.scheduals;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.yooli.autoloan.common.PropertyFactory;

@Component
public class ScheduledJobs {

	//日志
	private Logger log = Logger.getLogger(ScheduledJobs.class);

	
	
	/**
	 * 
	 * 定时器每天凌晨零点，查询是否有需要进行更新的数据
	 */
	//@Scheduled(cron="0 1/5 2-6 * * ?")
	//@Scheduled(cron="0 1/2 14-17 * * ?")
	//@Scheduled(cron="0 0 1 * * ?")
	void autoAllocateOffice(){
		//当定时器打开的时候开始执行任务
		if((PropertyFactory.getProperty("timer.switch.automatic.back")).equals("1")){
		log.error("定时器执行中........");
		}
	}
	
	
	
	/**
	* Description
	*  保单扫描的定时器，用来扫描保单已经到了生效日期但未续保的保单状态修改
	*@return void
	*@author wgs
	*@createTime 2013-12-2
	*/
	//@Scheduled(cron="0 0/1 * * * ?")
//	@Scheduled(cron="0 1 0 * * ?")
	void insurancePolicy(){
		//当定时器打开的时候开始执行任务
		if((PropertyFactory.getProperty("timer.switch.insurancePolicy.back")).equals("1")){
		log.error("保单定时器执行中........");
		}
	}
}
