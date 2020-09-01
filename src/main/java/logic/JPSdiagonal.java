package logic;

import datastructures.Cordinate;
import datastructures.CordinateList;
import datastructures.MinHeap;
import datastructures.PathMap;

/**
 * Implementation of the Jump Point Search pathfinding algorithm in a uniform grid.
 * This version allows movement in 8 directions.(i.e. orthagonal and diagonal movement)
 */
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
            //System.out.println("current "+current);
            int line = current.getLineNumber();
            int colum = current.getColum();

            if(visited[line][colum]) {
                //System.out.println("already visited");
                // do nothing
            } else { 
                visited[line][colum] = true;
                if(foundGoal(current)) {
                    // System.out.println("JPS");
                    //Helpers.printPath(prev, goalLine, goalColum, startLineNumber, startColum);
                    //Helpers.printMapWithPath(prev, goalLine, goalColum, startLineNumber, startColum, map);
                    return distance[line][colum];
                }
                findSuccessors(current);
                
            }

        }
        return -1;
    }
    private void findSuccessors(Cordinate current) {
        CordinateList neighbours = getNeightbours(current);
        //ArrayList<Cordinate> neigbours = getNeightbours(current);
        //System.out.println("neighbours " +neigbours);
        for(int i = 0; i < neighbours.size(); i++) {
            Cordinate neighbour = neighbours.get(i);
            Cordinate next = jump(neighbour, current);
            //System.out.println("next "+next);
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
            double distanceFromHereToTarget = prevDistance + ((double)(Helpers.abs(verticalDistance)) * twosqrt);
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

    private CordinateList getNeightbours(Cordinate current) {
        CordinateList neighbours = new CordinateList();
        int currentLine = current.getLineNumber();
        int currentColum = current.getColum();
        Cordinate parent = prev[currentLine][currentColum];
        Cordinate neighbour;
        if(parent == null) { // this is the starting square
            if(map.terrainPassableAt(currentLine+1 , currentColum)) {
                neighbours.add(new Cordinate(currentLine +1, currentColum));
                distance[currentLine+1][currentColum] = 1;
                prev[currentLine+1][currentColum] = current;
            }
            if(map.terrainPassableAt(currentLine-1 , currentColum)) {
                neighbours.add(new Cordinate(currentLine -1, currentColum));
                distance[currentLine-1][currentColum] = 1;
                prev[currentLine-1][currentColum] = current;
            }
            if(map.terrainPassableAt(currentLine, currentColum+1)) {
                neighbours.add(new Cordinate(currentLine, currentColum+1));
                distance[currentLine][currentColum+1] = 1;
                prev[currentLine][currentColum+1] = current;
            }
            if(map.terrainPassableAt(currentLine, currentColum-1)) {
                neighbours.add(new Cordinate(currentLine, currentColum-1));
                distance[currentLine][currentColum-1] = 1;
                prev[currentLine][currentColum-1] = current;
            }
            if(map.canGoNorthEast(currentLine, currentColum)) {
                neighbours.add(new Cordinate(currentLine-1, currentColum+1));
                distance[currentLine-1][currentColum+1] = twosqrt;
                prev[currentLine-1][currentColum+1] = current;
            }
            if(map.canGoNorthWest(currentLine, currentColum)) {
                neighbours.add(new Cordinate(currentLine-1, currentColum-1));
                distance[currentLine-1][currentColum-1] = twosqrt;
                prev[currentLine-1][currentColum-1] = current;
            }
            if(map.canGoSouthEast(currentLine, currentColum)) {
                neighbours.add(new Cordinate(currentLine+1, currentColum+1));
                distance[currentLine+1][currentColum+1] = twosqrt;
                prev[currentLine+1][currentColum+1] = current;
            }
            if(map.canGoSouthWest(currentLine, currentColum)) {
                neighbours.add(new Cordinate(currentLine+1, currentColum-1));
                distance[currentLine+1][currentColum-1] = twosqrt;
                prev[currentLine+1][currentColum-1] = current;
            }
            
        } else {
            int pLine = parent.getLineNumber();
            int pColum = parent.getColum();
            int verticalDistance = currentLine - pLine;
            int horizontalDistance = currentColum - pColum;

            if (verticalDistance != 0 && horizontalDistance != 0) {
                if (verticalDistance > 0 && horizontalDistance > 0) { // going south east
                    if(map.terrainPassableAt(currentLine+1, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthEast(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine, currentColum+1)) {
                        neighbour = new Cordinate(currentLine, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }

                } else if(verticalDistance < 0 && horizontalDistance < 0) { // going northwest
                    if(map.terrainPassableAt(currentLine, currentColum-1)) {
                        neighbour = new Cordinate(currentLine, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthWest(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine-1, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }

                } else if(verticalDistance > 0) { // going south west
                    if(map.terrainPassableAt(currentLine, currentColum-1)) {
                        neighbour = new Cordinate(currentLine, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthWest(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine+1, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }

                } else { // going north east
                    if(map.canGoNorthEast(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine-1, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine, currentColum+1)) {
                        neighbour = new Cordinate(currentLine, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }

                }

            }else if (verticalDistance != 0) { 
                if(verticalDistance > 0) { // going south
                    if(map.terrainPassableAt(currentLine+1, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthEast(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthWest(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine, currentColum-1) && !map.canGoNorthWest(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine, currentColum+1) && !map.canGoNorthEast(currentLine, currentColum) ) {
                        neighbour = new Cordinate(currentLine, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                } else { // going north
                    if(map.terrainPassableAt(currentLine-1, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthWest(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthEast(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine, currentColum-1) && !map.canGoSouthWest(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine, currentColum+1) && !map.canGoSouthEast(currentLine, currentColum) ) {
                        neighbour = new Cordinate(currentLine, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                }
            } else if (horizontalDistance != 0) {

                if(horizontalDistance > 0) { // going east
                    if(map.terrainPassableAt(currentLine, currentColum+1)) {
                        neighbour = new Cordinate(currentLine, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthEast(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthEast(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum+1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine-1, currentColum) && !map.canGoNorthWest(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine+1, currentColum) && !map.canGoSouthWest(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }

                } else { // going west
                    if(map.terrainPassableAt(currentLine, currentColum-1)) {
                        neighbour = new Cordinate(currentLine, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoSouthWest(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.canGoNorthWest(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum-1);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine-1, currentColum) && !map.canGoNorthEast(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine-1, currentColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                    if(map.terrainPassableAt(currentLine+1, currentColum) && !map.canGoSouthEast(currentLine, currentColum)) {
                        neighbour = new Cordinate(currentLine+1, currentColum);
                        neighbours.add(neighbour);
                        updateDistance(current, neighbour);
                    }
                }
            }

        }

        return neighbours;
    }

    private Cordinate jump(Cordinate target, Cordinate prevCordinate) {
        //System.out.println("jumping to "+target+" from "+prevCordinate);
        int currentLine = target.getLineNumber(); // change to target line
        int currentColum = target.getColum(); // change to target colum
        if(foundGoal(target)) {
            return target;
        }

        if (!map.terrainPassableAt(currentLine,currentColum)) {
            return null;
        }
        

        int pLine = prevCordinate.getLineNumber();
        int pColum = prevCordinate.getColum();
        int verticalDistance = currentLine - pLine;
        int horizontalDistance = currentColum - pColum;
        
        if(horizontalDistance != 0 && verticalDistance != 0) { // diagonal
            if(verticalDistance > 0 && horizontalDistance > 0) { // going south east
                // when moving diagonally must check for vertical and horizontal jumppoints
                if(jump(new Cordinate(currentLine, currentColum+1), target) != null) { // check east
                    return target;
                }
                if(jump(new Cordinate(currentLine+1, currentColum), target) != null) { // check south
                    return target;
                }
                // jump south east
                if(map.canGoSouthEast(currentLine, currentColum)) {
                    return jump(new Cordinate(currentLine+1, currentColum+1),target);
                }
                
            } else if (verticalDistance < 0 && horizontalDistance < 0) { // going north west
                // when moving diagonally must check for vertical and horizontal jumppoints
                if(jump(new Cordinate(currentLine, currentColum-1), target) != null) { // check west
                    return target;
                }
                if(jump(new Cordinate(currentLine-1, currentColum), target) != null) { // check north
                    return target;
                }
                // jump north west
                if(map.canGoNorthWest(currentLine, currentColum)) {
                    return jump(new Cordinate(currentLine-1, currentColum-1),target);
                }
            } else if (verticalDistance > 0) { // going south west
                // when moving diagonally must check for vertical and horizontal jumppoints
                if(jump(new Cordinate(currentLine, currentColum-1), target) != null) { // check west
                    return target;
                }
                if(jump(new Cordinate(currentLine+1, currentColum), target) != null) { // check south
                    return target;
                }
                // jump south west
                if(map.canGoSouthWest(currentLine, currentColum)) {
                    return jump(new Cordinate(currentLine+1, currentColum-1), target);
                }
            } else { // going north east
                // when moving diagonally must check for vertical and horizontal jumppoints
                if(jump(new Cordinate(currentLine-1, currentColum), target) != null) { // check north
                    return target;
                }
                if(jump(new Cordinate(currentLine, currentColum+1), target) != null) { // check east
                    return target;
                }
                // jump north east
                if(map.canGoNorthEast(currentLine, currentColum)) {
                    return jump(new Cordinate(currentLine-1, currentColum+1), target);
                }
            }
        } else if(horizontalDistance != 0) {
            if(horizontalDistance > 0) { // going east
                if(map.terrainPassableAt(currentLine-1, currentColum) && !map.canGoNorthWest(currentLine, currentColum)) {
                    return target;
                }
                if(map.terrainPassableAt(currentLine+1, currentColum) && !map.canGoSouthWest(currentLine, currentColum)) {
                    return target;
                }
                return jump(new Cordinate(currentLine,currentColum+1), target);
            } else { // going west
                if(map.terrainPassableAt(currentLine-1, currentColum) && !map.canGoNorthEast(currentLine, currentColum)) {
                    return target;
                }
                if(map.terrainPassableAt(currentLine+1, currentColum) && !map.canGoSouthEast(currentLine, currentColum)) {
                    return target;
                }
                return jump(new Cordinate(currentLine,currentColum-1), target);
            }
        } else if (verticalDistance != 0) {
            boolean goingSouth = verticalDistance > 0;
            if(goingSouth) { // going south
                if(map.terrainPassableAt(currentLine, currentColum-1) && !map.canGoNorthWest(currentLine, currentColum)) {
                    return target;
                }
                if(map.terrainPassableAt(currentLine, currentColum+1) && !map.canGoNorthEast(currentLine, currentColum) ) {
                    return target;
                }
                return jump(new Cordinate(currentLine+1, currentColum),target);
                
            } else { // going north
                if(map.terrainPassableAt(currentLine, currentColum-1) && !map.canGoSouthWest(currentLine, currentColum)) {
                    return target;
                }
                if(map.terrainPassableAt(currentLine, currentColum+1) && !map.canGoSouthEast(currentLine, currentColum) ) {
                    return target;
                }
                return jump(new Cordinate(currentLine-1, currentColum), target);
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