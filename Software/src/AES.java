import java.security.*;
import javax.crypto.*;
import sun.misc.*;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    
    private static final String ALGO = "AES";
    private static final byte[] keyValue = 
        new byte[] {'*','*','T', 'h', 'e','S','e','c','r','e','t','K', 'e', 'y','*','*'};

	public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
	}
	
	/*public static void main(String[] args) throws Exception {

        String password = "mypassword";
        String passwordEnc = AES.encrypt(password);
        String passwordDec = AES.decrypt(passwordEnc);

        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted Text : " + passwordEnc);
        System.out.println("Decrypted Text : " + passwordDec);
    }*/
}