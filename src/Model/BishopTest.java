package Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BishopTest {

    @Test
    public void getValidMoves() {

//        ****
//        *K**
//        **B*
//        *b**

        Model board = new Model(4);

        King king = new King(board,true, 1, 1);

        Bishop bishop = new Bishop(board, true, 2, 2);

        Bishop blackBishop = new Bishop(board, false, 3, 1);


        List<Position> valiMoves = bishop.getValidMoves();

        List<Position> expected = new ArrayList<>();

        expected.add(new Position(3, 3));

        expected.add(new Position(1, 3));


        System.out.println(valiMoves);

        assertEquals(expected, valiMoves);
    }

    @Test
    public void getValidCapture() {

        //        ****
//        *K**
//        **B*
//        *b**

        Model board = new Model(4);

        King king = new King(board,true, 1, 1);

        Bishop bishop = new Bishop(board, true, 2, 2);

        Bishop blackBishop = new Bishop(board, false, 3, 1);


        List<Piece> validCapture = bishop.getValidCapture();

        List<Piece> expected = new ArrayList<>();

        expected.add(blackBishop);

        System.out.println(validCapture);

        assertEquals(expected, validCapture);


    }
}