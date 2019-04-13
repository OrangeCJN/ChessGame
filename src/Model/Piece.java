package Model;

import java.util.List;

/**
 * abstract Piece class
 */
public abstract class Piece implements Comparable<Piece> {

    protected Position position;
    protected Model board;

    public Position getPosition() {
        return position;
    }

    public Piece(Piece other){
        this.board = other.board;
        this.firstPlayer = other.firstPlayer;
        this.position = other.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

   /**  true for the piece of first player
    * false for the piece of second player
    * white first move from down to up
    * black second move from up to down
    */
    private boolean firstPlayer;

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public Piece(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Piece(Model board, boolean firstPlayer, int row, int col) {
        this.board = board;
        this.firstPlayer = firstPlayer;
    }

    /**
     * @param other
     * @return whether enemy piece
     */
    public boolean isEnemy(Piece other) {

        return this.isFirstPlayer() != other.isFirstPlayer();
    }

    /**
     * move piece to position
     *
     * @param position to position
     * @return if moved successfully
     */
    public boolean moveTo(Position position) {
        if (!isValidMoveOrCapture(position)) {
            return false;
        }
        backUpLastGrid(position);
        board.clear(this.getPosition());
        board.setPiece(this, position.getRow(), position.getCol());
        return true;
    }

    /**
     * backup last grid
     */
    private void backUpLastGrid(Position positionTo){
        Model.positionFrom = getPosition();
        Model.positionTo = positionTo;
        Model.pieceFrom = this;
        Model.pieceTo = board.getPiece(positionTo);
    }

    /**
     * move piece to position
     *
     * @param col to column index
     * @param row to row index
     * @return if moved successfully
     */
    public boolean moveTo(int row, int col) {
        return moveTo(new Position(row, col));
    }

    /**
     * @return row
     */
    public int getRow() {
        return position.getRow();
    }

    /**
     * @return column
     */
    public int getCol() {
        return position.getCol();
    }

    /**
     * @param position
     * @return whether valid move or capture
     */
    public boolean isValidMoveOrCapture(Position position) {
        if (!board.isInBoard(position)) {
            return false;
        }
        if (isValidMove(position)) {
            return true;
        }
        Piece piece = board.getPiece(position);
        if (piece == null && isValidMove(position)) {
            return true;
        }
        return piece != null && isValidCapture(piece);

    }

    /**
     * @return valid moves
     */
    public abstract List<Position> getValidMoves();

    /**
     * @return valid captures
     */
    public abstract List<Piece> getValidCapture();

    /**
     * @return is this piece is in check of opponent's king
     */
    public boolean isKingInCheck(){
        for (Piece piece : getValidCapture()){
            if (piece instanceof King){
                return true;
            }
        }
        return false;
    }

    /**
     * @param target
     * @return whether it is valid moves
     */
    public boolean isValidMove(Position target) {
        return getValidMoves().contains(target);
    }

    /**
     * @param piece
     * @return whether it is valid capture
     */
    public boolean isValidCapture(Piece piece) {
        return getValidCapture().contains(piece);
    }


    /**
     * @return true if there is no moves available
     */
    public boolean noMove() {
        return getValidMoves().isEmpty() && getValidCapture().isEmpty();
    }

    /**
     * @param x
     * @return array of x including negative x
     */
    protected int[] getArray(int x) {
        int drs[];

        if (x == 0) {
            drs = new int[]{0};

        } else {

            drs = new int[]{x, -x};
        }
        return drs;

    }

    public static final int EMPTY = 0;
    public static final int KING = 1;
    public static final int QUEEN = 2;
    public static final int BISHOP = 3;
    public static final int KNIGHT = 4;
    public static final int PAWN = 5;
    public static final int ROOK = 6;


    public static final int LEAPER = 7;
    public static final int RIDER = 8;
    public static final int CAMEL = 9;
    public static final int ZEBRARIDER = 10;


    /**
     * @return the type of piece
     */
    public abstract int getType();

    @Override
    public int compareTo(Piece o) {
        if (position.equals(o.position)) {
            return Integer.compare(getType(), o.getType());
        }
        return position.compareTo(o.position);
    }
}
