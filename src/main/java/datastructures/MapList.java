package datastructures;
/**
 * A datastructure(list) used by comparison engine
 */
public class MapList {
    PathMap[] list;
    int nextPos;
    int size;

    public MapList() {
        this.list = new PathMap[10];
        this.nextPos = 0;
        this.size = 10;
    }

    public MapList(int size) {
        this.list = new PathMap[size];
        this.nextPos = 0;
        this.size = size;
    }

    public void add(PathMap map) {
        if(nextPos == size) {
            grow();
        }
        list[nextPos] = map;
        nextPos++;
    }  

    public PathMap get(int index) {
        if(index < 0 || index >=nextPos) {
            return null;
        }
        return list[index];

    }

    public int size() {
        return this.nextPos;
    }

    private void grow() {
        PathMap[] newList = new PathMap[size*2];
        for(int i = 0; i < nextPos; i++) {
            newList[i] = list[i];
        }
        size = size * 2;
        this.list = newList;
    }
}