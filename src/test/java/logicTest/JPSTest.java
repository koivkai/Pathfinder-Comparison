package logicTest;

import static org.junit.Assert.*;
import org.junit.Test;
import datastructures.PathMap;
import logic.JPS;

public class JPSTest {
    PathMap testMap;
    JPS jps;
    
    @Test
    public void findsPathNoObstacles() {
        jps = new JPS();
        char[][] arrayMap = new char[10][10];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineTre = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineFou = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineFiv = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineSix = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineSev = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineEig = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineNin = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineTen = {'@','@','@','@','@','@','@','@','@','@'};
        arrayMap[0] = lineOne;
        arrayMap[1] = lineTwo;
        arrayMap[2] = lineTre;
        arrayMap[3] = lineFou;
        arrayMap[4] = lineFiv;
        arrayMap[5] = lineSix;
        arrayMap[6] = lineSev;
        arrayMap[7] = lineEig;
        arrayMap[8] = lineNin;
        arrayMap[9] = lineTen;

        testMap = new PathMap(arrayMap, 10, 10);
        int result = jps.findPath(testMap, 4, 1, 4, 8);
        assertEquals(7, result);
    }

    @Test
    public void findsPathNoObstaclesReverse() {
        jps = new JPS();
        char[][] arrayMap = new char[10][10];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineTre = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineFou = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineFiv = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineSix = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineSev = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineEig = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineNin = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineTen = {'@','@','@','@','@','@','@','@','@','@'};
        arrayMap[0] = lineOne;
        arrayMap[1] = lineTwo;
        arrayMap[2] = lineTre;
        arrayMap[3] = lineFou;
        arrayMap[4] = lineFiv;
        arrayMap[5] = lineSix;
        arrayMap[6] = lineSev;
        arrayMap[7] = lineEig;
        arrayMap[8] = lineNin;
        arrayMap[9] = lineTen;

        testMap = new PathMap(arrayMap, 10, 10);
        int result = jps.findPath(testMap, 4, 8, 4, 1);
        assertEquals(7, result);
    }
    
    @Test
    public void findsPathTreeline() {
        jps = new JPS();
        char[][] arrayMap = new char[10][10];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineTre = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineFou = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineFiv = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineSix = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineSev = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineEig = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineNin = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineTen = {'@','@','@','@','@','@','@','@','@','@'};
        arrayMap[0] = lineOne;
        arrayMap[1] = lineTwo;
        arrayMap[2] = lineTre;
        arrayMap[3] = lineFou;
        arrayMap[4] = lineFiv;
        arrayMap[5] = lineSix;
        arrayMap[6] = lineSev;
        arrayMap[7] = lineEig;
        arrayMap[8] = lineNin;
        arrayMap[9] = lineTen;

        PathMap testMap = new PathMap(arrayMap, 10, 10);

        int result = jps.findPath(testMap, 4, 1, 4, 8);
        assertEquals(13, result);
    }

    @Test
    public void findsPathThreeTreelines() {
        jps = new JPS();
        char[][] arrayMap = new char[10][10];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','.','.','T','.','.','.','T','.','@'};
        char[] lineTre = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineFou = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineFiv = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineSix = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineSev = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineEig = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineNin = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineTen = {'@','@','@','@','@','@','@','@','@','@'};
        arrayMap[0] = lineOne;
        arrayMap[1] = lineTwo;
        arrayMap[2] = lineTre;
        arrayMap[3] = lineFou;
        arrayMap[4] = lineFiv;
        arrayMap[5] = lineSix;
        arrayMap[6] = lineSev;
        arrayMap[7] = lineEig;
        arrayMap[8] = lineNin;
        arrayMap[9] = lineTen;

        PathMap testMap = new PathMap(arrayMap, 10, 10);

        int result = jps.findPath(testMap, 1, 2, 1, 8);
        assertEquals(34, result);
    }

    @Test
    public void findsPathThreeTreelinesReverse() {
        jps = new JPS();
        char[][] arrayMap = new char[10][10];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','.','.','T','.','.','.','T','.','@'};
        char[] lineTre = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineFou = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineFiv = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineSix = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineSev = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineEig = {'@','.','.','T','.','T','.','T','.','@'};
        char[] lineNin = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineTen = {'@','@','@','@','@','@','@','@','@','@'};
        arrayMap[0] = lineOne;
        arrayMap[1] = lineTwo;
        arrayMap[2] = lineTre;
        arrayMap[3] = lineFou;
        arrayMap[4] = lineFiv;
        arrayMap[5] = lineSix;
        arrayMap[6] = lineSev;
        arrayMap[7] = lineEig;
        arrayMap[8] = lineNin;
        arrayMap[9] = lineTen;

        PathMap testMap = new PathMap(arrayMap, 10, 10);

        int result = jps.findPath(testMap, 1, 8, 1, 2);
        assertEquals(34, result);
    }

    @Test
    public void noPathExits() {
        jps = new JPS();
        char[][] arrayMap = new char[10][10];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineTre = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineFou = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineFiv = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineSix = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineSev = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineEig = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineNin = {'@','.','.','.','.','T','.','.','.','@'};
        char[] lineTen = {'@','@','@','@','@','@','@','@','@','@'};
        arrayMap[0] = lineOne;
        arrayMap[1] = lineTwo;
        arrayMap[2] = lineTre;
        arrayMap[3] = lineFou;
        arrayMap[4] = lineFiv;
        arrayMap[5] = lineSix;
        arrayMap[6] = lineSev;
        arrayMap[7] = lineEig;
        arrayMap[8] = lineNin;
        arrayMap[9] = lineTen;

        PathMap testMap = new PathMap(arrayMap, 10, 10);

        int result = jps.findPath(testMap, 4, 1, 4, 8);
        assertEquals(-1, result);
    }
    
}