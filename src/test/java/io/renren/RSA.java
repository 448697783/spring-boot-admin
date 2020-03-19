package io.renren;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;


public class RSA {

    private static String src ="my name is cuichongdong";

    public static void main(String[] args) {
        jdkRSA();
    }

    public static void jdkRSA(){

        //1.初始化秘钥
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rSAPublicKey = (RSAPublicKey)keyPair.getPublic();
            RSAPrivateKey private1 = (RSAPrivateKey)keyPair.getPrivate();
            System.out.println("puk  :"+Base64.encodeBase64String(rSAPublicKey.getEncoded()));
            System.out.println("pvk  :"+Base64.encodeBase64String(private1.getEncoded()));

            //2.私钥,公钥--加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(private1.getEncoded());
            KeyFactory instance = KeyFactory.getInstance("RSA");
            KeyFactory keyFactory = instance;
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("私钥加密,公钥解密--加密:"+Base64.encodeBase64String(result));


            //3.私钥加密,公钥解密---解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rSAPublicKey.getEncoded());
            keyFactory=KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result=cipher.doFinal(result);
            System.out.println("私钥加密,公钥解密---解密"+new String(result));

            //4.公钥加密,私密解密--加密
            x509EncodedKeySpec =new X509EncodedKeySpec(rSAPublicKey.getEncoded());
            keyFactory=KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result=cipher.doFinal(src.getBytes());
            System.out.println("公钥加密,私密解密--加密"+Base64.encodeBase64String(result));

            //5公钥加密,私钥解密--解密
            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(private1.getEncoded());
            keyFactory=KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result=cipher.doFinal(result);
            System.out.println("公钥加密,私钥解密--解密"+ new String(result));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }



}
