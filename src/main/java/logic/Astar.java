package logic;

import java.util.PriorityQueue;

import datastructures.Cordinate;
import datastructures.PathMap;

/**
 * 
 */
public class Astar {
    int goalLine;
    int goalColum;
    PriorityQueue<Cordinate> que;

    public Astar() {
        this.goalLine = 0;
        this.goalColum = 0;
    }

    public int findPath(PathMap map, int startLineNumber, int startColum, int goalLineNumber, int goalColum) {
        this.que = new PriorityQueue<Cordinate>();
        boolean[][] visited = new boolean[map.getHeight()][map.getWidth()];
        Cordinate[][] prev = new Cordinate[map.getHeight()][map.getWidth()];
        int[][]distance = new int[map.getHeight()][map.getWidth()];
        que.add(new Cordinate(startLineNumber, startColum));
        this.goalLine = goalLineNumber;
        this.goalColum =goalColum;
        Cordinate current = new Cordinate (0,0);
        char terrain = 't';
        int fscore = 0;

        while(!que.isEmpty()) {
            current = que.poll();
            System.out.println(current);
            int line = current.getLineNumber();
            int colum = current.getColum();
            if(visited[line][colum]) {
                // do nothing
            } else {
                visited[line][colum] = true;
                // up
                terrain = map.terrainAt(line-1, colum);
                if (Helpers.passable(terrain)) {
                    if(visited[line-1][colum]) {
                        if (distance[line][colum]+1 < distance[line-1][colum]) { //path from here is shorter than old path
                            prev[line-1][colum] = current;
                            distance[line-1][colum] = distance[line][colum] +1;
                        }
   
                        } else {
                        prev[line-1][colum] = current;
                        distance[line-1][colum] = distance[line][colum] +1;
                            if(foundGoal(line-1, colum)) {
                                return distance[line-1][colum];
                            }
                        fscore = distance[line-1][colum] + estimate(line-1, colum);
                        que.add(new Cordinate(line-1,colum, fscore));
                        }
                    
                } 
                // down 
                terrain = map.terrainAt(line+1, colum);
                if (Helpers.passable(terrain)) {
                    if(visited[line+1][colum]) {
                        if (distance[line][colum]+1 < distance[line+1][colum]) { //path from here is shorter than old path
                            prev[line+1][colum] = current;
                            distance[line+1][colum] = distance[line][colum] +1;
                        }
                    } else {
                        prev[line+1][colum] = current;
                        distance[line+1][colum] = distance[line][colum] +1;
                        if(foundGoal(line+1, colum)) {
                            return distance[line+1][colum];
                        }
                        fscore = distance[line+1][colum] + estimate(line+1, colum);
                        que.add(new Cordinate(line+1,colum, fscore));
                    }
                    
                }
                // right 
                terrain = map.terrainAt(line, colum+1);
                if(Helpers.passable(terrain)) {
                    if(visited[line][colum+1]) {
                        if (distance[line][colum]+1 < distance[line][colum+1]) { //path from here is shorter than old path
                            prev[line][colum+1] = current;
                            distance[line][colum+1] = distance[line][colum] +1;
                        }
   
                    } else {
                        prev[line][colum+1] = current;
                        distance[line][colum+1] = distance[line][colum]+1;
                        if(foundGoal(line, colum+1)) {
                            return distance[line][colum+1];
                        }
                        fscore = distance[line][colum+1] +estimate(line, colum+1);
                        que.add(new Cordinate(line, colum+1,fscore));
                    }
                    
                }
                // left 
                terrain = map.terrainAt(line, colum-1);
                if(Helpers.passable(terrain)) {
                    if(visited[line][colum-1]) {
                        if (distance[line][colum]+1 < distance[line][colum-1]) { //path from here is shorter than old path
                            prev[line][colum-1] = current;
                            distance[line][colum-1] = distance[line][colum] +1;
                        }
   
                    } else {
                        prev[line][colum-1] = current;
                        distance[line][colum-1] = distance[line][colum]+1;
                        if(foundGoal(line, colum-1)) {
                            return distance[line][colum-1];
                        }
                        fscore = distance[line][colum-1] +estimate(line, colum-1);
                        que.add(new Cordinate(line, colum-1,fscore));
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

    private int estimate(int line, int colum) {
        return Math.abs((line -this.goalLine)) + Math.abs((colum - goalColum));
    }
}