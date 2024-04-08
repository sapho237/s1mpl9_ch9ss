
public class Rook {
    private int row;
    private int col;
    private boolean isBlack;

    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (endRow != row && endCol != col) {
            // Rook can't move diagonally
            return false;
        }

        if (endRow == row) {
            // Moving horizontally
            int direction = (endCol > col) ? 1 : -1;
            for (int i = col + direction; i != endCol; i += direction) {
                if (board.getPiece(row, i) != null) {
                    // Path is blocked by another piece
                    return false;
                }
            }
        } else if (endCol == col) {
            // Moving vertically
            int direction = (endRow > row) ? 1 : -1;
            for (int i = row + direction; i != endRow; i += direction) {
                if (board.getPiece(i, col) != null) {
                    // Path is blocked by another piece
                    return false;
                }
            }
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



