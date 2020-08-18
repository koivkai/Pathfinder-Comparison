package datastructures;

public class PathMap {

    char[][] map;
    int height;
    int width;
    String name;
    CordinateQue startPosQue;
    CordinateQue goalPosQue;

    public PathMap() {
    }

    public PathMap(char[][] map, int height, int width) {
        this.map = map;
        this.height = height;
        this.width = width;
    } 

    public PathMap(char[][] map, int height, int width, String name) {
        this.map = map;
        this.height = height;
        this.width = width;
        this.name = name;
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
    
    public boolean terrainPassableAt(int lineNumber, int colum) {
        if(lineNumber >= this.width || colum >= this.height || lineNumber < 0 || colum < 0) {
            return false;
        } 

        char terrain = this.map[lineNumber][colum];
        //System.out.println("checking terrain at " +lineNumber+" "+colum);
        //System.out.println("t "+terrain);
        if(terrain == '.' || terrain == 'G') {
            return true;
        }
        return false;
    }

    public void print() {
        System.out.println("Printing map");
        for(int lineNumber=0; lineNumber<this.height;lineNumber++){
            for(int colum = 0; colum <this.width;colum++) {
                System.out.print(this.map[lineNumber][colum]);
            }
            if(lineNumber != this.height -1){
                System.out.println("");
            }
        }
    }

    public CordinateQue getStartPosQue() {
        return startPosQue;
    }

    public void setStartPosQue(CordinateQue startPosQue) {
        this.startPosQue = startPosQue;
    }

    public CordinateQue getGoalPosQue() {
        return goalPosQue;
    }

    public void setGoalPosQue(CordinateQue goalPosQue) {
        this.goalPosQue = goalPosQue;
    }

    

    public void addPath(Cordinate start, Cordinate goal) {
        if(this.goalPosQue == null || this.startPosQue == null) { // in only one exits they wont work anyway so reset both
            this.goalPosQue = new CordinateQue(5);
            this.startPosQue = new CordinateQue(5);
        }
        this.startPosQue.add(start);
        this.getGoalPosQue().add(goal);
    }

    public String getName() {
        if(name == null) {
            return "unnamed map";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}