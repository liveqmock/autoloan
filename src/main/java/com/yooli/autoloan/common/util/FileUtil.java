package com.yooli.autoloan.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	/**
	 * 方法描述：根据FormFile和保存路径上传文件
	 * 新的文件名的规则是：aa.doc会变为:20080807133956093_386_aa.doc
	 */
	public static String uploadeFile(MultipartFile currentFormFile, String savePath) {
		//保存后的文件名称
		String saveAfterName = "";
		try{
			if (currentFormFile != null) {
				//得到当前文件的输入流
				InputStream stream = currentFormFile.getInputStream();
				//得到文件的真实名称
				String myFileName = currentFormFile.getOriginalFilename();
				if (myFileName != null && !"".equals(myFileName)) {
					Random random = new Random();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
					
					//给文件的名称加上前缀，防止文件名重复
					myFileName = sdf.format(new Date()) + "_" + String.valueOf(random.nextInt(900) + 100);
					
					File filefolt = new File(savePath);
					if (!filefolt.exists()) {
						filefolt.mkdirs();
					}
					
					OutputStream bos = new FileOutputStream(savePath + "/" + myFileName);
					
					int bytesRead = 0;
					byte[] buffer = new byte[8192];
					while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
						bos.write(buffer, 0, bytesRead);// 将文件写入服务器
					}
					bos.close();
					stream.close();
					
					saveAfterName = myFileName;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return saveAfterName;

	}
	/*
	 * 方法描述：根据输入流和保存路径上传文件
	 * 新的文件名的规则是：aa.doc会变为:20080807133956093_386_aa.doc
	 * stream:文件输入流
	 * savePath：保存路径
	 * oldFileName：保存的文件的名称
	 */
	public static String uploadeFileByInStream(InputStream stream, String savePath, String oldFileName) {
		//保存后的文件名称
		String saveAfterName = "";
		try{
			if (stream != null) {
				if (oldFileName != null && !"".equals(oldFileName)) {
					Random random = new Random();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
					
					//给文件的名称加上前缀，防止文件名重复
					oldFileName = sdf.format(new Date()) + "_" + String.valueOf(random.nextInt(900) + 100)+"."+oldFileName;
					
					File filefolt = new File(savePath);
					if (!filefolt.exists()) {
						filefolt.mkdirs();
					}
					
					OutputStream bos = new FileOutputStream(savePath + "/" + oldFileName);
					
					int bytesRead = 0;
					byte[] buffer = new byte[8192];
					while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
						bos.write(buffer, 0, bytesRead);// 将文件写入服务器
					}
					bos.close();
					stream.close();
					
					saveAfterName = oldFileName;
				}
			}
		}catch (Exception e){
			saveAfterName="error";
			e.printStackTrace();
		}
		
		return saveAfterName;
		
	}
	
	
    public static void del(String filepath) throws IOException{  
    	File f = new File(filepath);//定义文件路径         
    	if(f.exists() && f.isDirectory()){//判断是文件还是目录  
    		if(f.listFiles().length==0){//若目录下没有文件则直接删除  
    			f.delete();  
    			}else{//若有则把文件放进数组，并判断是否有下级目录  
    				File delFile[]=f.listFiles();  
    				int i =f.listFiles().length;  
    				for(int j=0;j<i;j++){
    					if(delFile[j].isDirectory()){  
    						del(delFile[j].getAbsolutePath());//递归调用del方法并取得子目录路径  
    						}
    					delFile[j].delete();
    					}
    				}  
    		}      
       }  
}
