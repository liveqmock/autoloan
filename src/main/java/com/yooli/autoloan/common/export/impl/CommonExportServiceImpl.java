package com.yooli.autoloan.common.export.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.yooli.autoloan.common.PropertyFactory;
import com.yooli.autoloan.common.dao.export.ICommonExportDateDao;
import com.yooli.autoloan.common.export.ICommonExportService;
import com.yooli.autoloan.common.page.Entity;


@Component
public class CommonExportServiceImpl implements ICommonExportService {
	private ICommonExportDateDao commonExportDate;

	public ICommonExportDateDao getCommonExportDate() {
		return commonExportDate;
	}

	// 日志
	private Logger log = Logger.getLogger(CommonExportServiceImpl.class);
	
	@Resource
	public void setCommonExportDate(ICommonExportDateDao commonExportDate) {
		this.commonExportDate = commonExportDate;
	}

	// 待导出的数据
	StringBuffer exportDate = new StringBuffer();

	/**
	 * 向页面导出数据
	 * 
	 * @param statementName
	 */
	public void exportDate(String statementName, Entity entity,
			HttpServletResponse response, String tableHead, String field) {
		
		// 输出流
		ServletOutputStream out = null;
		// 导出的文件名
		String fileName = null;
		
		if (statementName.equals("queryLoan")){
			fileName = PropertyFactory.getProperty("QUERYLOAN");
		}else if (statementName.equals("queryPayments")){
			fileName = PropertyFactory.getProperty("QUERYPAYMENTS");
		}
		else if (statementName.equals("queryClientReAllocate")){
			fileName = PropertyFactory.getProperty("QUERYCLIENTREALLOCATE");
		}
					
		try {
			Field[] fie = entity.getClass().getDeclaredFields();
			Field.setAccessible(fie, true);
			// 添加表头
			exportDate.append(tableHead);
			exportDate.append("\n");
			
			response.reset(); // reset the response
			response.setCharacterEncoding("GBK");
			response.setContentType("application/octet-stream;charset=GBK");
			//response.setHeader("content-disposition", "attachment; filename=\""
			//		+ new String( fileName.getBytes("utf-8"), "ISO8859-1" ) + "\"");
			response.setHeader("content-disposition", "attachment; filename=\""
					+ fileName + "\"");

			// 导出数据涉及到的字段
			String fieldArray[] = field.split(",");
			// 记录查询出的数据
			List list = null;
			// 记录所设计到的字段在实体类中的位置
			List<Integer> addrList = new ArrayList<Integer>();

			// 开始分批查询数据
			// 分页查询控制
			int j = 0;
			do {
				if (list != null) {
					list.clear();
					list = null;
					exportDate.delete(0, exportDate.length());
				}
				// 查询数据
				list = commonExportDate.getExportDate(statementName,entity,
						Integer.valueOf(PropertyFactory.getProperty("EXPORTPAGESIZE")) * j, Integer.valueOf(PropertyFactory.getProperty("EXPORTPAGESIZE")));
				// 解析数据
				for (Object obj : list) {
					// 第一次解析时依次遍历得到所涉及字段的位置并记录
					// 第二次之后解析时无需再依次遍历，只需从已记录字段地址的集合中取位置即可
					if (addrList.size() != fieldArray.length
							|| addrList == null) {
						addrList.clear();
						for (int i = 0; i < fieldArray.length; i++) {
							for (int h = 0; h < fie.length; h++) {
								if (fieldArray[i]
										.toLowerCase()
										.trim()
										.equals(fie[h].getName().toLowerCase()
												.trim())) {
									// 记录字段在实体类中的位置
									addrList.add(h);
									switchDate(fie, obj, h);
									exportDate.append(",");
									break;
								}
							}
						}
						exportDate.append("\n");
					} else {
						for (int i = 0; i < addrList.size(); i++) {
							switchDate(fie, obj, addrList.get(i));
							exportDate.append(",");
						}
						exportDate.append("\n");
					}
				}
				j++;
				out = response.getOutputStream();
				byte[] bytes = exportDate.toString().getBytes("GBK");
				out.write(bytes);
				out.flush();
				
			} while (list.size() != 0);
			
			// 关闭输出流
			if (out != null){
				exportDate.delete(0, exportDate.length());
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			log.error("导出数据时出现异常");
		}finally {
			if(out != null){
				try{
					out.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	// 对数据库中字典类字段进行匹配
	public void switchDate(Field[] fie, Object obj, int h) {
		try {
			if (fie[h].getName().toLowerCase().trim().equals("aistatus")) {
				if (fie[h].get(obj) != null) {
					switch ((Integer) fie[h].get(obj)) {
					case 1:
						exportDate.append(fie[h].get(obj) == null ? "" : "正常");
						break;
					case 2:
						exportDate.append(fie[h].get(obj) == null ? "" : "逾期");
						break;
					case 3:
						exportDate.append(fie[h].get(obj) == null ? "" : "结清");
						break;
					default:
						exportDate.append(fie[h].get(obj) == null ? "" : fie[h]
								.get(obj));
					}
				} else {
					exportDate.append("");
				}
			} else if (fie[h].getName().toLowerCase().trim().equals("aibankaccount")) {
				if (fie[h].get(obj) != null){
					exportDate.append(fie[h].get(obj).toString().substring(fie[h].get(obj).toString().length() - 4, fie[h].get(obj).toString().length()));
				}
			} else if (fie[h].getName().toLowerCase().trim()
					.equals("aiallocatestatus")) {
				if (fie[h].get(obj) != null) {
					switch ((Integer) fie[h].get(obj)) {
					case 1:
						exportDate.append(fie[h].get(obj) == null ? "" : "未分配");
						break;
					case 2:
						exportDate.append(fie[h].get(obj) == null ? "" : "已分配");
						break;
					default:
						exportDate.append(fie[h].get(obj) == null ? "" : fie[h]
								.get(obj));
					}
				} else {
					exportDate.append("");
				}
			} else if (fie[h].getName().toLowerCase().trim()
					.equals("aiborrowsort")) {
				if (fie[h].get(obj) != null) {
					switch ((Integer) fie[h].get(obj)) {
					case 1:
						exportDate.append(fie[h].get(obj) == null ? "" : "新薪贷");
						break;
					case 2:
						exportDate.append(fie[h].get(obj) == null ? "" : "学信通");
						break;
					case 3:
						exportDate.append(fie[h].get(obj) == null ? "" : "精英贷");
						break;
					case 4:
						exportDate
								.append(fie[h].get(obj) == null ? "" : "助学贷 ");
						break;
					case 5:
						exportDate.append(fie[h].get(obj) == null ? "" : "助业贷");
						break;
					case 6:
						exportDate.append(fie[h].get(obj) == null ? ""
								: "助业宜楼贷");
						break;
					case 7:
						exportDate.append(fie[h].get(obj) == null ? ""
								: "新薪宜楼贷");
						break;
					case 8:
						exportDate.append(fie[h].get(obj) == null ? "" : "学历贷");
						break;
					case 9:
						exportDate.append(fie[h].get(obj) == null ? "" : "宜商贷");
						break;
					case 10:
						exportDate.append(fie[h].get(obj) == null ? "" : "宜车贷");
						break;
					case 11:
						exportDate
								.append(fie[h].get(obj) == null ? "" : "小企业贷");
						break;
					default:
						exportDate.append(fie[h].get(obj) == null ? "" : fie[h]
								.get(obj));
					}
				} else {
					exportDate.append("");
				}
			} else if (fie[h].getName().toLowerCase().trim().equals("aiisloop")) {
				if (fie[h].get(obj) != null) {
					switch (Integer.parseInt((String) fie[h].get(obj))) {
					case 0:
						exportDate.append(fie[h].get(obj) == null ? "" : "否");
						break;
					case 1:
						exportDate.append(fie[h].get(obj) == null ? "" : "是");
						break;
					default:
						exportDate.append(fie[h].get(obj) == null ? "" : fie[h]
								.get(obj));
					}
				} else {
					exportDate.append("");
				}
			} else if (fie[h].getName().toLowerCase().trim().equals("aiidnumber")) {
				// 如果是身份证号，在其之前追加一个单引号，使其在显示时不使用科学计数法
				exportDate.append("'");
				exportDate.append(fie[h].get(obj) == null ? "" : fie[h]
						.get(obj));
			}
			else {
				exportDate.append(fie[h].get(obj) == null ? "" : fie[h]
						.get(obj));
			}
		} catch (Exception e) {
			log.error(e);
			log.error("字典数据匹配是出现异常");
		}
	}
}
