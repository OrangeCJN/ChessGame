package Model;

/**
 * Custom Chess Piece. Camel is a (3,1) leaper
 */
public class Camel extends Leaper {


    public Camel(Model board, boolean firstPlayer, int row, int col) {
        super(board, firstPlayer, row, col, 3, 1);
    }

    public Camel(Model board, boolean firstPlayer) {
        this(board, firstPlayer, 0, 0);
    }


    @Override
    public int getType() {

        return CAMEL;


    }
}