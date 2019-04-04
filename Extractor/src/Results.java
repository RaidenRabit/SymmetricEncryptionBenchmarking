
public class Results {
float encryptedTime;
float decryptedTime;
float heapMemory;
String fileName;
int generation;
String alg;
String device;

	public String[] toStringArray() {
		String[] a = {device, alg, fileName, Float.toString(encryptedTime),
				Float.toString(decryptedTime), Float.toString(heapMemory)};
		return a;
	}
}
