package com.Hoime.CareClean.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Component
public class Security extends JwtTokenProvider {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Security() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String hashPassword(String str) {
        if (str != null) {
            return bCryptPasswordEncoder.encode(str);
        }
        return "";
    }

    public Boolean comparePasswords(String pass, String hash) {
        if (!(pass.isEmpty() && hash.isEmpty())) {
            return bCryptPasswordEncoder.matches(pass, hash);
        } else return false;
    }

    private static final String AES_ALGORITHM = "Bee_point";

    public static String encrypt(String plaintext, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(
                key.getBytes(StandardCharsets.UTF_8),
                AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(
                plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }


    public String buildIdLocker(String idCategory) {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        timeStamp = idCategory + timeStamp.trim().substring(timeStamp.length() - 7);

        int result = 0;
        int timeLength = timeStamp.length();
        for (int i = 0; i < timeLength / 2; i++) {
            int start = timeStamp.charAt(i) - '0';
            int end = timeStamp.charAt(timeLength - 1 - i) - '0';
            result += start * end;
        }

        result %= 10;

        return timeStamp + result;
    }

    public boolean checkSumIdLocker(String idLocker) {
        int idLength = idLocker.length();
        int digitCheck = idLocker.charAt(idLength - 1) - '0';
        String idCheck = idLocker.substring(0, idLength - 1);
        idLength = idCheck.length();

        int result = 0;
        for (int i = 0; i < idLength / 2; i++) {
            int start = idCheck.charAt(i) - '0';
            int end = idCheck.charAt(idLength - 1 - i) - '0';
            result += start * end;
        }

        result %= 10;
        return (digitCheck == result);
    }

}