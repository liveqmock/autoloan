/* 
 * Copyright (C) 2006-2012 普信恒业科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: UploadRecordService.java 
 *
 * Created: [2012-9-5 上午11:09:03] by pgq 
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

package com.yooli.autoloan.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.yooli.autoloan.common.domain.ApplyUploadRel;
import com.yooli.autoloan.common.domain.FilePath;
import com.yooli.autoloan.common.domain.UploadRecord;
import com.yooli.autoloan.common.domain.User;


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

public interface UploadRecordService {
	/**插入文件记录**/
	public Long saveRecord(UploadRecord ur);
	/***插入文件关系**/
	public void saveApplyRel(ApplyUploadRel applyUploadRel);
	/**得到进件不同类型的图片**/
	public List<ApplyUploadRel> getUploadRecordByType(ApplyUploadRel applyUploadRel);
	/**生成图片名称
	 * 规则为 yyyy_MM_dd_hh_mm_ss_userID.jpg
	 * **/
	public String getImageName(MultipartFile file,User user);
	
	public String deleteimageByID(String applyRelID);
	
	public ApplyUploadRel getApplyUploadRelById(Long uploadrelID);
	/**
	 *删除该进件的某一类型图片
	 */
	public Integer deleteImageByAttachtype(ApplyUploadRel applyUploadRel);
	
	public List<ApplyUploadRel> getApplyUploadRelsByID(String idList);
	/**
	 * 根据进件ID和图片类型列表查询图片信息
	 * TODO 
	 * @param applyID 进件ID
	 * @param types 图片类型列表 ，逗号分隔，类型来自属性文件
	 * @return
	 */
	public List<ApplyUploadRel> getImages(String applyID, String types);
	/**
	 * 根据图片类型列表返回类型对应的中文描述用于页面展示
	 * TODO 
	 * @param types
	 * @return
	 */
	public Map<String,String> getTypeCHS(String types);
	/**
	 * 删除指定图片id的图片
	 * TODO 
	 * @param id
	 * @param attachType
	 * @param addRess
	 * @return
	 */
	Integer deleteImg(String[] imgIds);
	/**
	 * TODO 保存上传图片
	 * @param file
	 * @param applyID
	 * @param path
	 * @param relativepath
	 * @param type
	 */
	void saveImg(MultipartFile file, String applyID, String path,String relativepath, String type);
	
	
	/**
	* Description
	*  保存上传文件
	*@return Map<String,String>
	*@author wgs
	*@createTime 2013-11-6
	*/
	public Map<String,String> saveFile(String fileType,MultipartFile muFile,Long orderid,String localPath,Long linkedId);
	/**
	* Description
	*  获取文件路径集合
	*@return List<FilePath>
	*@author wgs
	*@createTime 2013-11-6
	*/
	public List<FilePath> getfilePath(FilePath filePath);
	
	/**
	* Description
	*  根据路径删除文件
	*@return Map<String,String>
	*@author wgs
	*@createTime 2013-11-6
	*/
	public Map<String,String> deleteFile(FilePath filePath);
	
	
	
	

}
