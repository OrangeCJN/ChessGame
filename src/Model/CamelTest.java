package Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CamelTest {


    @Test
    public void getValidMoves() {

//        ****b
//        *K***
//        *****
//        **k**

        Model board = new Model(4, 5);

        Bishop blackBishop = new Bishop(board, true, 0, 4);

        Camel camel = new Camel(board, true, 1, 1);

        King blackKing = new King(board, false, 3, 2);


        List<Position> valiMoves = camel.getValidMoves();

        List<Position> expected = new ArrayList<>();

        expected.add(new Position(2, 4));


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

        Camel camel = new Camel(board, true, 1, 1);

        King blackKing = new King(board, false, 3, 2);


        List<Piece> validCapture = camel.getValidCapture();

        List<Piece> expected = new ArrayList<>();


        System.out.println(validCapture);

        assertEquals(expected, validCapture);


    }
}
