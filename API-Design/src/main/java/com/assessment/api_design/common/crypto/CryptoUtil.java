package com.assessment.api_design.common.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptoUtil {
    private static final String AES = "AES";
    private static final String AES_GCM = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH_BITS = 128;

    public static String encryptAES(String data, String base64Token, String nonce) {
        validateNonce(nonce);

        try {
            SecretKey key = decodeBase64Key(base64Token);
            byte[] iv = nonce.getBytes(StandardCharsets.UTF_8);

            Cipher cipher = Cipher.getInstance(AES_GCM);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv);

            cipher.init(Cipher.ENCRYPT_MODE, key, spec);

            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            throw new IllegalStateException("AES encryption failed", e);
        }
    }

    private static void validateNonce(String nonce) {
        if (nonce == null || nonce.length() != 12) {
            throw new IllegalArgumentException("Nonce must be exactly 12 characters long");
        }
    }

    private static SecretKey decodeBase64Key(String base64Key) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(decodedKey, AES);
    }
}