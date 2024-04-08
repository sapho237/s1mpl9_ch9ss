
import java.util.Scanner;

public class Game {
    private Board board;

    public Game() {
        this.board = new Board();
    }

    public void printBoard() {
        // This is just a placeholder. need to use the board's
        // methods to fetch the current state and print it in a format similar
        // to what's shown in the image you shared.
        System.out.println(board);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean isWhiteTurn = true;

        while (true) {
            printBoard();
            if (isWhiteTurn) {
                System.out.println("It is currently white's turn to play.");
            } else {
                System.out.println("It is currently black's turn to play.");
            }

            System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
            int startRow = scanner.nextInt();
            int startCol = scanner.nextInt();
            int endRow = scanner.nextInt();
            int endCol = scanner.nextInt();

            // Assuming the Board class has a method called movePiece
            boolean moveSuccessful = board.movePiece(startRow, startCol, endRow, endCol, isWhiteTurn);

            if (moveSuccessful) {
                // Check for game-ending conditions
                if (board.isKingCaptured(isWhiteTurn)) {
                    if (isWhiteTurn) {
                        System.out.println("Black has won the game!");
                    } else {
                        System.out.println("White has won the game!");
                    }
                    break;
                }
                // Switch turns
                isWhiteTurn = !isWhiteTurn;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
