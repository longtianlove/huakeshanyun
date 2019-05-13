package com.stys.ipfs.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.stys.ipfs.codec.Base64;

public class RSAUtil {

	public static final String SIGN_RSA = "RSA";

	public static final String SIGN_SHA1RSA_ALGORITHMS = "SHA1WithRSA";

	public static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

	public static final String CHARSET_UTF8 = "UTF-8";

	/**
	 * 生成密钥对
	 */
	public static Map<String, String> generateKeyPair(int keySize) {
		try {
			/** RSA算法要求有一个可信任的随机数源 */
			SecureRandom sr = new SecureRandom();
			/** 为RSA算法创建一个KeyPairGenerator对象 */
			KeyPairGenerator kpg = KeyPairGenerator.getInstance(SIGN_RSA);
			/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
			kpg.initialize(keySize, sr);
			/** 生成密匙对 */
			KeyPair kp = kpg.generateKeyPair();
			/** 得到公钥 */
			Key publicKey = kp.getPublic();
			byte[] publicKeyBytes = publicKey.getEncoded();
			String pub = new String(Base64.encodeBase64(publicKeyBytes), CHARSET_UTF8);
			/** 得到私钥 */
			Key privateKey = kp.getPrivate();
			byte[] privateKeyBytes = privateKey.getEncoded();
			String pri = new String(Base64.encodeBase64(privateKeyBytes), CHARSET_UTF8);

			Map<String, String> map = new HashMap<String, String>();
			map.put("publicKey", pub);
			map.put("privateKey", pri);

			return map;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取签名内容
	 * 
	 * @param params
	 * @return
	 */
	public static String getSignContent(Map<String, String> params) {
		// 移除签名值
		params.remove("sign");

		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (FmStringUtils.areNotEmpty(key, value)) {
				content.append((index == 0 ? "" : "&") + key + "=" + value);
				index++;
			}
		}
		return content.toString();
	}

	/**
	 * sha1WithRsa 加签
	 * 
	 * @param params
	 * @param privateKey
	 * @param charset
	 * @return
	 */
	public static String rsaSign(Map<String, String> params, String privateKey) {
		try {
			String content = getSignContent(params);
			return rsaSign(content, privateKey, CHARSET_UTF8);
		} catch (SignException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * sha1WithRsa 加签
	 * 
	 * @param content
	 * @param privateKey
	 * @param charset
	 * @return
	 * @throws SignException
	 */
	public static String rsaSign(String content, String privateKey, String charset) throws SignException {
		try {
			PrivateKey priKey = getPrivateKeyFromPKCS8(SIGN_RSA, new ByteArrayInputStream(privateKey.getBytes()));

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_SHA1RSA_ALGORITHMS);

			signature.initSign(priKey);

			if (StringUtils.isEmpty(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));
		} catch (InvalidKeySpecException ie) {
			throw new SignException("RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥", ie);
		} catch (Exception e) {
			throw new SignException("RSAcontent = " + content + "; charset = " + charset, e);
		}

	}

	/**
	 * sha256WithRsa 加签
	 * 
	 * @param params
	 * @param privateKey
	 * @param charset
	 * @return
	 */
	public static String rsa256Sign(Map<String, String> params, String privateKey) {
		try {
			String content = getSignContent(params);
			return rsa256Sign(content, privateKey, CHARSET_UTF8);
		} catch (SignException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * sha256WithRsa 加签
	 * 
	 * @param content
	 * @param privateKey
	 * @param charset
	 * @return
	 * @throws SignException
	 */
	public static String rsa256Sign(String content, String privateKey, String charset) throws SignException {
		try {
			PrivateKey priKey = getPrivateKeyFromPKCS8(SIGN_RSA, new ByteArrayInputStream(privateKey.getBytes()));

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);

			signature.initSign(priKey);

			if (StringUtils.isEmpty(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));
		} catch (InvalidKeySpecException ie) {
			throw new SignException("RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥", ie);
		} catch (Exception e) {
			throw new SignException("RSAcontent = " + content + "; charset = " + charset, e);
		}

	}

	/**
	 * sha1WithRsa 验签
	 * 
	 * @param params
	 * @param sign
	 * @param publicKey
	 * @return
	 */
	public static boolean rsaCheckContent(Map<String, String> params, String sign, String publicKey) {
		try {
			String content = getSignContent(params);
			return rsaCheckContent(content, sign, publicKey, CHARSET_UTF8);
		} catch (SignException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * sha1WithRsa 验签
	 * 
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @param charset
	 * @return
	 * @throws SignException
	 */
	public static boolean rsaCheckContent(String content, String sign, String publicKey, String charset)
			throws SignException {
		try {
			PublicKey pubKey = getPublicKeyFromX509(SIGN_RSA, new ByteArrayInputStream(publicKey.getBytes()));

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_SHA1RSA_ALGORITHMS);

			signature.initVerify(pubKey);

			if (StringUtils.isEmpty(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			return signature.verify(Base64.decodeBase64(sign.getBytes()));
		} catch (Exception e) {
			throw new SignException("RSAcontent = " + content + ",sign=" + sign + ",charset = " + charset, e);
		}
	}

	/**
	 * sha256WithRsa 验签
	 * 
	 * @param params
	 * @param sign
	 * @param publicKey
	 * @return
	 */
	public static boolean rsa256CheckContent(Map<String, String> params, String sign, String publicKey) {
		try {
			String content = getSignContent(params);
			return rsa256CheckContent(content, sign, publicKey, CHARSET_UTF8);
		} catch (SignException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * sha256WithRsa 验签
	 * 
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @param charset
	 * @return
	 * @throws SignException
	 */
	public static boolean rsa256CheckContent(String content, String sign, String publicKey, String charset)
			throws SignException {
		try {
			PublicKey pubKey = getPublicKeyFromX509(SIGN_RSA, new ByteArrayInputStream(publicKey.getBytes()));

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);

			signature.initVerify(pubKey);

			if (StringUtils.isEmpty(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			return signature.verify(Base64.decodeBase64(sign.getBytes()));
		} catch (Exception e) {
			throw new SignException("RSAcontent = " + content + ",sign=" + sign + ",charset = " + charset, e);
		}
	}

	/**
	 * 加密方法
	 * 
	 * @param source
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String source, String publicKey) {
		try {
			PublicKey key = getPublicKeyFromX509(SIGN_RSA, new ByteArrayInputStream(publicKey.getBytes()));
			/** 得到Cipher对象来实现对源数据的RSA加密 */
			Cipher cipher = Cipher.getInstance(SIGN_RSA);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] b = source.getBytes();
			/** 执行加密操作 */
			byte[] b1 = cipher.doFinal(b);
			return new String(Base64.encodeBase64(b1), CHARSET_UTF8);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 解密
	 * 
	 * @param cryptograph
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String cryptograph, String privateKey) {
		try {
			PrivateKey key = getPrivateKeyFromPKCS8(SIGN_RSA, new ByteArrayInputStream(privateKey.getBytes()));

			/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
			Cipher cipher = Cipher.getInstance(SIGN_RSA);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] b1 = Base64.decodeBase64(cryptograph.getBytes());
			/** 执行解密操作 */
			byte[] b = cipher.doFinal(b1);
			return new String(b);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
		if (ins == null || StringUtils.isEmpty(algorithm)) {
			return null;
		}

		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		byte[] encodedKey = StreamUtil.readText(ins).getBytes();

		encodedKey = Base64.decodeBase64(encodedKey);

		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
	}

	public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		StringWriter writer = new StringWriter();
		StreamUtil.io(new InputStreamReader(ins), writer);

		byte[] encodedKey = writer.toString().getBytes();

		encodedKey = Base64.decodeBase64(encodedKey);

		return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
	}

	public static void main(String[] args) {
		int keySize = 2048;// 密钥长度，1024或2048
		Map<String, String> keys = RSAUtil.generateKeyPair(keySize);
		String privateKey = keys.get("privateKey");
		String publicKey = keys.get("publicKey");
		System.out.println("生成的私钥：\n" + privateKey);
		System.out.println("生成的公钥：\n" + publicKey);
		System.out.println("");
		System.out.println("=========================================================================");
		System.out.println("");

		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "liuguihua");
		params.put("mobile", "18680509833");
		params.put("timestamp", "1539144000");

		System.out.println("签名内容：" + RSAUtil.getSignContent(params));

		String sign = RSAUtil.rsa256Sign(params, privateKey);
		System.out.println("签名值：" + sign);

		boolean check = RSAUtil.rsa256CheckContent(params, sign, publicKey);
		System.out.println("验签结果：" + check);

	}

}
