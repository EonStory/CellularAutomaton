package nks;

public class LongMath {
	private LongMath() {
		
	}
	
	public static long power(int num1, int num2) {
		long ans = 1;
		for (int i = 0; i < num2; i++) {
			ans *= num1;
		}		
		return ans;
		
	}
}
