/**
 *
 * Copyright (C) 2006-2012 普信恒业科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: ExportDataUtil.java 
 *
 * Created: [2012-10-15 上午10:29:40] by ShawnYao 
 *
 * ============================================================ 
 * 
 * ProjectName: collection 
 * 
 * Description: 
 * 
 * ==========================================================
 *
 */

package com.yooli.autoloan.common.util;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * <p>
 * 数据导出工具类
 * </p>
 * 
 * @author ShawnYao
 * @date 2012-10-15 上午10:29:40
 * @version
 * @project AutoLoan
 */
public class ExportDataUtil {

	// 日志
	private Logger log = Logger.getLogger(ExportDataUtil.class);

	/**
	 * <p>
	 * 将数据导入到CSV文件方法
	 * </p>
	 * 
	 * @author ShawnYao
	 * @date 2012-10-16 下午3:08:43
	 * @param dataList
	 *            必须为实体类型数据集
	 * @param showTitle
	 *            key为实体属性字段，value为页面显示字段，注意key->value的对应
	 * @param fileName
	 *            文件名称
	 * @param request
	 *            不允许为空
	 * @param response
	 *            不允许为空
	 * @return flag true:导出正常 , false:导出过程出现异常
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	public boolean exportDataToCSV(List dataList,
			LinkedHashMap<String, String> showTitle, String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		boolean flag = false;
		if (response != null && request != null) {
			//获取浏览器类型
			final String userAgent = request.getHeader("USER-AGENT");
			// 输出流
			OutputStream out = null;

			// 待导出的数据
			StringBuffer exportDate = new StringBuffer();

			if (fileName == null) {
				fileName = "";
			}

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			fileName = fileName + formatter.format(curDate) + ".csv";

			if (userAgent.contains("MSIE")) {// IE浏览器
				fileName = URLEncoder.encode(fileName, "UTF8");
			} else if (userAgent.contains("Mozilla")) {// google,火狐浏览器
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} else {
				fileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
			}
			// 提示框设置
			response.reset(); // reset the response
			// response.setCharacterEncoding("UTF-8");
			 response.setContentType("application/octet-stream");
			response.setHeader("content-disposition", "attachment; filename=\""
					+ fileName + "\"");
			try {
				// 获得显示字段(实体属性)
				List<String> fields = new ArrayList<String>();
				// 表头(实体中文名称)
				if (showTitle != null && showTitle.size() > 0) {
					StringBuffer tableHead = new StringBuffer();
					for (Map.Entry<String, String> entry : showTitle.entrySet()) {
						tableHead.append(entry.getValue()).append(",");
						fields.add(entry.getKey());
					}
					// 添加表头
					exportDate.append(tableHead);
					exportDate.append("\n");
				}
				// 列表数据
				if (dataList != null && dataList.size() > 0) {
					Object obj = dataList.get(0);
					// 获得实体类的set/get方法
					Method methods[] = obj.getClass().getDeclaredMethods();
					// 将显示字段的get方法存入集合
					List<Method> invokeMethods = new ArrayList<Method>();
					for (int j = 0; j < fields.size(); j++) {
						for (int i = 0; i < methods.length; i++) {
							String methodName = methods[i].getName();
							String tmpMethodName = methodName.substring(3,
									methodName.length());
							if (fields.get(j).toString().trim().toUpperCase()
									.equals(tmpMethodName.toUpperCase())
									&& methodName.indexOf("set") != 0
									&& methodName.indexOf("get") == 0) {
								invokeMethods.add(methods[i]);
							}
						}
					}
					// 获取数据
					for (int i = 0; i < dataList.size(); i++) {
						obj = dataList.get(i);
						for (int j = 0; j < invokeMethods.size(); j++) {
							Method method = invokeMethods.get(j);
							Object tmpObj = method.invoke(obj);
							if (tmpObj == null) {
								tmpObj = "";
							}
							exportDate.append(tmpObj.toString());
							exportDate.append(",");
						}
						exportDate.append("\n");
					}
				}

				// System.out.println("============" + exportDate.toString());
				out = response.getOutputStream();
				byte[] bytes = exportDate.toString().getBytes("GBK");
				out.write(bytes);
				out.flush();
				// 关闭输出流
				if (out != null) {
					exportDate.delete(0, exportDate.length());
				}
				flag = true;
				log.info("文件导出完毕！");
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				log.error("导出数据时出现异常");
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}else{
			new NullPointerException("HttpServletRequest Or HttpServletResponse Is Null !");
		}
		return flag;
	}

}
