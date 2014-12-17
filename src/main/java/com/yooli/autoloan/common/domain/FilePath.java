package com.yooli.autoloan.common.domain;

public class FilePath {
	
	//主键id
	private Long id;
	
	//进件id
	private Long applyId;
	
	//文件名称
	private String fileName;
	
	//文件类型
	private String fileType;
	
	//文件标识
	private String filemark;
	
	//文件路径 
	private String filePath;
	
    //最后更新人id
	private String lastUpdateUserId;
	
	//最后更新时间
	private String lastUpdateTime;
	
	//关联单号
	private Long linkedId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilemark() {
		return filemark;
	}

	public void setFilemark(String filemark) {
		this.filemark = filemark;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(String lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getLinkedId() {
		return linkedId;
	}

	public void setLinkedId(Long linkedId) {
		this.linkedId = linkedId;
	}
	
	

}
