package com.yooli.autoloan.common.util;

import com.yooli.autoloan.common.PropertyFactory;


/**
 * 计算器工具集
 * @author TomXu
 * @since 2012-04-28
 */
public class Calculators {

	//计算坐标距离
	public static double positionDistanceCalculator(double sourceLng , double sorceLat , 
			double destLng , double destLat){
		//计算方式采用与直角坐标系中计算方式相同，纬度之差的平方加上经度之差的平方开方
		double delLng = Math.abs(sourceLng - destLng);
		double delLat = Math.abs(sorceLat - destLat);
		return Math.sqrt(delLng * delLng + delLat * delLat);
	}
	
	/**
	 * 
	 * Description: 计算进度
	 *
	 * @param rate:当前的进度；processName:节点名称；flag:加OR减，加传true,减传false；
	 * @return Integer，直接返回当前进度数字
	 * @throws 
	 * @Author TomXu
	 * Create Date: 2012-8-8 下午4:22:20
	 */
	public static Integer processRateCalculator(int rate , String processName , boolean  flag){
		Integer result = rate ;
		//获取节点权值
		String weightStr = PropertyFactory.getProperty(processName);
		if(weightStr != null){
			try{
				int weight = Integer.parseInt(weightStr);
				if(!flag){
					weight = 0 - weight;
				}
				result = rate + weight;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * Description: 根据中文比较值进行比较
	 *
	 * @param 
	 * @return Boolean
	 * @throws 
	 * @Author TomXu
	 * Create Date: 2012-12-11 下午8:06:02
	 */
	public static Boolean chineseCalculator(String chineseOperator , Double compareValueLeft , Double compareValueRight){
		//根据中文比较值比较
		if((chineseOperator.equals("大于") && (compareValueLeft > compareValueRight))
				|| (chineseOperator.equals("大于等于") && (compareValueLeft >= compareValueRight))
				|| (chineseOperator.equals("等于") && (compareValueLeft == compareValueRight))
				|| (chineseOperator.equals("小于等于") && (compareValueLeft <= compareValueRight))
				|| (chineseOperator.equals("小于") && (compareValueLeft < compareValueRight))){
			return true;
		} else {
			return false;
		}
	}
}
