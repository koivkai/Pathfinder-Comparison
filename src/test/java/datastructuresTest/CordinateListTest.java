package datastructuresTest;

import datastructures.Cordinate;
import datastructures.CordinateList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CordinateListTest {
    CordinateList list;

    @Before
    public void createList() {
        list = new CordinateList();
    }

    @Test
    public void addAndGetWork() {
        Cordinate cordinate = new Cordinate(0,0);
        list.add(cordinate);
        assertEquals(cordinate, list.get(0));
    }

    @Test
    public void cantGetNegativePos() {
        assertEquals(null, list.get(-1));
    }

    @Test
    public void cantGetPosThatsNotBeenAdded() {
        Cordinate cordinate = new Cordinate(0,0);
        list.add(cordinate);
        assertEquals(null, list.get(50));
    }

    @Test
    public void listGrows() {
        list = new CordinateList(1);
        Cordinate cordinate = new Cordinate(0,0);
        list.add(cordinate);
        Cordinate cordinate2 = new Cordinate(1,1);
        list.add(cordinate2);
        assertEquals(cordinate, list.get(0));
        assertEquals(cordinate2, list.get(1));
    }

    public void sizeWorks() {
        Cordinate cordinate = new Cordinate(0,0);
        list.add(cordinate);
        assertEquals(1, list.size());
    }
}