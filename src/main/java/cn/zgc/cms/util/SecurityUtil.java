package cn.zgc.cms.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密、解密相关类 
 * @author gczhang  E-mail:coding_zgc@163.com 
 */
public class SecurityUtil {
	
	public static String md5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		return new BigInteger(1, md.digest()).toString(16);
	}
	
	/*对密码进行MD5运算加入username更难破解*/
	public static String md5(String username,String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(username.getBytes());
		md.update(password.getBytes());
		return new BigInteger(1, md.digest()).toString(16);
	}
}
