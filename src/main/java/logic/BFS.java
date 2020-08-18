package logic;

import datastructures.Cordinate;
import datastructures.CordinateQue;
import datastructures.PathMap;

public class BFS {
    CordinateQue que;
    double[][]distance;
    boolean[][]visited;
    Cordinate[][]prev;
    PathMap map;
    
    public BFS() {
    }

    public double findPath(PathMap map, int startLinenumber, int startColum, int goalLineNumber, int goalColum) {
        this.map = map;
        distance = new double[map.getHeight()][map.getWidth()];
        visited = new boolean[map.getHeight()][map.getWidth()];
        prev = new Cordinate[map.getHeight()][map.getWidth()];
        que = new CordinateQue();
        Cordinate start = new Cordinate(startLinenumber, startColum);
        que.add(start);
        Cordinate current = new Cordinate(0, 0);
        while(!que.isEmpty()) {
            current = que.poll();
            int line = current.getLineNumber();
            int colum = current.getColum();
            
            if(line == goalLineNumber && colum == goalColum) {
                //path found
                return distance[line][colum];
            }

            if(visited[line][colum]) {
                // do nothing
            } else  {
                visited[line][colum] = true;
                checkNorth(current);
                checkSouth(current);
                checkEast(current);
                checkWest(current);
            }
        }
        return -1; // no path found
    }

    private void checkNorth(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.terrainPassableAt(line-1, colum)) {
            que.add(new Cordinate(line-1,colum));
            prev[line-1][colum] = current;
            distance[line-1][colum] = distance[line][colum] +1;
        }
    }

    private void checkSouth(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.terrainPassableAt(line+1, colum)) {
            que.add(new Cordinate(line+1,colum));
            prev[line+1][colum] = current;
            distance[line+1][colum] = distance[line][colum] +1;
        }
    }

    private void checkEast(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.terrainPassableAt(line, colum+1)) {
            que.add(new Cordinate(line,colum+1));
            prev[line][colum+1] = current;
            distance[line][colum+1] = distance[line][colum] +1;
        }
    }

    private void checkWest(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.terrainPassableAt(line, colum-1)) {
            que.add(new Cordinate(line,colum-1));
            prev[line][colum-1] = current;
            distance[line][colum-1] = distance[line][colum] +1;
        }
    }

}