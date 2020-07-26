package logic;

import java.io.File;
import java.util.Scanner;

import datastructures.Map;

public class MapReader {

    public MapReader() {
    }
    
    public Map Read(String path) {
        Map newMap = new Map();
        File file;
        Scanner scanner;
        try {
            file = new File(path);
            scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        

    
        return newMap;
    }
    
}