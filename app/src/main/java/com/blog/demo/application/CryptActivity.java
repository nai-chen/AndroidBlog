package com.blog.demo.application;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.blog.demo.R;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CryptActivity extends Activity implements View.OnClickListener {
	private static String HexStr = "0123456789abcdef";
	private static String DES_PWD = "12345678";
	private static String AES_PWD = "1234567890abcdef";
	
	private static byte[] DES_IV = {1,2,3,4,5,6,7,8};
	private static byte[] AES_IV = {1,2,3,4,5,6,7,8,9,0,'a','b','c','d','e','f'};
	
	private EditText mEtOriginal, mEtDesPwd, mEtAesPwd, mEtResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_crypt);
		
		mEtOriginal = (EditText) findViewById(R.id.et_original);
		
		findViewById(R.id.btn_sha).setOnClickListener(this);
		findViewById(R.id.btn_md5).setOnClickListener(this);
		
		findViewById(R.id.btn_des_encrypt).setOnClickListener(this);
		findViewById(R.id.btn_des_decrypt).setOnClickListener(this);
		mEtDesPwd = (EditText) findViewById(R.id.et_des_pwd);
		
		findViewById(R.id.btn_aes_encrypt).setOnClickListener(this);
		findViewById(R.id.btn_aes_decrypt).setOnClickListener(this);
		mEtAesPwd = (EditText) findViewById(R.id.et_aes_pwd);
		
		mEtResult = (EditText) findViewById(R.id.et_result);
	}

	@Override
	public void onClick(View v) {
		String value = mEtOriginal.getText().toString();
		if (v.getId() == R.id.btn_sha) {
			mEtResult.setText(shaEncrypt(value));
		} else if (v.getId() == R.id.btn_md5) {
			mEtResult.setText(md5Encrypt(value));
		} else if (v.getId() == R.id.btn_des_encrypt) {
//			mEtResult.setText(desEncrypt(value, mEtDesPwd.getText().toString()));
			mEtResult.setText(desEncrypt2(value, mEtDesPwd.getText().toString()));
		} else if (v.getId() == R.id.btn_des_decrypt) {
//			mEtResult.setText(desDecrypt(value, mEtDesPwd.getText().toString()));
			mEtResult.setText(desDecrypt2(value, mEtDesPwd.getText().toString()));
		}  else if (v.getId() == R.id.btn_aes_encrypt) {
//			mEtResult.setText(aesEncrypt(value, mEtAesPwd.getText().toString()));
			mEtResult.setText(aesEncrypt2(value, mEtAesPwd.getText().toString()));
		} else if (v.getId() == R.id.btn_aes_decrypt) {
//			mEtResult.setText(aesDecrypt(value, mEtAesPwd.getText().toString()));
			mEtResult.setText(aesDecrypt2(value, mEtAesPwd.getText().toString()));
		}  
	}
	
	private String shaEncrypt(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(value.getBytes());
			return byte2hex(md.digest());
		} catch (Exception e) {
			return value;
		}
	}
	
	private String md5Encrypt(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(value.getBytes());
			return byte2hex(md.digest());
		} catch (Exception e) {
			return value;
		}
	}

	public String desEncrypt(String value, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			byte[] encryptBytes = cipher.doFinal(value.getBytes());
			String result = byte2hex(encryptBytes);
			System.out.println("DES Encrypt:" + result);
			return result;
		} catch (Exception e) {
			return value;
		}
	}

	public String desDecrypt(String value, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			byte[] encryptBytes = cipher.doFinal(hex2byte(value));
			String result = new String(encryptBytes);
			System.out.println("DES Decrypt:" + result);

			return result;
		} catch (Exception e) {
			return value;
		}
	}

	private String aesEncrypt(String value, String pwd) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(pwd.getBytes("utf-8")));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptBytes = cipher.doFinal(value.getBytes());
			String result = byte2hex(encryptBytes);
			System.out.println("AES Encrypt:" + result);

			return result;
		} catch (Exception e) {
			return value;
		}
	}

	private String aesDecrypt(String value, String pwd) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(pwd.getBytes("utf-8")));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] encryptBytes = cipher.doFinal(hex2byte(value));
			String result = new String(encryptBytes);
			System.out.println("AES Decrypt:" + value);

			return result;
		} catch (Exception e) {
			return value;
		}
	}
	
	private String desEncrypt2(String value, String pwd) {
		try {
			SecretKeySpec key = new SecretKeySpec(pwd.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec zeroIv = new IvParameterSpec(DES_IV);
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] encryptBytes = cipher.doFinal(value.getBytes());
			String result = byte2hex(encryptBytes);
			System.out.println("DES Encrypt2:" + result);
			return result;
		} catch (Exception e) {
			return value;
		}
	}
	
	private String desDecrypt2(String value, String pwd) {
		try {
			SecretKeySpec key = new SecretKeySpec(pwd.getBytes(), "DES");  
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec zeroIv = new IvParameterSpec(DES_IV);
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);  
	        byte[] decryptBytes = cipher.doFinal(hex2byte(value));
	        String result = new String(decryptBytes);
	        System.out.println("DES Decrypt2:" + result);
	        return result;
		} catch (Exception e) {
			return value;
		}
	}
	
	private String aesEncrypt2(String value, String pwd) {
		try {
			SecretKeySpec key = new SecretKeySpec(pwd.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			IvParameterSpec zeroIv = new IvParameterSpec(AES_IV);
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] encryptBytes = cipher.doFinal(value.getBytes());
			String result = byte2hex(encryptBytes);
			System.out.println("AES Encrypt2:" + result);
			
			return result;
		} catch (Exception e) {
			return value;
		}
	}
	
	private String aesDecrypt2(String value, String pwd) {
		try {
			SecretKeySpec key = new SecretKeySpec(pwd.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec zeroIv = new IvParameterSpec(AES_IV);
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte[] decryptBytes = cipher.doFinal(hex2byte(value));
			String result = new String(decryptBytes);
			System.out.println("AES Decrypt2:" + result);
			return result;
		} catch (Exception e) {
			return value;
		}

	}
	
	private String byte2hex(byte[] bytes) {
		String result = new String();
		for (byte b : bytes) {
			result = result + HexStr.charAt((b >> 4) & 0xF);
			result = result + HexStr.charAt(b& 0xF);
		}
		return result;
	}
	
	private byte[] hex2byte(String value) {
		byte[] bytes = new byte[value.length()/2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)Integer.parseInt(value.substring(i * 2, (i + 1) * 2), 16);
		}
		return bytes;
	}

	public String getSignature() {
		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo pkgInfo = packageManager.getPackageInfo(
					getPackageName(), PackageManager.GET_SIGNATURES);

			if (pkgInfo.signatures != null && pkgInfo.signatures.length > 0) {
				return getCert(pkgInfo.signatures[0].toByteArray());
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return "";
	}

	private String getCert(byte[] cert) {
		InputStream input = new ByteArrayInputStream(cert);
		CertificateFactory cf = null;
		X509Certificate c = null;
		try {
			cf = CertificateFactory.getInstance("X509");
			c = (X509Certificate) cf.generateCertificate(input);
		} catch (CertificateException e) {
			e.printStackTrace();
		}

		String hexString = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA256");
			byte[] publicKey = md.digest(c.getEncoded());
			hexString = byte2HexFormat(publicKey);
		} catch (NoSuchAlgorithmException e1) {

		} catch (CertificateEncodingException e) {

		}
		return hexString;
	}

	private static String byte2HexFormat(byte[] arr) {
		StringBuilder str = new StringBuilder(arr.length * 2);
		for (int i = 0; i < arr.length; i++) {
			String h = Integer.toHexString(arr[i]);
			int l = h.length();
			if (l == 1)
				h = "0" + h;
			if (l > 2)
				h = h.substring(l - 2, l);
			str.append(h.toUpperCase());
			if (i < (arr.length - 1))
				str.append(':');
		}
		return str.toString();
	}
	
}
