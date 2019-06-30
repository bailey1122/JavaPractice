package security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class RSAExample {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String s = "This is example of RSACipher";

        Cipher cipher = Cipher.getInstance("RSA");

        KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = new SecureRandom();
        pairgen.initialize(512, random);
        KeyPair keyPair = pairgen.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] bytes = cipher.doFinal(s.getBytes());
        for (byte b : bytes) {
            System.out.print(b);
        }
        System.out.println("\n");

        Cipher descriptCipher = Cipher.getInstance("RSA");
        descriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrBytes = descriptCipher.doFinal(bytes);
        for (byte d : decrBytes) {
            System.out.print((char) d);
        }
    }
}
