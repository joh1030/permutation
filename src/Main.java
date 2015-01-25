import java.util.List;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// Permutation algorithm found @ http://stackoverflow.com/questions/25704754/get-arraylist-of-all-possible-permutations-of-an-arraylist

public class Main {

	private ArrayList<Point2D> points;
	List<List<Point2D>> accum;
	List<Point2D> shortestSet;

	public Main(String file) throws FileNotFoundException {
		loadFile(file);
	}

	public void loadFile(String file) throws FileNotFoundException {
		points = new ArrayList<Point2D>();
		FileReader reader = new FileReader(file);
		Scanner scan = new Scanner(reader);
		int size, x, y;
		size = Integer.parseInt(scan.next());
		while( scan.hasNext() ) {
			if( scan.hasNextLine() ) {
				x = Integer.parseInt(scan.next());
				y = Integer.parseInt(scan.next());
				points.add(new Point(x,y));
			}
		}
		scan.close();
	}

	public void permutation(List<Point2D> nums) {
		accum = new ArrayList<List<Point2D>>();
		permutation(accum, Arrays.<Point2D>asList(), nums);
	}

	private void permutation(List<List<Point2D>> accum, List<Point2D> prefix, List<Point2D> nums) {
		//int n = nums.size();
		if (nums.size() == 0) {
			accum.add(prefix);
		} else {
			for (int i = 0; i < nums.size(); ++i) {
				List<Point2D> newPrefix = new ArrayList<Point2D>();
				newPrefix.addAll(prefix);
				newPrefix.add(nums.get(i));
				List<Point2D> numsLeft = new ArrayList<Point2D>();
				numsLeft.addAll(nums);
				numsLeft.remove(i);
				permutation(accum, newPrefix, numsLeft);
			}
		}
	}

	private double totalDistance(List<Point2D> points) {
		double totalD = 0;
		for (int i = 0; i < points.size()-1; i++ ) {
			double tempD = Point2D.distance(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY());
			totalD += tempD;
		}
		return totalD;
	}

	private void findShortestPath(List<List<Point2D>> accum) {
		double tempD = 0;
		List<Point2D> tempSet = null;
		for (List<Point2D> points: accum) {
			if (tempD == 0 || totalDistance(points) < tempD) {
				tempD = totalDistance(points);
				tempSet = points;
			}
		}
		shortestSet = tempSet;
	}
	
	// Coordinates are randomly generated between -20 and 20.
	public static void generateInputs() throws FileNotFoundException, UnsupportedEncodingException {
		Random random = new Random();
		// n = 9
		PrintWriter writer = new PrintWriter("input1.txt", "UTF-8");
		int n = 9;
		writer.println(n);
		for (int i = 0; i < n; i++) {
			writer.print(random.nextInt(41)+(-20) + " ");
			writer.print(random.nextInt(41)+(-20));
			writer.println();
		}
		writer.close();
		// n = 10
		writer = new PrintWriter("input2.txt", "UTF-8");
		n = 10;
		writer.println(n);
		for (int i = 0; i < n; i++) {
			writer.print(random.nextInt(41)+(-20) + " ");
			writer.print(random.nextInt(41)+(-20));
			writer.println();
		}
		writer.close();
		// n = 11
		writer = new PrintWriter("input3.txt", "UTF-8");
		n = 11;
		writer.println(n);
		for (int i = 0; i < n; i++) {
			writer.print(random.nextInt(41)+(-20) + " ");
			writer.print(random.nextInt(41)+(-20));
			writer.println();
		}
		writer.close();
		// n = 12
		writer = new PrintWriter("input4.txt", "UTF-8");
		n = 12;
		writer.println(n);
		for (int i = 0; i < n; i++) {
			writer.print(random.nextInt(41)+(-20) + " ");
			writer.print(random.nextInt(41)+(-20));
			writer.println();
		}
		writer.close();
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Main.generateInputs();
		Main main = new Main("input1.txt");
		long startTime = System.currentTimeMillis(); // start_time
		main.permutation(main.points);
		main.findShortestPath(main.accum);
		long duration = System.currentTimeMillis() - startTime; // runtime = start_time - current_time
		System.out.println("Runtime: " + duration + " milliseconds");
		System.out.println("Shortest Path: " + main.shortestSet); 
		System.out.println("Total Distance: " + main.totalDistance(main.shortestSet));
	}
}
