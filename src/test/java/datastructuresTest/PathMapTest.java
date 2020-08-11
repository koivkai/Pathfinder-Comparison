package datastructuresTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datastructures.PathMap;

public class PathMapTest {
    PathMap map;

    @Before
    public void setUpMap() {  
        map = new PathMap(basicMap(), 10, 10);
    }

    @Test
    public void gettersAndSetters() {
        assertEquals(10, map.getHeight());
        assertEquals(10, map.getWidth());
        assertArrayEquals(basicMap(), map.getMap());    
        map.setMap(tinyArrayMap());
        map.setHeight(4);
        map.setWidth(4);
        assertArrayEquals(tinyArrayMap(), map.getMap());
        assertEquals(4, map.getHeight());
        assertEquals(4, map.getWidth());
    }

    @Test
    public void terrainAtWithinBounds() {
        assertEquals('.', map.terrainAt(1, 1));
    }

    @Test
    public void terrainOutOfBounds() {
        assertEquals('@', map.terrainAt(-1, 1));
        assertEquals('@', map.terrainAt(1, -1));
        assertEquals('@', map.terrainAt(1000, 1));
        assertEquals('@', map.terrainAt(1, 1000));
    }

    private static char[][] tinyArrayMap() {
        char[][] tinyArrayMap = new char[4][4];
        char[] first = {'@','@','@','@'};
        char[] second = {'@','.','.','@'};
        char[] third = {'@','.','.','@'};
        char[] fourth = {'@','@','@','@'};
        tinyArrayMap[0] = first;
        tinyArrayMap[1] = second;
        tinyArrayMap[2] = third;
        tinyArrayMap[3] = fourth;
        return tinyArrayMap;
    }

    private static char[][] basicMap() {
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
        return arrayMap;
    }

    
}