package day01;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
	
public class Main {
	private static ArrayList<Integer> list1 = new ArrayList<Integer>();
	private static ArrayList<Integer> list2 = new ArrayList<Integer>();
	private static HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
	
	// Read file and populate data structures
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
			addCount(num2); // Record count for Part 2
			}
		
		fileReader.close();
		
		Collections.sort(list1);
		Collections.sort(list2);
	}
	
	// Get total distance between list elements for part 1
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
	
	// Helper function to set up data structure for part 2
	private static void addCount(int n) {
		counts.putIfAbsent(n, 0);
		
		int curCount = counts.get(n);
		counts.put(n, (curCount+1));
	}
	
	// Get similarity score for part 2
	private static int getSimilarity() {
		int result = 0;
		
		for (int i : list1) {
			if (!counts.containsKey(i)) continue;
			
			int curCount = counts.get(i);
			int curValue = curCount * i;
			result += curValue;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		readFile(args[0]);
		int distance = getDistance();
		int similarity = getSimilarity();
		
		System.out.println("Total distance: " + distance);
		System.out.println("Similarity score: " + similarity);
	}
}
