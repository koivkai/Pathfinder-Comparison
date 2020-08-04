package logic;

public class Helpers {

    public Helpers() {
    }

    public static boolean passable(char terrain) {
        if(terrain == '.' || terrain == 'G') {
            return true;
        }
        return false;
    }
    
}