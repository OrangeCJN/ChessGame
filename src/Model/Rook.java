package Model;

/**
 *  Rook is a (1,0) rider
 */
public class Rook extends Rider
{
    public Rook(Model board, boolean firstPlayer, int row, int col) {
        super(board, firstPlayer, row, col, 1, 0);
    }

    public Rook(Model board, boolean firstPlayer) {
        this(board, firstPlayer, 0, 0);
    }

    @Override
    public int getType() {
        return ROOK;
    }
}
