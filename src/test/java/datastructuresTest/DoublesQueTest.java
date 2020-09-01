package datastructuresTest;

import datastructures.DoublesQue;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DoublesQueTest {
    DoublesQue que;

    @Before
    public void initialize() {
        que = new DoublesQue(4);
    }

    @Test
    public void standartConstructorExists() {
        que = new DoublesQue();
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
    public void isEmptyAfterContentsArePolled() {
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
        assertEquals(1, que.poll(),0.1);
    }

    @Test
    public void worksWhenTailIsBeforeHead() {
        que.add(1);
        que.add(2);
        que.add(3);
        que.add(4);
        assertEquals(1, que.poll(),0.1);
        assertEquals(2, que.poll(),0.1);
        que.add(5);
        que.add(6);
        assertEquals(3, que.poll(),0.1);
        assertEquals(4, que.poll(),0.1);
        assertEquals(5, que.poll(),0.1);
        assertEquals(6, que.poll(),0.1);

    }

    @Test
    public void queGrows() {
        que.add(1);
        que.add(2);
        que.add(3);
        que.add(4);
        que.add(5);
        assertEquals(1, que.poll(),0.1);
        assertEquals(2, que.poll(),0.1);
        assertEquals(3, que.poll(),0.1);
        assertEquals(4, que.poll(),0.1);
        assertEquals(5, que.poll(),0.1);
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
        assertEquals(3, que.poll(),0.1);
        assertEquals(4, que.poll(),0.1);
        assertEquals(5, que.poll(),0.1);
        assertEquals(6, que.poll(),0.1);
        assertEquals(7, que.poll(),0.1);
        assertEquals(8, que.poll(),0.1);
    }
}