package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.taotao.common.util.FTPUtil;
import com.taotao.common.util.IDUtils;
import com.taotao.service.PictureService;

/**
 * 图片上传服务
 * 
 * @author 浮生若梦 2016年10月3日 下午5:02:02
 */
@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private int FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	@Value("${FILI_UPLOAD_PATH}")
	private String FILI_UPLOAD_PATH;

	@Override
	public Map<String, Object> uploadPicture(MultipartFile uploadFile) {
		Map<String, Object> map = new HashMap<>();
		try {
			// 生成一个新的文件名
			// 取原始文件的原始文件名
			String oldName = uploadFile.getOriginalFilename();
			// 生成新的文件名
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			// 图片上传
			Boolean flag = FTPUtil.uploadFile(FTP_ADDRESS.trim(), FTP_PORT, FTP_USERNAME.trim(), FTP_PASSWORD.trim(), FTP_BASEPATH.trim(),
					imagePath, newName, uploadFile.getInputStream());
			if(!flag){
				map.put("error", 1);
				map.put("message", "上传失败");
				return map;
			}else{
				map.put("error", 0);
				map.put("url", IMAGE_BASE_URL+imagePath+"/"+newName);
				return map;
			}
		} catch (IOException e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", "上传失败，发生异常");
			return map;
		}
	}
}
