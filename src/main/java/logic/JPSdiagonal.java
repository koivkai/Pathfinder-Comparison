package logic;

import java.util.ArrayList;
import datastructures.Cordinate;
import datastructures.MinHeap;
import datastructures.PathMap;

public class JPSdiagonal {
    int goalLine;
    int goalColum;
    MinHeap que;
    boolean[][] visited;
    Cordinate[][] prev;
    double[][]distance;
    PathMap map;
    private final double twosqrt = Math.sqrt(2);

    public JPSdiagonal() {
    }

    public double findPath(PathMap map, int startLineNumber, int startColum, int goalLineNumber, int goalColum) {
        this.que = new MinHeap();
        visited = new boolean[map.getHeight()][map.getWidth()];
        prev = new Cordinate[map.getHeight()][map.getWidth()];
        distance = new double[map.getHeight()][map.getWidth()];
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
        ArrayList<Cordinate> neigbours = getNeightbours(current);
        for(Cordinate c:neigbours) {

            Cordinate next = jump(c, prev[c.getLineNumber()][c.getColum()]);
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
        int verticalDistance = targetCordinate.getLineNumber() - fromCordinate.getLineNumber();
        int horizontalDistance = targetCordinate.getColum() - fromCordinate.getColum();
        double oldDistanceToTarget = distance[targetCordinate.getLineNumber()][targetCordinate.getColum()];
        double prevDistance = distance[fromCordinate.getLineNumber()][fromCordinate.getColum()];

        if(verticalDistance != 0 && horizontalDistance != 0) { //moving diagonally
            double distanceFromHereToTarget = prevDistance + (Helpers.abs(verticalDistance) * twosqrt);
            if(oldDistanceToTarget == 0 || distanceFromHereToTarget < oldDistanceToTarget) {
                distance[targetCordinate.getLineNumber()][targetCordinate.getColum()] = distanceFromHereToTarget;
                prev[targetCordinate.getLineNumber()][targetCordinate.getColum()] = fromCordinate;
            }
        } else { // moving orthogonally
            int difference = Helpers.abs(verticalDistance)+Helpers.abs(horizontalDistance);
            if(oldDistanceToTarget == 0 || (prevDistance + difference)<oldDistanceToTarget) {
                distance[targetCordinate.getLineNumber()][targetCordinate.getColum()] = prevDistance + difference;
                prev[targetCordinate.getLineNumber()][targetCordinate.getColum()] = fromCordinate;
            }
        }
        
    }

    private ArrayList<Cordinate> getNeightbours(Cordinate current) {
        ArrayList<Cordinate> neighbours = new ArrayList<Cordinate>();
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
            if(map.canGoNorthEast(cLine, cColum)) {
                neighbours.add(new Cordinate(cLine-1, cColum+1));
                distance[cLine-1][cColum+1] = twosqrt;
                prev[cLine-1][cColum+1] = current;
            }
            if(map.canGoNorthWest(cLine, cColum)) {
                neighbours.add(new Cordinate(cLine-1, cColum-1));
                distance[cLine-1][cColum-1] = twosqrt;
                prev[cLine-1][cColum-1] = current;
            }
            if(map.canGoSouthEast(cLine, cColum)) {
                neighbours.add(new Cordinate(cLine+1, cColum+1));
                distance[cLine+1][cColum+1] = twosqrt;
                prev[cLine+1][cColum+1] = current;
            }
            if(map.canGoSouthWest(cLine, cColum)) {
                neighbours.add(new Cordinate(cLine+1, cColum-1));
                distance[cLine+1][cColum-1] = twosqrt;
                prev[cLine+1][cColum-1] = current;
            }
            
        } else {
            int pLine = parent.getLineNumber();
            int pColum = parent.getColum();
            int verticalDistance = cLine - pLine;
            int horizontalDistance = cColum - pColum;

            if (verticalDistance != 0 && horizontalDistance != 0) {
                if (verticalDistance > 0 && horizontalDistance > 0) { // going south east
                    if(map.terrainPassableAt(cLine+1, cColum)) {
                        neighbour = new Cordinate(cLine+1, cColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthEast(cLine, cColum)) {
                        neighbour = new Cordinate(cLine+1, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(cLine, cColum+1)) {
                        neighbour = new Cordinate(cLine, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(cLine-1, cColum+1)&& !map.terrainPassableAt(cLine-1, cColum)) {
                        neighbour = new Cordinate(cLine-1, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(cLine+1, cColum-1) && !map.terrainPassableAt(cLine, cColum-1)) {
                        neighbour = new Cordinate(cLine+1, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                } else if(verticalDistance < 0 && horizontalDistance < 0) { // going northwest
                    if(map.terrainPassableAt(cLine, cColum-1)) {
                        neighbour = new Cordinate(cLine, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthWest(cLine, cColum)) {
                        neighbour = new Cordinate(cLine-1, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(cLine-1, cColum)) {
                        neighbour = new Cordinate(cLine-1, cColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthWest(cLine, cColum) && !map.terrainPassableAt(cLine-1, cColum)) {
                        neighbour = new Cordinate(cLine+1, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthEast(cLine, cColum) && !map.terrainPassableAt(cLine, cColum+1)) {
                        neighbour = new Cordinate(cLine-1, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                } else if(verticalDistance > 0) { // going south west
                    if(map.terrainPassableAt(cLine, cColum-1)) {
                        neighbour = new Cordinate(cLine, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthWest(cLine, cColum)) {
                        neighbour = new Cordinate(cLine+1, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(cLine+1, cColum)) {
                        neighbour = new Cordinate(cLine+1, cColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthEast(cLine, cColum) && !map.terrainPassableAt(cLine, cColum+1)) {
                        neighbour = new Cordinate(cLine+1, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthWest(cLine, cColum) && !map.terrainPassableAt(cLine-1, cColum)) {
                        neighbour = new Cordinate(cLine-1, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                } else { // going north east
                    if(map.canGoNorthEast(cLine, cColum)) {
                        neighbour = new Cordinate(cLine-1, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(cLine-1, cColum)) {
                        neighbour = new Cordinate(cLine-1, cColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(cLine, cColum+1)) {
                        neighbour = new Cordinate(cLine, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthWest(cLine, cColum) && !map.terrainPassableAt(cLine, cColum-1)) {
                        neighbour = new Cordinate(cLine-1, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthEast(cLine, cColum) && !map.terrainPassableAt(cLine+1, cColum)){
                        neighbour = new Cordinate(cLine+1, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                }

            }else if (verticalDistance != 0) { 
                if(verticalDistance > 0) { // going south
                    if(map.terrainPassableAt(cLine+1, cColum)) {
                        neighbour = new Cordinate(cLine+1, cColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthEast(cLine, cColum) && !map.terrainPassableAt(cLine, cColum+1)) {
                        neighbour = new Cordinate(cLine+1, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthWest(cLine, cColum) && !map.terrainPassableAt(cLine, cColum-1)) {
                        neighbour = new Cordinate(cLine+1, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                } else { // going north
                    if(map.terrainPassableAt(cLine-1, cColum)) {
                        neighbour = new Cordinate(cLine-1, cColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthWest(cLine, cColum) && !map.terrainPassableAt(cLine, cColum-1)) {
                        neighbour = new Cordinate(cLine-1, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthEast(cLine, cColum) && !map.terrainPassableAt(cLine, cColum+1)) {
                        neighbour = new Cordinate(cLine-1, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                }
            } else if (horizontalDistance != 0) {

                if(horizontalDistance > 0) { // going east
                    if(map.terrainPassableAt(cLine, cColum+1)) {
                        neighbour = new Cordinate(cLine, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthEast(cLine, cColum) && !map.terrainPassableAt(cLine-1, cColum)) {
                        neighbour = new Cordinate(cLine-1, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthEast(cLine, cColum) && !map.terrainPassableAt(cLine+1, cColum)) {
                        neighbour = new Cordinate(cLine+1, cColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }

                } else { // going west
                    if(map.terrainPassableAt(cLine, cColum-1)) {
                        neighbour = new Cordinate(cLine, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthWest(cLine, cColum) && !map.terrainPassableAt(cLine+1, cColum)) {
                        neighbour = new Cordinate(cLine+1, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthWest(cLine, cColum) && !map.terrainPassableAt(cLine-1, cColum)) {
                        neighbour = new Cordinate(cLine-1, cColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                }
            }

        }

        return neighbours;
    }

    private Cordinate jump(Cordinate target, Cordinate prevCordinate) {
        int cLine = target.getLineNumber(); // change to target line
        int cColum = target.getColum(); // change to target colum
        if(foundGoal(target)) {
            return target;
        }

        if (!map.terrainPassableAt(cLine,cColum)) {
            return null;
        }
        

        int pLine = prevCordinate.getLineNumber();
        int pColum = prevCordinate.getColum();
        int verticalDistance = cLine - pLine;
        int horizontalDistance = cColum - pColum;
        
        if(horizontalDistance != 0 && verticalDistance != 0) { // diogonal
            if(verticalDistance > 0 && horizontalDistance > 0) { // going south east
                // when moving diagonally must check for vertical and horizontal jumppoints
                if(jump(new Cordinate(cLine, cColum+1), target) != null) { // check east
                    return target;
                }
                if(jump(new Cordinate(cLine+1, cColum), target) != null) { // check south
                    return target;
                }
                //check for forced diagonal neighbours
                if(map.terrainPassableAt(cLine-1, cColum+1)&& !map.terrainPassableAt(cLine-1, cColum)) {
                    return target;
                }
                if(map.terrainPassableAt(cLine+1, cColum-1) && !map.terrainPassableAt(cLine, cColum-1)) {
                    return target;
                }
                // jump south east
                return jump(new Cordinate(cLine+1, cColum+1),target);
            } else if (verticalDistance < 0 && horizontalDistance < 0) { // going north west
                // when moving diagonally must check for vertical and horizontal jumppoints
                if(jump(new Cordinate(cLine, cColum-1), target) != null) { // check west
                    return target;
                }
                if(jump(new Cordinate(cLine-1, cColum), target) != null) { // check north
                    return target;
                }
                //check for forced diagonal neighbours
                if(map.canGoSouthWest(cLine, cColum) && !map.terrainPassableAt(cLine-1, cColum)) {
                    return target;
                }
                if(map.canGoNorthEast(cLine, cColum) && !map.terrainPassableAt(cLine, cColum+1)) {
                    return target;
                }
                // jump north west
                return jump(new Cordinate(cLine-1, cColum-1),target);
            } else if (verticalDistance > 0) { // going south west
                // when moving diagonally must check for vertical and horizontal jumppoints
                if(jump(new Cordinate(cLine, cColum-1), target) != null) { // check west
                    return target;
                }
                if(jump(new Cordinate(cLine+1, cColum), target) != null) { // check south
                    return target;
                }
                //check for forced diagonal neighbours
                if(map.canGoSouthEast(cLine, cColum) && !map.terrainPassableAt(cLine, cColum+1)) {
                    return target;
                }
                if(map.canGoNorthWest(cLine, cColum) && !map.terrainPassableAt(cLine-1, cColum)) {
                    return target;
                }
                // jump south west
                return jump(new Cordinate(cLine+1, cColum-1), target);
            } else { // going north east
                // when moving diagonally must check for vertical and horizontal jumppoints
                if(jump(new Cordinate(cLine-1, cColum), target) != null) { // check north
                    return target;
                }
                if(jump(new Cordinate(cLine, cColum+1), target) != null) { // check east
                    return target;
                }
                //check for forced diagonal neighbours
                if(map.canGoNorthWest(cLine, cColum) && !map.terrainPassableAt(cLine, cColum-1)) {
                    return target;
                }
                if(map.canGoSouthEast(cLine, cColum) && !map.terrainPassableAt(cLine+1, cColum)){
                    return target;
                }
                // jump north east
                return jump(new Cordinate(cLine-1, cColum+1), target);
            }
        } else if(horizontalDistance != 0) {
            if(horizontalDistance > 0) { // going east
                if(map.canGoNorthEast(cLine, cColum) && !map.terrainPassableAt(cLine-1, cColum)) {
                    return target;
                }
                if(map.canGoSouthEast(cLine, cColum) && !map.terrainPassableAt(cLine+1, cColum)) {
                    return target;
                }
                return jump(new Cordinate(cLine,cColum+1), target);
            } else { // going west
                if(map.canGoSouthWest(cLine, cColum) && !map.terrainPassableAt(cLine+1, cColum)) {
                    return target;
                }
                if(map.canGoNorthWest(cLine, cColum) && !map.terrainPassableAt(cLine-1, cColum)) {
                    return target;
                }
                return jump(new Cordinate(cLine,cColum-1), target);
            }
        } else if (verticalDistance != 0) {
            boolean goingSouth = verticalDistance > 0;
            if(goingSouth) { // going south
                if(map.canGoSouthEast(cLine, cColum) && !map.terrainPassableAt(cLine, cColum+1)) {
                    return target;
                }
                if(map.canGoSouthWest(cLine, cColum) && !map.terrainPassableAt(cLine, cColum-1)) {
                    return target;
                }
                return jump(new Cordinate(cLine+1, cColum),target);
                
            } else { // going north
                if(map.canGoNorthWest(cLine, cColum) && !map.terrainPassableAt(cLine, cColum-1)) {
                    return target;
                }
                if(map.canGoNorthEast(cLine, cColum) && !map.terrainPassableAt(cLine, cColum+1)) {
                    return target;
                }
                return jump(new Cordinate(cLine-1, cColum), target);
            }

        } 
        


        return null;
    }

    private boolean foundGoal(Cordinate c) {
        if (c.getLineNumber() == this.goalLine && c.getColum() == this.goalColum) {
            return true;
        }
        return false;
    }
    
    private double getFscore(Cordinate c) {
        int line = c.getLineNumber();
        int colum = c.getColum();
        double estimate = Math.max(Helpers.abs((line -this.goalLine)), Helpers.abs((colum - goalColum)));
        return estimate + distance[line][colum];
    }
    
}