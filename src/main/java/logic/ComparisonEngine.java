package logic;

import datastructures.Cordinate;
import datastructures.CordinateQue;
import datastructures.DoublesQue;
import datastructures.MapList;
import datastructures.PathMap;

/**
 * A class for comparing the speed at which Djikstra, A* and JPS find the sortest path.
 */
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
        System.out.println("djilstra total time used "+ convertNStoSessibleUnitOfTime(dijkstraTotalTime)+ ", avg " + convertNStoSessibleUnitOfTime((dijkstraTotalTime / numberOfPathsInComparison)) + " per path");
        System.out.println("astar total time used "+ convertNStoSessibleUnitOfTime(astartTotalTime)+ ", avg " + convertNStoSessibleUnitOfTime((astartTotalTime / numberOfPathsInComparison)) + " per path");
        System.out.println("jps total time used "+ convertNStoSessibleUnitOfTime(jpsTotalTime)+ ", avg " + convertNStoSessibleUnitOfTime((jpsTotalTime / numberOfPathsInComparison)) + " per path");
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

        System.out.println("Stats for "+map.getName());

            while(!goalQue.isEmpty()) {
                start = startQue.poll();
                goal = goalQue.poll();
                int startLineNumber = start.getLineNumber();
                int startColum = start.getColum();
                int goalLineNumber = goal.getLineNumber();
                int goalColum = goal.getColum();
                double optimalLenght = optimalLenghtQue.poll();
                 //System.out.println("Path from" + start + " to " + goal + " optimal lenght " +optimalLenght);


                // Dijkstra
                long dijkstraStartTime = System.nanoTime();
                dijkstraDistance = dijkstra.findPath(map, startLineNumber, startColum, goalLineNumber, goalColum);
                long djikstraEndTime = System.nanoTime();
                long djikstraPathTime = djikstraEndTime - dijkstraStartTime;
                dijktraMapTotal += djikstraPathTime;
                 //System.out.println("Dijsktra " +convertNStoSessibleUnitOfTime(djikstraPathTime));

                // A*
                long astarStartTime = System.nanoTime();
                astartDistance = astar.findPath(map, startLineNumber, startColum, goalLineNumber, goalColum);
                long astartEndTime = System.nanoTime();
                long astartPathTime = astartEndTime - astarStartTime;
                astarMapTotal += astartPathTime;
                 //System.out.println("A* " +convertNStoSessibleUnitOfTime(astartPathTime));

                // JPS
                long jpsStartTime = System.nanoTime();
                jpsDistance = jps.findPath(map, startLineNumber, startColum, goalLineNumber, goalColum);
                long jpsEndTime = System.nanoTime();
                long jpsPathTime = jpsEndTime - jpsStartTime;
                jpsMapTotal += jpsPathTime;
                 //System.out.println("JPS " +convertNStoSessibleUnitOfTime(jpsPathTime));

                
                if(allAreAboutTheSame(dijkstraDistance, astartDistance, jpsDistance, optimalLenght)) {
                    //System.out.println("Distance was "+dijkstraDistance);
                } else {
                    pathLenghtMisMatch = true;
                    System.out.println("WARNING diverge in path lenght between algorithms in Path from" + start + " to " + goal);
                    System.out.println("dijkstra " +dijkstraDistance+ " astar " + astartDistance + " jps " + jpsDistance + " scen optimal lenght " + optimalLenght);
                }
                

            }

        if(numberOfPathsForThisMap > 1) {
            System.out.println(map.getName());
            System.out.println("Total time spend on this map by Djikstra was "+ convertNStoSessibleUnitOfTime(dijktraMapTotal) + ", avg " + convertNStoSessibleUnitOfTime((dijktraMapTotal / numberOfPathsForThisMap)));
            System.out.println("Total time spend on this map by A* was "+ convertNStoSessibleUnitOfTime(astarMapTotal) + ", avg " + convertNStoSessibleUnitOfTime((astarMapTotal / numberOfPathsForThisMap)));
            System.out.println("Total time spend on this map by JPS was "+ convertNStoSessibleUnitOfTime(jpsMapTotal) + ", avg " + convertNStoSessibleUnitOfTime((jpsMapTotal / numberOfPathsForThisMap)));
        }
        
        dijkstraTotalTime += dijktraMapTotal;
        astartTotalTime += astarMapTotal;
        jpsTotalTime += jpsMapTotal;

    }

    private String convertNStoSessibleUnitOfTime(long timeInNanoSeconds) {
        long ms = timeInNanoSeconds / 1000000;
        if(ms < 1) {
            long microSeconds = timeInNanoSeconds / 1000;
            return Long.toString(microSeconds) +" microseconds";
        }
        return  Long.toString(ms) +" ms";
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