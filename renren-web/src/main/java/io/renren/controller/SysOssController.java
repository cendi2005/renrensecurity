package io.renren.controller;

import io.renren.entity.SysOssEntity;
import io.renren.oss.CloudStorageConfig;
import io.renren.oss.OSSFactory;
import io.renren.service.SysConfigService;
import io.renren.service.SysOssService;
import io.renren.utils.ConfigConstant;
import io.renren.utils.Constant;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.RRException;
import io.renren.validator.ValidatorUtils;
import io.renren.validator.group.AliyunGroup;
import io.renren.validator.group.QcloudGroup;
import io.renren.validator.group.QiniuGroup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 文件上传
 * 
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:oss:all")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysOssEntity> sysOssList = sysOssService.queryList(query);
		int total = sysOssService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysOssList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}


    /**
     * 云存储配置信息
     */
    @RequestMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@RequestMapping("/saveConfig")
	@RequiresPermissions("sys:oss:all")
	public R saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constant.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}
		

        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));

		return R.ok();
	}
	

	/**
	 * 上传文件
	 */
	@RequestMapping("/upload")
	@RequiresPermissions("sys:oss:all")
	public R upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}

		//上传文件
		String url = OSSFactory.build().upload(file.getBytes());

		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.save(ossEntity);

		return R.ok().put("url", url);
	}

	//处理文件上传
	@RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
	@ResponseBody
	public Map uploadImg(@RequestParam("file") MultipartFile file,
						 HttpServletRequest request, HttpServletResponse response) {
		String contentType = file.getContentType();
		String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
		String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
		try {
			TimeUnit.SECONDS.sleep(1);
			uploadFile(file.getBytes(), filePath, fileName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Map map = new HashMap(4);
		map.put("code",-1);
		System.out.println("=========================");
		//返回json
		return map;
	}




	//处理文件上传
	@RequestMapping(value="/testuploadimg1", method = RequestMethod.POST)
	@ResponseBody
	public Map uploadImg1(
						 HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");

		MultipartFile file = null;

		BufferedOutputStream stream = null;
		String filePath = request.getSession().getServletContext().getRealPath("imgupload/");


		for (int i =0; i< files.size(); ++i) {

			file = files.get(i);
			String fileName = file.getOriginalFilename();

			if (!file.isEmpty()) {

				try {
					TimeUnit.SECONDS.sleep(1);
					uploadFile(file.getBytes(), filePath, fileName);

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

		}
		Map map = new HashMap();
		map.put("code",0);

		return map;
	}


	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		System.out.println("path:"+targetFile.getAbsolutePath());
		FileOutputStream out = new FileOutputStream(filePath+fileName);
		out.write(file);
		out.flush();
		out.close();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public R delete(@RequestBody Long[] ids){
		sysOssService.deleteBatch(ids);

		return R.ok();
	}

}
