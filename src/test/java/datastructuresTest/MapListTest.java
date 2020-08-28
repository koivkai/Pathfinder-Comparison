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
    public void addAndPollWork() {
        PathMap map = new PathMap();
        list.add(map);
        
    }
}