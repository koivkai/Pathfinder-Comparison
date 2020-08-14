package textUI;

import java.util.Scanner;

import datastructures.Cordinate;
import datastructures.MapList;
import datastructures.PathMap;
import logic.ComparisonEngine;
import logic.MapReader;


public class PCTextUI {
    Scanner scanner;
    MapReader reader;

    public PCTextUI() {
        this.scanner = new Scanner(System.in);
        this.reader = new MapReader();
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
        System.out.println("Please give absolute filepath");

        String path = scanner.nextLine();
        //String path= "/Users/Kaius/TiraKartat/da2-map/ca_cave.map";

        System.out.println("reading "+path);
        PathMap map = reader.Read(path);
        //map.print();
        map.setName(path);
        getPathsForMap(map);

        
        maps.add(map);
    }

    private void getPathsForMap(PathMap map) {
        System.out.println("Give cordinates for paths on this map, input emtpy line as start line number to finish inputing lines");
        while(true) {
            int startLineNumber;
            int startColum;
            int goalLineNumber;
            int goalColum;

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

            map.addPath(new Cordinate(startLineNumber, startColum), new Cordinate(goalLineNumber, goalColum));
        }
    }
    
}