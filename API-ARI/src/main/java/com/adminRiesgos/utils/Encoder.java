package com.adminRiesgos.utils;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Data
@NoArgsConstructor
public class Encoder {

    private final String algorithm = "AES";

    private static SecretKey getKeyFromPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");

    }

    private static IvParameterSpec generateIV(String root){
        byte[] iv = root.getBytes();
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public  String encrypt(String data, String key, String salt) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, getKeyFromPassword(key, salt));
        byte[] cipherText = cipher.doFinal(data.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }


    public String decypt(String data, String key, String salt) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.DECRYPT_MODE,getKeyFromPassword(key, salt));
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(data));

        return new String(plainText);
    }


}
