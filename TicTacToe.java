import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];  // Initializes the board with default char values (0)
        currentPlayer = 'X';
    }

    public void startGame() {
        boolean gameWon = false;
        int movesCount = 0;

        while (!gameWon && movesCount < 9) {
            printBoard();
            playerMove();
            movesCount++;
            gameWon = checkForWin();
            if (gameWon) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            if (movesCount == 9) {
                printBoard();
                System.out.println("It's a draw!");
            }
            switchPlayer();
        }
    }

    private void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char toPrint = board[i][j];
                if (toPrint == 0) {
                    System.out.print((i * 3 + j + 1));  // Print cell number if empty
                } else {
                    System.out.print(toPrint);  // Print 'X' or 'O'
                }
                if (j < 2) System.out.print(" | "); // Column separator
            }
            System.out.println();
            if (i < 2) System.out.println("---------"); // Row separator
        }
    }

    private void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int move;
        while (true) {
            System.out.print("Player " + currentPlayer + " - where would you like to move? ");
            move = scanner.nextInt();
            if (move < 1 || move > 9) {
                System.out.println("That move is invalid! Please enter a number between 1 and 9.");
            } else if (isCellTaken(move)) {
                System.out.println("That cell is already taken! Please choose another.");
            } else {
                placeMove(move);
                break;
            }
        }
    }

    private boolean isCellTaken(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        return board[row][col] != 0;  // Checks if the cell is already occupied
    }

    private void placeMove(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        board[row][col] = currentPlayer;  // Places the current player's move
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';  // Switch players
    }

    private boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.startGame();
    }
}
