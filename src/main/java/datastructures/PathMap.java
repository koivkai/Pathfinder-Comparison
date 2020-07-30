package datastructures;

import java.util.Arrays;

public class PathMap {

    char[][] map;
    int height;
    int width;

    public PathMap(char[][] map, int height, int width) {
        this.map = map;
        this.height = height;
        this.width = width;
    }

    public PathMap() {
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public char terrainAt(int lineNumber, int colum) {
        if(lineNumber >= this.width || colum >= this.height || lineNumber < 0 || colum < 0) {
            return '@';
        } 
        return (this.map[lineNumber][colum]);
    }
    
    public void print() {

        System.out.println("Printing map");
        //System.out.println(Arrays.toString(map));
        for(int lineNumber=0; lineNumber<this.height;lineNumber++){
            for(int colum = 0; colum <this.width;colum++) {
                System.out.print(this.map[lineNumber][colum]);
            }
            if(lineNumber != this.height -1){
                System.out.println("");
            }
        }
    }


}