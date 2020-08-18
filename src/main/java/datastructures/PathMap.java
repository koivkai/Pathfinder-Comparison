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

    public boolean canGoNorthEast(int currentLine, int currentColum) {
        int nwLine = currentLine -1;
        int nwColum = currentColum +1;
        if(nwLine >= this.width || nwColum >= this.height || nwLine < 0 || nwColum < 0) {
            return false;
        }
        char terrain = this.map[nwLine][nwColum]; 
        if(terrain != '.' && terrain != 'G') {
            return false;
        }

        //check for diagonal wall
        int upLine = currentLine -1;
        int upColum = currentColum;
        int rightLine = currentLine;
        int rightColum = currentColum+1;
        if(!terrainPassableAt(upLine, upColum) && !terrainPassableAt(rightLine, rightColum)) {
            return false;
        }

        return true;
    }

    public boolean canGoNorthWest(int currentLine, int currentColum) {
        int neLine = currentLine -1;
        int neColum = currentColum -1;
        if(neLine >= this.width || neColum >= this.height || neLine < 0 || neColum < 0) {
            return false;
        }
        char terrain = this.map[neLine][neColum]; 
        if(terrain != '.' && terrain != 'G') {
            return false;
        }

        //check for diagonal wall
        int upLine = currentLine -1;
        int upColum = currentColum;
        int leftLine = currentLine;
        int leftColum = currentColum-1;
        if(!terrainPassableAt(upLine, upColum) && !terrainPassableAt(leftLine, leftColum)) {
            return false;
        }

        return true;
    }

    public boolean canGoSouthEast(int currentLine, int currentColum) {
        int swLine = currentLine +1;
        int swColum = currentColum +1;
        if(swLine >= this.width || swColum >= this.height || swLine < 0 || swColum < 0) {
            return false;
        }
        char terrain = this.map[swLine][swColum]; 
        if(terrain != '.' && terrain != 'G') {
            return false;
        }

        //check for diagonal wall
        int downLine = currentLine +1;
        int downColum = currentColum;
        int rightLine = currentLine;
        int rightColum = currentColum+1;
        if(!terrainPassableAt(downLine, downColum) && !terrainPassableAt(rightLine, rightColum)) {
            return false;
        }

        return true;
    }

    public boolean canGoSouthWest(int currentLine, int currentColum) {
        int seLine = currentLine +1;
        int seColum = currentColum -1;
        if(seLine >= this.width || seColum >= this.height || seLine < 0 || seColum < 0) {
            return false;
        }
        char terrain = this.map[seLine][seColum]; 
        if(terrain != '.' && terrain != 'G') {
            return false;
        }

        //check for diagonal wall
        int downLine = currentLine +1;
        int downColum = currentColum;
        int leftLine = currentLine;
        int leftColum = currentColum-1;
        if(!terrainPassableAt(downLine, downColum) && !terrainPassableAt(leftLine, leftColum)) {
            return false;
        }

        return true;
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