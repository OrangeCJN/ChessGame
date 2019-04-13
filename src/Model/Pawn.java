package Model;


import java.util.ArrayList;
import java.util.List;

/**
 *  Pawn piece
 */
public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(Model board, boolean firstPlayer, int row, int col) {
        super(board, firstPlayer, row, col);
    }

    public Pawn(Model board, boolean firstPlayer) {
        this(board, firstPlayer, 0, 0);
    }

    @Override
    public boolean moveTo(Position position) {
        boolean res = super.moveTo(position);

        if (res) {

            firstMove = false;
        }

        return res;
    }



    @Override
    public List<Position> getValidMoves() {

        List<Position> positions = new ArrayList<>();

        int dir = isFirstPlayer() ? -1 : 1;


        Position position = new Position(getRow() + dir, getCol());

        if (board.isInBoard(position) && board.isEmpty(position)) {

            positions.add(position);


            if(firstMove) {
                position = new Position(getRow() + dir + dir, getCol());

                if (board.isInBoard(position) && board.isEmpty(position)) {

                    positions.add(position);
                }
            }

        }


        return positions;

    }

    @Override
    public List<Piece> getValidCapture() {

        List<Piece> pieces = new ArrayList<>();


        int dir = isFirstPlayer() ? -1 : 1;

        Position position = new Position(getRow() + dir, getCol() - 1);

        if (board.isInBoard(position))
        {
            Piece piece = board.getPiece(position);

            if (piece != null && isEnemy(piece)) {

                pieces.add(piece);
            }
        }

        position = new Position(getRow() + dir, getCol() + 1);

        if (board.isInBoard(position))
        {
            Piece piece = board.getPiece(position);

            if (piece != null && isEnemy(piece)) {

                pieces.add(piece);
            }
        }

        return pieces;

    }

    @Override
    public int getType() {
        return PAWN;

    }
}
