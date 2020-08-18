package logic;

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
    
}