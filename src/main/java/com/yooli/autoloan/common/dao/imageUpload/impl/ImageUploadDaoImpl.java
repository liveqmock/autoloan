

package com.yooli.autoloan.common.dao.imageUpload.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yooli.autoloan.common.dao.ibatis.IBatisBaseDao;
import com.yooli.autoloan.common.dao.imageUpload.IimageUploadDao;
import com.yooli.autoloan.common.domain.ApplyUploadRel;
import com.yooli.autoloan.common.domain.FilePath;
import com.yooli.autoloan.common.domain.UploadRecord;



@Component
public class ImageUploadDaoImpl extends IBatisBaseDao implements IimageUploadDao {

	public void uploadRecord(Map<String, String> map){
		this.getSqlMapClientTemplate().update("", map);
	}
	
	
	public void uploadApplyUploadRel(Map<String, String> map){
		this.getSqlMapClientTemplate().update("", map);
	}
	
	
	
	public Integer getCountFile(String applyID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("",applyID);
	}
	

	public List<UploadRecord> getRecordInfoByApplyID(String applyID){
		return this.getSqlMapClientTemplate().queryForList("", applyID);
	}
	public UploadRecord getRecordInfoByID(Integer idnumber){
		return (UploadRecord)this.getSqlMapClientTemplate().queryForObject("",idnumber);
	}
	@Override
	public List<ApplyUploadRel> getApplyPath(ApplyUploadRel applyUploadRel) {
		return this.getSqlMapClientTemplate().queryForList("imageDaoview.getimagepath", applyUploadRel);
	}
	@Override
	public Long saveUploadRecord(UploadRecord uploadRecord) {
		return (Long)this.getSqlMapClientTemplate().insert("imageDaoview.seavRecord", uploadRecord);
	}
	@Override
	public void saveAppUploadRel(ApplyUploadRel appUploadRel) {
		this.getSqlMapClientTemplate().insert("imageDaoview.saveapplyUploadRel", appUploadRel);
	}

	@Override
	public void deleteApplyUploadRelByid(String appluRelID) {
		this.getSqlMapClientTemplate().delete("imageDaoview.deleteimageByid",appluRelID);
	}

	@Override
	public Integer countUploadImage(ApplyUploadRel applyUploadRel) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("imageDaoview.countUploadFile", applyUploadRel);
	}

	@Override
	public ApplyUploadRel getApplyUploadRelById(Long uploadrelID) {
		ApplyUploadRel applyUploadRel = new ApplyUploadRel();
		applyUploadRel.setId(uploadrelID);
		return (ApplyUploadRel)this.getSqlMapClientTemplate().queryForObject("imageDaoview.getimagepathbyid",applyUploadRel);
	}
	
	@Override
	public void deleteImageByAttachtype(ApplyUploadRel applyUploadRel) {
		this.getSqlMapClientTemplate().delete("imageDaoview.deleteimageByAttachtype", applyUploadRel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplyUploadRel> getApplyUploadRelsByID(String idList) {
		return this.getSqlMapClientTemplate().queryForList("imageDaoview.getuploadlistbyids", idList);
	}

	@Override
	public void insertIntoFilePath(FilePath filePath) {
		this.getSqlMapClientTemplate().insert("imageDaoview.insertIntoFilePath", filePath);
		
	}
	/**
	* Description
	*  删除文件
	*@return 
	*@author wgs
	*@createTime 2013-11-6
	*/
	@Override
	public Integer deleteFilePath(FilePath filePath) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().delete("imageDaoview.deleteFilePath", filePath);
	}
	
	
	/**
	* Description
	*  获取文件路径集合
	*@return 
	*@author wgs
	*@createTime 2013-11-6
	*/
	@SuppressWarnings("unchecked")
	public List<FilePath> getFilePathByFilePath(FilePath filePath){
		return this.getSqlMapClientTemplate().queryForList("imageDaoview.fandFilePath",filePath);
	}
	
}
