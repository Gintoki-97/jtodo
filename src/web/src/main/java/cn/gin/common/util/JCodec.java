package cn.gin.common.util;

import cn.gin.common.Constants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Provides codec tools that may be used in several projects
 *
 * <pre>
 *     1. The hex/base64 encode of commons-codec
 *     2. Custom Base62 encoding and decoding
 *     3. The xml/html escape based on commons-lang
 *     4. JDK URL encoding and decoding
 * </pre>
 */
public class JCodec {

    /**
     * Base62 encode meta char array
     */
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".
            toCharArray();

    /**
     * Converts byte array to Base64 encoded string
     * 
     * @param data Byte array that need encode
     * @return Encoded string
     */
    public static String base64Encode(byte[] data) {
        
        return new String(Base64.encodeBase64(data));
    }

    /**
     * Converts Base64 encoded string to Base64 decoded string
     *
     * @param data String that need decode
     * @return Decoded string
     */
    public static String base64Encode(String data) {

        try {
            return new String(Base64.encodeBase64(data.getBytes(Constants.System.ENCODING)));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException(unsupportedEncodingException);
        }
    }

    /**
     * Converts Base64 encoded string to Base64 decoded byte array
     *
     * @param data String that need decode
     * @return Decoded byte array
     */
    public static byte[] base64Decode(String data) {

        return Base64.decodeBase64(data.getBytes());
    }

    /**
     * Converts byte array to Base62 encoded string
     *
     * @param input Byte array that need encode
     * @return Encoded string
     */
    public static String base62Encode(byte[] input) {

        char[] chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
        }

        return new String(chars);
    }

    /**
     * Converts URL string to URL encoded string
     *
     * @param data URL string that need URL encode
     * @return URL encoded string
     */
    public static String urlEncode(String data) {

        try {
            return URLEncoder.encode(data, Constants.System.ENCODING);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException(unsupportedEncodingException);
        }
    }

    /**
     * Converts URL encoded string to URL decoded string
     *
     * @param data URL string that need URL decode
     * @return URL decoded string
     */
    public static String urlDecode(String data) {

        try {
            return URLDecoder.decode(data, Constants.System.ENCODING);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException(unsupportedEncodingException);
        }
    }
}