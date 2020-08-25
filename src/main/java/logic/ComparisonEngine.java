package logic;

import datastructures.Cordinate;
import datastructures.CordinateQue;
import datastructures.DoublesQue;
import datastructures.MapList;
import datastructures.PathMap;

// todo: rename me
public class ComparisonEngine {
    Dijkstra dijkstra;
    Astar astar;
    JPSdiagonal jps;
    long numberOfPathsInComparison;
    long dijkstraTotalTime;
    long astartTotalTime;
    long jpsTotalTime;
    boolean pathLenghtMisMatch;

    public ComparisonEngine() {
        this.dijkstra = new Dijkstra();
        this.astar = new Astar();
        this.jps = new JPSdiagonal();
    }

    public void compare(MapList list) {
        pathLenghtMisMatch = false;
        PathMap map;
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
        System.out.println("djilstra total time used "+ toMS(dijkstraTotalTime)+ " ms, avg " + toMS((dijkstraTotalTime / numberOfPathsInComparison)) + " ms per path");
        System.out.println("astar total time used "+ toMS(astartTotalTime)+ " ms, avg " + toMS((astartTotalTime / numberOfPathsInComparison)) + " ms per path");
        System.out.println("jps total time used "+ toMS(jpsTotalTime)+ " ms, avg " + toMS((jpsTotalTime / numberOfPathsInComparison)) + " ms per path");
        if(pathLenghtMisMatch) {
            System.out.println("There was atleast one instance where the algorithms got different results compared to each other or the scenarious expected lenght");
        }
    }

    private void mapComparisons(PathMap map) {
        CordinateQue goalQue = map.getGoalPosQue();
        CordinateQue startQue = map.getStartPosQue();
        DoublesQue optimalLenghtQue = map.getOptimalPathLenghtQue();
        Cordinate start;
        Cordinate goal;
        int numberOfPathsForThisMap = goalQue.size();
        numberOfPathsInComparison += numberOfPathsForThisMap;
        double dijkstraDistance;
        double astartDistance;
        double jpsDistance;
        long dijktraMapTotal = 0;
        long astarMapTotal = 0;
        long jpsMapTotal = 0;

        //map.print();
        System.out.println("Stats for "+map.getName());

            while(!goalQue.isEmpty()) {
                start = startQue.poll();
                goal = goalQue.poll();
                int startLineNumber = start.getLineNumber();
                int startColum = start.getColum();
                int goalLineNumber = goal.getLineNumber();
                int goalColum = goal.getColum();
                double optimalLenght = optimalLenghtQue.poll();
                System.out.println("Path from" + start + " to " + goal);


                // Dijkstra
                long dijkstraStartTime = System.nanoTime();
                dijkstraDistance = dijkstra.findPath(map, startLineNumber, startColum, goalLineNumber, goalColum);
                long djikstraEndTime = System.nanoTime();
                long djikstraPathTime = djikstraEndTime - dijkstraStartTime;
                dijktraMapTotal += djikstraPathTime;
                //System.out.println("Dijsktra " +toMS(djikstraPathTime) + " ms");

                // A*
                long astarStartTime = System.nanoTime();
                astartDistance = astar.findPath(map, startLineNumber, startColum, goalLineNumber, goalColum);
                long astartEndTime = System.nanoTime();
                long astartPathTime = astartEndTime - astarStartTime;
                astarMapTotal += astartPathTime;
                //System.out.println("A* " +toMS(astartPathTime) + " ms");

                // JPS
                long jpsStartTime = System.nanoTime();
                jpsDistance = jps.findPath(map, startLineNumber, startColum, goalLineNumber, goalColum);
                long jpsEndTime = System.nanoTime();
                long jpsPathTime = jpsEndTime - jpsStartTime;
                jpsMapTotal += jpsPathTime;
                //System.out.println("JPS " +toMS(jpsPathTime) + " ms");

                
                if(allAreAboutTheSame(dijkstraDistance, astartDistance, jpsDistance, optimalLenght)) {
                    //System.out.println("Distance was "+dijkstraDistance);
                } else {
                    pathLenghtMisMatch = true;
                    System.out.println("WARNING diverge in path lenght between algorithms");
                    System.out.println("dijkstra " +dijkstraDistance+ " astar " + astartDistance + " jps " + jpsDistance + " scen optimal lenght " + optimalLenght);
                }
                

            }

        if(numberOfPathsForThisMap > 1) {
            //System.out.println("Total time spend on this map by Djikstra was "+ toMS(dijktraMapTotal) + "ms, avg " + toMS((dijktraMapTotal / numberOfPathsForThisMap)) + " ms");
            //System.out.println("Total time spend on this map by A* was "+ toMS(astarMapTotal) + "ms, avg " + toMS((astarMapTotal / numberOfPathsForThisMap)) + " ms");
            //System.out.println("Total time spend on this map by JPS was "+ toMS(jpsMapTotal) + "ms, avg " + toMS((jpsMapTotal / numberOfPathsForThisMap)) + " ms");
        }
        
        dijkstraTotalTime += dijktraMapTotal;
        astartTotalTime += astarMapTotal;
        jpsTotalTime += jpsMapTotal;

    }

    private long toMS(long nameTime) {
        return nameTime / 1000000;
    }

    private boolean allAreAboutTheSame(double a, double b, double c, double d) {
        if(Helpers.abs(a-b) > 0.01) {
            return false; 
        }
        if(Helpers.abs(b-c) > 0.01) {
            return false; 
        }
        if(Helpers.abs(c-d) > 0.01) {
            return false; 
        }
        return true;
    }
    
}