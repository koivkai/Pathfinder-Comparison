package logic;

import datastructures.Cordinate;
import datastructures.CordinateList;
import datastructures.MinHeap;
import datastructures.PathMap;

public class JPS {
    int goalLine;
    int goalColum;
    MinHeap que;
    boolean[][] visited;
    Cordinate[][] prev;
    int[][]distance;
    PathMap map;

    public JPS() {
    }

    public int findPath(PathMap map, int startLineNumber, int startColum, int goalLineNumber, int goalColum) {
        this.que = new MinHeap();
        visited = new boolean[map.getHeight()][map.getWidth()];
        prev = new Cordinate[map.getHeight()][map.getWidth()];
        distance = new int[map.getHeight()][map.getWidth()];
        que.add(new Cordinate(startLineNumber, startColum));
        this.goalLine = goalLineNumber;
        this.goalColum =goalColum;
        Cordinate current = new Cordinate (0,0);
        this.map = map;

        while(!que.isEmpty()) {
            current = que.poll();
            int line = current.getLineNumber();
            int colum = current.getColum();

            if(visited[line][colum]) {
                //System.out.println("already visited");
                // do nothing
            } else { 
                visited[line][colum] = true;
                if(foundGoal(current)) {
                    return distance[line][colum];
                }
                findSuccessors(current);
                
            }

        }
        return -1;
    }
    private void findSuccessors(Cordinate current) {
        CordinateList neighbours = getNeightbours(current);
        for(int i = 0; i < neighbours.size(); i++) {
            Cordinate c = neighbours.get(i);
            Cordinate next = jump(c, current);
            if(next != null) {
                
                if(visited[next.getLineNumber()][next.getColum()]) {
                    // do nothing
                } else {
                    //prev[next.getLineNumber()][next.getColum()] = current;
                    updateDistance(current, next);
                    next.setDistanceEstimate(getFscore(next));
                    que.add(next);
                }
            }
        }

    }

    private void updateDistance(Cordinate fromCordinate, Cordinate targetCordinate) {
        int old = distance[targetCordinate.getLineNumber()][targetCordinate.getColum()];
        int difference = Helpers.abs(fromCordinate.getLineNumber()-targetCordinate.getLineNumber())+Helpers.abs(fromCordinate.getColum()-targetCordinate.getColum());
        int prevDistance = distance[fromCordinate.getLineNumber()][fromCordinate.getColum()];

        if(old == 0 || (prevDistance + difference)<old) {
            distance[targetCordinate.getLineNumber()][targetCordinate.getColum()] = prevDistance + difference;
            prev[targetCordinate.getLineNumber()][targetCordinate.getColum()] = fromCordinate;
        }
    }

    private void updateDistance(int targetLine, int targetColum, int prevLine, int prevColum) {
        int old = distance[targetLine][targetColum];
        int difference = Helpers.abs(prevLine-targetLine)+Helpers.abs(prevColum-targetColum);
        int prevDistance = distance[prevLine][prevColum];

        if(old == 0 || (prevDistance + difference)<old) {
            distance[targetLine][targetColum] = prevDistance + difference;
        }
    }

    private void updateDistance(Cordinate target, int prevLine, int prevColum) {
        int targetLine = target.getLineNumber();
        int targetColum = target.getColum();
        int old = distance[targetLine][targetColum];
        int difference = Helpers.abs(prevLine-targetLine)+Helpers.abs(prevColum-targetColum);
        int prevDistance = distance[prevLine][prevColum];

        if(old == 0 || (prevDistance + difference)<old) {
            distance[targetLine][targetColum] = prevDistance + difference;
            prev[targetLine][targetColum] = new Cordinate(prevLine, prevColum);
        }
    }

    private CordinateList getNeightbours(Cordinate current) {
        CordinateList neighbours = new CordinateList();
        int cLine = current.getLineNumber();
        int cColum = current.getColum();
        Cordinate parent = prev[cLine][cColum];
        Cordinate neighbour;
        if(parent == null) { // this is the starting square
            if(map.terrainPassableAt(cLine+1 , cColum)) {
                neighbours.add(new Cordinate(cLine +1, cColum));
                distance[cLine+1][cColum] = 1;
                prev[cLine+1][cColum] = current;
            }
            if(map.terrainPassableAt(cLine-1 , cColum)) {
                neighbours.add(new Cordinate(cLine -1, cColum));
                distance[cLine-1][cColum] = 1;
                prev[cLine-1][cColum] = current;
            }
            if(map.terrainPassableAt(cLine, cColum+1)) {
                neighbours.add(new Cordinate(cLine, cColum+1));
                distance[cLine][cColum+1] = 1;
                prev[cLine][cColum+1] = current;
            }
            if(map.terrainPassableAt(cLine, cColum-1)) {
                neighbours.add(new Cordinate(cLine, cColum-1));
                distance[cLine][cColum-1] = 1;
                prev[cLine][cColum-1] = current;
            }
            
        } else {
            int pLine = parent.getLineNumber();
            int pColum = parent.getColum();
            int verticalDistance = cLine - pLine;
            int horizontalDistance = cColum - pColum;

            if (verticalDistance != 0) { 
                
                if(map.terrainPassableAt(cLine, cColum+1)) {
                    neighbour = new Cordinate(cLine, cColum+1);
                    neighbours.add(neighbour);
                    updateDistance(current, neighbour);
                }
                if(map.terrainPassableAt(cLine, cColum-1)) {
                    neighbour = new Cordinate(cLine, cColum-1);
                    neighbours.add(neighbour);
                    updateDistance(current, neighbour);
                }
                if(verticalDistance > 0) { // going down
                    if(map.terrainPassableAt(cLine+1, cColum)) {
                        neighbour = new Cordinate(cLine+1, cColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                } else { // going up
                    if(map.terrainPassableAt(cLine-1, cColum)) {
                        neighbour = new Cordinate(cLine-1, cColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                }
            } else if (horizontalDistance != 0) {
                if(map.terrainPassableAt(cLine+1, cColum)) {
                    neighbour = new Cordinate(cLine+1, cColum);
                    neighbours.add(neighbour);
                    updateDistance(current, neighbour);
                }
                if(map.terrainPassableAt(cLine-1, cColum)) {
                    neighbour = new Cordinate(cLine-1, cColum);
                    neighbours.add(neighbour);
                    updateDistance(current, neighbour);
                }
                if(horizontalDistance > 0) { // came from left
                    if(map.terrainPassableAt(cLine, cColum+1)) {
                        neighbour = new Cordinate(cLine, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                } else { // came from right;
                    if(map.terrainPassableAt(cLine, cColum-1)) {
                        neighbour = new Cordinate(cLine, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                }
            }

        }

        return neighbours;
    }

    private Cordinate jump(Cordinate target, Cordinate prevCordinate) {
        int tLine = target.getLineNumber();
        int tColum = target.getColum();
        if(foundGoal(target)) {
            return target;
        }

        if (!map.terrainPassableAt(tLine,tColum)) {
            return null;
        }

        int pLine = prevCordinate.getLineNumber();
        int pColum = prevCordinate.getColum();
        int verticalDistance = tLine - pLine;
        int horizontalDistance = tColum - pColum;
        if(horizontalDistance != 0) {
            if(horizontalDistance > 0) { // came from left
                if(map.terrainPassableAt(tLine+1, tColum) && !map.terrainPassableAt(tLine+1, tColum-1)) {
                    return target;
                }
                if(map.terrainPassableAt(tLine-1, tColum) && !map.terrainPassableAt(tLine-1, tColum-1)) {
                    return target;
                }
                return jump(new Cordinate(tLine,tColum+1), target);
            } else { // came from right
                if(map.terrainPassableAt(tLine +1, tColum) && !map.terrainPassableAt(tLine+1, tColum+1)) {
                    return target;
                }
                if(map.terrainPassableAt(tLine-1, tColum) && !map.terrainPassableAt(tLine-1, tColum+1)) {
                    return target;
                }
                return jump(new Cordinate(tLine,tColum-1), target);
            }
        } else if (verticalDistance != 0) {
            boolean goingDown = verticalDistance > 0;
            if(goingDown) { // going down
                if(map.terrainPassableAt(tLine, tColum-1) && !map.terrainPassableAt(tLine-1, tColum-1)) {
                    return target;
                }
                if(map.terrainPassableAt(tLine, tColum+1) && !map.terrainPassableAt(tLine-1, tColum+1)) {
                    return target;
                }
                
            } else { // going up
                if(map.terrainPassableAt(tLine, tColum-1) && !map.terrainPassableAt(tLine+1, tColum-1)) {
                    return target;
                }
                if(map.terrainPassableAt(tLine, tColum+1) && !map.terrainPassableAt(tLine+1, tColum+1)) {
                    return target;
                }
            }

            // also check for horizontal jump points when moving vertically
            Cordinate right = new Cordinate(tLine, tColum+1);
            Cordinate left = new Cordinate(tLine, tColum-1);
            if(jump(right,target) != null || jump(left,target) != null) {
                return target;
            }

            if(goingDown){
                return jump(new Cordinate(tLine+1,tColum), target);
            } else { // going up
                return jump(new Cordinate(tLine-1,tColum), target);
            }
        } 
        


        return null;
    }

    private boolean foundGoal(int line, int colum) {
        if (line == this.goalLine && colum == this.goalColum) {
            return true;
        }
        return false;
    }
    private boolean foundGoal(Cordinate c) {
        if (c.getLineNumber() == this.goalLine && c.getColum() == this.goalColum) {
            return true;
        }
        return false;
    }

    private int getFscore(int line, int colum) {
        int estimate = Helpers.abs((line -this.goalLine)) + Helpers.abs((colum - goalColum));
        return estimate + distance[line][colum];
    }
    
    private int getFscore(Cordinate c) {
        int line = c.getLineNumber();
        int colum = c.getColum();
        int estimate = Helpers.abs((line -this.goalLine)) + Helpers.abs((colum - goalColum));
        return estimate + distance[line][colum];
    }
}