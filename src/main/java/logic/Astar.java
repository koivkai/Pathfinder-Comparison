package logic;

import datastructures.Cordinate;
import datastructures.MinHeap;
import datastructures.PathMap;

/**
 * Implementation of the A* pathfinding algorithm in a uniform grid.
 */
public class Astar {
    int goalLine;
    int goalColum;
    MinHeap que;
    boolean[][] visited;
    Cordinate[][] prev;
    double[][]distance;
    PathMap map;
    boolean goalFound;
    final double twosqrt = Math.sqrt(2);


    public Astar() {
    }

    public double findPath(PathMap map, int startLineNumber, int startColum, int goalLineNumber, int goalColum) {
        if(startLineNumber == goalLineNumber && startColum == goalColum) {
            return 0;
        }
        this.que = new MinHeap();
        this.map = map;
        this.goalFound = false;
        visited = new boolean[map.getHeight()][map.getWidth()];
        prev = new Cordinate[map.getHeight()][map.getWidth()];
        distance = new double[map.getHeight()][map.getWidth()];
        que.add(new Cordinate(startLineNumber, startColum));
        this.goalLine = goalLineNumber;
        this.goalColum =goalColum;
        Cordinate current = new Cordinate (0,0);
        distance[startLineNumber][startColum] = 0;

        while(!que.isEmpty()) {
            current = que.poll();
            int line = current.getLineNumber();
            int colum = current.getColum();
            if(foundGoal(line, colum)) {
                return distance[line][colum];
            }
            if(visited[line][colum]) {
                continue;
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

    /**
    * Checks if moving north is a legal move from specified cordinate.
    * If so, adds the northern cordinate to the que.
    */
    private void checkNorth(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if (map.terrainPassableAt(line-1, colum)) {
            double currentDistance = distance[line-1][colum];
            double newDistance = distance[line][colum]+1;

            if(newDistance < currentDistance || currentDistance == 0) {
                distance[line-1][colum] = distance[line][colum] +1;
                prev[line-1][colum] = current;
                double fscore = distance[line-1][colum] + estimate(line-1, colum);
                que.add(new Cordinate(line-1,colum, fscore));
                
            }            
        } 
    }

    /**
    * Checks if moving south is a legal move from specified cordinate.
    * If so, adds the southern cordinate to the que.
    */
    private void checkSouth(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if (map.terrainPassableAt(line+1, colum)) {
            double currentDistance = distance[line+1][colum];
            double newDistance = distance[line][colum]+1;

            if(newDistance < currentDistance || currentDistance == 0) {
                distance[line+1][colum] = distance[line][colum] +1;
                prev[line+1][colum] = current;
                double fscore = distance[line+1][colum] + estimate(line+1, colum);
                que.add(new Cordinate(line+1,colum, fscore));
                
            }
            
        }
    }

    /**
    * Checks if moving east is a legal move from specified cordinate.
    * If so, adds the eastern cordinate to the que.
    */
    private void checkEast(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.terrainPassableAt(line, colum+1)) {
            double currentDistance = distance[line][colum+1];
            double newDistance = distance[line][colum]+1;

            if(newDistance < currentDistance || currentDistance == 0) {
                distance[line][colum+1] = distance[line][colum] +1;
                prev[line][colum+1] = current;
                double fscore = distance[line][colum+1] + estimate(line, colum+1);
                que.add(new Cordinate(line,colum+1, fscore));
                
            }
            
        }
    }

    /**
    * Checks if moving west is a legal move from specified cordinate.
    * If so, adds the western cordinate to the que.
    */
    private void checkWest(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.terrainPassableAt(line, colum-1)) {
            double currentDistance = distance[line][colum-1];
            double newDistance = distance[line][colum]+1;

            if(newDistance < currentDistance || currentDistance == 0) {
                distance[line][colum-1] = distance[line][colum] +1;
                prev[line][colum-1] = current;
                double fscore = distance[line][colum-1] + estimate(line, colum-1);
                que.add(new Cordinate(line,colum-1, fscore));
                
            }
            
        }
    }

    /**
    * Checks if moving northeast is a legal move from specified cordinate.
    * If so, adds the northeastern cordinate to the que.
    */
    private void checkNorthEast(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.canGoNorthEast(line, colum)) {
            double currentDistance = distance[line-1][colum+1];
            double newDistance = distance[line][colum]+twosqrt;

            if(newDistance < currentDistance || currentDistance == 0) {
                distance[line-1][colum+1] = newDistance;
                prev[line-1][colum+1] = current;
                double fscore = distance[line-1][colum+1] + estimate(line-1, colum+1);
                que.add(new Cordinate(line-1,colum+1, fscore));
                
            }
        }
    }

    /**
    * Checks if moving northwest is a legal move from specified cordinate.
    * If so, adds the northwestern cordinate to the que.
    */
    private void checkNorthWest(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.canGoNorthWest(line, colum)) {
            double currentDistance = distance[line-1][colum-1];
            double newDistance = distance[line][colum]+twosqrt;

            if(newDistance < currentDistance || currentDistance == 0) {
                distance[line-1][colum-1] = newDistance;
                prev[line-1][colum-1] = current;
                double fscore = distance[line-1][colum-1] + estimate(line-1, colum-1);
                que.add(new Cordinate(line-1,colum-1, fscore));
                
            }
        }
    }

    /**
    * Checks if moving southeast is a legal move from specified cordinate.
    * If so, adds the southeastern cordinate to the que.
    */
    private void checkSouthEast(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.canGoSouthEast(line, colum)) {
            double currentDistance = distance[line+1][colum+1];
            double newDistance = distance[line][colum]+twosqrt;

            if(newDistance < currentDistance || currentDistance == 0) {
                distance[line+1][colum+1] = distance[line][colum] +twosqrt;
                prev[line+1][colum+1] = current;
                double fscore = distance[line+1][colum+1] + estimate(line+1, colum+1);
                que.add(new Cordinate(line+1,colum+1, fscore));
                
            }
        }
    }

    /**
    * Checks if moving southwest is a legal move from specified cordinate.
    * If so, adds the southwestern cordinate to the que.
    */
    private void checkSouthWest(Cordinate current) {
        int line = current.getLineNumber();
        int colum = current.getColum();
        if(map.canGoSouthWest(line, colum)) {
            double currentDistance = distance[line+1][colum-1];
            double newDistance = distance[line][colum]+twosqrt;

            if(newDistance < currentDistance || currentDistance == 0) {
                distance[line+1][colum-1] = distance[line][colum] +twosqrt;
                prev[line+1][colum-1] = current;
                double fscore = distance[line+1][colum-1] + estimate(line+1, colum-1);
                que.add(new Cordinate(line+1,colum-1, fscore));
                
            }
        }
    }
    

    private boolean foundGoal(int line, int colum) {
        if (line == this.goalLine && colum == this.goalColum) {
            return true;
        }
        return false;
    }

    private double estimate(int line, int colum) {
        return (double)Helpers.max(Helpers.abs((line -this.goalLine)), Helpers.abs((colum - goalColum)));
    }

}