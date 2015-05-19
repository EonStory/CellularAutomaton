package nks;

//keys with 3 inputs (nearest neighbors)
//all of this work is looping
public class KeyNSet {
	int colours;
	int numberOfKeys; //this is 
	int cells;
	int[] ruleSet;
	
	public KeyNSet(int cells, int colours, int[] ruleSet) {
		this.cells = cells;
		this.colours = colours;
		this.numberOfKeys = (int) Util.power(colours, cells);
		//construct rule set
		this.ruleSet = ruleSet;
	}
	
	public int computeCell(int[] input) {
		int num = 0;
		//System.out.println("total keys " + (int) Util.power(colours, input.length));
		for (int i = 0; i < input.length; i++) {
			//System.out.println("input i is " + input[i]);
			num += (int) Util.power(colours, input.length - i - 1) * input[i];
			//System.out.println(num + " is num" );
			//System.out.println("input.length - i - 1 is " + (input.length - i - 1));
		}		
		return ruleSet[num];
	}
	
	public String ruleSetString() {
		String s = "\nruleSet: ";
		for (int i = 0; i < ruleSet.length; i++) {
			s += " " + ruleSet[i];
		}
		return s;
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
