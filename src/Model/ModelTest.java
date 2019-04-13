package Model;

import static org.junit.Assert.*;

public class ModelTest {

    @org.junit.Test
    public void isStalemate() {

        Model board = new Model();

        King wk = new King(board, true, 3, 5);

        King bk = new King(board, false, 3, 7);

        Rook br = new Rook(board, true, 7, 7);

        assertEquals(false, board.isStalemate());
    }

    @org.junit.Test
    public void isCheckMate() {

        Model board = new Model();

        King wk = new King(board, true, 3, 5);

        King bk = new King(board, false, 3, 7);

        Rook br = new Rook(board, true, 7, 7);

        assertEquals(Model.BLACK, board.isCheckMate());

        board.setPiece(wk, 3, 4);
//
        assertEquals(Model.NOT_END, board.isCheckMate());
    }

    @org.junit.Test
    public void getWinner() {

        Model board = new Model();

        King wk = new King(board, true, 3, 5);

        King bk = new King(board, false, 3, 7);

        Rook br = new Rook(board, true, 7, 7);

        assertEquals(Model.WHITE, board.getWinner());

        board.setPiece(wk, 3, 4);

        assertEquals(Model.NOT_END, board.getWinner());

    }


}