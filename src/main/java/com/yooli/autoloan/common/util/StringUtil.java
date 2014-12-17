package com.yooli.autoloan.common.util;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 关于字符串处理的一些工具
 * @since 2012-05-21
 * @author TomXu
 *
 */
public class StringUtil {

	/**
	 * 菜单处理，从UC提供的菜单树种获取某个名称的子节点
	 * @param menuName 菜单名称
	 * @param uCMenuTree UC提供的菜单树
	 * @return 返回从<tree ... </tree>的节点字符串
	 */
	public static String getMenuString(String menuName , String uCMenuTree){
		
		try{
			int a = uCMenuTree.indexOf(menuName);
			if(a == -1){
				return "";
			}
			String temp = uCMenuTree.substring(0, a);
			int b = temp.lastIndexOf("<tree");
			String tempa = uCMenuTree.substring(b);
			int c = tempa.indexOf("</tree>");
			c = c + 7;
			return tempa.substring(0 , c);
		} catch(Exception e){
			return "";
		}
	}
	
	/**
	 * 根据字符串List拼接XML
	 * @param strList
	 * @return
	 */
	public static String getXMLByString(List<String> strList){
		String result = "";
		if(strList != null && strList.size() > 0){
			for(int i = 0 ; i < strList.size() ; i ++){
				//第一条，加上XML的开头
				if(i == 0 ){
					result += "<?xml version=\"1.0\" encoding=\"utf-8\"?><root>";
				}
				result += strList.get(i);
			}
		}
		result += "</root>";
		return result;
	}
	
	public static String processJSON(String illegalJSON){
		//先去掉开头部分的children：{
		if(illegalJSON.startsWith("{children:{")){
			illegalJSON = illegalJSON.substring(10, illegalJSON.length() - 1);
			//开头结尾增加中括号
			if(illegalJSON.startsWith("{")){
				illegalJSON = "[" + illegalJSON + "]";
			}
		}
		//检查是否有children 没有带中括号的
		int a = illegalJSON.indexOf("children:{");
		if(a != -1){
			int b = illegalJSON.indexOf("}" , a);
			//将中间部分截出来
			String temp = illegalJSON.substring(a + 9 , b + 1);
			return illegalJSON.replace(temp, "[" + temp + "]");
		} else {
			return illegalJSON;
		}
	}
	
	//判断一个字符串是否全部为汉字
	public static boolean checkChinaChar(String str){
		for(int i = 0 ;i<str.length();i++){
			char ch = str.charAt(i);
			if(!checkChar(ch)){
				return false;
			}
		}
		return true;
		
	}
	//判断一个字符是否为汉字
	public static boolean checkChar(char oneChar){
		if((oneChar >= '\u4e00' && oneChar <= '\u9fa5')
		||(oneChar >= '\uf900' && oneChar <='\ufa2d'))
		return true;
		return false;
	}
	//判断字符串是否为数字
	public static boolean isNumeric(String str){
		if(str.matches("\\d*")){
			return true;
		}else{
			return false;
		}
	}
	//判断数组中是否有重复值
	public static boolean checkRepeat(String[] array){
	    Set<String> set = new HashSet<String>();
	    for(String str : array){
	        set.add(str);
	    }
	    if(set.size() != array.length){
	        return false;//有重复
	    }else{
	        return true;//不重复
	    }
	 
	}
	
	//判断是否为一个字母
	public static boolean checkOneLetter(String   oneStr)  
    {
        if(1 == oneStr.length()){
        	char   c   =   oneStr.charAt(0);
        	if(((c>='a'&&c<='z')   ||   (c>='A'&&c<='Z')))   
            {   
                return true;
            }
        }
        return   false;
    }
	
	//UTF-8转换为ISO-8859-1
	public static String toISO88591(String str) {  
        try {  
            str = new String(str.getBytes("UTF-8"), "ISO-8859-1");  
        } catch (UnsupportedEncodingException ex) {  
            ex.printStackTrace();  
        }  
        return str;  
    }
}
