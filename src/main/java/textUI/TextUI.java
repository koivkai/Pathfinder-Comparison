package textUI;

import java.util.Scanner;


public class TextUI {
    Scanner scanner;
    public TextUI() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Pathfinder Comparison");
        System.out.println("Please give absolute filepath");
        String path = scanner.nextLine();

    }
    
}