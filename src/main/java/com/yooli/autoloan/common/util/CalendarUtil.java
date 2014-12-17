package com.yooli.autoloan.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
* Description
*  
* 还款计划时间计算类
*@author wgs
*@createTime 2013-9-10上午10:07:48
*/
public class CalendarUtil {
	/**计算首个还款日10,20,30,28,29
	 * @param payDate
	 * @return
	 */
	public String getRealPayDate(String payDate){
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			rightNow.setTime(sdf.parse(payDate));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String  days="";
//		System.out.println("当前日期："+rightNow.get(rightNow.DATE));
//		System.out.println("当前月份："+rightNow.get(rightNow.MONTH)+1);
//		System.out.println("当前年份："+rightNow.get(rightNow.YEAR));
		//如果该日期为1-10之间的，则下月还款日为10
		if(rightNow.get(rightNow.DATE)>0 && rightNow.get(rightNow.DATE)<=10){
			days="10";
			//如果该日期为10-20之间的，则下月还款日为20
		}else if(rightNow.get(rightNow.DATE)>10 && rightNow.get(rightNow.DATE)<=20){
			days="20";
			//如果该日期为20-30之间的，则下月还款日为30
		}else if(rightNow.get(rightNow.DATE)>20 && rightNow.get(rightNow.DATE)<=31){
			days="30";
			if(rightNow.get(rightNow.MONTH)+2==2){//如果是2月份
				if(rightNow.get(rightNow.YEAR)%100==0){//能被100整除的
					if(rightNow.get(rightNow.YEAR)%400==0){//又能被400整除的
						//闰年
						days="29";
					}else{
						//平年
						days="28";
					}
				}else {//不能被100整除的
					if(rightNow.get(rightNow.YEAR)%4==0){//能被4整除的是闰年
						//闰年
						days="29";
					}else{
						//平年
						days="28";
					}
				}
			}
		}
		return days;
	}
	
	/**获得首个还款日的具体日期
	 * @param payDate
	 * @return
	 */
	public String getFistPayDay(String payDate){
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			rightNow.setTime(sdf.parse(payDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int year=rightNow.get(rightNow.YEAR);//获取该日期的年
		int month=rightNow.get(rightNow.MONTH)+1;//获取该日期的月
//		System.out.println(rightNow.get(rightNow.MONTH)+2);
		//如果下个月为第13个月,则给年份+1,月份为1
		if(rightNow.get(rightNow.MONTH)+2>12){
//			System.out.println(rightNow.get(rightNow.YEAR)+1);
			year=year+1;
			month=1;
		}else{
			month=rightNow.get(rightNow.MONTH)+2;
		}
		//获取放款日
		String days=this.getRealPayDate(payDate);
		return String.valueOf(year+"-"+month+"-"+days);
	}
	
	/**计算最后一个还款日
	 * @param payDate
	 * @return
	 */
	public String getLastPayDay(String payDate, int  periods){
		periods=periods-1;
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			rightNow.setTime(sdf.parse(payDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获得第一还款日的年
		int year=rightNow.get(rightNow.YEAR);
		//获得第一个还款日的月
		int month=rightNow.get(rightNow.MONTH)+1;
		
//		System.out.println((int)Math.floor(periods/12));
//		System.out.println(periods%12);
		//计算最后一个还款日的年
		int newYear=year+(int)Math.floor(periods/12);
		//计算最后一个还款日的月
		int newMonth=month+periods%12;
		//如果月份大约12，则进位为年
		if(newMonth>12){
			newMonth=newMonth%12;
			newYear=newYear+1;
		}
//		System.out.println(newYear+"-"+newMonth+"-"+this.getRealPayDate(payDate));
//		int day=rightNow.get(rightNow.DATE);
//		System.out.println("年："+year+"月："+month+"日："+day);
//		rightNow.set(year, month+periods, day);
//		System.out.println("新年："+rightNow.get(rightNow.YEAR)+"新月："+rightNow.get(rightNow.MONTH)+"新日："+rightNow.get(rightNow.DATE));
		//最后一个还款日的年+月+款款日
		return newYear+"-"+newMonth+"-"+this.getRealPayDate(payDate);
	}

}
