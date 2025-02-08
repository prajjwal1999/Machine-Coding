package TicTacToe;

import java.util.Scanner;

public class Game {
    public static Boolean isValid(String[][] gameBoard, int x, int y){
        if(x>3 ||  x<1 || y>3 || y<1)
            return false;
        else if (!gameBoard[x-1][y-1].equalsIgnoreCase("")) {
            System.out.println("Already piece is there...");
            return false;
        }
        boolean check = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(gameBoard[i][j].equalsIgnoreCase("")){
                    check =false;
                    break;
                }
            }
        }
        if(check){
            System.out.println("Game Over....");
            System.exit(1);
            return false;
        }
        return true;
    }
    public static boolean Win(String[][] gameBoard) {
        int size = gameBoard.length; // Assuming a square board (3x3)

        // Check rows and columns
        for (int i = 0; i < size; i++) {
            if (!gameBoard[i][0].equals("") && gameBoard[i][0].equals(gameBoard[i][1]) && gameBoard[i][1].equals(gameBoard[i][2])) {
                return true; // Row match
            }
            if (!gameBoard[0][i].equals("") && gameBoard[0][i].equals(gameBoard[1][i]) && gameBoard[1][i].equals(gameBoard[2][i])) {
                return true; // Column match
            }
        }

        // Check diagonals
        if (!gameBoard[0][0].equals("") && gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[1][1].equals(gameBoard[2][2])) {
            return true; // Main diagonal
        }
        if (!gameBoard[0][2].equals("") && gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[1][1].equals(gameBoard[2][0])) {
            return true; // Anti-diagonal
        }

        return false; // No winner yet
    }
    public static void printBoard(String[][] board) {
        int size = board.length; // Assuming a square board (3x3)

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Print the cell value or a space if it's empty
                System.out.print(board[i][j].equals("") ? "   " : " " + board[i][j] + " ");

                // Print vertical separator if not the last column
                if (j < size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println(); // Move to the next line

            // Print horizontal separator if not the last row
            if (i < size - 1) {
                System.out.println("---+---+---");
            }
        }
    }


    public static void main(String[] args){
        System.out.println("Welcome To the Tic-Tac-Toe Game");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Player Name");
        String player1 = scanner.next();
        System.out.println("Enter Second Player Name");
        String player2 = scanner.next();
        Players A = new Players(player1,"1","X");
        Players B = new Players(player2,"2","O");
        String[][] board = new String[3][3];

        TicTacToe ticTacToe = new TicTacToe(player1,A,B);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j]="";
            }
        }
        ticTacToe.setCurrentPlayer(A.getName());
        int i=0;
        while(true) {
            Players currentPlayer = (i%2)==0?A:B;
            System.out.println("make your chance  Player for  " + currentPlayer.getName()+ "your symbol is" + currentPlayer.getType() );
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            boolean validMove = isValid(board,x,y);
            if(validMove){
                board[x-1][y-1]= currentPlayer.getType();
            }
            else{
                System.out.println("Please make a valid move...");
            }
            boolean isWin=Win(board);
            printBoard(board);
            if(isWin){
                System.out.println("Player won..."+ currentPlayer.getName());
                System.exit(1);
            }
            i++;
        }


    }
}
