package Model;

/**
 * ZebraRider is a (2,3) rider
 */
public class ZebraRider extends Rider{

    public ZebraRider(Model board, boolean firstPlayer, int row, int col) {
        super(board, firstPlayer, row, col, 2, 3);
    }


    public ZebraRider(Model board, boolean firstPlayer) {
        this(board, firstPlayer, 0, 0);
    }

    @Override
    public int getType() {
        return ZEBRARIDER;
    }
}
