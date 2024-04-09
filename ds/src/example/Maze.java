package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {


    private List<List<Node>> initializeMaze(int rows, int cols, List<List<Node>> maze) {

        int count = 0;
        for(int i=0; i<rows; i++) {
            List<Node> row = new ArrayList<>();
            for(int j=0; j<cols; j++) {
                row.add(new Node(count++, true, true)); //Stores current index as value of the node
            }
            maze.add(row);
        }

        return maze;
    }


    private void printMaze(int rows, int cols, List<List<Node>> maze) {

        System.out.print("   ");
        for(int j=1; j<cols; j++) {
            System.out.print(" __");
        }

        System.out.println();

        for(int i=0; i<rows; i++) {

            if(i == 0) {
                System.out.print(" ");
            } else {
                System.out.print("|");
            }

            for(int j=0; j<cols; j++) {

                if(maze.get(i).get(j).horizontal) {
                    System.out.print("__");
                } else {
                    System.out.print("  ");
                }

                if(maze.get(i).get(j).vertical) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }

    }

    public void createMaze(int rows, int cols) {

        int total = rows*cols;
        DisjSets ds = new DisjSets(total);

        List<List<Node>> maze = new ArrayList<List<Node>>();

        maze = initializeMaze(rows, cols, maze);

        Random rand = new Random();

        while(ds.find(0) != ds.find(total - 1)) {

            int currRow = rand.nextInt(rows);
            int currCol = rand.nextInt(cols);
            Node currNode = maze.get(currRow).get(currCol);
            int currValue = currNode.value;

            int root1 = ds.find(currValue);
            int root2;
            boolean removeHorizontal = false;

            if(currValue == total - 1) {
                continue;
            }

            if(currRow == rows - 1) {
                root2 = ds.find(currValue + 1);
            } else if(currCol == cols - 1) {
                root2 = ds.find(currValue + cols);
                removeHorizontal = true;
            } else {
                boolean selectRight;
                selectRight = rand.nextBoolean();

                if(selectRight) {
                    root2 = ds.find(currValue + 1);
                } else {
                    root2 = ds.find(currValue + cols);
                    removeHorizontal = true;
                }
            }

            if(root1 != root2) {

                ds.union(root1, root2);

                if(removeHorizontal) {
                    currNode.horizontal = false;
                } else {
                    currNode.vertical = false;
                }
            }
        }

        Node destination = maze.get(rows - 1).get(cols - 1);
        destination.horizontal = false;
        destination.vertical = false;

        printMaze(rows, cols, maze);
    }

    private class Node {
        int value;
        boolean horizontal;
        boolean vertical;

        Node(int value, boolean horizontal, boolean vertical) {
            this.value = value;
            this.horizontal = horizontal;
            this.vertical = vertical;
        }
    }

}