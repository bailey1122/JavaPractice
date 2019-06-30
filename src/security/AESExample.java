package security;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.login.LoginException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESExample {
    public static void main(String[] args) throws LoginException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String s = "This is example of AESCipher";

        Cipher cipher = Cipher.getInstance("AES");

//        KeyGenerator kgen = KeyGenerator.getInstance("AES"); //you have to use if you want to generate different values
//        kgen.init(128);
//        SecretKey key = kgen.generateKey();

        SecretKeySpec key = new SecretKeySpec("Bar12345Bar12345".getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] bytes = cipher.doFinal(s.getBytes());
        for (byte b : bytes) {
            System.out.print(b);
        }

        System.out.println("\n");

        Cipher descriptCipher = Cipher.getInstance("AES");

        descriptCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] descriptedBytes = descriptCipher.doFinal(bytes);
        for (byte b : descriptedBytes) {
            System.out.print((char) b);
        }

    }
}
