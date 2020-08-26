package logic;

import datastructures.Cordinate;
import datastructures.PathMap;

public class Helpers {
    public final double twoSqrt = Math.sqrt(2);

    public Helpers() {
    }

    public static boolean passable(char terrain) {
        if(terrain == '.' || terrain == 'G') {
            return true;
        }
        return false;
    }

    public static int abs(int i) {
        if(i >= 0) {
            return i;
        } 
        return -i;

    }

    public static void printPath(Cordinate[][] prevArray, int goalLine, int goalColum, int startLineNumber, int startColum) {
        Cordinate prev = prevArray[goalLine][goalColum];
        System.out.println("Printing path");
        System.out.println("goal " + new Cordinate(goalLine, goalColum));
        while(!(prev.getLineNumber() == startLineNumber && prev.getColum() == startColum)) {
            System.out.println(prev);
            prev = prevArray[prev.getLineNumber()][prev.getColum()];
        }
        System.out.println("start "+prev);
    }

    public static void printMapWithPath(Cordinate[][] prevArray, int goalLine, int goalColum, int startLineNumber, int startColum, PathMap map) {
        char[][] mapWithPath = map.getCopyOfMap();
        Cordinate prev = prevArray[goalLine][goalColum];
        mapWithPath[goalLine][goalColum] = 'G';
        while(!(prev.getLineNumber() == startLineNumber && prev.getColum() == startColum)) {
            mapWithPath[prev.getLineNumber()][prev.getColum()] = 'X';
            prev = prevArray[prev.getLineNumber()][prev.getColum()];
        }
        mapWithPath[prev.getLineNumber()][prev.getColum()] = 'S';
        printCharArray(mapWithPath, map.getHeight(), map.getWidth());
    }

    public static void printCharArray(char[][] map, int height, int width) {
        for(int lineNumber=0; lineNumber<height;lineNumber++){
            for(int colum = 0; colum <width;colum++) {
                System.out.print(map[lineNumber][colum]);
            }
            if(lineNumber != height -1){
                System.out.println("");
            }
        }
    }

    public static double abs(double i) {
        if(i >= 0) {
            return i;
        } 
        return -i;

    }
    
}