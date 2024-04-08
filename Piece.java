
public class Piece {
    // Instance Variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;


    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    
    public boolean isBlack() {
        return this.isBlack;
    }


    
    public void promotePawn(int row, boolean isBlack) {
        if ((isBlack && row == 7) || (!isBlack && row == 0)) {
            this.character = isBlack ? '\u265b' : '\u2655';
        }
    }

    
    @Override
    public String toString() {
        return String.valueOf(this.character);
    }
}