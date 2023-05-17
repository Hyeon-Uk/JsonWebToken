package com.example.jwt.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
//AES 대칭 암호화 관련 모듈
public class AES implements Encrypt{
    private static final String AES_ALGORITHM = "AES";//암호화 알고리즘
    private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String KEY = "0123456789abcdef";//키는 16바이트여야 한다.

    @Override
    public String encrypt(String data) throws Exception{
        byte[] encryptedBytes;

        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8),AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);

        encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    @Override
    public String decrypt(String encrypted) throws Exception {
        byte[] decryptedBytes;

        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8),AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE,secretKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);
        decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
