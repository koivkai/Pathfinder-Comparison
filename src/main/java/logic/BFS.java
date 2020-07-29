package logic;

import java.util.ArrayDeque;
import java.util.Queue;

import datastructures.Cordinate;
import datastructures.PathMap;

public class BFS {

    public BFS() {
    }

    public int findPath(PathMap map, int startLinenumber, int startColum, int goalLineNumber, int goalColum) {
        int[][]distance = new int[map.getHeight()][map.getWidth()];
        boolean[][]visited = new boolean[map.getHeight()][map.getWidth()];
        Cordinate[][]prev = new Cordinate[map.getHeight()][map.getWidth()];
        Queue<Cordinate> que = new ArrayDeque<Cordinate>();
        Cordinate start = new Cordinate(startLinenumber, startColum);
        que.add(start);

        Cordinate current = new Cordinate(0, 0);
        while(!que.isEmpty()) {
            current = que.poll();
            int line = current.getLineNumber();
            int colum = current.getColum();
            
            System.out.println(current);
            if(line == goalLineNumber && colum == goalColum) {
                //path found
                return distance[line][colum];
            }

            if(visited[line][colum]) {
                // do nothing
            } else  {
                visited[line][colum] = true;
                char terrain = 'a';
                // up
                terrain = map.terrainAt(line-1, colum);
                if (passable(terrain)) {
                    que.add(new Cordinate(line-1,colum));
                   prev[line-1][colum] = current;
                    distance[line-1][colum] = distance[line][colum] +1;
                }
                // down
                terrain = map.terrainAt(line+1, colum);
                if (passable(terrain)) {
                    que.add(new Cordinate(line+1,colum));
                   prev[line+1][colum] = current;
                    distance[line+1][colum] = distance[line][colum] +1;
                }
                // right
                terrain = map.terrainAt(line, colum+1);
                if (passable(terrain)) {
                    que.add(new Cordinate(line,colum+1));
                   prev[line][colum+1] = current;
                    distance[line][colum+1] = distance[line][colum] +1;
                }
                // left
                terrain = map.terrainAt(line, colum-1);
                if (passable(terrain)) {
                    que.add(new Cordinate(line,colum-1));
                   prev[line][colum-1] = current;
                    distance[line][colum+1] = distance[line][colum] +1;
                }
            }
        }
        return -1; // no path found
    }

    private boolean passable(char terrain) {
        if(terrain == '.' || terrain == 'G') {
            return true;
        }
        return false;
    }
    
}