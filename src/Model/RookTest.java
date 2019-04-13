package Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RookTest {

    @Test
    public void getValidMoves() {


//        ****
//        ****
//        *KRQ
//        *k**

        Model board = new Model(4);

        Rook rook = new Rook(board, true, 2, 2);

        King king = new King(board,true, 2, 1);

        Queen queen = new Queen(board, true, 2, 3);

        Knight blackKnight = new Knight(board, false, 3, 1);

        List<Position> valiMoves = rook.getValidMoves();

        List<Position> expected = new ArrayList<>();

        expected.add(new Position(3, 2));

        expected.add(new Position(1, 2));

        expected.add(new Position(0, 2));

        System.out.println(valiMoves);

        assertEquals(expected, valiMoves);
    }

    @Test
    public void getValidCapture() {

//        **r*
//        ****
//        *KRQ
//        *k**

        Model board = new Model(4);

        Rook rook = new Rook(board, true, 2, 2);

        King king = new King(board,true, 2, 1);

        Queen queen = new Queen(board, true, 2, 3);

        Knight blackKnight = new Knight(board, false, 3, 1);

        Rook blackRook = new Rook(board, false, 0, 2);

        List<Piece> validCapture = rook.getValidCapture();

        List<Piece> expected = new ArrayList<>();

        expected.add(blackRook);

        System.out.println(validCapture);

        assertEquals(expected, validCapture);
    }
}