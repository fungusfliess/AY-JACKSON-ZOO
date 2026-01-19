/*
   File Name: MapTester.java
   Names: Arianna Liu, Jerry Ning, Jason Liu, Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: MapTester is a testing class for the Map functionality.
                It creates test mazes and validates map operations.
*/

public class MapTester {
    public static void main(String[] args) {
        Map test = new Map (16, 15);
        // START AT (5, 7)
        char[][] testMaze = {{'.', '.', '.', '.', '.', '.', 'N', '.', 'N', '.', '.', '.', '.', '.', '.', }, 
                             {'.', '.', '.', '.', 'N', 'X', 'N', '.', 'N', '.', 'N', 'N', 'N', 'N', 'N', }, 
                             {'.', 'N', 'N', 'N', 'N', 'N', 'N', '.', '.', '.', 'N', 'N', 'N', 'N', 'N', }, 
                             {'.', 'N', '.', '.', '.', '.', '.', '.', 'N', '.', '.', '.', '.', '.', '.', }, 
                             {'.', 'N', '.', 'N', '.', 'N', '.', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', }, 
                             {'.', 'N', '.', 'N', '.', 'N', '.', '.', 'N', 'N', '.', '.', '.', '.', '.', }, 
                             {'.', 'N', 'N', 'N', '.', 'N', '.', '.', '.', '.', '.', '.', '.', '.', '.', }, 
                             {'.', '.', '.', '.', '.', 'N', '.', '.', '.', 'N', '.', '.', '.', '.', '.', }, 
                             {'.', 'N', 'N', '.', 'N', 'N', 'N', 'N', 'N', 'N', 'N', '.', 'N', 'N', 'N', }, 
                             {'.', '.', '.', '.', '.', '.', '.', 'N', 'N', '.', 'N', '.', '.', '.', '.', }, 
                             {'.', 'N', '.', 'N', 'N', 'N', '.', '.', '.', '.', 'N', 'N', 'N', '.', 'N', }, 
                             {'.', 'N', '.', 'N', '.', 'N', 'N', 'N', '.', 'N', 'N', '.', 'N', '.', '.', }, 
                             {'.', 'N', '.', 'N', '.', 'N', '.', '.', '.', '.', '.', '.', 'N', '.', 'N', }, 
                             {'.', 'N', '.', 'N', '.', 'N', '.', 'N', 'N', 'N', 'N', '.', 'N', '.', '.', }, 
                             {'.', 'N', 'N', 'N', '.', 'N', '.', '.', '.', '.', 'N', '.', 'N', '.', 'N', }, 
                             {'.', '.', '.', '.', '.', 'N', '.', 'N', '.', '.', 'N', '.', '.', '.', '.', }, };
        //asdfasf
        test.setCharArray(testMaze);
        test.printMap();   
        System.out.println();
        System.out.println("Solved maze: ");
        System.out.println();
        test.printMap();  
    }
}