package Model;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class KnightTest {

    @Test
    public void getValidMoves() {

//        ****b
//        *K***
//        *****
//        **k**

        Model board = new Model(4, 5);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Knight knight = new Knight(board, true, 1, 1);

        King blackKing = new King(board, false, 3, 2);


        List<Position> valiMoves = knight.getValidMoves();

        List<Position> expected = new ArrayList<>();

        expected.add(new Position(3, 0));

        expected.add(new Position(2, 3));

        expected.add(new Position(0, 3));

        System.out.println(valiMoves);

        System.out.println(valiMoves.size());

        assertEquals(new HashSet<>(expected), new HashSet<>(valiMoves));
    }

    @Test
    public void getValidCapture() {


//        ****b
//        *K***
//        *****
//        **k**

        Model board = new Model(4, 5);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Knight knight = new Knight(board, true, 1, 1);

        King blackKing = new King(board, false, 3, 2);


        List<Piece> validCapture = knight.getValidCapture();

        List<Piece> expected = new ArrayList<>();

        expected.add(blackKing);

        System.out.println(validCapture);

        assertEquals(expected, validCapture);


    }
}