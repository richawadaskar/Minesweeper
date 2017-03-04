import java.util.Random;
import java.util.Scanner;

Public class MinesweeperConsole(){
	char MINE = ‘*’, FLAG = ‘x’;
	static int NUMBERS = 0, revealedNumbers = 0;
	static int NUMMINES;
	int[] arrH = {-1,0,1,-1,1,-1,0,1};
	int[] arrV = {-1,-1,-1,0,0,1,1,1};
	static int BOARDWIDTH;
	static String level = “”;
	static Scanner in = new Scanner(System.in);
	char[][] lowerBoard;
	char[][] upperBoard;
	static boolean ENDGAME = false;
	public static void main(String[] args){
	P4_Wadaskar_Richa_MinesweeperConsole a = new P4_Wadaskar_Richa_MinesweeperConsole();
	System.out.println(“Minesweeper”);
	a.levelChoosing();
	a.runMinesweeper();
Public void levelChoosing(){
	System.out.println(“Choose a level (easy:1, medium:2, hard:3)”);
	level = in.next();
	if(level.equals(“1”){
		BOARDWIDTH = 10;
		NUMMINES = 10;
	} else if(level.equals(“2”){
		BOARDWIDTH = 15;
		NUMMINES = 50;
	} else if(level.equals(“3”){
		BOARDWIDTH = 20;
		NUMMINES = 70;
	}
	lowerBoard = new char[BOARDWIDTH][BOARDWIDTH];
	upperBoard = new char[BOARDWIDTH][BOARDWIDTH];
	createBoard();
	printBoard();
}
Public void runMinesweeper(){
	While(revealedNumbers <= NUMBERS && !ENDGAME){
	System.out.println(“Would you like to flag a cell of reveal a cell? (f/r) “);
	String fb = in.next();
	If(fb.equals(“f”){
		System.out.println(“Enter row: “);
		Int row = in.nextInt();
		System.out.println(“Enter col: “);
		Int col = in.nextInt();
		upperBoard[row][col] = FLAG;
		printBoard();
} else {
		System.out.println(“Enter row: “);
		Int row = in.nextInt();
		System.out.println(“Enter col: “);
		Int col = in.nextInt();
		If(minePresent(row,col)){
			ENDGAME = true;
			Reveal(row,col);
			printBoard();
			break;
} else {
	updateUpperBoard(row,col);
	printBoard();
}
}
}
If(ENDGAME){
	System.out.println(“Sorry, you lost ?” );
	Restart();
} else {
	System.out.println(“Congrats you won!”);
	Restart();
}
	}
	Public void restart(){
		System.out.println(“Want to play again? (y/n) ”);
	String nextGame =- in.next();
		If(nextGame.equals(“y”)){
			NUMBERS = 0;
			revealedNumbers = 0;
			lowerBoard = new char[BOARDWIDTH][BOARDWIDTH];
			ENDGAME = false;
			levelChoosing();
			runMineSweeper();
}
}
Public Boolean minePresent(int row,int col){
	If(lowerBoard[row][col] == MINE){
		Return true;
}
Return false;
}
Public void reveal(int row, int col){
	upperBoard[row][col] = lowerBoard[row][col];
}
Public void updateUpperBoard(int row, int col){
	If(lowerBoard[row][col] == ‘ ‘){
		runRecursion(row,col);
} else {
	Reveal(row,col);
	revealedNumbers++;
}
}
Public void runRecursion(int row, int col){
	If(lowerBoard[row][col] == ‘ ‘){
		Reveal(row,col);
		If(inbounds(row-1,col-1) && upperBoard[row-1][col-1] == ‘-‘) runRecursion(row-1,col-1);
If(inbounds(row,col-1) && upperBoard[row][col-1] == ‘-‘) runRecursion(row1,col-1);
If(inbounds(row+1,col-1) && upperBoard[row+1][col-1] == ‘-‘) runRecursion(row-1,col-1);
If(inbounds(row-1,col) && upperBoard[row-1][col] == ‘-‘) runRecursion(row-1,col);
If(inbounds(row+1,col) && upperBoard[row+1][col] == ‘-‘) runRecursion(row+1,col);
If(inbounds(row-1,col+1) && upperBoard[row-1][col+1] == ‘-‘) runRecursion(row-1,col+1);
If(inbounds(row,col+1) && upperBoard[row][col+1] == ‘-‘) runRecursion(row,col+1);
If(inbounds(row+1,col+1) && upperBoard[row+1][col+1] == ‘-‘) runRecursion(row+1,col+1);
} else if(isNumber(row,col)){
	Reveal(row,col);
	revealedNumbers++;
}
}
Public Boolean isNumber(int row, int col){
	Char b = lowerBoard[row][col];
	If(b >= 48 &7 b <= 57){
		Return true;
}
Return false;
}
Public void createBoard(){
	createUpperBoard();
	createLowerBoard();
}
Public void createUpperBoard(){
	For(int I = 0; I < BOARDWIDTH; i++){
		For(int j = 0; j < BOARDWIDTH; j++){
			upperBoard[i][j] = ‘-‘;
}
}
}
Public void createLowerBoard(){
	placeMines();
	addNumbersToSurroundMines();
}
Public void placeMines(){
	Int numMines = 0;
	While(numMines < NUMMINES){
		Random r = new Random();
		Int a = r.nextInt(BOARDWIDTH);
		Int b = r.nextInt(BOARDWIDTH);
		If(lowerBoard[a][b] != MINE){
			lowerBoard[a][b] = MINE;
			numMines++;
}
}
}
Public void addNumbersToSurroundMines(){
	Int num = 0;
	For(int I  = 0; I < BOARDWIDTH; i++){
		For(int j = 0; j < BOARDWIDTH; j++){
			Char a = lowerBoard[i][j];
			If(a != MINE){
				Num = findSurroundingMines(I,j);
				If(num == 0){
	lowerBoard[i][j] = ‘ ‘;
} else {
	String str = “” + num;
	Char c = str.charAt(0);
	lowerBoard[i][j] = c;
	NUMBERS++;
}
}
}
}
}
Public int findSurroundingMines(int row, int col){
	Int currRow = row, currCol = col, numMines = 0;
	For(int I= 0; I < BOARDWIDTH; i++){
		currRow += arrV[i];
		currCol += arrH[i];
		if(inbounds(currRow,currCol)){
			if(lowerBoard[currRow][currCol] == MINE){
				numMines++;
}
}
currRow = row;
currCol = col;
}
Return numMines;
}
Public Boolean inbounds(int row, int col){
	If(row >= 0 && row < BOARDWIDTH && col >=0 && col < BOARDWIDTH){
		Return true;
}
Return false;
}
Public void printBoard(){
	System.out.println(“  “);
	For(int I = 0; I < BOARDWIDTH; i++){
		If(i>=10){
	System.out.print((i-10) + “ ”); 
} else {
	System.out.print(I + “ “);
}
}
System.out.println();
For(int I = 0; I < BOARDWIDTH; i++){
		If(i>=10){
	System.out.print((i-10) + “ ”); 
} else {
	System.out.print(I + “ “);
}
For(int j = 0; j < BOARDWIDTH; j++){
	System.out.print(upperBoard[i][j] + “ “);
}
System.out.println();
}
}
Public void printLowerBoard(){
	System.out.println(“  “);
	For(int I = 0; I < BOARDWIDTH; i++){
		If(i>=10){
	System.out.print((i-10) + “ ”); 
} else {
	System.out.print(I + “ “);
}
}
System.out.println();
For(int I = 0; I < BOARDWIDTH; i++){
		If(i>=10){
	System.out.print((i-10) + “ ”); 
} else {
	System.out.print(I + “ “);
}
For(int j = 0; j < BOARDWIDTH; j++){
	System.out.print(lowerBoard[i][j] + “ “);
}
System.out.println();
}

}


}
	