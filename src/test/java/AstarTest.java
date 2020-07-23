import static org.junit.Assert.*;
import org.junit.Test;

import logic.Astar;

public class AstarTest {


    @Test
    public void isAstar() {
        Astar testStar = new Astar();
        assertEquals("yes", testStar.isAstar());
    }
}