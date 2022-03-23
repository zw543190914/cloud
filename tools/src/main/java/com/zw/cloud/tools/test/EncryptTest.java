package com.zw.cloud.tools.test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptTest {

    private static final String ALGO = "DES";

    private static final String PSW = "12345678";

    private static byte[] encrypt(byte[] src) {
        try {
            // DES 算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(EncryptTest.PSW.getBytes());
            // 创建一个密匙工厂，然后用它把 DESKeySpec 转换成 SecretKey
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGO);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(ALGO);
            // 用密匙初始化 Cipher 对象，ENCRYPT_MODE 用于将 Cipher 初始化为加密模式的常量
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 加密数据
            return cipher.doFinal(src);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(byte[] src) {
        try {
            // DES 算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个 DESKeySpec 对象
            DESKeySpec desKey = new DESKeySpec(EncryptTest.PSW.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGO);
            // 将 DESKeySpec 对象转换成 SecretKey 对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher 对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(ALGO);
            // 用密匙初始化 Cipher 对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 解密数据
            return cipher.doFinal(src);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String data = "hello Test symmetric encry";
//        byte[] encryData = encrypt(data.getBytes());
//        System.out.println("encryData = " + new String(encryData));
//        byte[] decryData = decrypt(encryData);
//        System.out.println("decryData = " + new String(decryData));
        String encrypted = encrypted(data);
        System.out.println(encrypted);
        System.out.println(decrypted("II/KEzGFvHp8HH2z1rw0YSZc9AVbP66NMIEqUPYRT+w="));
    }

    public static String encrypted(String password){
        return Base64.getEncoder().encodeToString(encrypt(password.getBytes()));
    }

    public static String decrypted(String data){
        byte[] decode = Base64.getDecoder().decode(data);
        return new String(decrypt(decode));
    }
}
