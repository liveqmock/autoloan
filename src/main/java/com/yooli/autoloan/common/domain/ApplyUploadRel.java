/* 
 * Copyright (C) 2006-2012 普信恒业科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: ApplyUploadRel.java 
 *
 * Created: [2012-9-5 下午06:22:14] by pgq 
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

public class ApplyUploadRel {
	protected Long id;
	protected Long inQuireId;
	protected Long upid;
	protected String attachType;
	protected String fileType;
	protected String addRess;
	protected Integer exhibitionTimes;
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
	 * @return the inQuireId
	 */
	public Long getInQuireId() {
		return inQuireId;
	}
	/**
	 * @param inQuireId the inQuireId to set
	 */
	public void setInQuireId(Long inQuireId) {
		this.inQuireId = inQuireId;
	}
	/**
	 * @return the upid
	 */
	public Long getUpid() {
		return upid;
	}
	/**
	 * @param upid the upid to set
	 */
	public void setUpid(Long upid) {
		this.upid = upid;
	}
	/**
	 * @return the attachType
	 */
	public String getAttachType() {
		return attachType;
	}
	/**
	 * @param attachType the attachType to set
	 */
	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return the addRess
	 */
	public String getAddRess() {
		return addRess;
	}
	/**
	 * @param addRess the addRess to set
	 */
	public void setAddRess(String addRess) {
		this.addRess = addRess;
	}
	public Integer getExhibitionTimes() {
		return exhibitionTimes;
	}
	public void setExhibitionTimes(Integer exhibitionTimes) {
		this.exhibitionTimes = exhibitionTimes;
	}
}
