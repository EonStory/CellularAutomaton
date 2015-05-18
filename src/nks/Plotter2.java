package nks;
import java.awt.*;
import javax.swing.*;
//will model using ints instead of bools because future extentions to multiple colours
public class Plotter2 extends JFrame {	
	
	public Plotter2() {
		setLayout(new GridLayout(1, 0, 0, 0));
		//int[] startingRow, int colours, int ruleNumber, int height
		int[] start = {0,0,0,0,0,0,1,0,0,0};
		
		Graph hey = new Graph(20, 89, 10, 20, start);
		JScrollPane scrollPane = new JScrollPane(hey);	
		scrollPane.setPreferredSize(new Dimension(300, 300));
		add(scrollPane);
		
		Keys keys = new Keys(2, 89);
		//add(hey);
		add(keys);	
	}
	
	public static void main(String[] args) {
		Plotter2 frame = new Plotter2();
		frame.setTitle("Cellular Automata");
		frame.setSize(30 * 10, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);		
		
		int[] test = createRuleSet(2, 1);
		System.out.print("[");
		for (int a: test) {
			System.out.print(a +" ");
		}
		System.out.print("]");
		System.out.println();
		System.out.print(computeCell(test, 2, 1, 1, 1) + " ");
		System.out.print(computeCell(test, 2, 1, 1, 0) + " ");
		System.out.print(computeCell(test, 2, 1, 0, 1) + " ");
		System.out.print(computeCell(test, 2, 1, 0, 0) + " ");
		System.out.print(computeCell(test, 2, 0, 1, 1) + " ");
		System.out.print(computeCell(test, 2, 0, 1, 0) + " ");
		System.out.print(computeCell(test, 2, 0, 0, 1) + " ");
		System.out.print(computeCell(test, 2, 0, 0, 0) + " ");
		
		int[] testRow = {0, 0, 0, 1, 1, 0};
		int[] printMe = new int[6];
		computeNextRowLooping(testRow, test, 2);
		for (int a: printMe) {
			System.out.println("a is " + a );
		}
		
		
		
	}
	
	//Rule set for T shaped keys
	//0 0 0 0 0 0 0 0 = 0, 0 0 0 0 0 0 1 = 1 etc
	
	public static int[] createRuleSet(int colours, int ruleNumber) {
		int[] ruleSet = new int[colours * colours * colours];
		for (int i = 0; i < ruleSet.length; i++) {
			ruleSet[i] = ruleNumber / (int) Math.pow(colours, ruleSet.length - i - 1);
			ruleNumber -= ruleSet[i] * (int) Math.pow(colours, ruleSet.length - i - 1);
		}
		return ruleSet;
	}
	
	//colours is cuberoot of ruleset.length
	public static int computeCell(int[] ruleSet, int colours, int a, int b, int c) {
		return ruleSet[(int) a  + b * colours + c * colours * colours];
	}	
	
	//turns the a's to black with probability p. works only with 2 colours
	public void randomize(int[] a, double p) {
		for (int i = 0; i < a.length; i++) {
			if (Math.random() < p) {
				a[i] = 1;
			}
			else {
				a[i] = 0;
			}
		}
	}
	
	//assume area outside the array width is all white cells
	public static int[] computeNextRow(int[] a, int[] ruleSet, int colours) {		
		int[] nextRow = new int[a.length];		
		nextRow[0] = computeCell(ruleSet, colours, 0, a[0], a[1]);
		
		for (int i = 1; i < a.length - 1; i++) {
			nextRow[i] = computeCell(ruleSet, colours, a[i - 1], a[i], a[i + 1]);
		}
		nextRow[a.length - 1] = computeCell(ruleSet, colours, a[a.length - 2], a[a.length-1], 0);
		
		return nextRow;
	}
	
	//loops around the edges
	public static int[] computeNextRowLooping(int[] a, int ruleSet[], int colours) {
		int[] nextRow = new int[a.length];		
		nextRow[0] = computeCell(ruleSet, colours, a[a.length - 1], a[0], a[1]);
		
		for (int i = 1; i < a.length - 1; i++) {
			nextRow[i] = computeCell(ruleSet, colours, a[i - 1], a[i], a[i + 1]);;
		}
		
		nextRow[a.length - 1] = computeCell(ruleSet, colours, a[a.length - 2], a[a.length-1], a[0]);
		
		for (int i = 0; i < a.length; i++) {
			System.out.print("nextRow[" + i + "] is " + nextRow[i]);
		}
		
		return nextRow;
	}
	
	public boolean isReversible(int[] ruleSet) {
		return false;
	}
	
	//detect repetition. works in O(n^2) time complexity if not reversable, O(n) if reversable
	public int detectRepetition(int[] startingRow, int[] ruleSet) {
		return 4;
	}	
	
	
	//width is implied by startingRow.length
	public int[][] computeEntire(int[] startingRow, int colours, int ruleNumber, int height) {
		int[] ruleSet = new int[colours * colours * colours]; //cubed because key size is 3
		ruleSet = createRuleSet(colours, ruleNumber);		
		int[][] allRows = new int[height][startingRow.length];
		
		allRows[0] = startingRow.clone();		
		for (int i = 1; i < height; i++) {		
			allRows[i] = computeNextRowLooping(allRows[i - 1], ruleSet, colours);
		}
		/*
		for (int i = 0; i < allRows[0].length; i++) {
			System.out.println("allrows[0][" + i + "] is " + allRows[0][i]);
		}
		for (int i = 0; i < allRows[1].length; i++) {
			System.out.println("allrows[1][" + i + "] is " + allRows[1][i]);
		}
		*/
		
		return allRows;
	}
	
	class Graph extends JPanel {
		int cellSize = 10; //cell size in pixels (height & width)
		int colours;
		int ruleNumber;
		int width; //width in cells
		int height; //height in cells
		int[][] wholeThing; //all the cells to be drawn		
		
		public Graph(int colours, int ruleNumber, int width, int height, int[] initialRow) {
			System.out.println("constructed");
			this.colours = colours;
			this.ruleNumber = ruleNumber;
			this.width = width;
			this.height = height;
			this.setPreferredSize(new Dimension(width * cellSize, height * cellSize));
			//wholeThing = new int[height][width]; //maybe swap these
			wholeThing = computeEntire(initialRow, colours, ruleNumber, height);
			System.out.println("wholeThing: " + wholeThing[1][6]);
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (wholeThing[i][j] == 1) {
						g.setColor(Color.BLACK);
					}
					else {
						g.setColor(Color.WHITE);
					}
					g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
				}
			}
		}		
	}
	
	class Keys extends JPanel {
		int cellSize = 20;
		int[] ruleSet;
		int colours;
		public Keys(int colours, int ruleNumber) {
			this.colours = colours;
			this.ruleSet = createRuleSet(colours, ruleNumber);
		}
		//draw green squares surrounding the key for all keys
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, cellSize * 5, cellSize * 4 * colours * colours * colours);
			for (int i = 0; i < colours * colours * colours; i++) {			
				int temp = i;				
				for (int j = 0; j < 3; j++) {
					//System.out.println("math pow is " + (int) (Math.pow(colours, colours - i)));
					//System.out.println("as double is " + Math.pow(colours, colours - i));
					if (temp / ((int) (Math.pow(colours, 3 -1 - j))) == 0) {
						g.setColor(Color.WHITE);
					}
					else {
						g.setColor(Color.BLACK);
					}					
					temp -= (int) Math.pow(colours, 3 - 1 - j) * (temp / ((int) (Math.pow(colours, 3 -1 - j))));
					
					g.fillRect(cellSize + j * cellSize, cellSize + cellSize * 4 * i, cellSize, cellSize);
				}				
				
				if (ruleSet[i] == 1) {
					g.setColor(Color.WHITE);
				}
				else {
					g.setColor(Color.BLACK);
				}
				g.fillRect(cellSize * 2, cellSize * 2 + cellSize * 4 * i, cellSize, cellSize);				
			}
		}
		
		public Dimension getPreferredSize() {
			return new Dimension(cellSize * 5, colours * colours * colours * cellSize * 4);
		}
		
		public Dimension getMinimumSize() {
			return new Dimension(cellSize * 5, colours * colours * colours * cellSize * 4);
		}
		
	}
	
	
}
