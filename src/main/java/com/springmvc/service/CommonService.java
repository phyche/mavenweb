package com.springmvc.service;

import com.springmvc.image.ImageConfigEnum;
import com.springmvc.image.ImageGroup;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
	/**
	 * 保存图片
	 * @param file
	 * @return
	 */
	public ImageGroup imageHandle(MultipartFile file, long largeWidth, long largeHeiht, long mediumWidth, long mediumHeiht, long thumbnailWidth, long thumbnailHeiht);
	/**
	 * 保存图片
	 * @param file
	 * @param configEnum
	 * @param directory 目录
	 * @return
	 */
	public ImageGroup imageHandle(MultipartFile file, ImageConfigEnum configEnum, String directory);

}
