
public class King {
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - row);
        int colDiff = Math.abs(endCol - col);

        // Check if the move is exactly one square in any direction
        if ((rowDiff == 1 && colDiff <= 1) || (rowDiff <= 1 && colDiff == 1)) {
            // Check if destination square has our own piece
            if (board.getPiece(endRow, endCol) != null) {
                if (board.getPiece(endRow, endCol).isBlack() == this.isBlack())
                {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}

