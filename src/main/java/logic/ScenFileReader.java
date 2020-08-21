package logic;

import java.io.File;
import java.util.Scanner;

import datastructures.Cordinate;
import datastructures.PathMap;

public class ScenFileReader {

    public ScenFileReader() {
    }

    public void read(String path, PathMap map) {
        System.out.println("reading file: "+path);
        File file;
        Scanner scanner;
        try {
            file = new File(path);
            scanner = new Scanner(file);

            String versionLine = scanner.nextLine();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values= line.split("\\s+");
                String scenLinenumber = values[0];
                String mapName = values[1];

                
                String mapWitdhString = values[2];
                String mapHeightString = values[3];
                String startColumString = values[4];
                String startLineString = values[5];
                String goalColumString = values[6];
                String goalLineString = values[7];
                String optimalLenghtString = values[8];
                
                /*
                String mapWitdhString = values[2];
                String mapHeightString = values[3];
                String startLineString = values[4];
                String startColumString = values[5];
                String goalLineString = values[6];
                String goalColumString = values[7];
                String optimalLenghtString = values[8];
                */
                
                int mapWitdh = Integer.parseInt(mapWitdhString);
                int mapHeight = Integer.parseInt(mapHeightString);
                int startLine = Integer.parseInt(startLineString);
                int startColum = Integer.parseInt(startColumString);
                int goalLine = Integer.parseInt(goalLineString);
                int goalColum = Integer.parseInt(goalColumString);
                double optimalLenght = Double.parseDouble(optimalLenghtString);

                if(mapWitdh == map.getWidth() && mapHeight == map.getHeight()) {

                    map.addPath(new Cordinate(startLine, startColum), new Cordinate(goalLine, goalColum), optimalLenght);
                }

            }

        } catch (Exception e) {
            System.out.println("failed to read scenarios");
            System.out.println(e.toString());
            //TODO: handle exception
        }
    }
    
}