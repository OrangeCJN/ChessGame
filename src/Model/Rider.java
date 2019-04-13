package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *  Rider piece
 */
public class Rider extends Piece{


    private int m;

    private int n;

    /**
     *
     * @param board
     * @param firstPlayer
     * @param row
     * @param col
     * @param m
     * @param n
     */
    public Rider(Model board, boolean firstPlayer, int row, int col, int m, int n) {
        super(board, firstPlayer, row, col);

        this.m = m;

        this.n = n;
    }

    /**
     *
     * @param x
     * @param y
     * @param moves
     * @param captures
     */
    private void getMoveOrCapture(int x, int y, List<Position> moves, List<Piece> captures)
    {
        int drs[] = getArray(x);

        int dcs[] = getArray(y);

        for (int dr : drs) {

            for (int dc : dcs) {

                for (int row = position.getRow() + dr, col = position.getCol() + dc; board.isInBoard(row, col); row += dr, col += dc) {

                    Piece piece = board.getPiece(row, col);

                    if (piece == null) {

                        moves.add(new Position(row, col));

                    } else{


                        if (isEnemy(piece) ) {

                            captures.add(piece);
                        }

                        break;

                    }
                }
            }
        }
    }


    /**
     *
     * @param moves
     * @param captures
     * @param x
     * @param y
     */
    protected void getMoveOrCapture(List<Position> moves, List<Piece> captures, int x, int y) {

        getMoveOrCapture(x, y, moves, captures);

        if (x != y) {

            getMoveOrCapture(y, x, moves, captures);

        }
    }

    protected static class PairResult{
        private List<Position> moves;
        private List<Piece> captures;

        public PairResult(List<Position> moves, List<Piece> captures) {
            this.moves = moves;
            this.captures = captures;
        }

        public List<Position> getMoves() {
            return moves;
        }

        public void setMoves(List<Position> moves) {
            this.moves = moves;
        }

        public List<Piece> getCaptures() {
            return captures;
        }

        public void setCaptures(List<Piece> captures) {
            this.captures = captures;
        }

        @Override
        public String toString() {
            return "PairResult{" +
                    "moves=" + moves +
                    ", captures=" + captures +
                    '}';
        }
    }

    /**
     *
     * @return move and capture result
     */
    protected PairResult getMoveOrCapture() {

        List<Position> moves = new ArrayList<>();

        List<Piece> captures = new ArrayList<>();

        getMoveOrCapture(moves, captures, m, n);

        return new PairResult(moves, captures);
    }


    @Override
    public List<Position> getValidMoves() {

       return getMoveOrCapture().getMoves();

    }

    @Override
    public List<Piece> getValidCapture() {

        return getMoveOrCapture().getCaptures();
    }

    @Override
    public int getType() {
        return RIDER;
    }


}
