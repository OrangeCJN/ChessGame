package Model;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Rider {


    public Queen(Model board, boolean firstPlayer, int row, int col) {
        super(board, firstPlayer, row, col, 0, 0);
    }

    public Queen(Model board, boolean firstPlayer) {
        this(board, firstPlayer, 0, 0);
    }

    @Override
    protected PairResult getMoveOrCapture() {

        List<Position> moves = new ArrayList<>();

        List<Piece> captures = new ArrayList<>();

        getMoveOrCapture(moves, captures, 1, 1);

        getMoveOrCapture(moves, captures, 1, 0);

        return new PairResult(moves, captures);
    }

    @Override
    public int getType() {
        return QUEEN;
    }
}
