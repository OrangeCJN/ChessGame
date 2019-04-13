package Model;

/**
 * chess board class
 */
public class Model {
    private int numRow;
    private int numCol;
    private Piece[][] grid;

    static Position positionFrom, positionTo;
    static Piece pieceFrom, pieceTo;

    /**
     * @param numRow number of rows
     * @param numCol number of columns
     */
    public Model(int numRow, int numCol) {
        this.numRow = numRow;
        this.numCol = numCol;
        init();
    }

    /**
     * @param size board size
     */
    public Model(int size) {

        this(size, size);
    }

    /**
     * @return number of rows
     */
    public int getNumRow() {
        return numRow;
    }

    /**
     * @return number of columns
     */
    public int getNumCol() {
        return numCol;
    }

    public Model() {
        this(8);
    }

    public void init(){
        grid = new Piece[numRow][numCol];
        initBoard(this);
    }

    public void initCustom(){
        setHalfCustomBoard(this, true);
        setHalfCustomBoard(this, false);
    }

    private void setHalfCustomBoard(Model board, boolean firstPlayer){
        int rowSecond = firstPlayer ? 6 : 1;
        board.setPiece(new Camel(this, firstPlayer), rowSecond, 0);
        board.setPiece(new Camel(this, firstPlayer), rowSecond, 7);
        board.setPiece(new ZebraRider(this, firstPlayer), rowSecond, 1);
        board.setPiece(new ZebraRider(this, firstPlayer), rowSecond, 6);
    }

    private void setHalfBoard(Model board, boolean firstPlayer) {
        int row = firstPlayer ? 7 : 0;

        board.setPiece(new Rook(this, firstPlayer), row, 0);
        board.setPiece(new Rook(this, firstPlayer), row, 7);

        board.setPiece(new Knight(this, firstPlayer), row, 1);
        board.setPiece(new Knight(this, firstPlayer), row, 6);

        board.setPiece(new Bishop(this, firstPlayer), row, 2);
        board.setPiece(new Bishop(this, firstPlayer), row, 5);

        board.setPiece(new Queen(this, firstPlayer), row, 3);
        board.setPiece(new King(this, firstPlayer), row, 4);
    }

    /**
     * initialise the board to standard
     */
    private void initBoard(Model board) {
        setHalfBoard(board, true);
        setHalfBoard(board, false);
        for (int i = 0; i < 8; i++) {
            board.setPiece(new Pawn(this, false), 1, i);
            board.setPiece(new Pawn(this, true), 6, i);
        }
    }

    /**
     * @param row
     * @param col
     * @return whether on board
     */
    public boolean isInBoard(int row, int col) {
        return row >= 0 && row < numRow && col >= 0 && col < numCol;
    }

    /**
     * @param position
     * @return whether on board
     */
    public boolean isInBoard(Position position) {
        return isInBoard(position.getRow(), position.getCol());
    }

    /**
     * @param row
     * @param col
     * @return whether the position is empty
     */
    private boolean isEmpty(int row, int col) {
        return grid[row][col] == null;
    }


    boolean isEmpty(Position position) {
        return isEmpty(position.getRow(), position.getCol());
    }


    /**
     * clear cell
     *
     * @param position
     */
    void clear(Position position) {
        grid[position.getRow()][position.getCol()] = null;
    }

    /**
     * set piece in position
     *
     * @param piece
     * @param row
     * @param col
     */
    public void setPiece(Piece piece, int row, int col) {
        grid[row][col] = piece;
        if (piece != null) {
            piece.setPosition(new Position(row, col));
        }
    }

    /**
     * set piece in position
     *
     * @param piece
     * @param position
     */
    public void setPiece(Piece piece, Position position) {
        setPiece(piece, position.getRow(), position.getCol());
    }

    /**
     * restore last grid
     */
    public void undo(){
        setPiece(pieceFrom, positionFrom);
        setPiece(pieceTo, positionTo);
    }

    /**
     * @param row
     * @param col
     * @return piece in position
     */
    public Piece getPiece(int row, int col) {
        return grid[row][col];
    }

    /**
     * @param position
     * @return piece in position
     */
    public Piece getPiece(Position position) {
        return getPiece(position.getRow(), position.getCol());
    }

    public static final int WHITE = 1;
    public static final int BLACK = 0;
    public static final int NOT_END = 2;
    public static final int DRAW = 3;

    /**
     * @return whether in stalemate
     */
    public boolean isStalemate() {
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (grid[i][j] != null && !grid[i][j].noMove()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return which player is in checkmate
     * <p>
     * return NO_END if no one
     */
    public int isCheckMate() {
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (grid[i][j] != null && grid[i][j] instanceof King) {
                    King king = (King) grid[i][j];
                    if (king.isCheckMate()) {
                        if (king.isFirstPlayer()) {
                            return WHITE;
                        } else {
                            return BLACK;
                        }
                    }
                }
            }
        }
        return NOT_END;
    }

    /**
     * @return whether the king in check or not
     */
    public boolean isKingInCheck(){
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (grid[i][j] != null && grid[i][j] instanceof King) {
                    King king = (King) grid[i][j];
                    if (king.underThreaten()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return if there are two kings
     */
    private int checkKings(){
        boolean black = false, white = false;
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (grid[i][j] != null && grid[i][j] instanceof King) {
                    if (grid[i][j].isFirstPlayer()){
                        white = true;
                    } else {
                        black = true;
                    }
                }
            }
        }
        if (! white){
            return WHITE;
        } else if (! black){
            return BLACK;
        } else {
            return NOT_END;
        }
    }

    /**
     * @return winner
     * return NO_END, if not game over
     */
    public int getWinner() {
        int kingLack = checkKings();
        if (kingLack == WHITE){
            return BLACK;
        } else if (kingLack == BLACK){
            return WHITE;
        }

        if (isStalemate()) {
            return DRAW;
        }

        int res = isCheckMate();
        if (res == NOT_END) {
            return NOT_END;
        }
        if (res == BLACK) {
            return WHITE;
        } else {
            return BLACK;
        }
    }
}
