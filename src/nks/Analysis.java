package nks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Analysis {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		//long[] cands = createCandidates2();
		//boolean[] hasPairedgreens = new boolean[cands.length];
		//int[][] repeat = new int[cands.length][2];
		/*
		int[] ba = {3, -4, 1, 2, -3};
		int[] sta = numbersAsInput(ba);
		Key3Set k = new Key3Set(3, 7609907732661l);
		int[][] image = Reform.computeEntire(sta, k, 1000000);
		System.out.println(firstReversibleRepeat(image));
		System.out.println("other way " + firstReversibleRepeat(k, sta, 1000000000));
		
		int[] ab = numbersAsInput(ba);
		for (int i = 0; i < cands.length/50; i++) {
			Key3Set r = new Key3Set(3, cands[i]);			
			int[] bleh = firstRepeat(Reform.computeEntire(ab, r, 4000));
			repeat[0][0] = bleh[0];
			repeat[i][1] = bleh[1] - bleh[0];
		}		
		
		for (int i = 0; i < repeat.length; i++) {
			if (repeat[i][1] == -5) {
				System.out.println("hey, rule " + cands[i] + " has a repeat length of " + repeat[i][1]);
				System.out.println("wut");
			}
		}
		
		//for (int i = 0; i < ab.length; i++) {
		//	System.out.print(ab[i] + " ");
		//}
		
		//int[] start = Analysis.numbersAsInput(new int[] {29, -5, -17, -3, 7});
		//Key3Set rule = new Key3Set(3, 7377827710293l);
		//int[][] image = Reform.computeEntire(start, rule, 100);
		//System.out.println("first repeat is ..... (drumroll please) ... " + firstRepeat(image)[0] + " and " +  firstRepeat(image)[1] );
		
		/*
		int[] start = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1};
		//
	
		int[][] baba =
		
		
		for (long i = 0; i < 7625597484987l; i++) {
			Key3Set k = new Key3Set(3, i);
			if (isSymmetricalAboutGreen(k)) {
				System.out.println(i);
			}
			if (i % 1000000 == 0) {
				System.out.println("i is " + i);
			}
		}
		*/
		//start[5] = 1;
		//Key3Set rule30 = new Key3Set(3, 3097483878567l);
		//Key3Set rule30 = new Key3Set(3, 1123289366095l);
		//Key3Set rule30 = new Key3Set(3, 277206003607l);
		//Key3Set rule30 = new Key3Set(2, 30);
		//Key3Set rule = new Key3Set(3, 15689752325l);
		//System.out.println(iterationsUntilRepeat(start, rule));
		
		
		ArrayList<Key3Set> keke = new ArrayList<Key3Set>();
		Scanner input = new Scanner(new File("reversibles.txt"));
		input.useDelimiter(", \\n|, ");
		int q = 0;
		while (input.hasNext()) {
			q++;
			String beb = input.nextLine();
			if (beb.length() > 3) {
				String bob = beb;
				bob.trim();
				String[] ans = bob.split(", ");
				for (int i = 0; i < ans.length; i++) {
					long fred = Long.parseLong(ans[i].trim());
					keke.add(new Key3Set(3, fred));
				}
			}
			//System.out.println(input.nextInt());
			//if (count % 10 == 0) {
			//	System.out.println("");
			//}
		}
		System.out.println("Done, now for the rulese which are good: ");
		for (Key3Set k: keke) {
			if (isSymmetricalAboutGreen(k)) {
				System.out.println(k.ruleNumber);
			}
		}
		
	}
	
	/* returns true if when flipping the red to blue and blue to red bits in the top 3 parts, the bottom part is also flipped
	 * 
	 */
	//0 = red, 1 = green, 2 = blue
	
	
	public static boolean isSymmetricalAboutGreen(Key3Set key) {
		for (int i = 0; i < key.ruleSet.length / 2; i++) {
			//System.out.println("key.ruleSet.length - i  is " + (key.ruleSet.length - i));
			if (key.ruleSet[key.ruleSet.length - 1- i] != 2 - key.ruleSet[i]) {
				return false;
			}
		}
		/*
		System.out.println("true for: " + key.ruleNumber);
		for (int i = 0; i < 13; i++) {
			System.out.print(key.ruleSet[i] + " ");
		}
		System.out.println("----");
		for (int i = 26; i > 13; i--) {
			System.out.print(key.ruleSet[i] + " ");
		}
		System.out.println("****");
		*/
		return true;		
	}
	
	//returns the initial row for 3 colour CA
	public static int[] numbersAsInput(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += Math.abs(nums[i]);
		}
		int ans[] = new int[sum + nums.length];
		int index = 0; //index of bit that neeeds updating
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < Math.abs(nums[i]); j++) {
				if (nums[i] > 0) {
					ans[index] = 0; //red = 0 = positive
				}
				else {
					ans[index] = 2;
				}
				index++;
			}
			ans[index] = 1;
			index++;
		}
		return ans;
	}
	
	//O(n)
	public static int firstReversibleRepeat(int[][] image) {
		for (int i = 1; i < image.length; i++) {
			boolean repeat = false;
			for (int j = 0; j < image[0].length; j++) {
				if (image[0][j] != image[i][j]) {
					repeat = true;
					break;
				}
			}
			if (repeat == false) {
				return i;
			}
		}
		return -1;
	}
	
	//O(n)
	public static long firstReversibleRepeat(Key3Set key, int[] initialRow, long limit) {
		long iterations = 0;
		int[] row = initialRow.clone();
		
		while (true) {
			boolean repeat = false;
			row = Reform.computeNextRow(row, key);
			for (int i = 0; i < initialRow.length; i++) {
				if (row[i] != initialRow[i]) {
					repeat = true;
					break;
				}
			}			
			iterations++;
			if (repeat == false) {
				break;
			}
			if (iterations == limit) {
				return -1;
			}
		}
		return iterations;
	}
	
	//O(rows^2)	
	public static int[] firstRepeat(int[][] image) {
		int[] pair = new int[2];//the two row numbers that repeat eachother
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < i; j++) {
				boolean repeat = true;
				for (int k = 0; k < image[0].length; k++) { //all images same length
					if (image[j][k] != image[i][k]) {
						repeat = false;
						break;
					}
				}
				if (repeat == true) {
					pair[0] = j;
					pair[1] = i;
					return pair;
				}
			}
		}
		pair[0] = -3;
		pair[1] = -8;
		return pair;
	}
	
	public static int oscillationSize(int[][] image, int firstRepetition) {
		for (int i = firstRepetition; i >= 0; i--) {
			boolean repeat = true;
			for (int j = 0; j < image[0].length; j++) {
				if (image[i][j] != image[firstRepetition][j]) {
					repeat = false;
					break;
				}
			}
			if (repeat == true) {
				return firstRepetition - i - 1;
			}
		}
		return -1;
	}
	
	/*
	public static long iterationsUntilRepeat(int[] initialRow, Key3Set key) {
		long bob = 0;
		int[] copy = initialRow.clone();
		while (true) {
			copy = Reform.computeNextRow(copy, key);
			bob++;
			boolean repeat = false;
			for (int i = 0; i < copy.length; i++) {
				if (copy[i] != initialRow[i]) {
					repeat = true;
					break;
				}
			}
			if (repeat == false) {
				break;
			}
			if (bob % 500000 == 0) {
				System.out.println("bob is " + bob);
			}
		}		
		return bob;
	}
	*/
	public static long ruleNumber(int colours, int[] rule) {
		long ruleNumber = 0;
		for (int i = 0; i < rule.length; i++) {
			ruleNumber += LongMath.power(colours, i) * rule[rule.length - 1 - i];
		}
		return ruleNumber;
	}
	
	//create cellular automata which are symmetrical about the center
	public static long[] createCandidates() {
		long[] candidates = new long[1594323]; //3 ^ 13 due to symmetry about cente of 27 keys
		int[] set = new int[13];
		int[] fullset = new int[27];
		for (int i = 0; i < 1594323; i++) { //minused 1 from it and it worked, hahahaha
			for (int j = 0; j < 13; j++) {
				fullset[j] = 2 - set[set.length - 1 - j]; //2 - beecause flipping it
			}
			fullset[13] = 1;
			for (int j = 14; j < 27; j++) {
				fullset[j] = set[j - 14];
			}
			candidates[i] = ruleNumber(3, fullset);
			next(set, 3);
		}
		return candidates;
	}
	
	//same as createcandidate except keys with two green squares next to eachother must output green
	public static long[] createCandidates2() {
		long[] candidates = new long[177147]; //3 ^ 11 due to symmetry about cente of 27 keys and excluding 2 which have2 greens next
		int[] set = new int[11];
		int[] fullset = new int[27];
		for (int i = 0; i < 177147; i++) { //minused 1 from it and it worked, hahahaha
			for (int j = 0; j < 4; j++) {
				fullset[j] = 2 - set[set.length - 1 - j]; //2 - beecause flipping it
			}
			fullset[4] = 1;
			for (int j = 5; j < 12; j++) {
				fullset[j] = 2 - set[set.length - 1 - j + 1]; //2 - beecause flipping it. plus naother 1 so it lines up with the skipped value [4]
			}
			fullset[12] = 1;
			fullset[13] = 1;
			fullset[14] = 1;
			for (int j = 15; j < 22; j++) {
				fullset[j] = set[j - 14];
			}
			fullset[22] = 1;
			
			for (int j = 23; j < 27; j++) {
				fullset[j] = set[j - 14 - 2]; //minus 2 to align again
			}
			candidates[i] = ruleNumber(3, fullset);
			next(set, 3);
		}
		return candidates;
	}
	
	
	public static void next(int[] rule, int colors) { //rule is input as cut in half
		nextHelper(rule, colors, rule.length - 1);
	}
	
	public static void nextHelper(int[] rule, int colors, int index) { //rule is input as cut in half
		if (rule[index] == colors - 1) {
			rule[index] = 0;
			if (index - 1 < 0) {
				return;
			}
			nextHelper(rule, colors, index - 1);
		}
		else {
			rule[index]++;
		}
	}
}
