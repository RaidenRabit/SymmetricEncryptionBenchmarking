import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

public class Main {
	public static void main(String[] args) {
		ArrayList<Results> results = ReadResults("Results.txt");
		WriteToFile(results, "ResultsCSV.csv");
	}
	
	public static void WriteToFile(ArrayList<Results> results, String fileName) {
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String path = jarDir.getAbsolutePath();
		fileName = path.concat("\\IPC\\Results\\" + fileName);
		File file = new File(fileName); 
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding header to csv 
	        String[] header = { "Device", "Algorithm", "FileSize", "EncryptionTime", "DecryptionTime",
	        		"HeapMemory"}; 
	        writer.writeNext(header); 
	        
	        for (Results i : results) {
				writer.writeNext(i.toStringArray());
			}
	  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
	}
	
	public static void CountOnAlg(ArrayList<Results> results) {
		int[] count = {0,0,0,0,0,0,0,0};
		
		for (Results i : results) {
			switch(i.alg) {
			case "AES":
				count[0]++;
				break;
			case "DES":
				count[1]++;
				break;
			case "3DES":
				count[2]++;
				break;
			case "Blowfish":
				count[3]++;
				break;
			case "Serpent":
				count[4]++;
				break;
			case "RC5":
				count[5]++;
				break;
			case "RC6":
				count[6]++;
				break;
			case "Twofish":
				count[7]++;
			}
		}
			for (int i : count) {
				System.out.println(i);
		}
	}
	
	public static void CountOnFiles(ArrayList<Results> results) {
		int[] count = {0,0,0,0,0,0,0,0,0,0,0,0};
		
		for (Results i : results) {
			 switch(i.fileName) {
		        case "32Bytes": //16 character long password, 32 bytes
				count[0]++;
		        	break;
		        case "1KB":
		        	count[1]++;
		        	break;
				case "10KB":
		        	count[2]++;
					break;
				case"100KB":
		        	count[3]++;
					break;
				case "1MB":
		        	count[4]++;
					break;
				case "10MB":
		        	count[5]++;
					break;
				case"100MB":
		        	count[6]++;
					break;
				case"150MB":
		        	count[7]++;
					break;
				case"200MB":
		        	count[8]++;
					break;
		        default:
		        	count[10]++;
		        	break;
			 }
		 }
		for (int i : count) {
			System.out.println(i);
		}
	}
	
	public static ArrayList<Results> ReadResults(String fileName) {
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String path = jarDir.getAbsolutePath();
		fileName = path.concat("\\IPC\\Results\\" + fileName);
        String text = "";
		String[] paths = {"32Bytes", "1KB", "10KB", "100KB", "1MB", "10MB", "100MB", "150MB", "200MB"};
		String[] algs = {"AES", "DES", "3DES", "Blowfish", "Twofish", "RC5", "RC6", "Serpent"};
		ArrayList<Results> results = new ArrayList<Results>();
		String currentFile = "";
		String currentAlg = "";
		String currentDevice = "";
		Results result = null;
		int generationIdex = 1;
		int arrayIndex = 1;
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			while (line != null) {
				for (String i : paths) {
					if(line.contains(i))
					{
						currentFile = i;
						generationIdex = 1;
					}
				}
				
				for(String i : algs) {
					if(line.contains(i))
					{
						currentAlg = i;
						generationIdex = 1;
					}
				}
				
				if(line.contains("Device"))
					currentDevice = line;
					
					if(line.contains("Gen"))
					{
						if (result != null)
						{
							results.add(result);
							arrayIndex++;
						}
						result = new Results();
						result.device = currentDevice;
						result.fileName = currentFile;
						result.alg = currentAlg;
						result.generation = generationIdex;
						generationIdex++;
					}
					
					if(line.contains("EncryptedTime"))
					{
						long encryptedTime = Long.parseLong(line.replaceAll("[^\\d]", "" ));
						result.encryptedTime = (float)encryptedTime/1000000;
					}
					
					if(line.contains("DecryptedTime"))
					{
						long decryptedTime = Long.parseLong(line.replaceAll("[^\\d]", "" ));
						result.decryptedTime = (float)decryptedTime/1000000;
					}
					
					if(line.contains("HeapMemoryUsed"))
					{
						long HeapMemoryUsed = Long.parseLong(line.replaceAll("[^\\d]", "" ));
						result.heapMemory = (float)HeapMemoryUsed/1048576;
					}
			

				line = reader.readLine();
			}
			reader.close();
			return results;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
