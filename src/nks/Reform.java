package nks;
import java.awt.*;
import javax.swing.*;
//will model using ints instead of bools because future extentions to multiple colours
public class Reform extends JFrame {	
	
	public Reform(int x) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		/*
		int[] start = new int[300];
		for (int i = 0; i < start.length; i++) {
			double rand = Math.random();
			if (rand < 0.333333) {
				start[i] = 0;
			}
			else if (rand < 0.666666) {
				start[i] = 1;
			}
			else {
				start[i] = 2;
			}
		}
		*/
		//int[] start = {0, 0, 0, 0 ,0, 1, 2, 2, 2, 1, 2 ,2, 1, 0, 0, 0, 0, 0, 0, 1,};
		
		//life travels through this one
		//int[] start = Analysis.numbersAsInput(new int[] {3, -4, 8, 45, -33, 24, -12, 0, 0, 3, -1, -4, 62, 34, 66});
		//Key3Set rule = new Key3Set(3, 1999124969474l);
		//int[] start = Analysis.numbersAsInput(new int[] {-33, 100, -43, 2, 0, 0, 34});
		//System.out.println("START 0 IS "  + start[0]);
		//start[5] = 1;
		//Key3Set rule30 = new Key3Set(3, 3097483878567l);
		//Key3Set rule30 = new Key3Set(3, 1123289366095l);
		//Key3Set rule30 = new Key3Set(3, 277206003607l);
		//Key3Set rule30 = new Key3Set(2, 30);
		
		//this one is pretty weird shapes
		//Key3Set rule = new Key3Set(3, 1982122969474l);
		
		//
		//Key3Set rule30 = new Key3Set(3, 7625343990667l);
		//not rightly alligned, doesnt match up with CA on emphatious
		//KeyNSet test = new KeyNSet(3, 3, new int[] {0, 1, 0, 2, 2, 2,1,0,1,1,0,1,2,2,2,0,1,0,0,1,0,2,2,2,1,0,1});
		int[] bob = randomSymRule(3, 3);
		//int[] bob = new int[] {0,1,0,0,2,1,1,1,0,1,2,2,1,1,1,0,0,1,2,1,1,1,0,2,2,1,2,};
		//int[] bob = symRule(x);
		//int[] bob = new int[] {0,0,0,2,0,1,2,0,2,1,0,2,0,2,2,1,2,0,0,1,2,0,2,0,1,1,0,1,0,1,0,0,0,2,2,2,2,0,0,0,2,0,2,0,2,2,2,2,1,2,2,1,1,2,0,1,2,0,1,2,0,1,1,2,1,2,0,0,1,0,2,1,2,0,1,1,0,2,1,1,2};
		//int[] bob = new int[] {1,2,2,0,2,0,0,1,2,0,2,0,1,0,0,2,1,0,2,2,2,1,0,1,2,1,2,2,1,1,0,1,1,2,2,0,0,0,1,2,1,0,1,2,2,2,0,0,1,1,2,1,1,0,0,1,0,1,2,1,0,0,0,2,1,0,2,2,1,2,0,2,0,1,2,2,0,2,0,0,1};
		
		
		KeyNSet test = new KeyNSet(3, 3, bob);
		for (int i = 0; i < bob.length; i++) {
			//System.out.print(bob[i] + " ");
		}
		//int[] start = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,};
		//int[] start = randomStart(3, 160);
		int[] start = new int[880];
		start[440]= 1;
		//start[44] = 2;
		
		//KeyNSet test = new KeyNSet(3, 3, new int[] {0, 1, 0, 0, 2, 2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2});
		//KeyNSet test = new KeyNSet(2, 3, new int[] {0,1,2,2,1,2,0, 1,1});
		Graph hey = new Graph(test, 500, start);
		//Key3Set keys, int width, int height, int[] initialRow) 
		JScrollPane scrollPane = new JScrollPane(hey);
		scrollPane.setPreferredSize(new Dimension(1500, 973));
		//scrollPane.setPreferredSize(new Dimension(Math.min(hey.cellSize * start.length, 50 * hey.cellSize), 800));
		add(scrollPane);
		
		
		KeysPanel keys = new KeysPanel(test);
		add(keys);		
	}
	
	public static void main(String[] args) {
		int i = 3;
		Reform frame = new Reform(i);
		frame.setTitle("Cellular Automata " + i);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
		
	//turns the a's to black with probability p.
	public void randomize(int[] a, double p, int colours) {
		for (int i = 0; i < a.length; i++) {
			double rand = Math.random();
			rand *= colours;
			a[i] = (int) Math.floor(rand);
		}
	}
	
	//loops around the edges
	public static int[] computeNextRowLooping(int[] row, KeyNSet keys) {
		int[] nextRow = new int[row.length];
		for (int i = 0; i < row.length; i++) {
			int[] input = new int[keys.cells];
			for (int j = 0; j < input.length; j++) {
				input[j] = row[(i + j) % row.length];
			}
			nextRow[i] = keys.computeCell(input);
		}
		///IMPORTANT * TODO: THIS SHIFTS IT ALL TO THE RIGHT
		return nextRow;
	}
	
	//detect repetition. works in O(n^2) time complexity if not reversable, O(n) if reversable
	public int detectRepetition(int[] startingRow, int[] ruleSet) {
		return 4;
	}	
	
	public static int[] randomRule(int cells, int colours) {
		int[] rule = new int[(int) LongMath.power(colours, cells)];
		for (int i = 0; i < rule.length; i++) {
			rule[i] = (int) (Math.random() * colours);
		}
		//System.out.println("rule length is " + rule.length);
		return rule;
	}
	
	//same as method below
	public static int[] random3Sym(int cells) {//always 3 colours
		int[] rule = new int[(int) LongMath.power(3, cells)];
		for (int i = 0; i < (rule.length / 2); i++) {
			rule[i] = (int) (Math.random() * 3);
			rule[rule.length - 1 - i] = 2 - (int) (Math.random() * 3);
		}
		rule[rule.length/2] = 1;
		System.out.println("rule length is " + rule.length);
		return rule;
	}
	
	//only works for 3 colors
	public static int[] randomSymRule(int cells, int colours) {
		int[] rule = new int[(int) LongMath.power(colours, cells)];
		rule[rule.length / 2] = 1;
		for (int i = 0; i < rule.length / 2; i++) {
			int bob = (int) (Math.random() * colours);
			rule[i] = bob;
			rule[rule.length - i - 1] = 2 - bob;
		}
		System.out.println("rule length is " + rule.length);
		for (int i = 0; i < rule.length; i++) {
			System.out.print(rule[i] + " ");
		}
		return rule;
	}
	
	public static int[] symRule(int x) {//temp method, iterating through 2 cell 3 colours sym rules.
		int[] rule = new int[(int) LongMath.power(3, 2)];
		rule[rule.length / 2] = 1;
		for (int i = 0; i < 4; i++) {
			rule[i] = x / (int) LongMath.power(3, 4 - 1 - i);
			rule[rule.length - i - 1] = 2 - rule[i];
			x -= rule[i] * (int) LongMath.power(3, 4 - 1 - i);
		}
		return rule;
	}
	
	
	public static int[] randomStart(int colours, int size) {
		int[] row = new int[size];
		for (int i = 0; i < row.length; i++) {
			row[i] = (int) (Math.random() * colours);
		}
		return row;
	}
	
	
	//width is implied by startingRow.length
	public static int[][] computeEntire(int[] startingRow, KeyNSet keys, int height) {
		int[][] allRows = new int[height][startingRow.length];		
		allRows[0] = startingRow.clone();		
		for (int i = 1; i < height; i++) {		
			allRows[i] = computeNextRowLooping(allRows[i - 1], keys);
		}		
		return allRows;
	}
	
	class Graph extends JPanel {
		int cellSize = 3; //cell size in pixels (height & width)
		int width; //width in cells
		int height; //height in cells
		int[][] wholeThing; //all the cells to be drawn	
		Color[] cols;
		
		public Graph(KeyNSet keys, int height, int[] initialRow) {
			this.cols = new Color[keys.colours];
			for (int i = 0; i < cols.length; i++) {
				cols[i] = new Color(i * (255 / (cols.length - 1)), i * (255 / (cols.length - 1)), i * (255 / (cols.length - 1)));
			}			
			this.width = initialRow.length;
			this.height = height;
			this.setPreferredSize(new Dimension(cellSize * width, height * cellSize));
			wholeThing = computeEntire(initialRow, keys, height);			
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {					
					g.setColor(cols[wholeThing[i][j]]);
					g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
				}
			}
		}
	}	
	
	class KeysPanel extends JPanel {
		int cellSize = 9;
		KeyNSet keys;
		Color[] cols;
		public KeysPanel(KeyNSet keys) {
			this.keys = keys;
			this.cols = new Color[keys.colours];
			for (int i = 0; i < cols.length; i++) {
				cols[i] = new Color(i * (255 / (cols.length - 1)), i * (255 / (cols.length - 1)), i * (255 / (cols.length - 1)));
			}
		}
		
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(new Color(100, 100,0));
			g.fillRect(0, 0, cellSize * (keys.cells  + 2), cellSize * 4 * keys.numberOfKeys);
			for (int i = 0; i < keys.numberOfKeys; i++) {			
				int temp = i;				
				for (int j = 0; j < keys.cells; j++) {
					g.setColor(cols[temp / ((int) (Math.pow(keys.colours, keys.cells -1 - j)))]);
					temp -= (int) Math.pow(keys.colours, keys.cells - 1 - j) * (temp / ((int) (Math.pow(keys.colours, keys.cells - 1 - j))));
					
					g.fillRect(cellSize + j * cellSize, cellSize + cellSize * 4 * i, cellSize, cellSize);
				}	
				g.setColor(cols[keys.ruleSet[i]]); //2 -  because inverted? (not sure qill check later but does work)
				
				g.fillRect(cellSize * 1, cellSize * 2 + cellSize * 4 * i, cellSize, cellSize);				
			}
			
		}
		
		public Dimension getPreferredSize() {
			return new Dimension(cellSize * (keys.cells  + 2), keys.numberOfKeys * cellSize * 4);
		}
		
		public Dimension getMinimumSize() {
			return new Dimension(cellSize * (keys.cells  + 2), keys.numberOfKeys * cellSize * 4);
		}
		
	}	
}
