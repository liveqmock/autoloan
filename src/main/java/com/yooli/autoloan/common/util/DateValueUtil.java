
package com.yooli.autoloan.common.util;

import java.text.ParseException;




/** 
 *
 * Description: 
 *
 * @author Honest
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2012-11-7    Honest       1.0        1.0 Version 
 * </pre>
 */

public class DateValueUtil {

	public static long getDaySub(String beginDateStr,String endDateStr)
	{
	long day=0;
	java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
	java.util.Date beginDate;
	java.util.Date endDate;
	try
	{
	beginDate = format.parse(beginDateStr);
	endDate= format.parse(endDateStr); 
	day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000); 
	//System.out.println("相隔的天数="+day); 
	} catch (ParseException e)
	{
	// TODO 自动生成 catch 块
	e.printStackTrace();
	} 
	return day;
	}
	
}
