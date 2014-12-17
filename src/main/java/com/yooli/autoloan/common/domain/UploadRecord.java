/* 
 * Copyright (C) 2006-2012 普信恒业科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: UploadRecord.java 
 *
 * Created: [2012-9-5 上午10:47:00] by pgq 
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

package com.yooli.autoloan.common.domain;

/** 
 *
 * Description: 
 *
 * @author pgq
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2012-9-5    pgq       1.0        1.0 Version 
 * </pre>
 */

public class UploadRecord {
	protected Long id;
	protected String uploadUid;
	protected String uploadUname;
	protected String deptID;
	protected String deptName;
	protected String fileName;
	protected String raddress;
	protected String uploadTime;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the uploadUid
	 */
	public String getUploadUid() {
		return uploadUid;
	}
	/**
	 * @param uploadUid the uploadUid to set
	 */
	public void setUploadUid(String uploadUid) {
		this.uploadUid = uploadUid;
	}
	/**
	 * @return the uploadUname
	 */
	public String getUploadUname() {
		return uploadUname;
	}
	/**
	 * @param uploadUname the uploadUname to set
	 */
	public void setUploadUname(String uploadUname) {
		this.uploadUname = uploadUname;
	}
	/**
	 * @return the deptID
	 */
	public String getDeptID() {
		return deptID;
	}
	/**
	 * @param deptID the deptID to set
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the raddress
	 */
	public String getRaddress() {
		return raddress;
	}
	/**
	 * @param raddress the raddress to set
	 */
	public void setRaddress(String raddress) {
		this.raddress = raddress;
	}
	/**
	 * @return the uploadTime
	 */
	public String getUploadTime() {
		return uploadTime;
	}
	/**
	 * @param uploadTime the uploadTime to set
	 */
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	
}
