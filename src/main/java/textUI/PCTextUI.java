package textUI;

import java.util.Scanner;
import datastructures.Cordinate;
import datastructures.MapList;
import datastructures.PathMap;
import io.MapReader;
import io.ScenFileReader;
import logic.ComparisonEngine;

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

    private void usePreset() {
        String mapPath1 = "/Users/Kaius/TiraKartat/dao-map/brc203d.map";
        String scenPath1 = "/Users/Kaius/TiraScenaariot/dao-scen/brc203d.map.scen";
        PathMap map1 = mapReader.Read(mapPath1);
        scenFileReader.read(scenPath1, map1);
        maps.add(map1);

        //String mapPath2 = "/Users/Kaius/TiraKartat/maze512-1-0.map";
        //String scenPath2 = "/Users/Kaius/TiraScenaariot/maze512-1-0.map.scen";
        //PathMap map2 = mapReader.Read(mapPath2);
        //scenFileReader.read(scenPath2, map2);
        //maps.add(map2);

        String mapPath3 = "/Users/Kaius/TiraKartat/Berlin_0_512.map";
        String scenPath3 = "/Users/Kaius/TiraScenaariot/Berlin_0_512.map.scen";
        PathMap map3 = mapReader.Read(mapPath3);
        scenFileReader.read(scenPath3, map3);
        maps.add(map3);

        String mapPath4 = "/Users/Kaius/TiraKartat/dao-map/den005d.map";
        String scenPath4 = "/Users/Kaius/TiraScenaariot/dao-scen/den005d.map.scen";
        PathMap map4 = mapReader.Read(mapPath4);
        scenFileReader.read(scenPath4, map4);
        maps.add(map4);
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