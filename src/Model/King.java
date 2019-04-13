package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * King is a combination of (1,1)-leaper abd (1,0) leaper
 */
public class King extends Leaper {


    public King(Model board, boolean firstPlayer, int row, int col) {
        super(board, firstPlayer, row, col, 0, 0);
    }


    public King(Model board, boolean firstPlayer) {
        this(board, firstPlayer, 0, 0);
    }

    protected List<Position> getNeighbors() {

        List<Position> positions = new ArrayList<>();

        getNeighbors(1, 1, positions);

        getNeighbors(1, 0, positions);

        return positions;
    }


    /**
     * @return whether under threaten
     */
    public boolean underThreaten() {

        for (int i = 0; i < board.getNumRow(); i++) {

            for (int j = 0; j < board.getNumCol(); j++) {

                Piece piece = board.getPiece(i, j);

                if (piece != null && isEnemy(piece)) {

                    if (piece.isValidCapture(this)) {

                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * @return whether kin can scape
     */
    private boolean canEscape() {

        int row = position.getRow();

        int col = position.getCol();

        boolean res = false;

        for (Position position : getValidMoves()) {

            moveTo(position);

            res = underThreaten();

            board.setPiece(this, row, col);

            board.setPiece(null, position.getRow(), position.getCol());

            if (!res) {

                return true;
            }
        }

        for (Piece piece : getValidCapture()) {

            int trow = piece.getPosition().getRow();

            int tcol = piece.getPosition().getCol();

            moveTo(piece.getPosition());

            res = underThreaten();

            board.setPiece(this, row, col);

            board.setPiece(piece, trow, tcol);

            if (!res) {

                return true;
            }
        }

        return false;
    }

    /**
     * @return whether it is checkmate
     */
    public boolean isCheckMate() {

        return underThreaten() && !canEscape();

    }

    @Override
    public int getType() {
        return KING;

    }
}
