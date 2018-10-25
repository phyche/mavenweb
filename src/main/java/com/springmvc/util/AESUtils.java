/*
package com.springmvc.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
*/
/*
 * 加密用的Key 可以用26个字母和数字组成
* 此处使用AES-128-CBC加密模式，key需要为16位。
*//*

private String sKey="0123456789abcdef";
private String ivParameter="0123456789abcdef";
private static AESUtils instance=null;
private AESUtils(){

            }
public static AESUtils getInstance(){
            if (instance==null)
                instance= new AESUtils();
            return instance;
            }
// 加密
         public String encrypt(String sSrc) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。
         }

         // 解密
         public String decrypt(String sSrc) throws Exception {
            try {
                byte[] raw = sKey.getBytes("ASCII");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
                byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
                } catch (Exception ex) {
                return null;
                }
            }

        public static void main(String[] args) throws Exception {
            // 需要加密的字串
            String cSrc = "一个有希望的民族，不能没有英雄。一个有前途的国家，不能没有先锋。";
            System.out.println("原始数据：" + cSrc);
            // 加密
            long lStart = System.currentTimeMillis();
            String enString = AESUtils.getInstance().encrypt(cSrc);
            System.out.println("加密后的字串是：" + enString);

            long lUseTime = System.currentTimeMillis() - lStart;
            System.out.println("加密耗时：" + lUseTime + "毫秒");
            // 解密
            lStart = System.currentTimeMillis();
            String DeString = AESUtils.getInstance().decrypt(enString);
            System.out.println("解密后的字串是：" + DeString);
            lUseTime = System.currentTimeMillis() - lStart;
            System.out.println("解密耗时：" + lUseTime + "毫秒");
}

}
*/
