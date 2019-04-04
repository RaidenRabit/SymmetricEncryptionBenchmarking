import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class main {
	public static void main(String[] args) {
		System.out.println("NOTE: all encryption tests require for the resource files to be generated firstly\n"+
	"1 - GenerateFiles at JAR location (~1.2Gb)\n"+
	"2 - AES\n"+
	"3 - DES\n"+
	"4 - 3DES\n"+
	"5 - Blowfish\n"+
	"6 - RC5\n"+
	"7 - RC6\n"+
	"8 - Serpent\n"+
	"9 - Twofish\n"+
	"0 - All of the above in order\n"+
	"-1 - Only selected few (use -1 as an ending of selections\n"+
	"-2 - Only algorithms\n");
		
		int i = SelectAction();
		
		switch(i)
		{
		case 1:
			GenerateFiles.Generate();
			break;
		case 0:
			GenerateFiles.Generate();
			TestingBench(2);
			TestingBench(3);
			TestingBench(4);
			TestingBench(5);
			TestingBench(6);
			TestingBench(7);
			TestingBench(8);
			TestingBench(9);
			break;
		case -1:
			int[] a = {};
			int k = 0;
			i = SelectAction();
			while(i != -1) {
				a[k] = i;
				k++;
				}
			for (int j : a) {
				TestingBench(j);				
			}
			break;
		case -2:
			TestingBench(2);
			TestingBench(3);
			TestingBench(4);
			TestingBench(5);
			TestingBench(6);
			TestingBench(7);
			TestingBench(8);
			TestingBench(9);
			break;
		default:
			TestingBench(i);
			break;
		}            
		
	}
	
	private static void TestingBench(int i) {
		String mainPath = "\\Results\\Results.txt";
		switch(i) {
		case 2:
			System.out.println("AES_CBC_256\r\n");
			GenerateFiles.WriteResultsToFiles("AES_CBC_256\r\n", mainPath);
			Compute(new AES_CBC_256());
			System.out.println("AES_CBC_256" + " - DONE!!!");
			break;
		case 3:
			System.out.println("DES\r\n");
			GenerateFiles.WriteResultsToFiles("DES'\r\n", mainPath);
			Compute(new DES());
			System.out.println("DES" + " - DONE!!!");
			break;
		case 4:
			System.out.println("3DES\r\n");
			GenerateFiles.WriteResultsToFiles("3DES'\r\n", mainPath);
			Compute(new TrippleDES());
			System.out.println("3DES" + " - DONE!!!");
			break;
		case 5:
			System.out.println("Blowfish\r\n");
			GenerateFiles.WriteResultsToFiles("Blowfish'\r\n", mainPath);
			Compute(new Blowfish());
			System.out.println("Blowfish" + " - DONE!!!");
			break;
		case 6:
			System.out.println("RC5\r\n");
			GenerateFiles.WriteResultsToFiles("RC5'\r\n", mainPath);
			Compute(new RC5());
			System.out.println("RC5" + " - DONE!!!");			
			break;
		case 7:
			System.out.println("RC6\r\n");
			GenerateFiles.WriteResultsToFiles("RC6'\r\n", mainPath);
			Compute(new RC6());
			System.out.println("RC6" + " - DONE!!!");			
			break; 
		case 8:
			System.out.println("Serpent\r\n");
			GenerateFiles.WriteResultsToFiles("Serpent'\r\n", mainPath);
			Compute(new Serpent());
			System.out.println("Serpent" + " - DONE!!!");			
			break;
		case 9:
			System.out.println("Twofish\r\n");
			GenerateFiles.WriteResultsToFiles("Twofish'\r\n", mainPath);
			Compute(new Twofish());
			System.out.println("Twofish" + " - DONE!!!");			
			break;
		}
	}
	
	private static void Compute(Algorithm alg) {
		String[] paths = {"32Bytes", "1KB", "10KB", "100KB", "1MB", "10MB", "100MB", "150MB", "200MB"};
		//String[] paths = {"150MB", "200MB"};
		//String[] paths = {"32Bytes", "1KB", "10KB", "100KB", "1MB", "10MB", "100MB"};
		String mainPath = "\\Results\\Results.txt";
		String avgsPath = "\\Results\\AvgResults.txt";
		for (String file : paths) {
			String text = ReadFile(file);
			GenerateFiles.WriteResultsToFiles(file, mainPath);
			System.out.print(file + " : \r\n");
			long eTime = 0;
			long dTime = 0;
			long hMem = 0;
			for(int i1 = 0; i1 < 100; i1++) {
				Results result = alg.execute(text, file);
				eTime += result.encryptedTime;
				dTime += result.decryptedTime;
				hMem += result.heapMemory;
				String a = "	Gen " + (i1+1) + ":\r\n" + result.toString();
				GenerateFiles.WriteResultsToFiles(a, mainPath);
				progressPercentage(i1+1, 100, 2);
			}
			long avg = hMem/100;
			GenerateFiles.WriteResultsToFiles("\r\n" + 
			file + " : " + alg.getClass().toString() + "\r\n" +
			"	Total encryptionTime: " + eTime + "ns\r\n" +
			"	Total decryptionTime: " + dTime + "ns\r\n" +
			"	Total memory used: " + hMem + "B\r\n" +
			"	AVG encryptionTime: " + eTime/100 + "ns\r\n" +
			"	AVG decryptionTime: " + dTime/100 + "ns\r\n" +
			"	AVG Memory: " + avg + "B\r\n", avgsPath);
			GenerateFiles.WriteResultsToFiles("\r\n\r\n\r\n", mainPath);
			System.out.println(avg + "B (" + avg/1048576 + " MB)");
			
		}
	}
	
	private static int SelectAction() {
		System.out.println("Please enter a number:");
        System.out.println("Please enter one of the numbers:-2, -1 , 0, 1, 2, 3, 4, 5, 6, 7, 8, 9;\n");
		Scanner scanner = new Scanner(System.in);
	    String i = scanner.nextLine();
        try {
    	    int a = Integer.parseInt(i);	
		    if((a >= -2) && (a <= 9))
			    {
			    	scanner.close();
				    return a;
			    }
		    return SelectAction();
		}catch(Exception e) {
			return SelectAction();
		}
	}
	
	private static String ReadFile(String fileName) {

		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String path = jarDir.getAbsolutePath();
        // This will reference one line at a time
        String line = null;
        String text = "";
        System.out.println("Reading " + fileName);
        int total = 0;
        switch(fileName) {
        case "32Bytes": //16 character long password, 32 bytes
		total = 32;
        	break;
        case "1KB":
        	total = 1024;
        	break;
		case "10KB":
			total = 10240;
			break;
		case"100KB":
			total = 102400;
			break;
		case "1MB":
			total = 1048576;
			break;
		case "10MB":
			total = 10485760;
			break;
		case"100MB":
			total = 104857600;
			break;
		case"150MB":
			total = 157286400;
			break;
		case"200MB":
			total = 209715200;
			break;
        }
		fileName = path.concat("\\IPC\\OriginalFiles\\" + fileName);
        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8); 
            return text;
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");           
            return "";
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");
            return "";
        }
	}
	
	public static void progressPercentage(int remain, int total, int reading) {
	    if (remain > total) {
	        throw new IllegalArgumentException();
	    }
	    int maxBareSize = 10; // 10unit for 100%
	    int remainProcent = ((100 * remain) / total) / maxBareSize;
	    char defaultChar = '-';
	    String icon = "*";
	    String bare = new String(new char[maxBareSize]).replace('\0', defaultChar) + "]";
	    StringBuilder bareDone = new StringBuilder();
	    bareDone.append("[");
	    for (int i = 0; i < remainProcent; i++) {
	        bareDone.append(icon);
	    }
	    String bareRemain = bare.substring(remainProcent, bare.length());
	    String optional = "";
	    switch (reading) {
	    case 1:
	    	optional = (float)remain/1048576 + "MB" + " : " + (float)total/1048576 + "MB";
	    	break;
	    case 2:
	    	optional = remain + " : " + total;
	    	break;
	    }
	    System.out.print("\r                                                                  ");
	    System.out.print("\r" + bareDone + bareRemain + " " + ((100 * (float)remain) / total) + "%  " + optional);
	    if (remain == total) {
	        System.out.print("\n");
	    }
	}
}
