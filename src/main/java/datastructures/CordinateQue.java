package datastructures;

import java.util.Arrays;

/**
 * A datastructure(min-heap) used by A* and djikstra
 */
public class CordinateQue {
    int size;
    int used;
    Cordinate[] array;
    int head;
    int tail;

    public CordinateQue() {
        this.size = 50;
        this.array = new Cordinate[50];
        this.used = 0;
        this.head = 0;
        this.tail = 0;
    }

    public CordinateQue(int size) {
        this.size = size;
        this.array = new Cordinate[size];
        this.used = 0;
        this.head = 0;
        this.tail = 0;
    }

    public void add(Cordinate c) {
        if (used < size) {
            array[tail] = c;
            tail++;
            used++;
            if (tail == size) {
                tail = 0;
            }
        } else {
            grow();
            add(c);
        }
    }

    public Cordinate poll() {
        Cordinate c = array[head];
        head++;
        used--;
        if (head == size) {
            head = 0;
        }

        return c;
    }

    public boolean isEmpty() {
        return this.used == 0;
    }

    private void grow() {
        Cordinate[] newArray = new Cordinate[size * 2];
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

    // returns number of cordinates remaining in que
    public int size() {
        return used;
    }

    @Override
    public String toString() {
        return "MyDeque " + Arrays.toString(this.array);
    }

}