package securityex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1AndMD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "This is example of SHA-1 and MD5";
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
//        MessageDigest md5 = MessageDigest.getInstance("MD5"); //if you want to use MD5 Cipher
        byte[] bytes = sha1.digest(str.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02X ", b));
        }
        System.out.println(builder.toString());
    }
}
