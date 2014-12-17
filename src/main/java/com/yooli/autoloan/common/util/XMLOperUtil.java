/**
 *
 * Copyright (C) 2006-2012 普信恒业科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: XMLOperUtil.java 
 *
 * Created: [2012-11-30 上午11:07:28] by ShawnYao 
 *
 * ============================================================ 
 * 
 * ProjectName: RealEstate_v0.1 
 * 
 * Description: 
 * 
 * ==========================================================
 *
 */

package com.yooli.autoloan.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * <p>
 * Description: xml文件操作类
 * </p>
 * @author shenghuayao
 * @version 1.0
 * Create Date: 2012-11-30 上午11:07:28
 * Project Name: RealEstate_v0.1
 *
 * <pre>
 * Modification History: 
 *             Date                                Author                   Version          Description 
 * -----------------------------------------------------------------------------------------------------------  
 * LastChange: $Date:: 2012-12-06 #$      $Author: shenghuayao $          $Rev: 2223 $         
 * </pre>
 *
 */
public class XMLOperUtil {

	String path = ""; 

	File file;

	SAXReader saxReader = new SAXReader();

	Document document = null;
	OutputFormat format = OutputFormat.createPrettyPrint();

	public XMLOperUtil(Document document) {
		this.document = document;
		path = document.getPath();
	}

	public XMLOperUtil(String path) {
		try {
			saxReader.setEncoding("UTF-8");
			this.path = path;
			file = new File(path);
			document = saxReader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * <p>
	 * 将Document转换成字符串
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:44:21
	 * @return
	 */
	public String getStringFromDocument() {
		String text = document.asXML();
		return text;
	}

	/**
	 * <p>
	 * 读取文件装载成Document
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:32:47
	 * @param filePath xml文件路径
	 */
	public void setDocument(String filePath) {
		try {
			StringReader in  = new StringReader(filePath);
			document = saxReader.read(in);
			//System.out.println(document.asXML());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 根据 节点 / 属性 名称，获取 节点 / 属性 值
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:12:52
	 * @param key 节点 / 属性 路径  ("/resTypeMapping/relaod",resTypeMapping为根节点) 
	 * @param isAttribute 是否是属性
	 * @return {@link java.lang.String}
	 */
	@SuppressWarnings("rawtypes")
	public String getElementValue(String elementPath, Boolean isAttribute) {
		try {
			List list = document.selectNodes(elementPath);
			if (list != null && list.size() > 0) {
				if (isAttribute) {
					return ((Attribute) list.iterator().next()).getText();
				} else {
					return ((Element) list.iterator().next()).getText();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; 
	}

	/**
	 * <p>
	 * 根据 节点 / 属性 名称，获取 节点 / 属性 列表
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:14:23
	 * @param elementPath 节点 / 属性 路径
	 * @param isAttribute 是否是属性
	 * @return {@link java.lang.String}
	 */
	@SuppressWarnings("rawtypes")
	public List getElements(String elementPath, Boolean isAttribute) {
		try {
			if (!isAttribute) {
				List list = document.selectNodes(elementPath);
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * 给 节点 / 属性 赋值
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:17:48
	 * @param elementPath 节点 / 属性 路径
	 * @param value 节点 / 属性 值 
	 * @param isAttribute 是否是属性
	 * @return flag boolean
	 */
	@SuppressWarnings("rawtypes")
	public boolean setElementValue(String elementPath, String value, Boolean isAttribute) {
		boolean flag = false;
		try {
			if (isAttribute) {
				List list = document.selectNodes(elementPath);
				((Attribute) list.iterator().next()).setValue(value);
			} else {
				List list = document.selectNodes(elementPath);
				((Element) list.iterator().next()).setText(value);
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * <p>
	 * 删除节点
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:20:57
	 * @param elementPath 节点 / 属性 路径
	 * @param value 节点 / 属性 值
	 * @param isAttribute 是否是属性 
	 * @return flag boolean
	 */
	@SuppressWarnings("rawtypes")
	public boolean delete(String elementPath, String value, Boolean isAttribute) {
		boolean flag = false;
		try {
			List list = document.selectNodes(elementPath);
			Element element = (Element) list.iterator().next();
			if (isAttribute) {
				List attributeList = document.selectNodes(elementPath + "/@" + value); 
				Attribute attribute = (Attribute) attributeList.iterator()
						.next();
				element.remove(attribute);
			} else {
				List childList = document.selectNodes(elementPath + "/" + value); 
				Element childElement = (Element) childList.iterator().next();
				element.remove(childElement);
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag; 
	}

	/**
	 * <p>
	 * 添加 子节点 / 属性
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:26:42
	 * @param elementPath 子节点 / 属性 路径
	 * @param stringArray [子节点名称][子节点值] / [属性名称][属性值]
	 * @param isAttribute 是否是属性
	 * @return {@link org.dom4j.Element}
	 */
	@SuppressWarnings("rawtypes")
	public Element addChildElement(String elementPath, String[] stringArray, Boolean isAttribute) {
		Element element = null;
		try {
			List list = document.selectNodes(elementPath);
			element = (Element) list.iterator().next();
			if (isAttribute) {
				element = element.addAttribute(stringArray[0], stringArray[1]);
			} else {
				element = element.addElement(stringArray[0]);
				element.setText(stringArray[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	/**
	 * <p>
	 * 在当前操作的XML文件中写入Document
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:55:34
	 * @param document {@link org.dom4j.Document}
	 * @throws IOException
	 */
	public void save(Document document) throws IOException {
		try {

			format.setEncoding("utf-8"); 
			XMLWriter writer = new XMLWriter(new FileWriter(new File(path)),
					format);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * <p>
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:59:10
	 */
	/*public void doSave(){
		writeXML(path, document, "UTF-8"); 
	}*/

	/**
	 * <p>
	 * 在指定XML文件中写入 Document 
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:36:12
	 * @param filePath 文件路径
	 * @param document {@link org.dom4j.Document}
	 * @param encoding 写入时的字符编码
	 * @return flag boolean
	 */
	public boolean writeXML(String filePath, Document document, String encoding) {
		boolean flag = false;
		try {
			document.setXMLEncoding("UTF-8"); 
			OutputStreamWriter outWriter = new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"); 
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding == null ? format.getEncoding()
					: encoding);
			XMLWriter writer = new XMLWriter(outWriter, format);
			writer.write(document);
			writer.close();
			flag = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * <p>
	 * 获得根节点
	 * </p>
	 * @author ShawnYao
	 * @date 2012-11-30 上午11:57:27
	 * @param document {@link org.dom4j.Document}
	 * @return {@link org.dom4j.Element}
	 */
	public Element getRootElement(Document document) {
		return document.getRootElement();
	}

	public void treeWalk(Element element) {
		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				treeWalk((Element) node);
			} else { // do something....
			}
		}
	}

}

