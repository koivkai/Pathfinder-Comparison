package io;

import java.io.File;
import java.util.Scanner;
import datastructures.PathMap;

public class MapReader {

    public MapReader() {
    }
    
    public PathMap Read(String path) {
        PathMap newMap = new PathMap();
        File file;
        Scanner scanner;
        try {
            file = new File(path);
            scanner = new Scanner(file);

            String mapType = scanner.nextLine();
            String heigtLine = scanner.nextLine();
            String widthLine = scanner.nextLine();
            heigtLine = heigtLine.substring(7);
            widthLine = widthLine.substring(6);

            int height = Integer.parseInt(heigtLine);
            int width = Integer.parseInt(widthLine);
            char[][] lines = new char[height][width];
            scanner.nextLine();
            for(int i = 0; i<height;i++) {
                if(scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    char[] lineContent = line.toCharArray();
                    lines[i] = lineContent;
                }
                
            }

            newMap.setHeight(height);
            newMap.setWidth(width);
            newMap.setMap(lines);
            scanner.close();
            //System.out.println("In map reader map height "+height+" width "+width);
        } catch (Exception e) {
            System.out.println("failed to read file");
            System.out.println(e.toString());
            return null;
            //TODO: handle exception
        }
        
        

    
        return newMap;
    }
    
}