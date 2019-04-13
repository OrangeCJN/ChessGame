package Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class KingTest {

    @Test
    public void getValidMoves() {

        Model board = new Model(5,5);

        King king = new King(board,true, 3, 4);

        Queen queen = new Queen(board, true, 3, 3);

//        Knight blackKnight = new Knight(board, false, 3, 5);

        Bishop blackBishop = new Bishop(board, false, 4, 3);

        List<Position> valiMoves = king.getValidMoves();

        List<Position> expected = new ArrayList<>();



        expected.add(new Position(2, 3));

        expected.add(new Position(2, 4));

        expected.add(new Position(4, 4));



        System.out.println(valiMoves);

        assertEquals(new TreeSet<>(expected), new TreeSet<>(valiMoves));
    }

    @Test
    public void getValidCapture() {

        Model board = new Model();

        King king = new King(board,true, 3, 4);

        Queen queen = new Queen(board, true, 3, 3);

        Knight blackKnight = new Knight(board, false, 3, 5);

        Bishop blackBishop = new Bishop(board, false, 4, 5);

        List<Piece> validCapture = king.getValidCapture();

        List<Piece> expected = new ArrayList<>();

        expected.add(blackKnight);
        expected.add(blackBishop);

        assertEquals(new TreeSet<>(expected), new TreeSet<>(validCapture));

    }

    @Test
    public void isCheckMateFalse() {

        Model board = new Model();

        King king = new King(board,true, 3, 4);

        Queen queen = new Queen(board, true, 3, 3);

        Knight blackKnight = new Knight(board, false, 3, 5);

        Bishop blackBishop = new Bishop(board, false, 4, 5);

        assertEquals(false, king.isCheckMate());
    }

    @Test
    public void isCheckMateTrue() {

        Model board = new Model();

//        Model board = new Model();

        King wk = new King(board, true, 3, 5);

        King bk = new King(board, false, 3, 7);

        Rook br = new Rook(board, true, 7, 7);

        assertEquals(true, bk.isCheckMate());

        wk.moveTo(new Position(3, 4));
//
        assertEquals(false, bk.isCheckMate());
    }


}