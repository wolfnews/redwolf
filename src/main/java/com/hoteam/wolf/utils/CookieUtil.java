package com.hoteam.wolf.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CookieUtil {

	private final static Logger logger = Logger.getLogger(CookieUtil.class);
	private final static String webKey = "RED_WOLF";// 加密cookie时的网站自定码

	private final static long cookieMaxAge = 60 * 60 * 24 * 7;// 设置cookie有效期是两个星期，根据需要自定义
	// 保存Cookie到客户端-------------------------------------------------------------------------

	public static void saveCookie(String cookieDomainName, String cookieDomainValue, HttpServletResponse response)
			throws Exception {
		// cookie的有效期
		long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000);
		// MD5加密用户详细信息
		String cookieNameWithMd5 = getMD5(cookieDomainName + webKey);
		cookieDomainValue = DESUtil.encrypt(cookieDomainValue, webKey);
		// 将要被保存的完整的Cookie值
		String cookieValue = cookieDomainValue + ":" + validTime + ":" + cookieNameWithMd5;
		// 再一次对Cookie的值进行BASE64编码
		String cookieValueBase64 = new String(BASE64.encode(cookieValue.getBytes()));
		// 开始保存Cookie
		Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64);
		// 存两年(这个值应该大于或等于validTime)
		cookie.setMaxAge(60 * 60 * 24 * 30);
		// cookie有效路径是网站根目录
		cookie.setPath("/");
		// 向客户端写入
		response.addCookie(cookie);
	}

	public static String readCookie(String cookieDomainName, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 根据cookieName取cookieValue
		Cookie cookies[] = request.getCookies();
		String cookieValue = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookieDomainName.equals(cookies[i].getName())) {
					cookieValue = cookies[i].getValue();
					break;
				}
			}
		}
		if (cookieValue == null) {// 如果cookieValue为空,返回
			logger.warn("cookie is null");
			return null;
		}
		// 如果cookieValue不为空,才执行下面的代码
		// 先得到的CookieValue进行Base64解码
		String cookieValueAfterDecode = new String(BASE64.decode(cookieValue), "utf-8");
		String cookieValues[] = cookieValueAfterDecode.split(":");
		if (cookieValues.length != 3) {// 非法登录
			logger.error("非法登录");
			return null;
		}
		String cookieNameWithMd5 = getMD5(cookieDomainName + webKey);
		if (!cookieValues[2].equals(cookieNameWithMd5)) {
			logger.error("非法登录");
			return null;
		}
		// 判断是否在有效期内,过期就删除Cookie
		long validTimeInCookie = new Long(cookieValues[1]);
		if (validTimeInCookie < System.currentTimeMillis()) {// cookie过期
			clearCookie(response, cookieDomainName);
			logger.error("cookie");
			return null;
		}
//		return cookieValues[0];
		return DESUtil.decrypt(cookieValues[0], webKey);
	}

	// 用户注销时,清除Cookie,在需要时可随时调用-----------------------------------------------------
	public static void clearCookie(HttpServletResponse response, String cookieDomainName) {
		Cookie cookie = new Cookie(cookieDomainName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	// 获取Cookie组合字符串的MD5码的字符串----------------------------------------------------------------
	public static String getMD5(String value) {
		String result = null;
		try {
			byte[] valueByte = value.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(valueByte);
			result = toHex(md.digest());
		} catch (NoSuchAlgorithmException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	// 将传递进来的字节数组转换成十六进制的字符串形式并返回
	private static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}
}