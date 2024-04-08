
public class Bishop {
    private int row;
    private int col;
    private boolean isBlack;

    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (Math.abs(endRow - row) != Math.abs(endCol - col)) {
            // Bishop can only move diagonally
            return false;
        }

        int rowDirection = (endRow > row) ? 1 : -1;
        int colDirection = (endCol > col) ? 1 : -1;

        int r = row + rowDirection;
        int c = col + colDirection;

        while (r != endRow && c != endCol) {
            if (board.getPiece(r, c) != null) {
                // Path is blocked by another piece
                return false;
            }
            r += rowDirection;
            c += colDirection;
        }

        // Check if destination square has our own piece
        if (board.getPiece(endRow, endCol) != null) {
            if (board.getPiece(endRow, endCol).isBlack() == this.isBlack()) {
                return false;
            }
        }

        return true;
    }
}
