import java.util.Random;
import java.util.Scanner;

public class MinesweeperModel {
	char MINE = '*', FLAG = 'x';
	int NUMBERS = 0, revealedNumbers = 0;
	int NUMMINES;
	int[] arrH = {-1,0,1,-1,1,-1,0,1};
	int[] arrV = {-1,-1,-1,0,0,1,1,1};
	int BOARDWIDTH;
	String level = "";
	Scanner in = new Scanner(System.in);
	char[][] lowerBoard;
	char[][] upperBoard; 
	boolean ENDGAME = false;
	
	public boolean minePresent(int row,int col){
		if(lowerBoard[row][col] == MINE){
			return true;
		}
		return false;
	}
	public void reveal(int row, int col){
		upperBoard[row][col] = lowerBoard[row][col];
	}
	public void updateUpperBoard(int row, int col){
		if(lowerBoard[row][col] == ' '){
			runRecursion(row,col);
		} else {
			reveal(row,col);
			revealedNumbers++;
		}
	}
	public void runRecursion(int row,int col){
		if(lowerBoard[row][col] == ' '){
			reveal(row,col);
			if(inBounds(row-1, col-1) && upperBoard[row-1][col-1] == '-') runRecursion(row-1, col-1);
			if(inBounds(row, col-1) && upperBoard[row][col-1] == '-')  runRecursion(row, col-1);
			if(inBounds(row+1, col-1) && upperBoard[row+1][col-1] == '-') runRecursion(row+1, col-1);
			if(inBounds(row-1, col) && upperBoard[row-1][col] == '-') runRecursion(row-1, col);
			if(inBounds(row+1, col) && upperBoard[row+1][col] == '-') runRecursion(row+1, col);
			if(inBounds(row-1, col+1) && upperBoard[row-1][col+1] == '-') runRecursion(row-1, col+1);
			if(inBounds(row, col+1) && upperBoard[row][col+1] == '-') runRecursion(row, col+1);
			if(inBounds(row+1, col+1) && upperBoard[row+1][col+1] == '-') runRecursion(row+1, col+1);
		} else if (isNumber(row,col)){
			reveal(row,col);
			revealedNumbers++;
		}
	}
	public boolean isNumber(int row,int col){
		char b = lowerBoard[row][col];
		if(b >= 48 && b <=57){
			return true;
		}
		return false;
	}
	
	public int findSurroundingMines(int row, int col){
		int currRow = row, currCol = col, numMines = 0;
		for(int i = 0; i < arrV.length; i++){
			currRow += arrV[i];
			currCol += arrH[i];
			if(inBounds(currRow,currCol)){
				if(lowerBoard[currRow][currCol] == MINE){
					numMines++;
				}
			}
			currRow = row;
			currCol = col;
		}
		return numMines;
	}
	public boolean inBounds(int row, int col){
		if(row >= 0 && row < BOARDWIDTH && col >= 0 && col < BOARDWIDTH){
			return true;
		}
		return false;
	}
}