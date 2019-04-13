package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *  An (m,n)-leaper is a piece that moves by a fixed type of vector between its starting and destination squares.
 */
public class Leaper extends Piece {

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
    public Leaper(Model board, boolean firstPlayer, int row, int col, int m, int n) {

        super(board, firstPlayer, row, col);

        this.m = m;

        this.n = n;
    }

    /**
     * add neighbor positions
     * @param x
     * @param y
     * @param positions
     */
    protected void getNeighbors(int x, int y, List<Position> positions) {

        int drs[] = getArray(x);
        int dcs[] = getArray(y);

        for (int dr : drs) {

            for (int dc : dcs) {

                positions.add(new Position(position.getRow() + dr, (position.getCol() + dc)));

                if (dr != dc) {
                    positions.add(new Position(position.getRow() + dc, (position.getCol() + dr)));
                }
            }
        }

    }

    /**
     *
     * @return neighbor positions
     */
    protected List<Position> getNeighbors() {

        List<Position> positions = new ArrayList<>();

        getNeighbors(m, n, positions);

        return positions;
    }


    @Override
    public List<Position> getValidMoves() {

        List<Position> neighbors = getNeighbors();

        List<Position> results = new ArrayList<>();

        for (Position position : neighbors) {

            if (board.isInBoard(position) && board.isEmpty(position)) {

                results.add(position);
            }

        }

        return results;
    }

    @Override
    public List<Piece> getValidCapture() {


        List<Position> neighbors = getNeighbors();

        List<Piece> results = new ArrayList<>();

        for (Position position : neighbors) {


            if (board.isInBoard(position)) {

                Piece piece = board.getPiece(position);

                if (piece != null && isEnemy(piece)) {

                    results.add(piece);
                }
            }


        }

        return results;
    }

    @Override
    public int getType() {
        return LEAPER;
    }
}
