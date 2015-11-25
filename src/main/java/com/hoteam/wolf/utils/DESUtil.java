package com.hoteam.wolf.utils;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESUtil {
	private final static String DES = "DES";

	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws Exception {
		byte[] bt = encrypt(data.getBytes(), key.getBytes());
		String strs = BASE64.encode(bt);
		return strs;
	}

	/**
	 * Description 根据键值进行解密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws IOException, Exception {
		if (data == null)
			return null;
		byte[] buf = BASE64.decode(data);
		byte[] bt = decrypt(buf, key.getBytes());
		return new String(bt);
	}

	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}

	/**
	 * Description 根据键值进行解密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}
	private static final String SYSTEM_KEY="DMWSQWHLS";
	public static void main(String[] args) throws Exception {
//		String account  = DESUtil.encrypt("zhhwdmw@163.com", "WolfNews888");
//		String password  = DESUtil.encrypt("5213344", "WolfNews888");
//		String server  = DESUtil.encrypt("smtp.163.com", "WolfNews888");
//		String portal  = DESUtil.encrypt("smtp", "WolfNews888");
//		String port  = DESUtil.encrypt("465", "WolfNews888");
//		
//		System.out.println(account);
//		System.out.println(password);
//		System.out.println(server);
//		System.out.println(portal);
//		System.out.println(port);
		System.out.println(DESUtil.encrypt("1267018001", SYSTEM_KEY));
		System.out.println(DESUtil.encrypt("b749a0799fa7cb2b3798c9b64defd45b", SYSTEM_KEY));
		System.out.println(DESUtil.decrypt("0rr3Aytlm+mfHLxDRXeumA==", SYSTEM_KEY));
		System.out.println(DESUtil.decrypt("n0bK5H1SgLhLzXEavhYfZH1IXMMdTArpGT08NGZpHW/NBbQ5dIslTg==", SYSTEM_KEY));
	}
}