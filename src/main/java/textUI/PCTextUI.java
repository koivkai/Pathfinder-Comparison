package textUI;

import java.util.Scanner;

import datastructures.PathMap;
import logic.MapReader;


public class PCTextUI {
    Scanner scanner;
    MapReader reader;

    public PCTextUI() {
        this.scanner = new Scanner(System.in);
        this.reader = new MapReader();
    }

    public void start() {
        System.out.println("Pathfinder Comparison");
        System.out.println("Please give absolute filepath");

       // String path = scanner.nextLine();
        String path= "/Users/Kaius/TiraKartat/da2-map/ca_cave.map";

        System.out.println("reading "+path);
        PathMap map = reader.Read(path);
        //map.print();
    }
    
}