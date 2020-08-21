package logic;

import java.util.Arrays;

import datastructures.Cordinate;

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

    public static double abs(double i) {
        if(i >= 0) {
            return i;
        } 
        return -i;

    }
    
}