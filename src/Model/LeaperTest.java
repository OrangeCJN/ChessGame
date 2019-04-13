package Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class LeaperTest {

    @Test
    public void getValidMoves() {

        Model board = new Model(8, 8);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Leaper leaper = new Leaper(board, true, 1, 1, 3, 2);

        King blackKing = new King(board, false, 3, 2);


        List<Position> valiMoves = leaper.getValidMoves();

        List<Position> expected = new ArrayList<>();

        expected.add(new Position(4, 3));

        expected.add(new Position(3, 4));


        assertEquals(new HashSet<>(expected), new HashSet<>(valiMoves));
    }

    @Test
    public void getValidCapture() {


        Model board = new Model(7, 9);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Leaper leaper = new Leaper(board, true, 1, 1, 3, 2);

        King blackKing = new King(board, false, 3, 2);


        List<Piece> validCapture = leaper.getValidCapture();

        List<Piece> expected = new ArrayList<>();


        assertEquals(expected, validCapture);


    }
}