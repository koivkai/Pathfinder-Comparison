package datastructures;

import java.util.Arrays;

public class DoublesQue {
    int size;
    int used;
    double[] array;
    int head;
    int tail;

    public DoublesQue() {
        this.size = 50;
        this.array = new double[50];
        this.used = 0;
        this.head = 0;
        this.tail = 0;
    }

    public DoublesQue(int size) {
        this.size = size;
        this.array = new double[size];
        this.used = 0;
        this.head = 0;
        this.tail = 0;
    }

    public void add(double i) {
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

    public double poll() {
        double d = array[head];
        head++;
        used--;
        if (head == size) {
            head = 0;
        }

        return d;
    }

    public boolean isEmpty() {
        return this.used == 0;
    }

    private void grow() {
        double[] newArray = new double[size * 2];
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