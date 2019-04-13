package Model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BishopTest.class,
        ModelTest.class,
        KingTest.class,
        KnightTest.class,

        LeaperTest.class,

        PawnTest.class,

        PieceTest.class,

        QueenTest.class,

        RookTest.class,

        ZebraRiderTest.class


})
public class GameTest {
}
