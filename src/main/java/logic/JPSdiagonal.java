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
        Cordinate start = new Cordinate(startLineNumber, startColum);
        start.setDirection(0);
        que.add(start);
        this.goalLine = goalLineNumber;
        this.goalColum =goalColum;
        Cordinate current = new Cordinate (0,0);
        this.map = map;

        while(!que.isEmpty()) {
            current = que.poll();
            System.out.println("current "+current);
            int line = current.getLineNumber();
            int colum = current.getColum();

            if(visited[line][colum]) {
                //System.out.println("already visited");
                // do nothing
            } else { 
                visited[line][colum] = true;
                if(foundGoal(current)) {
                    System.out.println("JPS");
                    Helpers.printPath(prev, goalLine, goalColum, startLineNumber, startColum);
                    Helpers.printMapWithPath(prev, goalLine, goalColum, startLineNumber, startColum, map);
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
            Cordinate next = jump(neighbour);
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
        int direction = current.getDirection();

        switch(direction) {
            case 0:
                startingSquareNeighbours(current, neighbours);
                break;
            case 1:
                findNeightboursWhenGoingNorth(current, neighbours);
                break;
            case 2:
                findNeightboursWhenGoingNorthEast(current, neighbours);
                break;
            case 3:
                findNeightboursWhenGoingEast(current, neighbours);
                break;
            case 4:
                findNeightboursWhenGoingSouthEast(current, neighbours);
                break;
            case 5:
                findNeightboursWhenGoingSouth(current, neighbours);
                break;
            case 6:
                findNeightboursWhenGoingSouthWest(current, neighbours);
                break;
            case 7:
                findNeightboursWhenGoingWest(current, neighbours);
                break;
            case 8:
                findNeightboursWhenGoingNorthWest(current, neighbours);
                break;      
        }
        return neighbours;
    }

    private void startingSquareNeighbours(Cordinate current, CordinateList neighbours) {
        int currentLine = current.getLineNumber();
        int currentColum = current.getColum();
        Cordinate neighbour;
        if(map.terrainPassableAt(currentLine+1 , currentColum)) {
            neighbour = new Cordinate(currentLine +1, currentColum);
            neighbour.setDirection(5);
            neighbours.add(neighbour);
            distance[currentLine+1][currentColum] = 1;
            prev[currentLine+1][currentColum] = current;
        }
        if(map.terrainPassableAt(currentLine-1 , currentColum)) {
            neighbour = new Cordinate(currentLine -1, currentColum);
            neighbour.setDirection(1);
            neighbours.add(neighbour);
            distance[currentLine-1][currentColum] = 1;
            prev[currentLine-1][currentColum] = current;
        }
        if(map.terrainPassableAt(currentLine, currentColum+1)) {
            neighbour = new Cordinate(currentLine, currentColum+1);
            neighbour.setDirection(3);
            neighbours.add(neighbour);
            distance[currentLine][currentColum+1] = 1;
            prev[currentLine][currentColum+1] = current;
        }
        if(map.terrainPassableAt(currentLine, currentColum-1)) {
            neighbour = new Cordinate(currentLine, currentColum-1);
            neighbour.setDirection(7);
            neighbours.add(neighbour);
            distance[currentLine][currentColum-1] = 1;
            prev[currentLine][currentColum-1] = current;
        }
        if(map.canGoNorthEast(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine-1, currentColum+1);
            neighbour.setDirection(2);
            neighbours.add(neighbour);
            distance[currentLine-1][currentColum+1] = twosqrt;
            prev[currentLine-1][currentColum+1] = current;
        }
        if(map.canGoNorthWest(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine-1, currentColum-1);
            neighbour.setDirection(8);
            neighbours.add(neighbour);
            distance[currentLine-1][currentColum-1] = twosqrt;
            prev[currentLine-1][currentColum-1] = current;
        }
        if(map.canGoSouthEast(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine+1, currentColum+1);
            neighbour.setDirection(4);
            neighbours.add(neighbour);
            distance[currentLine+1][currentColum+1] = twosqrt;
            prev[currentLine+1][currentColum+1] = current;
        }
        if(map.canGoSouthWest(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine+1, currentColum-1);
            neighbour.setDirection(6);
            neighbours.add(neighbour);
            distance[currentLine+1][currentColum-1] = twosqrt;
            prev[currentLine+1][currentColum-1] = current;
        }
    }

    private void findNeightboursWhenGoingSouthEast(Cordinate current, CordinateList neighbours) {
        int currentLine = current.getLineNumber();
        int currentColum = current.getColum();
        Cordinate neighbour;
        if(map.terrainPassableAt(currentLine+1, currentColum)) {
            neighbour = new Cordinate(currentLine+1, currentColum);
            neighbour.setDirection(5);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.canGoSouthEast(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine+1, currentColum+1);
            neighbour.setDirection(4);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.terrainPassableAt(currentLine, currentColum+1)) {
            neighbour = new Cordinate(currentLine, currentColum+1);
            neighbour.setDirection(3);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
    }

    private void findNeightboursWhenGoingNorthWest(Cordinate current, CordinateList neighbours) {
        int currentLine = current.getLineNumber();
        int currentColum = current.getColum();
        Cordinate neighbour;
        if(map.terrainPassableAt(currentLine, currentColum-1)) {
            neighbour = new Cordinate(currentLine, currentColum-1);
            neighbour.setDirection(7);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.canGoNorthWest(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine-1, currentColum-1);
            neighbour.setDirection(8);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.terrainPassableAt(currentLine-1, currentColum)) {
            neighbour = new Cordinate(currentLine-1, currentColum);
            neighbour.setDirection(1);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
    }

    private void findNeightboursWhenGoingSouthWest(Cordinate current, CordinateList neighbours) {
        int currentLine = current.getLineNumber();
        int currentColum = current.getColum();
        Cordinate neighbour;
        if(map.terrainPassableAt(currentLine, currentColum-1)) {
            neighbour = new Cordinate(currentLine, currentColum-1);
            neighbour.setDirection(7);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.canGoSouthWest(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine+1, currentColum-1);
            neighbour.setDirection(6);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.terrainPassableAt(currentLine+1, currentColum)) {
            neighbour = new Cordinate(currentLine+1, currentColum);
            neighbour.setDirection(5);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
    }

    private void findNeightboursWhenGoingNorthEast(Cordinate current, CordinateList neighbours) {
        int currentLine = current.getLineNumber();
        int currentColum = current.getColum();
        Cordinate neighbour;
        if(map.canGoNorthEast(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine-1, currentColum+1);
            neighbour.setDirection(2);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.terrainPassableAt(currentLine-1, currentColum)) {
            neighbour = new Cordinate(currentLine-1, currentColum);
            neighbour.setDirection(1);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.terrainPassableAt(currentLine, currentColum+1)) {
            neighbour = new Cordinate(currentLine, currentColum+1);
            neighbour.setDirection(3);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
    }

    private void findNeightboursWhenGoingSouth(Cordinate current, CordinateList neighbours) {
        int currentLine = current.getLineNumber();
        int currentColum = current.getColum();
        Cordinate neighbour;
        if(map.terrainPassableAt(currentLine+1, currentColum)) {
            neighbour = new Cordinate(currentLine+1, currentColum);
            neighbour.setDirection(5);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.terrainPassableAt(currentLine, currentColum-1) && !map.canGoNorthWest(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine, currentColum-1);
            neighbour.setDirection(7);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
            if(map.canGoSouthWest(currentLine, currentColum)) {
                neighbour = new Cordinate(currentLine+1, currentColum-1);
                neighbour.setDirection(6);
                neighbours.add(neighbour);
                updateDistance(current, neighbour); 
            }

        }
        if(map.terrainPassableAt(currentLine, currentColum+1) && !map.canGoNorthEast(currentLine, currentColum) ) {
            neighbour = new Cordinate(currentLine, currentColum+1);
            neighbour.setDirection(3);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
            if(map.canGoSouthEast(currentLine, currentColum)) {
                neighbour = new Cordinate(currentLine+1, currentColum+1);
                neighbour.setDirection(4);
                neighbours.add(neighbour);
                updateDistance(current, neighbour);
            }

        }
    }

    private void findNeightboursWhenGoingNorth(Cordinate current, CordinateList neighbours) {
        int currentLine = current.getLineNumber();
        int currentColum = current.getColum();
        Cordinate neighbour;
        if(map.terrainPassableAt(currentLine-1, currentColum)) {
            neighbour = new Cordinate(currentLine-1, currentColum);
            neighbour.setDirection(1);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.terrainPassableAt(currentLine, currentColum-1) && !map.canGoSouthWest(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine, currentColum-1);
            neighbour.setDirection(7);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
            if(map.canGoNorthWest(currentLine, currentColum)) {
                neighbour = new Cordinate(currentLine-1, currentColum-1);
                neighbour.setDirection(8);
                neighbours.add(neighbour);
                updateDistance(current, neighbour);
            }
        }
        if(map.terrainPassableAt(currentLine, currentColum+1) && !map.canGoSouthEast(currentLine, currentColum) ) {
            neighbour = new Cordinate(currentLine, currentColum+1);
            neighbour.setDirection(3);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
            if(map.canGoNorthEast(currentLine, currentColum)) {
                neighbour = new Cordinate(currentLine-1, currentColum+1);
                neighbour.setDirection(2);
                neighbours.add(neighbour);
                updateDistance(current, neighbour);  
            }
        }
    }

    private void findNeightboursWhenGoingEast(Cordinate current, CordinateList neighbours) {
        int currentLine = current.getLineNumber();
        int currentColum = current.getColum();
        Cordinate neighbour;
        if(map.terrainPassableAt(currentLine, currentColum+1)) {
            neighbour = new Cordinate(currentLine, currentColum+1);
            neighbour.setDirection(3);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.terrainPassableAt(currentLine-1, currentColum) && !map.canGoNorthWest(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine-1, currentColum);
            neighbour.setDirection(1);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
            if(map.canGoNorthEast(currentLine, currentColum)) {
                neighbour = new Cordinate(currentLine-1, currentColum+1);
                neighbour.setDirection(2);
                neighbours.add(neighbour);
                updateDistance(current, neighbour);  
            }
        }
        if(map.terrainPassableAt(currentLine+1, currentColum) && !map.canGoSouthWest(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine+1, currentColum);
            neighbour.setDirection(5);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
            if(map.canGoSouthEast(currentLine, currentColum)) {
                neighbour = new Cordinate(currentLine+1, currentColum+1);
                neighbour.setDirection(4);
                neighbours.add(neighbour);
                updateDistance(current, neighbour);
            }
        }
    }

    private void findNeightboursWhenGoingWest(Cordinate current, CordinateList neighbours) {
        int currentLine = current.getLineNumber();
        int currentColum = current.getColum();
        Cordinate neighbour;
        if(map.terrainPassableAt(currentLine, currentColum-1)) {
            neighbour = new Cordinate(currentLine, currentColum-1);
            neighbour.setDirection(7);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
        }
        if(map.terrainPassableAt(currentLine-1, currentColum) && !map.canGoNorthEast(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine-1, currentColum);
            neighbour.setDirection(1);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
            if(map.canGoNorthWest(currentLine, currentColum)) {
                neighbour = new Cordinate(currentLine-1, currentColum-1);
                neighbour.setDirection(8);
                neighbours.add(neighbour);
                updateDistance(current, neighbour);
            }
        }
        if(map.terrainPassableAt(currentLine+1, currentColum) && !map.canGoSouthEast(currentLine, currentColum)) {
            neighbour = new Cordinate(currentLine+1, currentColum);
            neighbour.setDirection(5);
            neighbours.add(neighbour);
            updateDistance(current, neighbour);
            if(map.canGoSouthWest(currentLine, currentColum)) {
                neighbour = new Cordinate(currentLine+1, currentColum-1);
                neighbour.setDirection(6);
                neighbours.add(neighbour);
                updateDistance(current, neighbour); 
            }
        }
    }

    private Cordinate jump(Cordinate target) {
        //System.out.println("jumping to "+target+" direction "+target.getDirection());
        int currentLine = target.getLineNumber(); // change to target line
        int currentColum = target.getColum(); // change to target colum
        if(foundGoal(target)) {
            return target;
        }

        if (!map.terrainPassableAt(currentLine,currentColum)) {
            return null;
        }

        int direction = target.getDirection();
        Cordinate nextTarget;
        switch(direction) {
            case 1: //north
            if(map.terrainPassableAt(currentLine, currentColum-1) && !map.canGoSouthWest(currentLine, currentColum)) {
                return target;
            }
            if(map.terrainPassableAt(currentLine, currentColum+1) && !map.canGoSouthEast(currentLine, currentColum) ) {
                return target;
            }
            nextTarget = new Cordinate(currentLine-1, currentColum);
            nextTarget.setDirection(1);
            return jump(nextTarget);
            case 2: //north east
                // when moving diagonally must check for vertical and horizontal jumppoints
                nextTarget = new Cordinate(currentLine-1, currentColum);
                nextTarget.setDirection(1);
                if(jump(nextTarget) != null) { // check north
                    return target;
                }
                nextTarget = new Cordinate(currentLine, currentColum+1);
                nextTarget.setDirection(3);
                if(jump(nextTarget) != null) { // check east
                    return target;
                }
                // jump north east
                if(map.canGoNorthEast(currentLine, currentColum)) {
                    nextTarget = new Cordinate(currentLine-1, currentColum+1);
                    nextTarget.setDirection(2);
                    return jump(nextTarget);
                }
                break;
            case 3: // east
                if(map.terrainPassableAt(currentLine-1, currentColum) && !map.canGoNorthWest(currentLine, currentColum)) {
                    return target;
                }
                if(map.terrainPassableAt(currentLine+1, currentColum) && !map.canGoSouthWest(currentLine, currentColum)) {
                    return target;
                }
                nextTarget = new Cordinate(currentLine,currentColum+1);
                nextTarget.setDirection(3);
                return jump(nextTarget);
            case 4: // south east
                // when moving diagonally must check for vertical and horizontal jumppoints
                nextTarget = new Cordinate(currentLine,currentColum+1);
                nextTarget.setDirection(3);
                if(jump(nextTarget) != null) { // check east
                    return target;
                }
                nextTarget = new Cordinate(currentLine+1, currentColum);
                nextTarget.setDirection(5);
                if(jump(nextTarget) != null) { // check south
                    return target;
                }
                // jump south east
                if(map.canGoSouthEast(currentLine, currentColum)) {
                    nextTarget = new Cordinate(currentLine+1, currentColum+1);
                    nextTarget.setDirection(4);
                    return jump(nextTarget);
                }
                break;
            case 5: // south
                if(map.terrainPassableAt(currentLine, currentColum-1) && !map.canGoNorthWest(currentLine, currentColum)) {
                    return target;
                }
                if(map.terrainPassableAt(currentLine, currentColum+1) && !map.canGoNorthEast(currentLine, currentColum) ) {
                    return target;
                }
                nextTarget = new Cordinate(currentLine+1, currentColum);
                nextTarget.setDirection(5);
                return jump(nextTarget);
            case 6: // south west
                // when moving diagonally must check for vertical and horizontal jumppoints
                nextTarget = new Cordinate(currentLine, currentColum-1);
                nextTarget.setDirection(7);
                if(jump(nextTarget) != null) { // check west
                    return target;
                }
                nextTarget = new Cordinate(currentLine+1, currentColum);
                nextTarget.setDirection(5);
                if(jump(nextTarget) != null) { // check south
                    return target;
                }
                // jump south west
                if(map.canGoSouthWest(currentLine, currentColum)) {
                    nextTarget = new Cordinate(currentLine+1, currentColum-1);
                    nextTarget.setDirection(6);
                    return jump(nextTarget);
                }
                break;
            case 7: // west
                if(map.terrainPassableAt(currentLine-1, currentColum) && !map.canGoNorthEast(currentLine, currentColum)) {
                    return target;
                }
                if(map.terrainPassableAt(currentLine+1, currentColum) && !map.canGoSouthEast(currentLine, currentColum)) {
                    return target;
                }
                nextTarget = new Cordinate(currentLine, currentColum-1);
                nextTarget.setDirection(7);
                return jump(nextTarget);
            case 8: // north west
                // when moving diagonally must check for vertical and horizontal jumppoints
                nextTarget = new Cordinate(currentLine, currentColum-1);
                nextTarget.setDirection(7);
                if(jump(nextTarget) != null) { // check west
                    return target;
                }
                nextTarget = new Cordinate(currentLine-1, currentColum);
                nextTarget.setDirection(1);
                if(jump(nextTarget) != null) { // check north
                    return target;
                }
                // jump north west
                if(map.canGoNorthWest(currentLine, currentColum)) {
                    nextTarget = new Cordinate(currentLine-1, currentColum-1);
                    nextTarget.setDirection(8);
                    return jump(nextTarget);
                }
                break;      
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