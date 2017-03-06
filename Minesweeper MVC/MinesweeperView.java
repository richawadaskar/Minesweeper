import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Graphics;

public class MinesweeperView {
	JFrame window;
	MyDrawingPanel1  drawingPanel;
	JMenuItem newBGame, newIGame, newEGame, exit, setMines, howToPlay, about;
	JLabel timeElapsed, mines;
	JPanel mainPanel;
	
	MinesweeperController controller;
	public MinesweeperView(MinesweeperController control){
		controller = control;
	}
	public void showTitle(){
		System.out.println("MINESWEEPER :D");
	}
	public void initializeBoard(){
		window = new JFrame("Minesweeper");
		window.setBounds(100, 100, 445, 600);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawingPanel = new MyDrawingPanel1();
		drawingPanel.setBounds(20, 20, 400,400);
		drawingPanel.setBorder(BorderFactory.createEtchedBorder());

		JMenuBar menubar = new JMenuBar();
		JMenu game = new JMenu("Game");
		JMenu options = new JMenu("Options");
		JMenu help = new JMenu("Help");
		newBGame = new JMenuItem("New Beginner Game");
		newIGame = new JMenuItem("New Intermediate Game");
		newEGame = new JMenuItem("New Expert Game");
		exit = new JMenuItem("Exit");
		setMines = new JMenuItem("Set Number of Mines");
		howToPlay = new JMenuItem("How to Play");
		about = new JMenuItem("About");
		game.add(newBGame);
		game.add(newIGame);
		game.add(newEGame);
		game.add(exit);
		options.add(setMines);
		help.add(about);
		help.add(howToPlay);
		menubar.add(game);
		menubar.add(options);
		menubar.add(help);
		window.setJMenuBar(menubar);

		// Add GUI elements to the Java window's ContentPane
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.add(drawingPanel);

		timeElapsed = new JLabel("0");
		timeElapsed.setBorder(new TitledBorder("Time Elapsed"));
		mines = new JLabel("10");
		mines.setBorder(new TitledBorder("Mines"));
		timeElapsed.setBounds(50, 430, 100, 80);
		mines.setBounds(300, 430, 100, 80);
		
		window.add(timeElapsed);
		window.add(mines);
		window.getContentPane().add(mainPanel);

		window.setVisible(true);

	}
	public void drawSquare(int x, int y){
		MyDrawingPanel1 a = new MyDrawingPanel1();
		a.drawSquare(x,y);
	}
	private class MyDrawingPanel1 extends JPanel {

		// Not required, but gets rid of the serialVersionUID warning.  Google it, if desired.
		static final long serialVersionUID = 1234567890L;

		public void paintComponent(Graphics g) {

			g.setColor(Color.darkGray);
			g.fillRect(2, 2, this.getWidth()-2, this.getHeight()-2);

			g.setColor(Color.lightGray);
			
			drawGrid(g);
		}
		public void drawSquare(int x, int y){
			Graphics g = window.getGraphics();
			g.setColor(Color.red);
			g.drawRect(x, y, mainPanel.getWidth()/controller.getCols(), mainPanel.getHeight()/controller.getRows());
		}
		public void drawGrid(Graphics g){
			for (int x = 0; x < this.getWidth(); x += 20)
				g.drawLine(x, 0, x, this.getHeight());

			for (int y = 0; y < this.getHeight(); y += 20)
				g.drawLine(0, y, this.getWidth(), y);
		}
	}
	public void printBoard(char[][] board){
		System.out.print("  ");
		for(int i = 0; i < board.length; i++) {
			if(i >= 10){
				System.out.print((i-10) + " ");
			} else {
				System.out.print(i + " ");
			}
		}
		System.out.println();
		for(int i = 0; i < board.length; i++){
			if(i >= 10){
				System.out.print((i-10) + " ");
			} else {
				System.out.print(i + " ");
			}
			for(int j = 0; j < board.length; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	public void printLowerBoard(char[][] board){
		System.out.print("  ");
		for(int i = 0; i < board.length; i++) {
			if(i >= 10){
				System.out.print((i-10) + " ");
			} else {
				System.out.print(i + " ");
			}
		}
		System.out.println();
		for(int i = 0; i < board.length; i++){
			if(i >= 10){
				System.out.print((i-10) + " ");
			} else {
				System.out.print(i + " ");
			}
			for(int j = 0; j < board.length; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}