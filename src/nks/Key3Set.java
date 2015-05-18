package nks;

//keys with 3 inputs (nearest neighbors)
//all of this work is looping
public class Key3Set {
	int colours;
	int numberOfKeys; //this is 
	long ruleNumber;
	int[] ruleSet;
	
	public Key3Set(int colours, long ruleNumber) {
		this.colours = colours;
		this.ruleNumber = ruleNumber;
		this.numberOfKeys = colours * colours * colours;
		//construct rule set
		this.ruleSet = new int[numberOfKeys];		
		for (int i = 0; i < ruleSet.length; i++) {
			ruleSet[i] = (int) (ruleNumber / LongMath.power(colours, ruleSet.length - i - 1));
			ruleNumber -= ruleSet[i] * LongMath.power(colours, ruleSet.length - i - 1);
		}
	}
	
	//this can be optimised better
	public int computeCell(int a, int b, int c) {
		return ruleSet[a * colours * colours + b * colours + c];
		
	}
	
	public boolean isReversible() {
		
		return false;
	}
	
	public String ruleSetString() {
		String s = "\nruleSet: ";
		for (int i = 0; i < ruleSet.length; i++) {
			s += " " + ruleSet[i];
		}
		return s;
	}
	
	public long getRuleNumber() {
		return ruleNumber;
	}

	//delete this method later
	public int[] getRuleSet() {
		return ruleSet;
	}

	public int getColours() {
		return colours;
	}

	public void printRuleSet() {
		
	}
	
	
}
