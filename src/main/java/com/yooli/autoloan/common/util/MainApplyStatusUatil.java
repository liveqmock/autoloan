package com.yooli.autoloan.common.util;

import java.util.HashMap;
import java.util.Map;

public class MainApplyStatusUatil {
	
	//所有不允许操作的状态
	public static final String terminationStatus = "010064,010063,010062,010061,010053,010052,010051,010101,010102,010103,010104,010105,010106,010111,010112,010113,010114,010115";
	
	public static Map<String,Object> mainStatus(String statusCode){
		Map<String,Object> map = new HashMap<String,Object>();
        
		if(statusCode != null){
			boolean flog = false;
			String [] terminationStatuArr = terminationStatus.split(",");
			for (int i = 0; i < terminationStatuArr.length; i++) {
				if(terminationStatuArr[i].equals(statusCode)){
					flog = true;
					break;
				}
			}
			if(flog){
				map.put("code", "300");
				map.put("message", "当前进件已经被放弃或拒绝,抵押终止！");
			}else{
				map.put("code", "200");
				map.put("message", "允许操作！");
			}
		}else {
			map.put("code", "500");
			map.put("message", "系统异常！");
		}
		
		return map;
		
	}

}
