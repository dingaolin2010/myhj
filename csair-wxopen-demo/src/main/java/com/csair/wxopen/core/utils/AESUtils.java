package com.csair.wxopen.core.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;

/**
 * @author xh
 * @since 2016/10/1.
 */
public class AESUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtils.class);
    //算法名
    private static final String KEY_ALGORITHM = "AES";
    //加解密算法/模式/填充方式
    //可以任意选择，为了方便后面与iOS端的加密解密，采用与其相同的模式与填充方式
    //ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个参数iv
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    private AESUtils() {
    }


    //生成iv
    private static AlgorithmParameters generateIV(byte[] key) throws NoSuchAlgorithmException, InvalidParameterSpecException {
        AlgorithmParameters params = AlgorithmParameters.getInstance(KEY_ALGORITHM);
        params.init(new IvParameterSpec(key));
        return params;
    }

    //转化成JAVA的密钥格式
    private static Key convertToKey(byte[] keyBytes) {
        return new SecretKeySpec(keyBytes, KEY_ALGORITHM);
    }

    //加密
    public static byte[] encrypt(byte[] data, byte[] keyBytes) {
        try {
            AlgorithmParameters iv = generateIV(keyBytes);
            //转化为密钥
            Key key = convertToKey(keyBytes);
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            //设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            //此处使用BASE64做转码功能，同时能起到2次加密的作用。
            return Base64.encodeBase64(cipher.doFinal(data));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return new byte[0];
        }

    }

    //解密
    public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes)  {
        try {
            AlgorithmParameters iv = generateIV(keyBytes);
            Key key = convertToKey(keyBytes);
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            //设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            return cipher.doFinal(Base64.decodeBase64(encryptedData));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return new byte[0];
        }
    }
    
    public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes, byte[] iv){
    	try {
            AlgorithmParameters IV = generateIV(iv);
            Key key = convertToKey(keyBytes);
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            //设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, key, IV);
            return cipher.doFinal(Base64.decodeBase64(encryptedData));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return new byte[0];
        }
    }
}
