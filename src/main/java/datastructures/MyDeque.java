package datastructures;

import java.util.Arrays;

/**
 * A datastructure(que), currently unused.
 */
public class MyDeque {
    int size;
    int used;
    int[] array;
    int head;
    int tail;

    public MyDeque() {
        this.size = 50;
        this.array = new int[50];
        this.used = 0;
        this.head = 0;
        this.tail = 0;
    }

    public MyDeque(int size) {
        this.size = size;
        this.array = new int[size];
        this.used = 0;
        this.head = 0;
        this.tail = 0;
    }

    public void add(int i) {
        if (used < size) {
            array[tail] = i;
            tail++;
            used++;
            if (tail == size) {
                tail = 0;
            }
        } else {
            grow();
            add(i);
        }
    }

    public int poll() {
        int i = array[head];
        head++;
        used--;
        if (head == size) {
            head = 0;
        }

        return i;
    }

    public boolean isEmpty() {
        return this.used == 0;
    }

    private void grow() {
        int[] newArray = new int[size * 2];
        int pos = 0;
        int i = head;
        while (i < size) {
            newArray[pos] = array[i];
            pos++;
            i++;
        }
        int j = 0;
        while (j < tail) {
            newArray[pos] = array[j];
            j++;
            pos++;
        }

        this.array = newArray;
        this.size = size * 2;
        this.head = 0;
        this.tail = pos;
    }

    @Override
    public String toString() {
        return "MyDeque " + Arrays.toString(array);
    }

    
    

    
}