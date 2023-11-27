package utils;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class Crypto {

    private static final String ALGORITHM = "DES";

    public static String encrypt(String value, String password) throws Exception {
        byte[] keyBytes = adjustKey(password.getBytes(StandardCharsets.UTF_8));

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedByteValue = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedByteValue);
    }

    public static String decrypt(String value, String key) throws Exception {
        byte[] keyBytes = adjustKey(key.getBytes(StandardCharsets.UTF_8));

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] decryptedByteValue = cipher.doFinal(Base64.getDecoder().decode(value));
        return new String(decryptedByteValue, StandardCharsets.UTF_8);
    }

    public static SecretKey generateSecretKey() throws Exception {
        return KeyGenerator.getInstance(ALGORITHM).generateKey();
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }

    public static String generateKey() throws Exception {
        SecretKey generateKeykey = Crypto.generateSecretKey();
        byte[] keyBytes = generateKeykey.getEncoded();
        return Crypto.bytesToHex(keyBytes);
    }

    private static byte[] adjustKey(byte[] key) {
        if (key.length < 8) {
            return Arrays.copyOf(key, 8);
        } else if (key.length > 8) {
            return Arrays.copyOfRange(key, 0, 8);
        } else {
            return key;
        }
    }

}