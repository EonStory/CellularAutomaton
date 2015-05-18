package nks;
import java.awt.*;
import javax.swing.*;
//will model using ints instead of bools because future extentions to multiple colours
public class Plotter extends JFrame {	
	public Plotter() {
		//setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//setLayout(new GridLayout(1, 2, 0, 0));
		//setLayout(new BorderLayout());
		//int[] startingRow, int colours, int ruleNumber, int height
		//Graph hey = new Graph();
		//JScrollPane scrollPane = new JScrollPane(hey);
		//JScrollPane scrollPane = new JScrollPane(hey, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,          JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//JScrollPane testscroll = new JScrollPane(hey,   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,          JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		//scrollPane.setPreferredSize(new Dimension(300, 300));
		//test.setLayout(new FlowLayout(FlowLayout.LEFT, 0 ,0));
		//add(scrollPane);
		
	}
	
	public static void main(String[] args) {
		Plotter frame = new Plotter();
		frame.setTitle("Cellular Automata");
		frame.setSize(30 * 10, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);		
		
		int[] test = createRuleSet(2, 90);
		System.out.print("[");
		for (int a: test) {
			System.out.print(a +" ");
		}
		System.out.print("]");
		System.out.println(computeCell(test, 2, 1, 1, 1));
		System.out.println(computeCell(test, 2, 1, 1, 0));
		System.out.println(computeCell(test, 2, 1, 0, 1));
		System.out.println(computeCell(test, 2, 1, 0, 0));
		System.out.println(computeCell(test, 2, 0, 1, 1));
		System.out.println(computeCell(test, 2, 0, 1, 0));
		System.out.println(computeCell(test, 2, 0, 0, 1));
		System.out.println(computeCell(test, 2, 0, 0, 0));
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
		return ruleSet[(int) a * colours * colours + b * colours + c ];
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
	public int[] computeNextRow(int[] a, int[] ruleSet, int colours) {		
		int[] nextRow = new int[a.length];		
		nextRow[0] = computeCell(ruleSet, colours, 0, a[0], a[1]);
		
		for (int i = 1; i < a.length - 1; i++) {
			nextRow[i] = computeCell(ruleSet, colours, a[i - 1], a[i], a[i + 1]);
		}
		nextRow[a.length - 1] = computeCell(ruleSet, colours, a[a.length - 2], a[a.length-1], 0);
		
		return nextRow;
	}
	
	//loops around the edges
	public int[] computeNextRowLooping(int[] a, int ruleSet[], int colours) {
		int[] nextRow = new int[a.length];		
		nextRow[0] = computeCell(ruleSet, colours, a[a.length - 1], a[0], a[1]);
		
		for (int i = 1; i < a.length - 1; i++) {
			nextRow[i] = computeCell(ruleSet, colours, a[i - 1], a[i], a[i + 1]);;
		}
		nextRow[a.length - 1] = computeCell(ruleSet, colours, a[a.length - 2], a[a.length-1], a[0]);
		
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
		return allRows;
	}
	
	
	
	class Graph extends JPanel {
		int cellSize = 10; //cell size in pixels (height & width)
		int colours;
		int ruleNumber;
		int width; //width in cells
		int height; //height in cells
		int[][] wholeThing; //all the cells to be drawn
		
		public Graph(int colours, int ruleNumber, int width, int height) {
			this.colours = colours;
			this.ruleNumber = ruleNumber;
			this.width = width;
			this.height = height;
			this.setPreferredSize(new Dimension(width * cellSize, height * cellSize));
			wholeThing = new int[height][width]; //maybe swap these
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (wholeThing[i][j] == 1) {
						g.setColor(Color.BLACK);
						g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
					}
					else {
						g.setColor(Color.WHITE);
						g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
					}
				}
			}
		}		
	}
	
	class keys extends JPanel {
		int cellsize = 20;
		int colours;
		int ruleNumber;
		public keys(int colours, int ruleNumber) {
			this.colours = colours;
			this.ruleNumber = ruleNumber;
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
		
	}
	
	
}
