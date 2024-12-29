package day1;
import java.util.*;
import java.io.*;

public class Main {
	private static ArrayList<Integer> list1 = new ArrayList<Integer>();
	private static ArrayList<Integer> list2 = new ArrayList<Integer>();
	
	private static void readFile(String fileName) throws FileNotFoundException {
		File inputFile = new File(fileName);
		Scanner fileReader = new Scanner(inputFile);
		
		while (fileReader.hasNextLine()) {
			String line = fileReader.nextLine();
			String[] words = line.split("   ");
			
			int num1 = Integer.parseInt(words[0]);
			int num2 = Integer.parseInt(words[1]);
			
			list1.add(num1);
			list2.add(num2);
			}
		
		fileReader.close();
		
		Collections.sort(list1);
		Collections.sort(list2);
	}
	
	private static int getDistance() {
		int result = 0;
		
		for (int i = 0; i < list1.size(); i++) {
			int num1 = list1.get(i);
			int num2 = list2.get(i);
			
			int difference = num1 - num2;
			result += Math.abs(difference);
		}
		
		return result;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		readFile(args[0]);
		int distance = getDistance();
		
		System.out.println("Total distance: " + distance);
	}
}
