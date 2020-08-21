package logic;

import datastructures.Cordinate;
import datastructures.MinHeap;
import datastructures.PathMap;

public class Dijkstra {
    int goalLine;
    int goalColum;
    MinHeap que;
    double[][] distance;
    Cordinate[][] prev;
    PathMap map;
    final double twosqrt = Math.sqrt(2);

    public Dijkstra() {
        
    }

    public double findPath(PathMap map, int startLineNumber, int startColum, int goalLineNumber, int goalColum) {
        this.que = new MinHeap();
        this.map = map;
        this.goalLine = goalLineNumber;
        this.goalColum =goalColum;
        boolean[][] visited = new boolean[map.getHeight()][map.getWidth()];
        prev = new Cordinate[map.getHeight()][map.getWidth()];
        //System.out.println("Djikstra");
        //System.out.println("map height " + map.getHeight());
        //System.out.println("map width " + map.getWidth());
        //System.out.println("startLine " + startLineNumber+ " startColum " + startColum);
        //System.out.println("goalLine "+goalLineNumber+" goalColum "+goalColum);
        prepDistance(map.getHeight(), map.getWidth());
        distance[startLineNumber][startColum] = 0;
        Cordinate start = new Cordinate(startLineNumber, startColum);
        que.add(start);
        Cordinate current = new Cordinate (0,0);

        while(!que.isEmpty()) {
            current = que.poll();
            int line = current.getLineNumber();
            int colum = current.getColum();
            if(foundGoal(line, colum)) {
                // Helpers.printPath(prev, goalLine, goalColum,startLineNumber,startColum);
                return distance[line][colum];
            }
            if(visited[line][colum]) {
                //do nothing
            } else {
                visited[line][colum] = true;
                checkNorth(current);
                checkSouth(current);
                checkEast(current);
                checkWest(current);
                checkNorthEast(current);
                checkNorthWest(current);
                checkSouthEast(current);
                checkSouthWest(current);
            }
        }
        return -1;
    }

    private void prepDistance(int height, int width){
        distance = new double[height][width];
        for (int h = 0;h<height;h++) {
            for(int w = 0;w<width;w++) {
                distance[h][w] = Double.MAX_VALUE;
            }
        }
    }

    private boolean foundGoal(int line, int colum) {
        if (line == this.goalLine && colum == this.goalColum) {
            return true;
        }
        return false;
    }

    private void checkNorth(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.terrainPassableAt(line-1, colum)) {
            double currentDistance = distance[line-1][colum];
            double newDistance = distance[line][colum]+1;
            if(newDistance < currentDistance) {
                distance[line-1][colum]= newDistance;
                Cordinate up = new Cordinate(line-1,colum,newDistance);
                prev[line-1][colum] = current;
                que.add(up);
            }
        }
    }

    private void checkSouth(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.terrainPassableAt(line+1, colum)) {
            double currentDistance = distance[line+1][colum];
            double newDistance = distance[line][colum]+1;
            if(newDistance < currentDistance) {
                distance[line+1][colum]= newDistance;
                Cordinate down = new Cordinate(line+1,colum,newDistance);
                prev[line+1][colum] = current;
                que.add(down);
            }
        }
    }

    private void checkEast(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.terrainPassableAt(line, colum+1)) {
            double currentDistance = distance[line][colum+1];
            double newDistance = distance[line][colum]+1;
            if(newDistance < currentDistance) {
                distance[line][colum+1]= newDistance;
                Cordinate right = new Cordinate(line,colum+1,newDistance);
                prev[line][colum+1] = current;
                que.add(right);
            }
        }
    }

    private void checkWest(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.terrainPassableAt(line, colum-1)) {
            double currentDistance = distance[line][colum-1];
            double newDistance = distance[line][colum]+1;
            if(newDistance < currentDistance) {
                distance[line][colum-1]= newDistance;
                Cordinate left = new Cordinate(line,colum-1,newDistance);
                prev[line][colum-1] = current;
                que.add(left);
            }
        }
    }

    private void checkNorthEast(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.canGoNorthEast(line, colum)) {
            double currentDistance = distance[line-1][colum+1];
            double newDistance = distance[line][colum] + twosqrt;
            if(newDistance < currentDistance) {
                distance[line-1][colum+1] = newDistance;
                Cordinate northEast = new Cordinate(line-1, colum+1, newDistance);
                prev[line-1][colum+1] = current;
                que.add(northEast);
            }
        }
    }

    private void checkNorthWest(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.canGoNorthWest(line, colum)) {
            double currentDistance = distance[line-1][colum-1];
            double newDistance = distance[line][colum] + twosqrt;
            if(newDistance < currentDistance) {
                distance[line-1][colum-1] = newDistance;
                Cordinate northWest = new Cordinate(line-1, colum-1, newDistance);
                prev[line-1][colum-1] = current;
                que.add(northWest);
            }
        }
    }

    private void checkSouthEast(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.canGoSouthEast(line, colum)) {
            double currentDistance = distance[line+1][colum+1];
            double newDistance = distance[line][colum] + twosqrt;
            if(newDistance < currentDistance) {
                distance[line+1][colum+1] = newDistance;
                Cordinate southEast = new Cordinate(line+1, colum+1, newDistance);
                prev[line+1][colum+1] = current;
                que.add(southEast);
            }
        }
    }

    private void checkSouthWest(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.canGoSouthWest(line, colum)) {
            double currentDistance = distance[line+1][colum-1];
            double newDistance = distance[line][colum] + twosqrt;
            if(newDistance < currentDistance) {
                distance[line+1][colum-1] = newDistance;
                Cordinate southWest = new Cordinate(line+1, colum-1, newDistance);
                prev[line+1][colum-1] = current;
                que.add(southWest);
            }
        }
    }
    
}