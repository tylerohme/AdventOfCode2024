package day02;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	private static enum Rate {INCREASING, DECREASING}
	private static ArrayList<ArrayList<Integer>> reports = new ArrayList<ArrayList<Integer>>();
	
	// Read file and populate data structure
	private static void readFile(String fileName) throws FileNotFoundException {
		File inFile = new File(fileName);
		Scanner fileReader = new Scanner(inFile);
		
		while (fileReader.hasNextLine()) {
			String line = fileReader.nextLine();
			String[] words = line.split(" ");
			
			ArrayList<Integer> report = new ArrayList<Integer>();
			for (String s : words) {
				int level = Integer.parseInt(s);
				report.add(level);
			}
			reports.add(report);
		}
		
		fileReader.close();
	}

	// Get count of safe reports for part 1
	private static int getSafeCount() {
		int result = 0;
		
		for (ArrayList<Integer> report : reports) {
			if (isValid(report)) result += 1;
		}
		
		return result;
	}
	
	// Check if increasing or decreaesing for part 1 and 2
	private static Rate getRate(ArrayList<Integer> report) {
		int x = report.get(0);
		int y = report.get(1);
		
		if (x > y) return Rate.DECREASING;
		if (x < y) return Rate.INCREASING;
		return null;
	}
	
	// Check if a report is valid for part 1 and 2
	private static boolean isValid(ArrayList<Integer> report) {
		Rate rate = getRate(report);
		
		int i = 1;
		while (i < report.size()) {
			int curVal = report.get(i-1);
			int nextVal = report.get(i);
			int difference = Math.abs(curVal - nextVal);
			
			// Check if rate is too fast or slow
			if (difference == 0 || difference > 3) return false;
			
			// Check if the pattern of increasing/decreasing is broken
			switch(rate) {
			case DECREASING:
				if (curVal < nextVal) return false;
				break;
			case INCREASING:
				if (curVal > nextVal) return false;
				break;
			}
			
			i += 1;
		}
		return true;
	}
	
	// Count valid reports with one number removed for part 2
	private static int getDampenedCount() {
		int result = 0;
		
		for (ArrayList<Integer> report : reports) {
			if (isValidDampened(report)) result += 1;
		}
		
		return result;
	}
	
	// Check if any reports are valid while missing one number for part 2
	private static boolean isValidDampened(ArrayList<Integer> report) {
		for (int i = 0; i < report.size(); i++) {
			ArrayList<Integer> myReport = new ArrayList<Integer>(report);
			myReport.remove(i);
			if (isValid(myReport)) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		readFile(args[0]);
		int safeCount = getSafeCount();
		int dampenedCount = getDampenedCount();
		
		System.out.println("Number of safe reports: " + safeCount);
		System.out.println("With safety dampener: " + dampenedCount);
	}

}
