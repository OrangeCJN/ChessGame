package Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class QueenTest {

    @Test
    public void getValidMoves() {

//        **B*
//        *K**
//        *BQ*
//        *b**

        Model board = new Model(4);

        new Bishop(board, true, 0, 2);

        new Bishop(board, true, 2, 1);

        King king = new King(board,true, 1, 1);

        Queen queen = new Queen(board, true, 2, 2);

        Bishop blackBishop = new Bishop(board, false, 3, 1);


        List<Position> valiMoves = queen.getValidMoves();

        List<Position> expected = new ArrayList<>();

        expected.add(new Position(2, 3));

        expected.add(new Position(3, 2));

        expected.add(new Position(1, 2));

        expected.add(new Position(3, 3));

        expected.add(new Position(1, 3));


        System.out.println(valiMoves);

        System.out.println(valiMoves.size());

        assertEquals(new TreeSet<>(expected), new TreeSet<>(valiMoves));

    }

    @Test
    public void getValidCapture() {
//        **B*
//        *K**
//        *BQ*
//        *b**

        Model board = new Model(4);

        new Bishop(board, true, 0, 2);

        new Bishop(board, true, 2, 1);

        King king = new King(board,true, 1, 1);

        Queen queen = new Queen(board, true, 2, 2);

        Bishop blackBishop = new Bishop(board, false, 3, 1);

        List<Piece> validCapture = queen.getValidCapture();

        List<Piece> expected = new ArrayList<>();

        expected.add(blackBishop);

        System.out.println(validCapture);

        assertEquals(new TreeSet<>(expected), new TreeSet<>(validCapture));


    }
}