package com.springmvc.service.impl;

import com.springmvc.image.ImageConfigEnum;
import com.springmvc.image.ImageGroup;
import com.springmvc.image.ImageHanlder;
import com.springmvc.service.CommonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service("commonService")
public class CommonServiceImpl implements CommonService {
	
	//@Value("${imagePath}")
	private String  imagePath="E:\\workspace2\\mavenweb\\target\\maven-web\\upload";
	
	@Override
	public ImageGroup imageHandle(MultipartFile file, ImageConfigEnum configEnum, String directory) {
		return handle(file, configEnum.getLargeWidth(),
				configEnum.getLargeHeight(), configEnum.getMediumWidth(),
				configEnum.getMediumHeight(), configEnum.getThumbnailWidth(),
				configEnum.getThumbnailHeight(), directory);
	}
	
	/**
	 * 保存图片
	 * @param file
	 * @return
	 */
	public ImageGroup imageHandle(MultipartFile file, long largeWidth,
								  long largeHeiht, long mediumWidth, long mediumHeiht,
								  long thumbnailWidth, long thumbnailHeiht) {
		return handle(file, largeWidth, largeHeiht, mediumWidth, mediumHeiht, thumbnailWidth, thumbnailHeiht, null);
	}
	/**
	 * 
	 * @param file
	 * @param largeWidth
	 * @param largeHeiht
	 * @param mediumWidth
	 * @param mediumHeiht
	 * @param thumbnailWidth
	 * @param thumbnailHeiht
	 * @param directory
	 * @return
	 */
	private ImageGroup handle(MultipartFile file, long largeWidth,
							  long largeHeiht, long mediumWidth, long mediumHeiht,
							  long thumbnailWidth, long thumbnailHeiht, String directory) {
		if (file == null || file.isEmpty()) {

		}
		String directoryStr = directory;
		if (directoryStr == null || directoryStr.trim().length() == 0) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			directoryStr = format.format(Calendar.getInstance().getTime());
		}
		String savePathStr = imagePath + (imagePath.endsWith("/") ? "" : "/")
				+ "images/" + directoryStr + "/";
		String reqPathStr = "/images/" + directoryStr + "/";
		ImageGroup imageModel = ImageHanlder.build(file, savePathStr,
				reqPathStr, largeWidth, largeHeiht, mediumWidth, mediumHeiht,
				thumbnailWidth, thumbnailHeiht, 50);
		return imageModel;
	}

}
