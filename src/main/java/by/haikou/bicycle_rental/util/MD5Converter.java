package by.haikou.bicycle_rental.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Converts string to hash using <b>MD5</b>.
 * Use to get the user password hash
 */
public class MD5Converter {
    private static final Logger log = LogManager.getLogger(MD5Converter.class);

    /**
     * @param string
     * @return MD5 hash of incoming string.
     */
    public static String getHash(String string) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(string.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException exc) {
            log.error(exc);
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }
}
