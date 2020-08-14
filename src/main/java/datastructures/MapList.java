package datastructures;

import java.util.ArrayList;

public class MapList {
    ArrayList<PathMap> list;

    public MapList() {
        this.list = new ArrayList<PathMap>();
    }

    public void add(PathMap map) {
        list.add(map);
    }  

    public PathMap get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }




    
}