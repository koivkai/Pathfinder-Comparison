package textUI;

import java.util.Scanner;
import datastructures.Cordinate;
import datastructures.MapList;
import datastructures.PathMap;
import io.MapReader;
import io.ScenFileReader;
import logic.ComparisonEngine;

/**
 * A very basic UI for using the program.
 */
public class PCTextUI {
    Scanner scanner;
    MapReader mapReader;
    ScenFileReader scenFileReader;
    MapList maps;

    public PCTextUI() {
        this.scanner = new Scanner(System.in);
        this.mapReader = new MapReader();
        this.scenFileReader = new ScenFileReader();
        this.maps = new MapList();
    }

    public void start() {  
        ComparisonEngine c = new ComparisonEngine();
        System.out.println("Pathfinder Comparison");
        System.out.println("First select maps");

        while(true) {
            String anwser = getUserInputString("Add a map y/n?");
            if(anwser.equals("n")) {
                break;
            } else if(anwser.equals("p")) {
                usePreset();
                break;
            }
            getMapFromUser();
        }
        
        c.compare(maps);
        
        
    }
    /**
    * When doing many comparions with same map to get averages, it can be helper to hardcode used data here as shown in example in commnet
    */
    private void usePreset() {
        /*
        String mapPath1 = "/Users/Kaius/TiraKartat/dao-map/brc203d.map";
        String scenPath1 = "/Users/Kaius/TiraScenaariot/dao-scen/brc203d.map.scen";
        PathMap map1 = mapReader.Read(mapPath1);
        scenFileReader.read(scenPath1, map1);
        maps.add(map1);
        */
    }

    private int getUserInputInt(String prompt) {
        System.out.println(prompt);
        int input = 0;
            String line = scanner.nextLine();
            try{
                input = Integer.parseInt(line);
            } catch (Exception e){
                System.out.println(e);
            }
        return input;
    }

    private String getUserInputString(String prompt) {
        System.out.println(prompt);
        String line = scanner.nextLine();
        return line;
    }

    private void getMapFromUser() {
        String path = getUserInputString("Please give absolute filepath for .map file");

        System.out.println("reading "+path);
        PathMap map = mapReader.Read(path);
        //map.print();
        map.setName(path);

        String anwser = getUserInputString("Get paths from scen file? y/n");
            if(anwser.equals("y")) {
                getScenFileFromUser(map);
            } else {
                getPathsForMap(map);
            }
        

        
        maps.add(map);
    }

    private void getScenFileFromUser(PathMap map) {
        String path = getUserInputString("Please give absolute filepath for .scen file");
        scenFileReader.read(path, map);
    }

    private void getPathsForMap(PathMap map) {
        System.out.println("Give cordinates for paths on this map, input emtpy line as start line number to finish inputing lines");
        while(true) {
            int startLineNumber;
            int startColum;
            int goalLineNumber;
            int goalColum;
            int optimalLenght;

            System.out.println("Give start line number");
            String startLine = scanner.nextLine();
            if(startLine.isEmpty()) {
                break;
            }
            try{
                startLineNumber = Integer.parseInt(startLine);
            } catch (Exception e){
                System.out.println(e);
                continue;
            }

            startColum = getUserInputInt("Give start colum number");
            goalLineNumber = getUserInputInt("Give goal line number");
            goalColum = getUserInputInt("Give goal colum number");
            optimalLenght = getUserInputInt("Optimal lenght for this path");
            map.addPath(new Cordinate(startLineNumber, startColum), new Cordinate(goalLineNumber, goalColum), optimalLenght);
        }
    }
    
}