
public class Results {
long encryptedTime;
long decryptedTime;
long heapMemory;
String encryptedStringPath;
String decryptedStringPath;

	public String toString() {
		return "	EncryptedTime: " + Long.toString(encryptedTime) + " ns;\r\n" +
	"	DecryptedTime: " + Long.toString(decryptedTime) + " ns;\r\n" +
	"	HeapMemoryUsed: " + heapMemory + ";\r\n" +
	"	EncryptedFileOutputPath: " + encryptedStringPath + ";\r\n" + 
	"	DecryptedFileOutputPath: " + decryptedStringPath + ";\r\n";
	}
}
