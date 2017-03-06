import java.awt.Point;
import java.util.Random;

class MinesweeperAI{
	Cell[][] cellArray;
	NewController controller;
	Point point;
	int[] arrH;
	int[] arrV;
	boolean revealable = false;
	boolean needMoreGuessing = true, flag = false, reveal = false, endLoop = false;
	Random a = new Random();
	public MinesweeperAI(NewController control){
		controller = control;
		arrH = controller.getHArr();
		arrV = controller.getVArr();
	}
	public void findNextMove(){
		cellArray = controller.getArray();
		endLoop = false;
		if(!revealedEnoughOfBoard()){
			needMoreGuessing = true;
		}
		if(needMoreGuessing){
			System.out.println("Guessing");
			while(true){
				int x = a.nextInt(cellArray.length);
				int y = a.nextInt(cellArray.length);
				if(cellArray[x][y].myRevealed != true && !cellArray[x][y].myFlagged){
//					possibleMoves[x][y] = true;
					controller.revealCell(x,y);
					if(revealedEnoughOfBoard()){
						flag = true;
						needMoreGuessing = false;
					}
					if(cellArray[x][y].myhasMine == true){
						controller.endGame();
					}
					break;
				}
			} 
		} else if(flag){ 
			System.out.println("Flagging");
			int i = 0;
			int j = 0;
			while(!endLoop){
				int unrevealed = numUnrevealedCells(i,j);
				if(unrevealed == cellArray[i][j].myNum){
					Point[] arr = findUnrevealed(unrevealed, i,j);
					for(int c = 0; c < arr.length; c++){
						int row = (int)(arr[c].getX());
						int col = (int)(arr[c].getY());
						if(!cellArray[row][col].myFlagged){
							controller.flag(row,col);
							cellArray[row][col].myFlagged = true;
						}
					}
				}
				if(j == cellArray.length-1){
					j = 0;
					i++;
					if(i == cellArray.length) endLoop = true;
				} else {
					j++;
				}
			}
			flag = false;
			reveal = true;
		} else if(reveal){
			System.out.println("On Reveal");
			for(int i = 0; i < cellArray.length; i++){
				for(int j = 0; j < cellArray.length; j++){
					if(cellArray[i][j].myNum == neighborsFlagged(i,j)){
						revealAllUnflaggedNeighbors(i,j);
					}
				}
			}
			if(!revealable){
				System.out.println("NOT Revealable");
				needMoreGuessing = true;
				revealable = false;
			}
			flag = true;
//			needMoreGuessing = true;
		}
	}
	public boolean revealedEnoughOfBoard(){
		int a = 0;
		for(int i = 0; i < cellArray.length; i++){
			for(int j = 0; j < cellArray.length; j++){
				if(cellArray[i][j].myRevealed == true){
					a++;
				}
			}
		}
		if(a/cellArray.length >= 0.10) return true;
		return false;
	}
	public int numUnrevealedCells(int row, int col){
		int currRow = row, currCol = col, numUnrevealed = 0;
		for(int i = 0; i < arrV.length; i++){
			currRow += arrV[i];
			currCol += arrH[i];
			if(inBounds(currRow,currCol)){
				if(!cellArray[currRow][currCol].myRevealed){
					numUnrevealed++;
				}
			}
			currRow = row;
			currCol = col;
		}
		return numUnrevealed;
	}
	public int neighborsFlagged(int row, int col){
		int currRow = row, currCol = col, numFlagged = 0;
		for(int i = 0; i < arrV.length; i++){
			currRow += arrV[i];
			currCol += arrH[i];
			if(inBounds(currRow,currCol)){
				if(cellArray[currRow][currCol].myFlagged){
					numFlagged++;
				}
			}
			currRow = row;
			currCol = col;
		}
		return numFlagged;
	}
	public void revealAllUnflaggedNeighbors(int row, int col){
		int currRow = row, currCol = col;
		for(int i = 0; i < arrV.length; i++){
			currRow += arrV[i];
			currCol += arrH[i];
			if(inBounds(currRow,currCol)){
				if(!cellArray[currRow][currCol].myFlagged && !cellArray[currRow][currCol].myRevealed){
					controller.revealCell(currRow,currCol);
					cellArray[currRow][currCol].myRevealed = true;
					System.out.println("Revealed: " + currRow + "," + currCol);
					revealable = true;
				}
			}
			currRow = row;
			currCol = col;
		}
	}
	public Point[] findUnrevealed(int unrevealed, int row, int col){
		int a = 0;
		Point[] arr = new Point[unrevealed];
		int currRow = row, currCol = col;
		for(int i = 0; i < arrV.length; i++){
			currRow += arrV[i];
			currCol += arrH[i];
			if(inBounds(currRow,currCol)){
				if(!cellArray[currRow][currCol].myRevealed){
					Point point = new Point(currRow,currCol);
					arr[a] = point;
					a++;
				}
			}
			currRow = row;
			currCol = col;
		}
		return arr;
	}
	public boolean inBounds(int row, int col){
		if(row >= 0 && row < cellArray.length && col >= 0 && col < cellArray.length){
			return true;
		}
		return false;
	}
}