
public class Board {

    // Instance variables
    private Piece[][] board;

    // Construct an object of type Board using given arguments.
    public Board() {
        this.board = new Piece[8][8];
    }

    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // Hint: this method should call isMoveLegal() on the starting piece.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece piece = getPiece(startRow, startCol);
        if (piece != null && piece.isMoveLegal(startRow, startCol, endRow, endCol)) {
            setPiece(endRow, endCol, piece);
            setPiece(startRow, startCol, null);
            return true;
        }
        return false;
    }

    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = getPiece(i, j);
                if (piece instanceof King) {
                    if (!((King) piece).isAlive()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
    }

    // Movement helper functions

    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 0 || startRow > 7 || startCol < 0 || startCol > 7) return false;
        if (endRow < 0 || endRow > 7 || endCol < 0 || endCol > 7) return false;

        Piece startPiece = getPiece(startRow, startCol);
        Piece endPiece = getPiece(endRow, endCol);

        if (startPiece == null) return false;
        if (startPiece.isBlack() != isBlack) return false;
        if (endPiece != null && endPiece.isBlack() == isBlack) return false;

        return true;
    }

    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);

        return (rowDiff <= 1 && colDiff <= 1);
    }

    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) return false;
        int step = (endCol > startCol) ? 1 : -1;

        for (int i = startCol + step; i != endCol; i += step) {
            if (getPiece(startRow, i) != null) return false;
        }
        return true;
    }

    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) return false;
        int step = (endRow > startRow) ? 1 : -1;

        for (int i = startRow + step; i != endRow; i += step) {
            if (getPiece(i, startCol) != null) return false;
        }
        return true;
    }

    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);

        if (rowDiff != colDiff) return false;
        int rowStep = (endRow > startRow) ? 1 : -1;
        int colStep = (endCol > startCol) ? 1 : -1;

        int row, col;
        for (row = startRow + rowStep, col = startCol + colStep;
             row != endRow && col != endCol;
             row += rowStep, col += colStep) {
            if (getPiece(row, col) != null) return false;
        }

        return true;
    }

    // Verifies whether the move from 'start' to 'end' position is clear of other pieces.
    public boolean verifyClearPath(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) {
            return verifyHorizontal(startRow, startCol, endRow, endCol);
        } else if (startCol == endCol) {
            return verifyVertical(startRow, startCol, endRow, endCol);
        } else {
            return verifyDiagonal(startRow, startCol, endRow, endCol);
        }
    }
}
