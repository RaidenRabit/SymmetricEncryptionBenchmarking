import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_CBC_256 implements Algorithm {

    static String plainText = "This is a plain text which need to be encrypted by AES Algorithm with CBC Mode";
    
	public Results execute(String text, String path) {

        KeyGenerator keyGenerator;
		Results results = new Results();
		try {
			
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(256);
	        // Generate Key
	        SecretKey key = keyGenerator.generateKey();
	        
	        // Generating IV.
	        byte[] IV = new byte[16];
	        SecureRandom random = new SecureRandom();
	        random.nextBytes(IV);
	        
	       //test encryption speed
			long startTime = System.nanoTime();   
			byte[] cipherText = AES_CBC_256.encrypt(text.getBytes(),key, IV); 
			results.encryptedTime = System.nanoTime() - startTime;
			String EncryptedString = Base64.getEncoder().encodeToString(cipherText);
			EncryptedString = null;
			text = null;
			
			//test decryption speed
			long startTime1 = System.nanoTime();   
			String DecryptedText = AES_CBC_256.decrypt(cipherText,key, IV);
			results.decryptedTime = System.nanoTime() - startTime;

			MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
			results.heapMemory = heapMemoryUsage.getUsed();
			
			return results;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
    
    public static byte[] encrypt (byte[] plaintext,SecretKey key,byte[] IV ) throws Exception
    {
        //Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        //Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        
        //Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        
        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        
        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);
        
        return cipherText;
    }
    
    public static String decrypt (byte[] cipherText, SecretKey key,byte[] IV) throws Exception
    {
        //Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        //Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        
        //Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        
        //Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        
        //Perform Decryption
        byte[] decryptedText = cipher.doFinal(cipherText);
        
        return new String(decryptedText);
    }

}
