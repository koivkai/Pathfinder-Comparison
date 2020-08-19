package logic;

import datastructures.Cordinate;
import datastructures.MinHeap;
import datastructures.PathMap;

/**
 * 
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
        this.goalLine = 0;
        this.goalColum = 0;
    }

    public double findPath(PathMap map, int startLineNumber, int startColum, int goalLineNumber, int goalColum) {
        this.que = new MinHeap();
        this.map = map;
        this.goalFound = false;
        visited = new boolean[map.getHeight()][map.getWidth()];
        prev = new Cordinate[map.getHeight()][map.getWidth()];
        prepDistance(map.getHeight(), map.getWidth());
        que.add(new Cordinate(startLineNumber, startColum));
        this.goalLine = goalLineNumber;
        this.goalColum =goalColum;
        Cordinate current = new Cordinate (0,0);
        double fscore = 0;
        distance[startLineNumber][startColum] = 0;

        while(!que.isEmpty()) {
            current = que.poll();
            //System.out.println(current);
            int line = current.getLineNumber();
            int colum = current.getColum();
            if(visited[line][colum]) {
                // do nothing
            } else {
                visited[line][colum] = true;

                // north
                if (map.terrainPassableAt(line-1, colum)) {
                    double currentDistance = distance[line-1][colum];
                    double newDistance = distance[line][colum]+1;

                    if(newDistance < currentDistance) {
                        distance[line-1][colum] = distance[line][colum] +1;
                        prev[line-1][colum] = current;
                        if(foundGoal(line-1, colum)) {
                            return distance[line-1][colum];
                        }
                        fscore = distance[line-1][colum] + estimate(line-1, colum);
                        que.add(new Cordinate(line-1,colum, fscore));
                        
                    }            
                } 
                // south 
                if (map.terrainPassableAt(line+1, colum)) {
                    double currentDistance = distance[line+1][colum];
                    double newDistance = distance[line][colum]+1;

                    if(newDistance < currentDistance) {
                        distance[line+1][colum] = distance[line][colum] +1;
                        prev[line+1][colum] = current;
                        if(foundGoal(line+1, colum)) {
                            return distance[line+1][colum];
                        }
                        fscore = distance[line+1][colum] + estimate(line+1, colum);
                        que.add(new Cordinate(line+1,colum, fscore));
                        
                    }
                    
                }
                // east 
                if(map.terrainPassableAt(line, colum+1)) {
                    double currentDistance = distance[line][colum+1];
                    double newDistance = distance[line][colum]+1;

                    if(newDistance < currentDistance) {
                        distance[line][colum+1] = distance[line][colum] +1;
                        prev[line][colum+1] = current;
                        if(foundGoal(line, colum+1)) {
                            return distance[line][colum+1];
                        }
                        fscore = distance[line][colum+1] + estimate(line, colum+1);
                        que.add(new Cordinate(line,colum+1, fscore));
                        
                    }
                    
                }
                // west 
                if(map.terrainPassableAt(line, colum-1)) {
                    double currentDistance = distance[line][colum-1];
                    double newDistance = distance[line][colum]+1;

                    if(newDistance < currentDistance) {
                        distance[line][colum-1] = distance[line][colum] +1;
                        prev[line][colum-1] = current;
                        if(foundGoal(line, colum-1)) {
                            return distance[line][colum-1];
                        }
                        fscore = distance[line][colum-1] + estimate(line, colum-1);
                        que.add(new Cordinate(line,colum-1, fscore));
                        
                    }
                    
                }

                //north east
                if(map.canGoNorthEast(line, colum)) {
                    double currentDistance = distance[line-1][colum+1];
                    double newDistance = distance[line][colum]+twosqrt;

                    if(newDistance < currentDistance) {
                        distance[line-1][colum+1] = distance[line][colum] +twosqrt;
                        prev[line-1][colum+1] = current;
                        if(foundGoal(line-1, colum+1)) {
                            return distance[line-1][colum+1];
                        }
                        fscore = distance[line-1][colum+1] + estimate(line-1, colum+1);
                        que.add(new Cordinate(line-1,colum+1, fscore));
                        
                    }
                }

                //north west
                if(map.canGoNorthWest(line, colum)) {
                    double currentDistance = distance[line-1][colum-1];
                    double newDistance = distance[line][colum]+twosqrt;

                    if(newDistance < currentDistance) {
                        distance[line-1][colum-1] = distance[line][colum] +twosqrt;
                        prev[line-1][colum-1] = current;
                        if(foundGoal(line-1, colum-1)) {
                            return distance[line-1][colum-1];
                        }
                        fscore = distance[line-1][colum-1] + estimate(line-1, colum-1);
                        que.add(new Cordinate(line-1,colum-1, fscore));
                        
                    }
                }

                //south east
                if(map.canGoSouthEast(line, colum)) {
                    double currentDistance = distance[line+1][colum+1];
                    double newDistance = distance[line][colum]+twosqrt;

                    if(newDistance < currentDistance) {
                        distance[line+1][colum+1] = distance[line][colum] +twosqrt;
                        prev[line+1][colum+1] = current;
                        if(foundGoal(line+1, colum+1)) {
                            return distance[line+1][colum+1];
                        }
                        fscore = distance[line+1][colum+1] + estimate(line+1, colum+1);
                        que.add(new Cordinate(line+1,colum+1, fscore));
                        
                    }
                }

                //south west
                if(map.canGoSouthWest(line, colum)) {
                    double currentDistance = distance[line+1][colum-1];
                    double newDistance = distance[line][colum]+twosqrt;

                    if(newDistance < currentDistance) {
                        distance[line+1][colum-1] = distance[line][colum] +twosqrt;
                        prev[line+1][colum-1] = current;
                        if(foundGoal(line+1, colum-1)) {
                            return distance[line+1][colum-1];
                        }
                        fscore = distance[line+1][colum-1] + estimate(line+1, colum-1);
                        que.add(new Cordinate(line+1,colum-1, fscore));
                        
                    }
                }
            }
        }
        
    return -1;
    }

    private boolean foundGoal(int line, int colum) {
        if (line == this.goalLine && colum == this.goalColum) {
            return true;
        }
        return false;
    }

    /*
    private double estimate(int line, int colum) {
        return Helpers.abs((line -this.goalLine)) + Helpers.abs((colum - goalColum));
    }
    */

    private double estimate(int line, int colum) {
        return Math.max(Helpers.abs((line -this.goalLine)), Helpers.abs((colum - goalColum)));
    }

    private void prepDistance(int height, int width){
        distance = new double[height][width];
        for (int h = 0;h<height;h++) {
            for(int w = 0;w<width;w++) {
                distance[h][w] = Double.MAX_VALUE;
            }
        }
    }

}