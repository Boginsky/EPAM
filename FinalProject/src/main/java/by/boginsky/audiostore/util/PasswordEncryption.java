package by.boginsky.audiostore.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type Password encryption.
 */
public class PasswordEncryption {

    private static final String MESSAGE_DIGEST_5 = "MD5";
    private static final Logger logger = LogManager.getLogger();

    /**
     * Encrypts password string.
     *
     * @param password the password
     * @return the string
     */
    public static String encryptsPassword(String password) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance(MESSAGE_DIGEST_5);
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
        }

        BigInteger bigInteger = new BigInteger(1, digest);
        StringBuilder encryptedPassword = new StringBuilder(bigInteger.toString(16));

        while (encryptedPassword.length() < 32) {
            encryptedPassword.insert(0, "0");
        }
        return encryptedPassword.toString();
    }
}
