package datastructuresTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import datastructures.MapList;
import datastructures.PathMap;

public class MapListTest {

    MapList list;

    @Before
    public void createList() {
        list = new MapList();
    }

    @Test
    public void addAndGetWork() {
        PathMap map = new PathMap();
        list.add(map);
        assertEquals(map, list.get(0));
    }

    @Test
    public void cantGetNegativePos() {
        PathMap map = new PathMap();
        list.add(map);
        assertEquals(null, list.get(-1));
    }

    @Test
    public void cantGetPosThatsNotBeenAdded() {
        PathMap map = new PathMap();
        list.add(map);
        assertEquals(null, list.get(50));
    }

    @Test
    public void listGrows() {
        list = new MapList(1);
        PathMap map = new PathMap();
        list.add(map);
        PathMap map2 = new PathMap();
        list.add(map2);
        assertEquals(map, list.get(0));
        assertEquals(map2, list.get(1));
    }

    public void sizeWorks() {
        PathMap map = new PathMap();
        list.add(map);
        assertEquals(1, list.size());
    }
}