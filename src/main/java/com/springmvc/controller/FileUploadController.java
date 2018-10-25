
package com.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.springmvc.entity.ResponseResult;
import com.springmvc.image.ImageConfigEnum;
import com.springmvc.image.ImageGroup;
import com.springmvc.image.ImageHanlder;
import com.springmvc.service.CommonService;
import com.springmvc.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

	private Logger logger = Logger.getLogger(FileUploadController.class);

	@Autowired
	private CommonService commonService;

    @RequestMapping(value = "/test")
    public ModelAndView index() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/jump/jump-index");
    	System.out.println("111111111111111");
    	return view;
        //return "/jump/jump-index";
    }

    @ResponseBody
    @RequestMapping("/uploadImg")
    public String uploadPicture(@RequestParam(value="file",required=false)MultipartFile file,
                                HttpServletRequest request){

        File targetFile=null;
        String msg="";//返回存储路径
        int code=1;
        String fileName=file.getOriginalFilename();//获取文件名加后缀
        if(fileName!=null&&fileName!=""){
            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
            String path = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名

            //先判断文件是否存在
            String fileAdd = DateUtil.dateToDateString(new Date(),"yyyyMMdd");
            File file1 =new File(path+"/"+fileAdd);
            //如果文件夹不存在则创建
            if(!file1 .exists()  && !file1 .isDirectory()){
                file1 .mkdir();
            }
            targetFile = new File(file1, fileName);
//          targetFile = new File(path, fileName);
            try {
                file.transferTo(targetFile);
//              msg=returnUrl+fileName;
                msg=returnUrl+fileAdd+"/"+fileName;
                code=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(ResponseResult.result(code, msg));
    }

	/**
	 * 图片上传
	 * @param
	 * @param type
	 * @param multipartFile
	 * @param response
	 */

	@RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
	public String imageUpload(@RequestParam("type")String type, @RequestParam("picture")MultipartFile multipartFile,
			HttpServletResponse response) throws Exception {
		Map<String, Object> resObject = new HashMap<String, Object>();
		try {
			if (type != null 
					&& multipartFile != null && !multipartFile.isEmpty()) {
				if ("carousel".equals(type)) {
					ImageGroup imageGroup = commonService.imageHandle(multipartFile, new ImageConfigEnum(), "carousel");
					resObject.put("imageGroup", imageGroup);
				}else if ("advertisement".equals(type)){
                    ImageConfigEnum configEnum = new ImageConfigEnum();
					//ImageGroup imageGroup = commonService.imageHandle(multipartFile, configEnum, "advertisement");
					ImageGroup imageGroup = handle(multipartFile, configEnum.getLargeWidth(),
                            configEnum.getLargeHeight(), configEnum.getMediumWidth(),
                            configEnum.getMediumHeight(), configEnum.getThumbnailWidth(),
                            configEnum.getThumbnailHeight(), "advertisement");
                    resObject.put("imageGroup", imageGroup);
				}else if ("channel".equals(type)){
					ImageGroup imageGroup = commonService.imageHandle(multipartFile, new ImageConfigEnum(), "channel");
					resObject.put("imageGroup", imageGroup);
				}
			}
		} catch (Exception e) {
			//logger.warn("imageUpload", e);
			throw e;
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");

		try {
			//response.getWriter().write(JSONObject.toJSON(resObject, new JsonConfig()).toString());
		} catch (Exception e) {
			//logger.warn("imageUpload", e);
			throw e;
		}
        return JSON.toJSONString(resObject);
	}

    private ImageGroup handle(MultipartFile file, long largeWidth,
                              long largeHeiht, long mediumWidth, long mediumHeiht,
                              long thumbnailWidth, long thumbnailHeiht, String directory) {
	    String imagePath = "E:\\workspace2\\mavenweb\\target\\maven-web";
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

    @RequestMapping("/filesUpload")
    //requestParam要写才知道是前台的那个数组
    public String filesUpload(@RequestParam("myfiles") MultipartFile[] files,
                              HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list);
            }
        }
        //写着测试，删了就可以
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        return "index";//跳转的页面
    }

    private List<String> saveFile(HttpServletRequest request,
                                  MultipartFile file, List<String> list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
                String filePath = request.getSession().getServletContext()
                        .getRealPath("/")
                        + "upload/imgs" + file.getOriginalFilename();
                list.add(file.getOriginalFilename());
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();

                // 转存文件
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}

