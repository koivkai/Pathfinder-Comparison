package textUI;

import java.util.Scanner;

import datastructures.Cordinate;
import datastructures.MapList;
import datastructures.PathMap;
import logic.ComparisonEngine;
import logic.MapReader;
import logic.ScenFileReader;


public class PCTextUI {
    Scanner scanner;
    MapReader mapReader;
    ScenFileReader scenFileReader;

    public PCTextUI() {
        this.scanner = new Scanner(System.in);
        this.mapReader = new MapReader();
        this.scenFileReader = new ScenFileReader();
    }

    public void start() {
        MapList maps = new MapList();
        ComparisonEngine c = new ComparisonEngine();
        System.out.println("Pathfinder Comparison");
        System.out.println("First select maps");

        while(true) {
            String anwser = getUserInputString("Add a map y/n?");
            if(anwser.equals("n")) {
                break;
            }
            getMapFromUser(maps);
        }
        
        c.compare(maps);
        
        
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

    private void getMapFromUser(MapList maps) {
        String path = getUserInputString("Please give absolute filepath for .map file");
        //path= "/Users/Kaius/TiraKartat/da2-map/ca_cave.map";
        path= "/Users/Kaius/TiraKartat/dao-map/brc203d.map";

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
        path= "/Users/Kaius/TiraScenaariot/dao-scen/brc203d.map.scen";
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