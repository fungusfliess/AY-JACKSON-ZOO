public class Map {
    private int length;
    private int width;
    public static final char EMPTY = '.'; // represents empty space on grid
    private char[][] map;


    // ACCESSOR MUTATORS
    public char[][] getCharArray() {
        return this.map;
    }

    /*
    @description: overwrites old character array map.
    @param: char[][] in. A new 2D character array. Must be of positive length and width. 
    */ 
    public void setCharArray (char[][] in) { // will override current map, use with caution.
        this.map = in;
        length = in.length;
        width = in[0].length;
    }

    /*
    @description: declares and initializes 2D char array (called map) of specified size.
    @param: int length1 represents the length 
    @param: int width1 represents the width
    */ 

    public Map (int length1, int width1) {
        this.width = width1;
        this.length = length1;
        // init array      Each row is an array of size width,  and map is an array of rows. 
        this.map = new char[this.length][this.width];
    }


    /*
    @description: returns map array expressed as a String, ready to be written into a file.
    @returns: formatted String, in the format that allows it to be written into a file. 
    */ 

    public String saveToString () {
        String save = "";
        // first 2 rows are length and width
        save += ("" + this.length + "\n");
        save += ("" + this.width + "\n");

        String row;
        // appending each row
        for (int i = 0; i < this.length; i++) {
            // creating row
            row = "";
            // loops through all indices of a row, except the last one.
            for (int j = 0; j < this.width-1; j++) {
                row += map[i][j];
                row += " ";
            }
            // adds last element 
            row += map[i][this.width-1];
            // new line
            
            // add row to string
            save += row;
            save += "\n";            
        }
        return save;
    }

    /*
    @description: Loads map array from an inputted, specifically-formatted String from Land class.
    @param: String input is the inputted String that will be processed
    @return: newly created Map object.
    */ 
    public static Map loadFromString (String input) {
        String[] lineList = input.split("\n");
        int inputLength, inputWidth;
        // parse in first 2 lines
        inputLength = Integer.parseInt(lineList[0]);
        inputWidth = Integer.parseInt(lineList[1]);

        char[][] inMap = new char[inputLength][inputWidth];
        // adding rows into the array
        String[] row;
        for (int i = 0; i < inputLength; i++) {
            // converting each line into a row and passing it into the array
            row = lineList[i+2].split(" ");

            for (int j = 0; j < inputWidth; j++) {
                inMap[i][j] = row[j].charAt(0);
            }
        }

        Map out = new Map(inputLength, inputWidth);
        out.setCharArray(inMap);
        return out;
    } 


    /*
    @description: Loads map array from an inputted, specifically-formatted String from Land class.
    @parameters: String input
    @return: boolean indicating success
    */ 
    public boolean buildStructureRectangular (Coord corner1, Coord corner2, char id) {
        
        // if either corner is out of bounds
        if (!(corner1.isInGrid(this.length, this.width) && corner2.isInGrid(this.length, this.width))) {
            return false;
        }
        
        int x1, y1, x2, y2;

        x1 = corner1.getX();
        y1 = corner1.getY();
        x2 = corner2.getX();
        y2 = corner2.getY();

        // ensuring x1 <= x2, y1 <= y2. this means that point (x1, y1) is always up and left from point (x2, y2)
        if (!(x1 <= x2)) {
            // flip
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }

        if (!(y1 <= y2)) {
            // flip
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }

        // checking for obstacles
        // If obstacle stays false, then there are no obstacles in the way of the construction of the building. 
        boolean obstacle = false;

        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                if (this.map[i][j] == EMPTY) {
                    obstacle = true;
                }
            }
        }
        
        if (obstacle) {
            return false;
        }

        // makes rectangular shape if no obstacles.
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                this.map[i][j] = id;
            }
        }

        return true;
    }

    
    /*
    @description: Random boolean generator, with a modifiable probability that will approach 100% true as count --> max. Used by recursive blob generation.
    @parameters: int max, int count. These are used in the probability calculation. Max is the maximum sprawl, and count is to change the probability with each call from buildStructureBlob's recursive algorithm.
    @return: boolean with a 1/(max-count) chance of being true. 
    */ 
    private boolean probabilityOfEnd (int max, int count) {
        int den = max - count;
        // limiting den to positive, just in case
        if (den < 0) {
            den = 0;
        }
        int d = (int)(Math.random() * (den));
        if (d == 0) {
            return true;
        } else {
            return false;
        }
    }

    // wrapper method
    public void buildStructureBlob (Coord seed, char id, int max) {
        buildStructureBlob(seed, id, max, 0);
    }

    /*
    @description: recursive blob shape generation.
    @parameters: Coord seed is the point on which the method is called, char id is the id of the blob, int max and count determine the bias of the randomized stopping distance (approaches 100% as count --> max, so that the blob sprawls at most <max> distance away)
    */ 
    private void buildStructureBlob (Coord seed, char id, int max, int count) {
        char empty = EMPTY;
        
        // map length and width
        int length = map.length;
        int width = map[0].length;

        // setting x, y
        int x = seed.getX();
        int y = seed.getY();

        map[y][x] = id;
    
        // new coordinates to represent each adjacent element
        Coord left = new Coord (x+1, y);
        Coord right = new Coord (x-1, y);
        Coord up = new Coord (x, y-1);
        Coord down = new Coord (x, y+1);


        /*
        RECURSION:
            base case: probabilityOfEnd(.....) = 0
            Increments towards base case: count+1 (probabilityOfEnd becomes more biased to return true)
        */

        // calling recursion in all 4 directions
        // can only go in a direction IF !outOfBounds & index can be replaced & probability says so
        if (!left.isInGrid(length, width) && canReplace(map[y][x+1], id, empty) && !probabilityOfEnd(max, count)) {
            buildStructureBlob(left, id, max, count+1);
        }
        if (!right.isInGrid(length, width) && canReplace(map[y][x-1], id, empty) && !probabilityOfEnd(max, count)) {
            buildStructureBlob(right, id, max, count+1);
        }
        if (!down.isInGrid(length, width) && canReplace(map[y+1][x], id, empty) && !probabilityOfEnd(max, count)) {
            buildStructureBlob(down, id, max, count+1);
        }
        if (!up.isInGrid(length, width) && canReplace(map[y-1][x], id, empty) && !probabilityOfEnd(max, count)) {
            buildStructureBlob(up, id, max, count+1);
        }     
    
    }

    /*
    @description: represents the way buildStructureBlob determines if it can overwrite an index.
    @returns: boolean indicating if the character c can be replaced by id, given that empty is the placeholder character.
    */
    private boolean canReplace(char c, char id, char empty) { 
      if (c != id && c == empty) { // currently placeholder, algorithm can be changed. 
         return true;
      } else {
         return false;
      }
   }

}

 