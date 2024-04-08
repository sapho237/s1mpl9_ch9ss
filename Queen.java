
public class Queen {
    private int row;
    private int col;
    private boolean isBlack;

    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - row);
        int colDiff = Math.abs(endCol - col);

        // Check if move is vertical, horizontal, or diagonal
        if (row != endRow && col != endCol && rowDiff != colDiff) {
            return false;
        }

        // Check the path for vertical move
        if (row == endRow) {
            int step = (endCol > col) ? 1 : -1;
            for (int i = col + step; i != endCol; i += step) {
                if (board.getPiece(row, i) != null) {
                    return false;
                }
            }
        }

        // Check the path for horizontal move
        else if (col == endCol) {
            int step = (endRow > row) ? 1 : -1;
            for (int i = row + step; i != endRow; i += step) {
                if (board.getPiece(i, col) != null) {
                    return false;
                }
            }
        }

        // Check the path for diagonal move
        else {
            int rowStep = (endRow > row) ? 1 : -1;
            int colStep = (endCol > col) ? 1 : -1;
            int i = row + rowStep;
            int j = col + colStep;

            while (i != endRow && j != endCol) {
                if (board.getPiece(i, j) != null) {
                    return false;
                }
                i += rowStep;
                j += colStep;
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

