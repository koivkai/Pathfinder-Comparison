package logicTest;

import static org.junit.Assert.*;
import org.junit.Test;
import datastructures.PathMap;
import logic.JPSdiagonal;

public class JPSdiagonalTest {
    PathMap testMap;
    JPSdiagonal jps;
    
    @Test
    public void findsPathNoObstacles() {
        jps = new JPSdiagonal();
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
        double result = jps.findPath(testMap, 4, 1, 4, 8);
        assertEquals(7, result, 0.01);
    }

    @Test
    public void findsPathNoObstaclesReverse() {
        jps = new JPSdiagonal();
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
        double result = jps.findPath(testMap, 4, 8, 4, 1);
        assertEquals(7, result, 0.01);
    }
    
    @Test
    public void findsPathTreeline() {
        jps = new JPSdiagonal();
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

        double result = jps.findPath(testMap, 4, 1, 4, 8);
        assertEquals(10.0710, result, 0.01);
    }
    
    @Test
    public void findsPathThreeTreelines() {
        jps = new JPSdiagonal();
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

        double result = jps.findPath(testMap, 1, 2, 1, 8);
        assertEquals(34, result, 0.01);
    }

    @Test
    public void findsPathThreeTreelinesReverse() {
        jps = new JPSdiagonal();
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

        double result = jps.findPath(testMap, 1, 8, 1, 2);
        assertEquals(34, result, 0.01);
    }

    @Test
    public void noPathExits() {
        jps = new JPSdiagonal();
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

        double result = jps.findPath(testMap, 4, 1, 4, 8);
        assertEquals(-1, result, 0.01);
    }
    
    @Test
    public void diagonalLines() {
        jps = new JPSdiagonal();
        char[][] arrayMap = new char[10][10];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','.','T','.','.','.','.','.','.','@'};
        char[] lineTre = {'@','.','.','T','.','.','T','.','.','@'};
        char[] lineFou = {'@','.','.','.','T','.','.','T','.','@'};
        char[] lineFiv = {'@','.','.','.','.','T','.','.','T','@'};
        char[] lineSix = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineSev = {'@','.','.','T','.','.','.','.','.','@'};
        char[] lineEig = {'@','.','.','.','T','.','.','.','.','@'};
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

        double result = jps.findPath(testMap, 8, 3, 2, 8);
        assertEquals(15.8284, result, 0.1);
    }
    
    @Test
    public void diagonalLinesReverse() {
        jps = new JPSdiagonal();
        char[][] arrayMap = new char[10][10];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','.','T','.','.','.','.','.','.','@'};
        char[] lineTre = {'@','.','.','T','.','.','T','.','.','@'};
        char[] lineFou = {'@','.','.','.','T','.','.','T','.','@'};
        char[] lineFiv = {'@','.','.','.','.','T','.','.','T','@'};
        char[] lineSix = {'@','.','.','.','.','.','.','.','.','@'};
        char[] lineSev = {'@','.','.','T','.','.','.','.','.','@'};
        char[] lineEig = {'@','.','.','.','T','.','.','.','.','@'};
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

        double result = jps.findPath(testMap, 2, 8, 8, 3);
        assertEquals(15.8284, result, 0.1);
    }

    @Test
    public void maze() {
        jps = new JPSdiagonal();
        char[][] arrayMap = new char[10][10];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','T','.','.','.','T','.','.','.','@'};
        char[] lineTre = {'@','.','.','T','.','T','.','.','.','@'};
        char[] lineFou = {'@','.','T','.','.','T','.','.','.','@'};
        char[] lineFiv = {'@','.','T','.','.','T','.','.','T','@'};
        char[] lineSix = {'@','.','T','T','.','.','T','.','.','@'};
        char[] lineSev = {'@','.','.','.','T','.','.','.','.','@'};
        char[] lineEig = {'@','.','.','.','T','.','T','.','T','@'};
        char[] lineNin = {'@','.','.','.','T','.','.','.','.','@'};
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

        double result = jps.findPath(testMap, 7, 2, 2, 7);
        assertEquals(21.4142, result, 0.1);
    }

    @Test
    public void mazeReverse() {
        jps = new JPSdiagonal();
        char[][] arrayMap = new char[10][10];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','T','.','.','.','T','.','.','.','@'};
        char[] lineTre = {'@','.','.','T','.','T','.','.','.','@'};
        char[] lineFou = {'@','.','T','.','.','T','.','.','.','@'};
        char[] lineFiv = {'@','.','T','.','.','T','.','.','T','@'};
        char[] lineSix = {'@','.','T','T','.','.','T','.','.','@'};
        char[] lineSev = {'@','.','.','.','T','.','.','.','.','@'};
        char[] lineEig = {'@','.','.','.','T','.','T','.','T','@'};
        char[] lineNin = {'@','.','.','.','T','.','.','.','.','@'};
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

        double result = jps.findPath(testMap, 2, 7, 7, 2);
        assertEquals(21.4142, result, 0.1);
    }

    @Test
    public void asd() {
        jps = new JPSdiagonal();
        char[][] arrayMap = new char[10][14];
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','.','.','.','.','.','.','.','.','.','.','.','.','@'};
        char[] lineTre = {'@','.','.','.','.','.','.','.','.','.','.','.','.','@'};
        char[] lineFou = {'@','.','.','.','.','.','.','.','.','.','.','.','.','@'};
        char[] lineFiv = {'@','.','.','.','.','.','.','.','.','.','.','.','.','@'};
        char[] lineSix = {'@','.','.','.','.','.','.','.','T','T','.','T','T','@'};
        char[] lineSev = {'@','.','.','.','.','.','T','T','T','T','T','T','T','@'};
        char[] lineEig = {'@','.','.','.','.','T','T','T','T','T','@','T','T','@'};
        char[] lineNin = {'@','.','.','.','T','T','T','T','T','T','T','T','T','@'};
        char[] lineTen = {'@','@','@','@','@','@','@','@','@','@','@','@','@','@'};
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

        double result = jps.findPath(testMap, 4, 7, 7, 2);
        assertEquals(6.2426, result, 0.1);
    }
    
}