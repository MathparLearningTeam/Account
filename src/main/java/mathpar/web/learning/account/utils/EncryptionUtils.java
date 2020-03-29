package mathpar.web.learning.account.utils;

import org.springframework.security.crypto.codec.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptionUtils {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateString(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    public static String createHash(String raw){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
            return new String(Hex.encode(encodedHash));
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Couldn't happen", e);
        }
    }
}
