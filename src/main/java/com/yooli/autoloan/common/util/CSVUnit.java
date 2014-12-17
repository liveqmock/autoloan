/* 
 * Copyright (C) 2006-2012 普信恒业科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: CSVUnit.java 
 *
 * Created: [2012-7-24 下午02:44:45] by pgq 
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: AutoLoan 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.yooli.autoloan.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.yooli.autoloan.common.PropertyFactory;


/**
 * 
 * Description:
 * 
 * @author pgq
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2012-7-24    pgq       1.0        1.0 Version
 * </pre>
 */

public class CSVUnit {
	private Logger log = Logger.getLogger(CSVUnit.class);
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> CSVtoDemo(String name, Reader file)
			throws Exception {
		Class<?> classcode = Class.forName(name);// 加载Bean的CLASS
		Field[] f = classcode.getDeclaredFields();// 得到bean的属性
		List<Object> listt = new ArrayList<Object>();
		BufferedReader rb = new BufferedReader(file);// 得到文件FileReader
		boolean poing = true; 	
		int lineCount = 0;  //文件行数
		if(rb.readLine() == null){// 去掉头信息，从第二行开始读取数据
			rb.close();
			return null;
		}else{
			lineCount ++;
		}
		String lineInfo = "";
		while ((lineInfo = rb.readLine()) != null) {// 判断是否读取到文件尾行
			if(!lineInfo.trim().equals("")){
				lineCount ++;
				String lineInfos[] = lineInfo.split(",");
				// 将数据写入DEOM对象
				/** 此判断方法有局限性暂时注释掉等有更好的思路再添加 **/
				// if(f.length==lineInfos.length){//判断行信息列时候和demo字段相同如有不相同则说明行文本不符合要求
				try {
					Object obj = classcode.newInstance();// 得到Bean的对象
					for (int i = 0; i < lineInfos.length; i++) {// 遍历加载属性
						PropertyDescriptor pd = new PropertyDescriptor(f[i]
								.getName(), classcode);
						Method wm = pd.getWriteMethod();
						String info= lineInfos[i].trim();
						info = new String( info.getBytes() , "UTF-8");
						if (f[i].getType().equals(String.class)) {
							wm.invoke(obj, info);
						} else if(f[i].getType().equals(Integer.class) && info.matches("[0-9]+")) {
								wm.invoke(obj, Integer.valueOf(info));
						}
					}
					listt.add(obj);
				} catch (IntrospectionException e) {
					e.printStackTrace();
				}
				// }else{
				// poing = false;
				// }
			}
		}
		if(lineCount<2){
			poing = false;
		}
		rb.close();
		return poing ? (List<T>) listt : null;
	}

	/** 
     * 下载 
     *  
     * @author jiexu
     * @date 2012-7-31 下午16:30:00 
     * @param request 
     * @param response 
     * @param storeName 
     * @param contentType 
     * @param realName 
     * @throws Exception 
     */
    public static String downloadFiles(HttpServletRequest request,  
            HttpServletResponse response, String storeName, String contentType,  
            String realName) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        String downLoadPath = request.getSession().getServletContext().getRealPath(PropertyFactory.getProperty("fileUpload.dir")+"/"+storeName);
        //Log.info("下载路径："+downLoadPath);
        try{
	        File file = new File(downLoadPath);
	        File[] filelisFiles = file.listFiles();
	        if(filelisFiles.length > 0){
		        long fileLength =filelisFiles[0].length(); 
		        String filename = filelisFiles[0].getName();
		        if (filename.indexOf(" ") != -1) {//文件名过滤掉空格
		        	filename = filename.replaceAll(" ", "");
				}
		        //Log.info("转换前文件名："+filename);
		        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
		        	filename = new String(filename.getBytes("UTF-8"), "ISO8859-1"); //firefox浏览器
		        }else if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
		        	filename = URLEncoder.encode(filename, "UTF-8");//IE浏览器
		        }else{
		        	filename = new String(filename.getBytes("GBK"), "ISO8859-1");
		        }
		        //Log.info("转换后文件名："+filename);
		        response.reset();
		        response.setContentType(contentType);
		        response.setHeader("Content-disposition", "attachment; filename=" + filename);  
		        response.setHeader("Content-Length", String.valueOf(fileLength));  
		        String downloadDir = request.getSession().getServletContext().getRealPath(PropertyFactory.getProperty("fileUpload.dir")+"/"+storeName+"/"+filelisFiles[0].getName());
		        bis = new BufferedInputStream(new FileInputStream(downloadDir));  
		        //Log.info("文件路径："+downloadDir);
		        bos = new BufferedOutputStream(response.getOutputStream());  
		        byte[] buff = new byte[1024];  
		        int bytesRead;  
		        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
		            bos.write(buff, 0, bytesRead);  
		        }  
	        }
	        bis.close();  
	        bos.close();
        }catch (Exception e) {
        	e.printStackTrace();
        	return "fail";
		}
        return "success";
    }
}
