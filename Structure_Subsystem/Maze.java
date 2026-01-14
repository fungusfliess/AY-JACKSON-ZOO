package Structure_Subsystem;
import Land_Subsystem.*;

public class Maze extends Attraction {
    
    // CONSTANTS
    public static final int LEARNING_PER_VISIT = 15;
    public static final double MAINTENANCE_RATE_PER_UNIT = 12.0d;
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
    public Maze (String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty, char[][] inMaze) {
        super (name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty);
        this.maze = new char[inMaze.length][inMaze[0].length];
        this.length = this.maze.length;
        this.width = this.maze[0].length;

        // loading in inMaze (has to copy using for loop, because I do not want to point towards an external maze)
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                this.maze[i][j] = inMaze[i][j];
            }
        }
        
        // creating solved maze
        solveMaze();
    }

    // RESET MAZE
    public void resetMaze () {
        // copying default maze. Do not point to the MAZE_DEFAULT_SHAPE, or else that will access the array it points to and potentially edit it. 
        // Final just means that that variable cannot point to anywhere else, it does not mean that the array is constant and locked.
        // could copy a different maze in the future, but just default for now.
        this.length = MAZE_DEFAULT_SHAPE.length;
        this.width = MAZE_DEFAULT_SHAPE[0].length;
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                this.maze[i][j] = MAZE_DEFAULT_SHAPE[i][j];
            }
        }
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
    @description: Calculates maintenance cost of the maze based on area
    @returns: double representing cost
    */
    public double calculateMaintenanceCost() {
        return MAINTENANCE_RATE_PER_UNIT * this.getArea();
    }

    public void updateVisitorLearning(Visitor toUpdate) {
        // COME_BACK_HERE waiting on how
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

   /*
   @description: prints the solved maze to standard output
   */
   public void printSolvedMaze() {
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.mazeSolved[i][j] + " ");
            }
            System.out.println();
        }
   }

   // SAVE LOAD
    /*
    @description: loads a Maze object from a String input
    @param: String input is the inputted String that will be processed
    @return: newly created Maze object.
     */
   public static Maze loadFromString (String input, Land onProperty) {
        // turning String into array of its lines
        String[] fields = input.split("\n");
        
        // parse each line into respective parameters, starting with subclass-specific, then parent class... etc.
        int idx = 0; // index that can count through the array of fields.
        int lengthOfMaze = Integer.parseInt(fields[idx]);
        idx++;
        int widthOfMaze = Integer.parseInt(fields[idx]);
        idx++;

        char[][] loadedMaze = new char[lengthOfMaze][widthOfMaze];
        String[] rows;
        // loading row by row
        for (int i = 0; i < lengthOfMaze; i++) {
            // splitting each row into individual characters
            rows = fields[i + idx].split(" ");
            // setting each element of the array accordingly
            for (int j = 0; j < widthOfMaze; j++) {
                loadedMaze[lengthOfMaze][widthOfMaze] = rows[j].charAt(0);
            }
        } // ends on idx + (length-1)
        // index is now = old index + length of maze
        idx += lengthOfMaze;

        // declaring variables to load in next.
        String name;
        char structureID;
        int area, timeBetweenMaintenance, daysSinceLastMaintenance;

        name = fields[idx];
        idx++;
        structureID = fields[idx].charAt(0);
        idx++;
        area = Integer.parseInt(fields[idx]);
        idx++;
        timeBetweenMaintenance = Integer.parseInt(fields[idx]);
        idx++;
        daysSinceLastMaintenance = Integer.parseInt(fields[idx]);
        
        Maze newMaze = new Maze (name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, loadedMaze); 
        return newMaze;

   }

   public String saveToString () {
        String sum = "";
        sum += "Maze" + "\n"; // class type, reference for reading in
        sum += this.length + "\n";
        sum += this.width + "\n";
        // loading in array
        String rows;
        for (int i = 0; i < this.length; i++) {
            rows = "";
            for (int j = 0; j < this.width; j++) {
                rows += maze[i][j];
                rows += " ";
            }
            sum += rows + "\n";
        }
        // other parameters
        sum += this.getName() + "\n";
        sum += this.getStructureID() + "\n";
        sum += this.getArea() + "\n";
        sum += this.getTimeBetweenMaintenance() + "\n";
        sum += this.getDaysSinceLastMaintenance() + "\n";
        // COME_BACK_HERE add extra empty line or no?? find out later when we integrate all subsystems together.

        return sum;
   }

   /*
   @description: returns a String representation of the Maze object
   @return: String representing the data of this Maze object
   */
   public String toString () {
        String sum = "";
        sum += "Maze Name: " + this.getName() + "\n";
        sum += "Structure ID: " + this.getStructureID() + "\n";
        sum += "Area: " + this.getArea() + "\n";
        sum += "Time Between Maintenance: " + this.getTimeBetweenMaintenance() + "\n";
        sum += "Days Since Last Maintenance: " + this.getDaysSinceLastMaintenance() + "\n";
        sum += "Maze Layout: \n";
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                sum += this.maze[i][j] + " ";
            }
            sum += "\n";
        }
        return sum;
   }
}