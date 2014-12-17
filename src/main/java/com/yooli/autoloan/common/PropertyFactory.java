package com.yooli.autoloan.common;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 加载配置文件
 * 
 */
public class PropertyFactory
{

	private static Log logger = LogFactory.getLog(PropertyFactory.class);

	private static java.util.Properties pros = new java.util.Properties();

	public PropertyFactory(List<String> filePaths)
	{
		if(filePaths!=null){
			for(int i=0;i<filePaths.size();i++){
				String filePath = filePaths.get(i);
				InputStream in = null;
				URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
				if (null == url)
				{
					url = this.getClass().getClassLoader().getResource(filePath);
				}
				if(null == url){
					url = this.getClass().getResource(filePath);
				}
				
				//解决文件路径可能出现空格的问题
				String path = url.getFile();
				if(!"".equals(path)){
					path = path.replace("%20", " ");
				}
				
				try
				{
					in = new BufferedInputStream(new FileInputStream(path));
					pros.load(in);
				}
				catch (Exception e)
				{
					logger.error("加载配置文件出错 ：\n", e);
				}
				finally
				{
					if(in != null)
					{
						try
						{
							in.close();
						}
						catch (IOException e)
						{
							logger.error("关闭输入流出错 ：\n", e);
						}
					}
				}
			}
		}
		
	}

	public static String getProperty(String key)
	{
		return pros.getProperty(key);
	}

	public void setProperty(String key, String value)
	{
		pros.setProperty(key, value);
	}

	public HashMap<String, String> readProperty(String path, String fileName)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		InputStream in = null;
		java.util.Properties p = new java.util.Properties();
		URL url = Thread.currentThread().getContextClassLoader().getResource(path + fileName);
		if (null == url)
		{
			url = this.getClass().getClassLoader().getResource(path + fileName);
		}
		
		//解决文件路径可能出现空格的问题
		String filepath = url.getFile();
		if(!"".equals(filepath)){
			path = path.replace("%20", " ");
		}
		
		try
		{
			in = new BufferedInputStream(new FileInputStream(filepath));
			p.load(in);
			Enumeration<?> e = p.propertyNames();
			while (e.hasMoreElements())
			{
				String key = (String) e.nextElement();
				String ruleStr = p.getProperty(key);
				map.put(key, ruleStr);
			}
		}
		catch (Exception e)
		{
			logger.error("加载验证文件出错 ：\n", e);
		}
		finally
		{
			if(in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					logger.error("关闭输入流出错 ：\n", e);
				}
			}
		}
		
		return map;
	}

	// 加载XML文件
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> readValidateXml(String path, String fileName)
	{
		URL url = Thread.currentThread().getContextClassLoader().getResource(path + fileName);
		if (null == url)
		{
			url = this.getClass().getClassLoader().getResource(path + fileName);
		}
		InputStream in = null;
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String name = "";
		String condition = "";
		String notNull = "";
		String express = "";
		String message = "";
		String nameStr = "";
		try
		{
			in = new BufferedInputStream(new FileInputStream(url.getFile()));
			SAXReader sReader = new SAXReader();
			Document document = sReader.read(in);
			Element root = document.getRootElement();
			List<Element> fields = root.elements("field");
			for (Element field : fields)
			{
				HashMap<String, String> ruleMap = new HashMap<String, String>();
				name = field.attributeValue("name");
				condition = field.elementText("condition");
				express = field.elementText("express");
				notNull = field.elementText("notNull");
				message = field.elementText("message");
				nameStr = field.elementText("nameStr");
				ruleMap.put("name", name);
				ruleMap.put("condition", condition);
				ruleMap.put("express", express);
				ruleMap.put("notNull", notNull);
				ruleMap.put("message", message);
				ruleMap.put("nameStr", nameStr);
				list.add(ruleMap);
			}
		}
		catch (Exception e)
		{
			logger.error("加载验证XML文件出错 ：\n", e);
		}
		finally
		{
			if(in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					logger.error("关闭输入流出错 ：\n", e);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 重新加载/config.properties配置信息。
	 * 
	 */
	public synchronized void reloadProperties()
	{
		HashMap<String, String> properties = this.readProperty("", "/config.properties");
		this.pros.putAll(properties);
	}
}