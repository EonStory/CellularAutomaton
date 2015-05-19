package nks;

public class Util {
	
	private Util() {		
	}

	public static int[] numberToRule(long ruleNumber, int cells, int colours) {
		System.out.println("rule number is " + ruleNumber);
		int[] rule = new int[(int) power(colours, cells)];
		for (int i = 0; i < rule.length; i++) {
			rule[rule.length - 1 - i] = (int) (ruleNumber % colours);
			ruleNumber = ruleNumber / colours;
		}
		for (int i = 0; i < rule.length; i++) {
			System.out.println("rule " + i + " is " + rule[i]);
		}
		return rule;
	}
	
	public static long power(int num1, int num2) {
		long ans = 1;
		for (int i = 0; i < num2; i++) {
			ans *= num1;
		}		
		return ans;
		
	}
}
