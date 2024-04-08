
public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - row);
        int colDiff = Math.abs(endCol - col);

        // Check if the move is in "L" shape
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }

        // Check if destination square has our own piece
        if (board.getPiece(endRow, endCol) != null) {
            if (board.getPiece(endRow, endCol).isBlack() == this.isBlack())
            {
                return false;
            }
        }

        return true;
    }
}
