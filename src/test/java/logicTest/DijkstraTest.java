package logicTest;

import static org.junit.Assert.*;
import org.junit.Test;
import datastructures.PathMap;
import logic.Dijkstra;

public class DijkstraTest {
    PathMap testMap;
    Dijkstra dijkstra;
    
    @Test
    public void findsPathNoObstacles() {
        dijkstra = new Dijkstra();
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
        double result = dijkstra.findPath(testMap, 4, 1, 4, 8);
        assertEquals(7, result, 0.1);
    }
        
    @Test
    public void findsPathTreeline() {
        dijkstra = new Dijkstra();
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

        double result = dijkstra.findPath(testMap, 4, 1, 4, 8);
        assertEquals(10.0710, result, 0.01);
    }


    @Test
    public void findsPathThreeTreelines() {
        dijkstra = new Dijkstra();
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

        double result = dijkstra.findPath(testMap, 1, 2, 1, 8);
        assertEquals(34, result, 0.1);
    }

    @Test
    public void noPathExits() {
        dijkstra = new Dijkstra();
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

        double result = dijkstra.findPath(testMap, 4, 1, 4, 8);
        assertEquals(-1, result, 0.1);
    }
    
    @Test
    public void diagonalLines() {
        dijkstra = new Dijkstra();
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

        double result = dijkstra.findPath(testMap, 8, 3, 2, 8);
        assertEquals(15.8284, result, 0.1);
    }
    @Test
    public void diagonalLinesReverse() {
        dijkstra = new Dijkstra();
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

        double result = dijkstra.findPath(testMap, 2, 8, 8, 3);
        assertEquals(15.8284, result, 0.1);
    }
    /*
        char[] lineOne = {'@','@','@','@','@','@','@','@','@','@'};
        char[] lineTwo = {'@','.','T','.','.','X','X','X','.','@'};
        char[] lineTre = {'@','.','.','T','.','X','T','.','G','@'};
        char[] lineFou = {'@','.','.','.','T','X','X','T','.','@'};
        char[] lineFiv = {'@','.','.','.','.','T','X','.','T','@'};
        char[] lineSix = {'@','.','X','X','X','X','X','.','.','@'};
        char[] lineSev = {'@','.','X','T','.','.','.','.','.','@'};
        char[] lineEig = {'@','.','X','.','T','.','.','.','.','@'};
        char[] lineNin = {'@','.','.','S','.','T','.','.','.','@'};
        char[] lineTen = {'@','@','@','@','@','@','@','@','@','@'};
    */

    
}