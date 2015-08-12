package com.hoteam.wolf.service.impl;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.config.SmsConfig;
import com.hoteam.wolf.common.vo.SmsBean;
import com.hoteam.wolf.common.vo.SmsResult;
import com.hoteam.wolf.dao.UserDao;
import com.hoteam.wolf.http.HttpClient;
import com.hoteam.wolf.service.SmsService;
import com.hoteam.wolf.utils.DESUtil;

@Service("smsService")
public class SmsServiceImpl implements SmsService {
	private static Logger logger = Logger.getLogger(SmsServiceImpl.class);
	private static final String KEY = "NIUGUHUI88";
	@Autowired
	private SmsConfig smsConfig;
	@Autowired
	private HttpClient httpClient;
	@Autowired
	private UserDao userDao;
	private SmsConfig config;

	@PostConstruct
	public void init() throws IOException, Exception {
		config = new SmsConfig();
		config.setHost(DESUtil.decrypt(smsConfig.getHost(), KEY));
		config.setUserId(DESUtil.decrypt(smsConfig.getUserId(), KEY));
		config.setUsername(DESUtil.decrypt(smsConfig.getUsername(), KEY));
		config.setPassword(DESUtil.decrypt(smsConfig.getPassword(), KEY));
	}

	@Override
	public Result sendMessage(String mobile, String content) {
		if (this.userDao.mobileExist(mobile)) {
			logger.warn("mobile【" +mobile + "】exist!");
			return new Result(false, "手机号码已经存在！");
		}else{
			try {
				SmsResult smsResult = Send(config.getHost(), new Integer(config.getUserId()), config.getUsername(),
						config.getPassword(), mobile, content);
				if (1 == smsResult.getStatusCode()) {
					logger.info("短信发送成功");
					return new Result(true, "发送成功！");
				} else {
					logger.warn("短信发送失败！" + smsResult.getErrors().toString());
					return new Result(false, "发送失败！");
				}
			} catch (NumberFormatException e) {
				logger.error("短信发送异常：数字格式错误！");
				return new Result(false, "发送失败！");
			} catch (Exception e) {
				logger.error("短信发送异常：", e);
				return new Result(false, "发送失败！");
			}
		}
	}

	@SuppressWarnings("restriction")
	private SmsResult Send(String url, int userId, String userName, String password, String mobile, String text)
			throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
		String stamp = df.format(new Date());
		String secret = password + stamp;
		secret = Md5Encrypt(secret);

		SmsBean smsBean = new SmsBean(userName, secret, stamp, mobile, text, "111", null);
		String jsonString = JSONObject.toJSONString(smsBean);
		byte[] data = jsonString.getBytes("utf-8");
		byte[] pwdArray = password.getBytes("ascii");// des加密的key，只取密码的前8位，不够时右侧补0。
		byte[] key = new byte[8];
		int len = Math.min(8, pwdArray.length);
		System.arraycopy(pwdArray, 0, key, 0, len);
		data = DESEncrypt(data, key);
		String text64 = new sun.misc.BASE64Encoder().encode(data);
		Map<String,String> param = new HashMap<String,String>();
		param.put("UserId", userId + "");
		param.put("Text64", text64);
		String resultString = httpClient.post(param, url);
		return assembleResult(resultString);
	}

	public byte[] DESEncrypt(byte[] datasource, byte[] key) throws Exception {
		// SecureRandom random = new SecureRandom();
		DESKeySpec desKey = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

		IvParameterSpec iv = new IvParameterSpec(key);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(datasource);
	}

	private String Md5Encrypt(String s) throws Exception {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		byte[] btInput = s.getBytes();
		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		// 使用指定的字节更新摘要
		mdInst.update(btInput);
		// 获得密文
		byte[] md = mdInst.digest();
		// 把密文转换成十六进制的字符串形式
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}

	private SmsResult assembleResult(String content){
		JSONObject jsonObject = JSONObject.parseObject(content, JSONObject.class);
		SmsResult result = new SmsResult();
		result.setAmount(jsonObject.getLongValue("Amount"));
		result.setDescription(jsonObject.getString("Description"));
		result.setMsgId(jsonObject.getString("MsgId"));
		result.setStatusCode(jsonObject.getIntValue("StatusCode"));
		result.setSuccessCounts(jsonObject.getIntValue("SuccessCounts"));
		return result;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(DESUtil.encrypt("http://dx.ipyy.net/ensms.ashx", KEY));
		System.out.println(DESUtil.encrypt("2405", KEY));
		System.out.println(DESUtil.encrypt("xd000913", KEY));
		System.out.println(DESUtil.encrypt("xd00091312", KEY));

	}
}
