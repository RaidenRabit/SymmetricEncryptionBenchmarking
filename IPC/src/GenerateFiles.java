import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GenerateFiles {
	public static void Generate() {
		GenerateDirectories();
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String path = jarDir.getAbsolutePath();
		path = path.concat("\\IPC\\OriginalFiles\\");
		
		WriteFiles(32, path + "32Bytes"); //16 character long password, 32 bytes
		System.out.println("32Bytes finished");
		
		WriteFiles(1024, path + "1KB"); //1KB
		System.out.println("1KB finished");
		
		WriteFiles(10240, path + "10KB"); //10 KB
		System.out.println("10KB finished");
		
		WriteFiles(102400, path + "100KB"); //100 KB
		System.out.println("100KB finished");
		
		WriteFiles(1048576, path + "1MB"); //1 MB
		System.out.println("1MB finished");
		
		WriteFiles(10485760, path + "10MB"); //10 MB
		System.out.println("10MB finished");
		
		WriteFiles(104857600, path + "100MB"); //100 MB
		System.out.println("100MB finished");

		System.out.println("150MB started:");
		for(int i=0; i<3;i++) {
			WriteFiles(52428800, path + "150MB"); //1 GB
			System.out.println("    "+(i+1)*50+"MB - done");
		}
		System.out.println("150MB finished");

		System.out.println("200MB started:");
		for(int i=0; i<4;i++) {
			WriteFiles(52428800, path + "200MB"); //1 GB
			System.out.println("    "+(i+1)*50+"MB - done");
		}
		System.out.println("200MB finished");
		System.out.println("DONE");
		
		   
	}
	
	private static void WriteFiles(int size, String path) {
		StringBuilder stringB = new StringBuilder(size);
		int i = 1;
		String paddingString = "abcdefghijklmnopqrs\n";
		while (stringB.length() + paddingString.length() < size) {
			stringB.append(i+" "+paddingString);
			i++;
		 }
		
		try{
				File file = new File(path);
				FileWriter fr = new FileWriter(file, true);
				BufferedWriter br = new BufferedWriter(fr);
				PrintWriter pr = new PrintWriter(br);
				pr.println(stringB.toString());
				pr.close();
				br.close();
				fr.close();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static String WriteResultsToFiles(String text, String specificPath) {
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String path = jarDir.getAbsolutePath();
		path = path.concat("\\IPC\\" + specificPath);
		
		try{
				File file = new File(path);
				FileWriter fr = new FileWriter(file, true);
				BufferedWriter br = new BufferedWriter(fr);
				PrintWriter pr = new PrintWriter(br);
				pr.println(text);
				pr.close();
				br.close();
				fr.close();
				return path;
		}catch(Exception e2) {
			e2.printStackTrace();
			return "";
		}
	}
	
	private static void GenerateDirectories() {
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String path = jarDir.getAbsolutePath();
		new File(path + "/IPC").mkdirs();
		new File(path + "/IPC/OriginalFiles").mkdirs();
		/*
		new File(path + "/IPC/EncryptedFiles").mkdirs();
		new File(path + "/IPC/EncryptedFiles/AES").mkdirs();
		new File(path + "/IPC/EncryptedFiles/DES").mkdirs();
		new File(path + "/IPC/EncryptedFiles/3DES").mkdirs();
		new File(path + "/IPC/EncryptedFiles/Blowfish").mkdirs();
		new File(path + "/IPC/EncryptedFiles/RC5").mkdirs();
		new File(path + "/IPC/EncryptedFiles/RC6").mkdirs();
		new File(path + "/IPC/EncryptedFiles/Serpent").mkdirs();
		new File(path + "/IPC/EncryptedFiles/Twofish").mkdirs();
		new File(path + "/IPC/DecryptedFiles").mkdirs();
		new File(path + "/IPC/DecryptedFiles/AES").mkdirs();
		new File(path + "/IPC/DecryptedFiles/DES").mkdirs();
		new File(path + "/IPC/DecryptedFiles/3DES").mkdirs();
		new File(path + "/IPC/DecryptedFiles/Blowfish").mkdirs();
		new File(path + "/IPC/DecryptedFiles/RC5").mkdirs();
		new File(path + "/IPC/DecryptedFiles/RC6").mkdirs();
		new File(path + "/IPC/DecryptedFiles/Serpent").mkdirs();
		new File(path + "/IPC/DecryptedFiles/Twofish").mkdirs(); 
		*/
		new File(path + "/IPC/Results").mkdirs();
	}
}
