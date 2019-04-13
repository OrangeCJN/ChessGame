package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PieceTest {


    @Test
    public void moveTo() {

//        ****b
//        *K***
//        *****
//        **k**

        Model board = new Model(4, 5);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Knight knight = new Knight(board, true, 1, 1);

        King blackKing = new King(board, false, 3, 2);

        assertEquals(false, knight.moveTo(new Position(0, 0)));

        assertEquals(false, knight.moveTo(new Position(0, 1)));

        assertEquals(false, knight.moveTo(new Position(-1, 1)));

        assertEquals(true, knight.moveTo(new Position(0, 3)));

        assertEquals(new Position(0, 3), knight.getPosition());

    }

    @Test
    public void isValidMoveOrCapture() {


//        ****b
//        *K***
//        *****
//        **k**

        Model board = new Model(4, 5);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Knight knight = new Knight(board, true, 1, 1);

        King blackKing = new King(board, false, 3, 2);


        assertTrue(knight.isValidMoveOrCapture(new Position(0, 3)));


        assertTrue(knight.isValidMoveOrCapture(new Position(3, 2)));


        assertFalse(knight.isValidMoveOrCapture(new Position(0, 4)));

    }


    @Test
    public void isValidMove() {

//        ****b
//        *K***
//        *****
//        **k**

        Model board = new Model(4, 5);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Knight knight = new Knight(board, true, 1, 1);

        King blackKing = new King(board, false, 3, 2);


        assertTrue(knight.isValidMove(new Position(3, 0)));


        assertFalse(knight.isValidMove(new Position(3, 2)));

    }

    @Test
    public void isValidCapture() {

//        ****b
//        *K***
//        *****
//        **k**

        Model board = new Model(4, 5);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Knight knight = new Knight(board, true, 1, 1);

        King blackKing = new King(board, false, 3, 2);


        assertFalse(knight.isValidCapture(blackBishop));


        assertTrue(knight.isValidCapture(blackKing));

    }

    @Test
    public void noMove() {

//        P***b
//        *K***
//        *****
//        **k**



        Model board = new Model(4, 5);

        Pawn pawn = new Pawn(board, true, 0, 0);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Knight knight = new Knight(board, true, 1, 1);

        King blackKing = new King(board, false, 3, 2);

        assertFalse(knight.noMove());

        assertFalse(blackKing.noMove());

        assertTrue(pawn.noMove());

    }
}