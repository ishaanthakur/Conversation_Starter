package com.backend.challenge.service.util;

import lombok.extern.slf4j.Slf4j;
import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




@Slf4j
public class EncryptionService {

    private static String key = "IguessThisKeyOk";
    private static SecretKeySpec secret = null;
    public static Optional<String> encrypt(String value) {
        setUp();
        if (secret == null) {
            log.error("[Encrypt] Secret was generated incorrectly");
            return Optional.empty();
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            return Optional.of(Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes("UTF-8"))));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException e) {
            log.error("[Encrypt] Error while encrypting");
            e.printStackTrace();
            return Optional.empty();
        } catch (BadPaddingException | UnsupportedEncodingException | InvalidKeyException e) {
            log.error("[Encrypt] Error while encrypting");
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private static void setUp() {
        if (secret == null) {
            MessageDigest sha = null;
            byte[] bytes_key = new byte[0];
            try {
                bytes_key = key.getBytes("UTF-8");
                sha = MessageDigest.getInstance("SHA-1");
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
                log.error("[Encrypt] Error creating secret cause:", e.getCause());
                secret = null;
            }
            bytes_key = sha.digest(bytes_key);
            bytes_key = Arrays.copyOf(bytes_key, 16);
            secret = new SecretKeySpec(bytes_key, "AES");
        }
    }
}
