package datastructuresTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import datastructures.Cordinate;
import datastructures.CordinateQue;

public class CordinateQueTest {
    CordinateQue que;

    @Before
    public void createQue() {
        this.que = new CordinateQue(4);
    }

    @Test
    public void emptyConstructorWorks() {
        que = new CordinateQue();
        assertNotNull(que);
    }

    @Test
    public void isEmptyWhenCreated() {
        assertEquals(true, que.isEmpty());
    }
    
    @Test
    public void isNotEmptyAfterOneAdded() {
        que.add(new Cordinate(1,1));
        assertEquals(false, que.isEmpty());
    }

    @Test
    public void isEmptyAfterContentsArePolled() {
        que.add(new Cordinate(1,1));
        que.add(new Cordinate(2,2));
        que.add(new Cordinate(3,3));
        que.poll();
        que.poll();
        que.poll();
        assertEquals(true, que.isEmpty());
    }

    @Test
    public void addAndPollWork() {
        Cordinate c = new Cordinate(1,1);
        que.add(c);
        assertEquals(c, que.poll());
    }

    @Test
    public void worksWhenTailIsBeforeHead() {
        Cordinate c1 = new Cordinate(1,1);
        Cordinate c2 = new Cordinate(2,2);
        Cordinate c3 = new Cordinate(3,3);
        Cordinate c4 = new Cordinate(4,4);
        que.add(c1);
        que.add(c2);
        que.add(c3);
        que.add(c4);
        assertEquals(c1, que.poll());
        assertEquals(c2, que.poll());
        Cordinate c5 = new Cordinate(5,5);
        Cordinate c6 = new Cordinate(6,6);
        que.add(c5);
        que.add(c6);
        assertEquals(c3, que.poll());
        assertEquals(c4, que.poll());
        assertEquals(c5, que.poll());
        assertEquals(c6, que.poll());

    }

    @Test
    public void queGrows() {
        Cordinate c1 = new Cordinate(1,1);
        Cordinate c2 = new Cordinate(2,2);
        Cordinate c3 = new Cordinate(3,3);
        Cordinate c4 = new Cordinate(4,4);
        que.add(c1);
        que.add(c2);
        que.add(c3);
        que.add(c4);
        Cordinate c5 = new Cordinate(5,5);
        que.add(c5);
        assertEquals(c1, que.poll());
        assertEquals(c2, que.poll());
        assertEquals(c3, que.poll());
        assertEquals(c4, que.poll());
        assertEquals(c5, que.poll());
    }

    @Test
    public void queGrowsWhenTailIsBeforeHead() {
        Cordinate c1 = new Cordinate(1,1);
        Cordinate c2 = new Cordinate(2,2);
        Cordinate c3 = new Cordinate(3,3);
        Cordinate c4 = new Cordinate(4,4);
        que.add(c1);
        que.add(c2);
        que.add(c3);
        que.add(c4);
        que.poll();
        que.poll();
        Cordinate c5 = new Cordinate(5,5);
        Cordinate c6 = new Cordinate(6,6);
        Cordinate c7 = new Cordinate(7,7);
        Cordinate c8 = new Cordinate(8,8);
        que.add(c5);
        que.add(c6);
        que.add(c7);
        que.add(c8);
        assertEquals(c3, que.poll());
        assertEquals(c4, que.poll());
        assertEquals(c5, que.poll());
        assertEquals(c6, que.poll());
        assertEquals(c7, que.poll());
        assertEquals(c8, que.poll());
    }

    @Test
    public void toStringWorks() {
        Cordinate c1 = new Cordinate(1,1);
        Cordinate c2 = new Cordinate(2,2);
        Cordinate c3 = new Cordinate(3,3);
        Cordinate c4 = new Cordinate(4,4);
        que.add(c1);
        que.add(c2);
        que.add(c3);
        que.add(c4);
        assertEquals("MyDeque [Cordinate [colum=1, lineNumber=1], Cordinate [colum=2, lineNumber=2], Cordinate [colum=3, lineNumber=3], Cordinate [colum=4, lineNumber=4]]",que.toString());
        
    }
}