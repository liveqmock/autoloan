package com.yooli.autoloan.common.util;

import java.math.BigDecimal;

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
 * 2013-5-3    Honest       1.0        1.0 Version 
 * </pre>
 */

public class BigDeUtil {
	
	public  static  String transitionStr(Double value){
		String num  = Double.toString(value);
		BigDecimal bdevaluateSum = new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP);
		String sumString = bdevaluateSum.toString();
		return sumString;
	}


}
