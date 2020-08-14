package logic;

import datastructures.Cordinate;
import datastructures.CordinateQue;
import datastructures.MapList;
import datastructures.PathMap;

// todo: rename me
public class ComparisonEngine {
    BFS bfs;
    Dijkstra dijkstra;
    Astar astar;
    JPS jps;
    long numberOfPathsInComparison;
    long bfsTotalTime;
    long dijkstraTotalTime;
    long astartTotalTime;
    long jpsTotalTime;

    public ComparisonEngine() {
        this.bfs = new BFS();
        this.dijkstra = new Dijkstra();
        this.astar = new Astar();
        this.jps = new JPS();
    }

    public void compare(MapList list) {
        PathMap map;
        bfsTotalTime = 0;
        dijkstraTotalTime = 0;
        astartTotalTime = 0;
        jpsTotalTime = 0;
        numberOfPathsInComparison = 0;
        System.out.println("list size "+list.size());
        for(int i = 0; i<list.size();i++) {
            map = list.get(i);
            mapComparisons(map);
        }


        System.out.println("Totals: ");
        System.out.println("bfs total time used "+ toMS(bfsTotalTime)+ " ms, avg " + toMS((bfsTotalTime / numberOfPathsInComparison)) + " ms per path");
        System.out.println("djilstra total time used "+ toMS(dijkstraTotalTime)+ " ms, avg " + toMS((dijkstraTotalTime / numberOfPathsInComparison)) + " ms per path");
        System.out.println("astar total time used "+ toMS(astartTotalTime)+ " ms, avg " + toMS((astartTotalTime / numberOfPathsInComparison)) + " ms per path");
        System.out.println("jps total time used "+ toMS(jpsTotalTime)+ " ms, avg " + toMS((jpsTotalTime / numberOfPathsInComparison)) + " ms per path");

    }

    private void mapComparisons(PathMap map) {
        CordinateQue goalQue = map.getGoalPosQue();
        CordinateQue startQue = map.getStartPosQue();
        Cordinate start;
        Cordinate goal;
        int numberOfPathsForThisMap = goalQue.size();
        numberOfPathsInComparison += numberOfPathsForThisMap;
        int bfsDistance;
        int dijkstraDistance;
        int astartDistance;
        int jpsDistance;
        long bfsMapTotal = 0;
        long dijktraMapTotal = 0;
        long astarMapTotal = 0;
        long jpsMapTotal = 0;

        System.out.println("Stats for "+map.getName());

            while(!goalQue.isEmpty()) {
                start = startQue.poll();
                goal = goalQue.poll();
                int startLineNumber = start.getLineNumber();
                int startColum = start.getColum();
                int goalLineNumber = goal.getLineNumber();
                int goalColum = goal.getColum();
                System.out.println("Path from" + start + " to " + goal);


                // BFS
                long bfsStartTime = System.nanoTime();
                bfsDistance = bfs.findPath(map, startLineNumber, startColum, goalLineNumber, goalColum);
                long bfsEndTime = System.nanoTime();
                long bfsPathTime = bfsEndTime - bfsStartTime;
                bfsMapTotal += bfsPathTime;
                System.out.println("BFS " +toMS(bfsPathTime) + " ms");

                // Dijkstra
                long dijkstraStartTime = System.nanoTime();
                dijkstraDistance = dijkstra.findPath(map, startLineNumber, startColum, goalLineNumber, goalColum);
                long djikstraEndTime = System.nanoTime();
                long djikstraPathTime = djikstraEndTime - dijkstraStartTime;
                dijktraMapTotal += djikstraPathTime;
                System.out.println("Dijsktra " +toMS(djikstraPathTime) + " ms");

                // A*
                long astarStartTime = System.nanoTime();
                astartDistance = astar.findPath(map, startLineNumber, startColum, goalLineNumber, goalColum);
                long astartEndTime = System.nanoTime();
                long astartPathTime = astartEndTime - astarStartTime;
                astarMapTotal += astartPathTime;
                System.out.println("A* " +toMS(astartPathTime) + " ms");

                // JPS
                long jpsStartTime = System.nanoTime();
                jpsDistance = jps.findPath(map, startLineNumber, startColum, goalLineNumber, goalColum);
                long jpsEndTime = System.nanoTime();
                long jpsPathTime = jpsEndTime - jpsStartTime;
                jpsMapTotal += jpsPathTime;
                System.out.println("JPS " +toMS(jpsPathTime) + " ms");

                if(allAreSame(bfsDistance, dijkstraDistance, astartDistance, jpsDistance)) {
                    System.out.println("Distance was "+bfsDistance);
                } else {
                    System.out.println("WARNING diverge in path lenght between algorithms");
                    System.out.println("bfs "+bfsDistance+" dijkstra " +dijkstraDistance+ " astar " + astartDistance + " jps " + jpsDistance);
                }


            }

        if(numberOfPathsForThisMap > 1) {
            System.out.println("Total time spend on this map by BFS was "+ toMS(bfsMapTotal) + "ms, avg " + toMS((bfsMapTotal / numberOfPathsForThisMap)) + " ms");
            System.out.println("Total time spend on this map by Djikstra was "+ toMS(dijktraMapTotal) + "ms, avg " + toMS((dijktraMapTotal / numberOfPathsForThisMap)) + " ms");
            System.out.println("Total time spend on this map by A* was "+ toMS(astarMapTotal) + "ms, avg " + toMS((astarMapTotal / numberOfPathsForThisMap)) + " ms");
            System.out.println("Total time spend on this map by JPS was "+ toMS(jpsMapTotal) + "ms, avg " + toMS((jpsMapTotal / numberOfPathsForThisMap)) + " ms");
        }
        
        bfsTotalTime += bfsMapTotal;
        dijkstraTotalTime += dijktraMapTotal;

    }

    private long toMS(long nameTime) {
        return nameTime / 1000000;
    }

    private boolean allAreSame(int a, int b, int c, int d) {
        if(a != b) {
            return false;
        }
        if(b != c) {
            return false;
        }
        if(c != d) {
            return false;
        }
        return true;
    }
    
}