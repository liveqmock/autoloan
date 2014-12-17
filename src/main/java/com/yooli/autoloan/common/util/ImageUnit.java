/* 
 * Copyright (C) 2006-2012 普信恒业科技发展（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: Test.java 
 *
 * Created: [2012-7-9 上午10:53:44] by pgq 
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
 * ProjectName: imagetest 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.yooli.autoloan.common.util;

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
 * 2012-7-9    pgq       1.0        1.0 Version 
 */

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.File;

public class ImageUnit {
	private static BufferedImage resize(BufferedImage source, int targetW,
			int targetH,boolean proportionalScaling) {
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		if(proportionalScaling){
			if (sx > sy) {
				sx = sy;
				targetW = (int) (sx * source.getWidth());
			} else {
				sy = sx;
				targetH = (int) (sy * source.getHeight());
			}
		}
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
					targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}
	
	/**将图片保存成缩略图
	 * 
	 * @param img 图片文件
	 * @param FileName 图片名称
	 * @param saveToFileStr 保存图片的地址
	 * @param width 缩略图的宽
	 * @param hight 缩略图的高
	 * @param proportionalScaling 是否按比例缩放--当为true时是在缩略图的高和款的范围内按比例缩放
	 */
	public static void saveASthumbnails(BufferedImage srcImage, String FileName,String saveToFileStr, int width, int hight,boolean proportionalScaling) {
		String imgType = "jpg";
		if (FileName.toLowerCase().endsWith(".png")) {
			imgType = "png";
		}
		File saveFile = new File(saveToFileStr+FileName);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		if (width > 0 || hight > 0) {
			srcImage = resize(srcImage, width, hight,proportionalScaling);
		}
		try {
			ImageIO.write(srcImage, imgType, saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**将图片保存成原图
	 * 
	 * @param img 图片文件
	 * @param FileName 图片名称
	 * @param saveToFileStr 保存图片的地址
	 */
	public static void saveASoriginal(BufferedImage srcImage,String FileName,String saveToFileStr){
		String imgType = "jpg";
		if (FileName.toLowerCase().endsWith(".png")) {
			imgType = "png";
		}
		File saveFile = new File(saveToFileStr+FileName);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		try {
			ImageIO.write(srcImage, imgType, saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
