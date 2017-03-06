import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


public class MinesweeperController implements MouseListener{
	MinesweeperModel model;
	MinesweeperView view;
	MinesweeperController controller;
	public MinesweeperController(){
		model = new MinesweeperModel();
		view = new MinesweeperView(controller);
	}
	public void play(){
		view.showTitle();
		view.initializeBoard();
		addActionListeners();
		levelChoosing();
		runMinesweeper();
	}
	public void addActionListeners(){
		view.mainPanel.addMouseListener(this);
	}
	public void levelChoosing(){
		System.out.println("Choose a level (easy:1 /medium:2 /hard:3) ");
		model.level = model.in.next();
		if(model.level.equals("1")){
			model.BOARDWIDTH = 10;
			model.NUMMINES = 10;
		} else if(model.level.equals("2")){
			model.BOARDWIDTH = 15;
			model.NUMMINES = 50;
		} else {
			model.BOARDWIDTH = 20;
			model.NUMMINES = 70;
		}
		model.lowerBoard = new char[model.BOARDWIDTH][model.BOARDWIDTH];
		model.upperBoard = new char[model.BOARDWIDTH][model.BOARDWIDTH];
		createBoard();
		view.printBoard(model.upperBoard);
	}
	public void runMinesweeper(){
		while(model.revealedNumbers < model.NUMBERS && !model.ENDGAME){
			System.out.println("Would you like to flag a cell or reveal a cell? (f/r)");
			String fb = model.in.next();
			if(fb.equals("f")){
				System.out.println("Enter row: ");
				int row = model.in.nextInt();
				System.out.println("Enter col: ");
				int col = model.in.nextInt();
				model.upperBoard[row][col] = model.FLAG;
				view.printBoard(model.upperBoard);
			} else {
				System.out.println("Enter row: ");
				int row = model.in.nextInt();
				System.out.println("Enter col: ");
				int col = model.in.nextInt();
				if(model.minePresent(row,col)){
					model.ENDGAME = true;
					model.reveal(row,col);
					view.printBoard(model.upperBoard);
					break;
				} else {
					model.updateUpperBoard(row,col);
					view.printBoard(model.upperBoard);
				}
			}
		}
		if(model.ENDGAME){
			System.out.println("Sorry, you lost :(");
			restart();
		} else {
			System.out.println("Congrats you won!");
			restart();
		}
	}
	public void restart(){
		System.out.println("Want to play again? (y/n) ");
		String nextGame = model.in.next();
		if(nextGame.equals("y")){
			model.NUMBERS = 0;
			model.revealedNumbers = 0;
			model.lowerBoard = new char[model.BOARDWIDTH][model.BOARDWIDTH];
			model.upperBoard = new char[model.BOARDWIDTH][model.BOARDWIDTH]; 
			model.ENDGAME = false;
			levelChoosing();
			runMinesweeper();
		}
	}
	public void createBoard(){
		createUpperBoard();
		createLowerBoard();
	}
	public void createUpperBoard(){
		for(int i = 0; i < model.BOARDWIDTH; i++){
			for(int j = 0; j < model.BOARDWIDTH; j++){
				model.upperBoard[i][j] = '-';
			}
		}
	}
	public void createLowerBoard(){
		placeMines();
		addNumbersToSurroundMines();
	}
	public void placeMines(){
		int numMines = 0;
		while(numMines < model.NUMMINES){
			Random r = new Random();
			int a = r.nextInt(model.BOARDWIDTH);
			int b = r.nextInt(model.BOARDWIDTH);
			if(model.lowerBoard[a][b] != model.MINE){
				model.lowerBoard[a][b] = model.MINE;
				numMines++;
			}  
		}
	}
	public void addNumbersToSurroundMines(){
		int num = 0;
		for(int i = 0; i < model.BOARDWIDTH; i++){
			for(int j = 0; j < model.BOARDWIDTH; j++){
				char a = model.lowerBoard[i][j];
				if(a != model.MINE) {
					num = model.findSurroundingMines(i,j);
					if(num == 0){
						model.lowerBoard[i][j] = ' ';
					} else {
						String str = "" + num;
						char c = str.charAt(0);
						model.lowerBoard[i][j] = c;
						model.NUMBERS++;
					}
				}
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(view.mainPanel)){
			int x = getCorrectedPos(e.getX());
			int y = getCorrectedPos(e.getY());
			view.drawSquare(x,y);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	public int getCorrectedPos(int a){
		int num = a;
		return num;
	}
	public int getCols(){
		return (model.BOARDWIDTH);
	}
	public int getRows(){
		return (model.BOARDWIDTH);
	}
}