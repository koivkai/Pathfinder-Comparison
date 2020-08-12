package datastructuresTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import datastructures.MinHeap;

public class MinHeapTest {
    MinHeap heap;
    
    @Before
    public void createHeap() {
        heap = new MinHeap();
    }

    @Test
    public void addAndPollWork() {
        heap.add(1);
        assertEquals(1, heap.poll());
    }

    @Test
    public void pollsHasCurrectOrder() {
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(1, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(3, heap.poll());
    }

    @Test
    public void heapGrowingWorks() {
        for(int i = 70; i > 0; i--) {
            heap.add(i);
        }

        for(int j = 1; j <= 70; j++) {
            assertEquals(j, heap.poll());
        }
    }
}