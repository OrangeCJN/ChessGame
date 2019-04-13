package Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PawnTest {


    @Test
    public void getValidMoves() {

//        ****b
//        *k***
//        **P**
//        **k**

        Model board = new Model(4, 5);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Knight blackKnight = new Knight(board, false, 1, 1);

        King blackKing = new King(board, false, 3, 2);

        Pawn pawn = new Pawn(board, true, 2, 2);


        List<Position> valiMoves = pawn.getValidMoves();

        List<Position> expected = new ArrayList<>();

        expected.add(new Position(1, 2));

        expected.add(new Position(0, 2));


        System.out.println(valiMoves);

        System.out.println(valiMoves.size());

        assertEquals(expected, valiMoves);
    }

    @Test
    public void getValidCapture() {


//        ****b
//        *k***
//        **P**
//        **k**

        Model board = new Model(4, 5);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Knight blackKnight = new Knight(board, false, 1, 1);

        King blackKing = new King(board, false, 3, 2);

        Pawn pawn = new Pawn(board, true, 2, 2);


        List<Piece> validCapture = pawn.getValidCapture();

        List<Piece> expected = new ArrayList<>();

        expected.add(blackKnight);

        System.out.println(validCapture);

        assertEquals(expected, validCapture);


    }
}