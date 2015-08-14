package com.hoteam.wolf.controller.site;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hoteam.wolf.domain.AppDownload;
import com.hoteam.wolf.service.DownLoadService;

@Controller
@RequestMapping("/download")
public class DownloadController {

	@Autowired
	private DownLoadService downLoadService;

	@RequestMapping("/app/{category}/{version}")
	public ModelAndView app(HttpServletRequest request, HttpServletResponse response, @PathVariable String version,
			@PathVariable String category) throws Exception {
		String address = this.getRemoteHost(request);
		response.setCharacterEncoding("utf-8");
		String fileName = "";
		if (category.equals("android")) {
			fileName = "niubox.apk";
		} else if (category.equals("ios")) {
			fileName = "niubox.ipa";
		} else {
			category = null;
		}
		if (null != category) {
			AppDownload appDownload = new AppDownload(address, version, category);
			this.downLoadService.saveRecord(appDownload);
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			String path = request.getSession().getServletContext().getRealPath("");
			path  = path+"app/";
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			File app = new File(path + fileName);
			// 获取文件的长度
			long fileLength = app.length();

			// 设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流
			bis = new BufferedInputStream(new FileInputStream(app));
			// 输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			// 关闭流
			bis.close();
			bos.close();
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
