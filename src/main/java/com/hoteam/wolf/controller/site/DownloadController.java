package com.hoteam.wolf.controller.site;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoteam.wolf.domain.AppDownload;
import com.hoteam.wolf.service.DownLoadService;

@Controller
@RequestMapping("/download")
public class DownloadController {

	@Autowired
	private DownLoadService downLoadService;

	@RequestMapping("/app")
	public String app(HttpServletRequest request, HttpServletResponse response, String version, String category) {
		String address = this.getRemoteHost(request);
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		String fileName = "";
		if (category.equals("android")) {
			fileName = "static/app/niuguhui.apk";
		} else if (category.equals("ios")) {
			fileName = "static/app/niuguhui.ipa";
		} else {
			category = null;
		}
		if (null != category) {
			AppDownload appDownload = new AppDownload(address, version, category);
			this.downLoadService.saveRecord(appDownload);
		}
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		try {
			InputStream inputStream = new FileInputStream(new File(basePath + fileName));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.flush();
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			return "文件没找到";
		} catch (IOException e) {
			return "文件没找到！";
		}
		return null;
	}

	private String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
}
