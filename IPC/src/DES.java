import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class DES implements Algorithm {
public Results execute(String texts, String path) {
	Results results = new Results();
	try{

	    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
	    SecretKey myDesKey = keygenerator.generateKey();
	    
	    Cipher desCipher;

	    // Create the cipher 
	    desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
	    
	    // Initialize the cipher for encryption
	    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

	    //sensitive information
	    byte[] text = texts.getBytes();

	    // Encrypt the text
		long startTime = System.nanoTime();   
	    byte[] textEncrypted = desCipher.doFinal(text);
		results.encryptedTime = System.nanoTime() - startTime;
		String EncryptedString = Base64.getEncoder().encodeToString(textEncrypted);
		
	    // Initialize the same cipher for decryption
	    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

	    // Decrypt the text
		long startTime1 = System.nanoTime();   
	    byte[] textDecrypted = desCipher.doFinal(textEncrypted);
		results.decryptedTime = System.nanoTime() - startTime;

		MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		results.heapMemory = heapMemoryUsage.getUsed();
	    return results;
	}catch(NoSuchAlgorithmException e){
		e.printStackTrace();
		return null;
	}catch(NoSuchPaddingException e){
		e.printStackTrace();
		return null;
	}catch(InvalidKeyException e){
		e.printStackTrace();
		return null;
	}catch(IllegalBlockSizeException e){
		e.printStackTrace();
		return null;
	}catch(BadPaddingException e){
		e.printStackTrace();
		return null;
	}
}
}
