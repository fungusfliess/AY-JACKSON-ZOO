package Structure_Subsystem;
import Land_Subsystem.*;

public class Maze extends Attraction {
    
    // CONSTANTS
    public static final int LEARNING_PER_VISIT = 15;
    public static final double MAINTNENACE_RATE_PER_UNIT = 12.0d;
    public static final char WALL = 'N';
    public static final char EMPTY_SPACE = '.';
    public static final char WIN = 'X';
    private static final char GOOD_PATH = '+';
    private static final char BAD_PATH = '-';
    private static final Coord START_POINT = new Coord (7, 5);
    public static final char[][] MAZE_DEFAULT_SHAPE = {
                             {'.', '.', '.', '.', '.', '.', 'N', '.', 'N', '.', '.', '.', '.', '.', '.', }, 
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
                             {'.', '.', '.', '.', '.', 'N', '.', 'N', '.', '.', 'N', '.', '.', '.', '.', }, };;

    // FIELDS
    private char[][] maze;
    private char[][] mazeSolved;
    private int length, width;

    // CONSTRUCTOR
    /*
    @description: constructs a Maze with default shape
    */
    public Maze (String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty) {
        super (name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty);
        this.maze = new char[MAZE_DEFAULT_SHAPE.length][MAZE_DEFAULT_SHAPE[0].length];
        this.length = this.maze.length;
        this.width = this.maze[0].length;

        // copying default maze. Do not point to the MAZE_DEFAULT_SHAPE, or else that will access the array it points to and potentially edit it. 
        // Final just means that that variable cannot point to anywhere else, it does not mean that the array is constant and locked.
        // could copy a different maze in the future, but just default for now.
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                this.maze[i][j] = MAZE_DEFAULT_SHAPE[i][j];
            }
        }
        
        // creating solved maze
        solveMaze();
    }

    // ACCESSOR MUTATORS
    public char[][] getMaze() {
        return this.maze;
    }
    public int getLength() {
        return this.length;
    }
    public int getWidth() {
        return this.width;
    }

    // OVERRIDDEN ABSTRACT METHODS
    /*
    @description: Calculates maintenance cost based on area
    @returns: cost
    */
    public double calculateMaintenanceCost() {
        return MAINTNENACE_RATE_PER_UNIT * this.getArea();
    }

    public void updateVisitorLearning(Visitor guest) {
        // waiting on arianna to tell me how
    }

    // MAZE METHODS

    /*
    @description: returns a solved version of the character array maze.
    */
    private void solveMaze () {
        mazeSolved = new char[length][width];

        // copy maze to solve
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                mazeSolved[i][j] = maze[i][j];
            }
        }

        // use recursion to solve.
        mazeSolver(START_POINT, mazeSolved);

        // replacing all tried bad paths with empty space, so that there is no random BAD_PATH character paths lying around in the finished product.
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (mazeSolved[i][j] == BAD_PATH) {
                    mazeSolved[i][j] = EMPTY_SPACE;
                }
            }
        }

    }

    /*
    @description: recursive maze solver helper method
    @returns: boolean indicating whether a path to the WIN has been found from the current coordinate
    @params: Coord tgt: current coordinate being checked, 
             char[][] solvedMaze: the maze being solved.
    */
    private boolean mazeSolver (Coord tgt, char[][] solvedMaze) {
        int x = tgt.getX();
        int y = tgt.getY();
        int length = solvedMaze.length;
        int width = solvedMaze[0].length;

        // series of checks to ensure the current coordinate is valid
        if (!tgt.isInGrid(length, width)) { // if out of bounds
            return false;
        } else if (solvedMaze[y][x] == WALL || solvedMaze[y][x] == BAD_PATH || solvedMaze[y][x] == GOOD_PATH) { // not supposed to land here, because it's either out of playable bounds or redundant.
            return false;
        } else if (solvedMaze[y][x] == WIN) { // if win
            return true;
        } else {
            // initializing all 4 adjacent Coords
            Coord left = new Coord(x-1, y);
            Coord right = new Coord(x+1, y);
            Coord up = new Coord(x, y-1);
            Coord down = new Coord(x, y+1);
            // mark current spot, as bad path by default
            solvedMaze[y][x] = BAD_PATH;
            // call recursive in all 4 directions. If any of these directions can continue recursing until finding the WIN, then return true.
            boolean isGoodPath = false;
            // use else if to prevent unnecessary calls once a good path is found
            if (mazeSolver(left, solvedMaze)) {
                isGoodPath = true;
            } else if (mazeSolver(right, solvedMaze)) {
                isGoodPath = true;
            } else if (mazeSolver(up, solvedMaze)) {
                isGoodPath = true;
            } else if (mazeSolver(down, solvedMaze)) {
                isGoodPath = true;
            }
            // if a good path is found through recursion,
            if (isGoodPath) {
                // mark it as a good path
                solvedMaze[y][x] = GOOD_PATH;
                return true;
            } else { // no good path is found
                // still marked as a bad path
                return false;
            }
        }
        
    }

    public void printMaze() {
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.maze[i][j] + " ");
            }
            System.out.println();
        }
   }

   public void printSolvedMaze() {
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.mazeSolved[i][j] + " ");
            }
            System.out.println();
        }
   }
}