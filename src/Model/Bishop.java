package Model;

/**
 * Bishop is a (1, 1) rider
 */
public class Bishop extends Rider {
    public Bishop(Model board, boolean firstPlayer, int row, int col) {
        super(board, firstPlayer, row, col, 1, 1);
    }

    public Bishop(Model board, boolean firstPlayer) {
        this(board, firstPlayer, 0, 0);
    }

    @Override
    public int getType() {
        return BISHOP;

    }
}
