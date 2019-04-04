import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Serpent implements Algorithm{
	public Results execute(String text, String path) {
		Results results = new Results();
		try {
			BouncyCastleProvider a = new BouncyCastleProvider();
		      KeyGenerator kg = KeyGenerator.getInstance("Serpent", a);
		      kg.init(256);
		      SecretKey sk = kg.generateKey();

		      long startTime = System.nanoTime();   
		      byte[] encrypted = encrypt(text, sk, a);
			results.encryptedTime = System.nanoTime() - startTime;
			String EncryptedString = Base64.getEncoder().encodeToString(encrypted);
	
			long startTime1 = System.nanoTime();   
		      String decrypted = decrypt(encrypted, sk, a);
				results.decryptedTime = System.nanoTime() - startTime;
	
				MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
				results.heapMemory = heapMemoryUsage.getUsed();
				
		      return results;}
		catch(Exception e) 
		{return null;}
	}
	
	public static byte[] encrypt(String toEncrypt, SecretKey key, BouncyCastleProvider a) throws Exception {
	      // create a binary key from the argument key (seed)

	  
	      // create an instance of cipher
	      Cipher cipher = Cipher.getInstance("Serpent", a);
	  
	      // initialize the cipher with the key
	      cipher.init(Cipher.ENCRYPT_MODE, key);
	  
	      // enctypt!
	      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
	  
	      return encrypted;
	   }
	  
	   public static String decrypt(byte[] toDecrypt, SecretKey key, BouncyCastleProvider a) throws Exception {
	      // create a binary key from the argument key (seed)
	  
	      // do the decryption with that key
	      Cipher cipher = Cipher.getInstance("Serpent", a);
	      cipher.init(Cipher.DECRYPT_MODE, key);
	      byte[] decrypted = cipher.doFinal(toDecrypt);
	  
	      return new String(decrypted);
	   }
}
