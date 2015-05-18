package nks;

import java.util.ArrayList;

public class subsetsum {
	
	public static void main(String[] args) {
		int[] bob = {6, 9, 20, -3, -17, -44, 38};
		ArrayList<ArrayList<Integer>> q = findSolutions(bob);
		for (ArrayList<Integer> a: q) {
			System.out.println(a);
		}
	}
	
	public int[] createRandom(int size, int maxValue) {
		int[] numbers = new int[size];
		
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = ((int) (Math.random() * maxValue * 2)) - maxValue;
		}
		
		return numbers;
	}
	
	public static ArrayList<ArrayList<Integer>> findSolutions(int[] numbers) {
		ArrayList<ArrayList<Integer>> solutions = new ArrayList<ArrayList<Integer>>();
		boolean[] eval = new boolean[numbers.length];
		eval[0] = true; //cant have all as false ever
		
		for (long i = 1; i < LongMath.power(2, numbers.length); i++) {
			int sum = 0;
			for (int j = 0; j < eval.length; j++) {
				if (eval[j]) {
					sum += numbers[j];
				}
			}
			if (sum == 0) {
				ArrayList<Integer> sol = new ArrayList<Integer>();
				for (int j = 0; j < eval.length; j++) {
					if (eval[j]) {
						sol.add(numbers[j]);
					}
				}
				solutions.add(sol);
			}
			next(eval);
		}
		return solutions;
	}
	
	public static void next(boolean[] b) {
		for (int i = 0; i < b.length; i++) {
			b[i] = !b[i];
			if (b[i] == true) {
				break;
			}
		}
	}
}
