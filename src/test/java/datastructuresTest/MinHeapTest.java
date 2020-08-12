package datastructuresTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import datastructures.Cordinate;
import datastructures.MinHeap;

public class MinHeapTest {
    MinHeap heap;
    
    @Before
    public void createHeap() {
        heap = new MinHeap();
    }

    @Test
    public void addAndPollWork() {
        Cordinate one = new Cordinate(1,1,1);
        heap.add(one);
        assertEquals(one, heap.poll());
    }

    @Test
    public void pollsHasCurrectOrder() {
        Cordinate thr = new Cordinate(3,3,3);
        Cordinate two = new Cordinate(2,2,2);
        Cordinate one = new Cordinate(1,1,1);
        heap.add(thr);
        heap.add(two);
        heap.add(one);
        assertEquals(one, heap.poll());
        assertEquals(two, heap.poll());
        assertEquals(thr, heap.poll());
    }

    @Test
    public void heapGrowingWorks() {
        for(int i = 70; i > 0; i--) {
            heap.add(new Cordinate(i,i,i));
        }

        for(int j = 1; j <= 70; j++) {
            assertEquals(new Cordinate(j,j,j), heap.poll());
        }
    }
}