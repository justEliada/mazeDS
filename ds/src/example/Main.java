package example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueGenerating = true;
        int mazeCount = 0;

        while (continueGenerating) {
            int rows = getSafeInt(scanner, "Enter the total rows of the maze: ");
            int cols = getSafeInt(scanner, "Enter the total columns of the maze: ");

            if (rows > 0 && cols > 0) {
                Maze maze = new Maze();
                System.out.println("Generated Maze:");
                maze.createMaze(rows, cols);
                mazeCount++;
            } else {
                System.out.println("The number of rows and columns must be positive.");
            }

            continueGenerating = getUserChoice(scanner, "Do you want to generate another maze? (y/n): ");
        }

        System.out.println("Total mazes generated: " + mazeCount);
        scanner.close();
        System.out.println("Program ended. Thank you for using the maze generator.");
    }

    private static int getSafeInt(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    private static boolean getUserChoice(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.next().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid choice. Please enter 'y' for yes or 'n' for no.");
            }
        }
    }
}
