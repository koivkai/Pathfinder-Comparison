package datastructuresTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datastructures.MyDeque;

public class MyDequeTest {
    MyDeque que;

    @Before
    public void initialize() {
        que = new MyDeque(4);
    }

    @Test
    public void standartConstructorExists() {
        que = new MyDeque();
        assertNotNull(que);
    }

    @Test
    public void isEmptyWhenCreated() {
        assertEquals(true, que.isEmpty());
    }

    @Test
    public void isNotEmptyAfterStuffAdded() {
        que.add(31415);
        assertEquals(false, que.isEmpty());
    }

    @Test
    public void isNotEmptyAfterContentsArePolled() {
        que.add(1);
        que.add(2);
        que.add(3);
        que.poll();
        que.poll();
        que.poll();
        assertEquals(true, que.isEmpty());
    }


    @Test
    public void addAndPollWork() {
        que.add(1);
        assertEquals(1, que.poll());
    }

    @Test
    public void worksWhenTailIsBeforeHead() {
        que.add(1);
        que.add(2);
        que.add(3);
        que.add(4);
        assertEquals(1, que.poll());
        assertEquals(2, que.poll());
        que.add(5);
        que.add(6);
        assertEquals(3, que.poll());
        assertEquals(4, que.poll());
        assertEquals(5, que.poll());
        assertEquals(6, que.poll());

    }

    @Test
    public void queGrows() {
        que.add(1);
        que.add(2);
        que.add(3);
        que.add(4);
        que.add(5);
        assertEquals(1, que.poll());
        assertEquals(2, que.poll());
        assertEquals(3, que.poll());
        assertEquals(4, que.poll());
        assertEquals(5, que.poll());
    }

    @Test
    public void queGrowsWhenTailIsBeforeHead() {
        que.add(1);
        que.add(2);
        que.add(3);
        que.add(4);
        que.poll();
        que.poll();
        que.add(5);
        que.add(6);
        que.add(7);
        que.add(8);
        assertEquals(3, que.poll());
        assertEquals(4, que.poll());
        assertEquals(5, que.poll());
        assertEquals(6, que.poll());
        assertEquals(7, que.poll());
        assertEquals(8, que.poll());
    }

    @Test
    public void toStringWorks() {
        que.add(1);
        que.add(2);
        que.add(3);
        que.add(4);
        System.out.println(que);
        assertEquals("MyDeque [1, 2, 3, 4]",que.toString());
        
    }
    
}