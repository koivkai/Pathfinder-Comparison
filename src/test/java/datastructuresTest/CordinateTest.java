package datastructuresTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import datastructures.Cordinate;

public class CordinateTest {
    Cordinate c1;
    Cordinate c2;
    Cordinate c3;
    Cordinate c4;
    Cordinate sameAsc1;


    @Before 
    public void setup() {
        c1 = new Cordinate(1,1,1);
        c2 = new Cordinate(2,1,1);
        c3 = new Cordinate(1,2,1);
        c4 = new Cordinate(1,1,2);
        sameAsc1 = new Cordinate(1,1,1);
    }

    @Test
    public void equalsWorks() {
        assertTrue(c1.equals(sameAsc1));
        assertFalse(c1.equals(c2));
        assertFalse(c1.equals(c3));
        assertFalse(c1.equals(c4));
        assertFalse(c1.equals(null));
        String s = "not a cordinate";
        assertFalse(c1.equals(s));
    }

    @Test
    public void compareToWorks() {
        
    }
}