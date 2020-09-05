package datastructures;

/**
 * A datastructure(list) used by JPS
 */
public class CordinateList {
    Cordinate[] list;
    int nextPos;
    int size;

    public CordinateList() {
        this.list = new Cordinate[10];
        this.nextPos = 0;
        this.size = 10;
    }

    public CordinateList(int size) {
        this.list = new Cordinate[size];
        this.nextPos = 0;
        this.size = size;
    }

    public void add(Cordinate cordinate) {
        if(nextPos == size) {
            grow();
        }
        list[nextPos] = cordinate;
        nextPos++;
    }  

    public Cordinate get(int index) {
        if(index < 0 || index >= nextPos) {
            return null;
        }
        return list[index];

    }

    public int size() {
        return this.nextPos;
    }

    private void grow() {
        Cordinate[] newList = new Cordinate[size*2];
        for(int i = 0; i < nextPos; i++) {
            newList[i] = list[i];
        }
        size = size * 2;
        this.list = newList;
    }
    
}