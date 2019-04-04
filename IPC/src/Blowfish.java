import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Blowfish implements Algorithm{
	public Results execute(String text, String path) {
		Results results = new Results();
		try {
        // create a key generator based upon the Blowfish cipher
	  KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");
	
	  // create a key
	  SecretKey secretkey = keygenerator.generateKey();
	
	  // create a cipher based upon Blowfish
	  Cipher cipher = Cipher.getInstance("Blowfish");
	
	  // initialise cipher to with secret key
	  cipher.init(Cipher.ENCRYPT_MODE, secretkey);
	
	  // get the text to encrypt
	
	  // encrypt message
		long startTime = System.nanoTime();   
	    byte[] encrypted = Encrypt(text, secretkey);
		results.encryptedTime = System.nanoTime() - startTime;
		String EncryptedString = Base64.getEncoder().encodeToString(encrypted);
	  
	  // decrypt message
		long startTime1 = System.nanoTime();   
	    String decrypted = Decrypt(encrypted, secretkey);
		results.decryptedTime = System.nanoTime() - startTime;
		

		MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		results.heapMemory = heapMemoryUsage.getUsed();
		return results;
		}catch(Exception e) {return null;}
	}
	
	private byte[] Encrypt(String text, SecretKey key) {
		try {
		Cipher cipher = Cipher.getInstance("Blowfish");
        if ( cipher == null || key == null) {
            throw new Exception("Invalid key or cypher");
        }
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text.getBytes("UTF-8"));
		}catch(Exception e) {return null;}
	}
	
	private String Decrypt(byte[] encryptedData, SecretKey key) {
		try {
		Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encryptedData);
        return new String(decrypted);}catch(Exception e) {return "";}
	}

}
