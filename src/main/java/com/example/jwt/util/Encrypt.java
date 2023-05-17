package com.example.jwt.util;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface Encrypt {
    public String encrypt(String data) throws Exception;
    public String decrypt(String encrypted) throws Exception;
}
