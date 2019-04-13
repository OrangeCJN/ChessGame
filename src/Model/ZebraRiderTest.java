package Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ZebraRiderTest {

    @Test
    public void getMoveOrCapture() {

        Model board = new Model(4);

        ZebraRider zebraRider = new ZebraRider(board, true, 0, 2);

        new Bishop(board, true, 2, 1);

        King king = new King(board,true, 1, 1);

        Queen queen = new Queen(board, true, 2, 2);

        Bishop blackBishop = new Bishop(board, false, 3, 1);

        Rider.PairResult pairResult = zebraRider.getMoveOrCapture();


        System.out.println(pairResult);

//        List<Position> valiMoves = queen.getValidMoves();
//
        List<Position> expectedMoves = new ArrayList<>();

        expectedMoves.add(new Position(3, 0));

        assertEquals(expectedMoves, pairResult.getMoves());

        assertTrue(pairResult.getCaptures().isEmpty());

    }
}