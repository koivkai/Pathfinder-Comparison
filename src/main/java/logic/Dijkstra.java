package logic;


import java.util.PriorityQueue;
import datastructures.Cordinate;
import datastructures.PathMap;

public class Dijkstra {
    int goalLine;
    int goalColum;
    PriorityQueue<Cordinate> que;
    int[][] distance;

    public Dijkstra() {
        
    }

    public int findPath(PathMap map, int startLineNumber, int startColum, int goalLineNumber, int goalColum) {
        this.que = new PriorityQueue<Cordinate>();
        boolean[][] visited = new boolean[map.getHeight()][map.getWidth()];
        Cordinate[][] prev = new Cordinate[map.getHeight()][map.getWidth()];
        prepDistance(map.getHeight(), map.getWidth());
        distance[startLineNumber][startColum] = 0;
        Cordinate start = new Cordinate(startLineNumber, startColum);
        que.add(start);
        this.goalLine = goalLineNumber;
        this.goalColum =goalColum;
        Cordinate current = new Cordinate (0,0);
        char terrain = 't';

        while(!que.isEmpty()) {
            current = que.poll();
            int line = current.getLineNumber();
            int colum = current.getColum();
            if(foundGoal(line, colum)) {
                return distance[line][colum];
            }
            if(visited[line][colum]) {
                //do nothing
            } else {
                visited[line][colum] = true;
                
                // up
                terrain = map.terrainAt(line-1, colum);
                if (Helpers.passable(terrain)) {
                    int currentDistance = distance[line-1][colum];
                    int newDistance = distance[line][colum]+1;
                    if(newDistance < currentDistance) {
                        distance[line-1][colum]= newDistance;
                        Cordinate up = new Cordinate(line-1,colum,newDistance);
                        prev[line-1][colum] = up;
                        que.add(up);
                    }
                    
                }
                // down
                terrain = map.terrainAt(line+1, colum);
                if (Helpers.passable(terrain)) {
                    int currentDistance = distance[line+1][colum];
                    int newDistance = distance[line][colum]+1;
                    if(newDistance < currentDistance) {
                        distance[line+1][colum]= newDistance;
                        Cordinate down = new Cordinate(line+1,colum,newDistance);
                        prev[line+1][colum] = down;
                        que.add(down);
                    }
                    
                }
                // right
                terrain = map.terrainAt(line, colum+1);
                if (Helpers.passable(terrain)) {
                    int currentDistance = distance[line][colum+1];
                    int newDistance = distance[line][colum]+1;
                    if(newDistance < currentDistance) {
                        distance[line][colum+1]= newDistance;
                        Cordinate right = new Cordinate(line,colum+1,newDistance);
                        prev[line][colum+1] = right;
                        que.add(right);
                    }
                    
                }
                // left
                terrain = map.terrainAt(line, colum-1);
                if (Helpers.passable(terrain)) {
                    int currentDistance = distance[line][colum-1];
                    int newDistance = distance[line][colum]+1;
                    if(newDistance < currentDistance) {
                        distance[line][colum-1]= newDistance;
                        Cordinate right = new Cordinate(line,colum-1,newDistance);
                        prev[line][colum-1] = right;
                        que.add(right);
                    }
                    
                }

            }
        }


        return -1;
    }

    private void prepDistance(int height, int width){
        distance = new int[height][width];
        for (int h = 0;h<height;h++) {
            for(int w = 0;w<width;w++) {
                distance[h][w] = Integer.MAX_VALUE;
            }
        }
    }

    private boolean foundGoal(int line, int colum) {
        if (line == this.goalLine && colum == this.goalColum) {
            return true;
        }
        return false;
    }
    
}