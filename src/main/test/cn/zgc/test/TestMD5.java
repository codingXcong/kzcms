package cn.zgc.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.junit.Test;

/**
 *  
 * @author gczhang
 * 
 */
public class TestMD5 {
	public static void main(String[] args) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update("abc".getBytes(), 0, "abc".length());
			BigInteger bi = new BigInteger(1, md.digest());
			System.out.println(bi.toString(16));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
}
