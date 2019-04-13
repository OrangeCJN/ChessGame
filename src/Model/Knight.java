package Model;

/**
 *  Knight is a (2, 1) leaper
 */
public class Knight extends Leaper {

    public Knight(Model board, boolean firstPlayer, int row, int col) {
        super(board, firstPlayer, row, col, 2, 1);
    }

    public Knight(Model board, boolean firstPlayer) {
        this(board, firstPlayer, 0, 0);
    }

    @Override
    public int getType() {
        return KNIGHT;


    }
}
