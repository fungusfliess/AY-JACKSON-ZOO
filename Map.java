
public class Map {
    
    // FIELDS
    private int length;
    private int width;
    private char[][] map;
    public static final char TEMPORARY_COUNTER = 'Σ'; // temporary character to mark counted spots for areaOf

    // CONSTANTS
    public static final char EMPTY = '.'; // represents empty space on grid

    //CONSTRUCTOR
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
        // filling with EMPTY character
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                this.map[i][j] = EMPTY;
            }
        }
    }


    // ACCESSOR MUTATORS
    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return this.width;
    }

    public char[][] getCharArray() {
        return this.map;
    }

    /*
    @description: overwrites old character array map. USE WITH CAUTION
    @param: char[][] in. A new RECTANGULAR 2D character array. Must be of positive length and width. 
    */ 
    public void setCharArray (char[][] in) { // will override current map, use with caution.
        if (in == null || in.length == 0 || in[0].length == 0) {
            System.out.println("Inputted array is invalid.");
            return;
        }

        // checking if rectangular
        for (int i = 0; i < in.length; i++) {
            if (in[i].length != in[0].length) {
                System.out.println("Inputted array is not rectangular.");
            }
        }
        
        // copy over
        length = in.length;
        width = in[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                this.map[i][j] = in[i][j];
            }
        }
        
    }

    /*
    @description: determines whether a character can be used in this array or not. (certain characters are reserved for things like the counting algorithm.)
    @param: char c is the character to be checked
    @return: boolean indicating whether character is allowed or not
    */
    public boolean charIsAllowed (char c) {
        if (c != Map.EMPTY && c != Map.TEMPORARY_COUNTER) {
            return true;
        }
        return false;
    }

    /*
    @description: returns information of this Map object as a String, ready to be written into a file.
    @returns: formatted String, in the desired format that allows it to be written into a file. 
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
        // if character is invalid
        if (!charIsAllowed(id)) { // illegal character
            return false;
        }

        // if either corner is out of bounds
        if (!corner1.isInGrid(this.length, this.width) || !corner2.isInGrid(this.length, this.width)) {
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
                if (this.map[i][j] != EMPTY) {
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
    @return: boolean with a 1/(max-count) chance of being false. 
    */ 
    private boolean probabilityOfEnd (int max, int count) {
        int den = max - count;
        // limiting den to positive, just in case
        if (den < 0) {
            den = 0;
        }
        int d = (int)(Math.random() * (den)); // random int from 0 to den exclusive (max-count integers)
        // chance of d being 0 is 1/(max-count)
        if (d == 0) {
            return false;
        } else {
            return true;
        }
    }

    // wrapper method
    public boolean buildStructureBlob (Coord seed, char id, int max) {
        if (!charIsAllowed(id)) { // illegal character
            return false;
        }
        if (!seed.isInGrid(this.length, this.width)) {
            return false;
        }
        if (this.map[seed.getY()][seed.getX()] != EMPTY) {
            return false;
        }
        buildStructureBlob(seed, id, max, 0);
        return true;
    }

    /*
    @description: recursive blob shape generation.
    @parameters: Coord seed is the point on which the method is called, char id is the id of the blob, int max and count determine the bias of the randomized stopping distance (approaches 100% as count --> max, so that the blob sprawls at most <max> distance away)
    */ 
    private void buildStructureBlob (Coord seed, char id, int max, int count) {
        char empty = EMPTY;

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
            base case: probabilityOfEnd(.....) returns false
            Increments towards base case: count+1 (probabilityOfEnd becomes more biased to return true)
        */

        // calling recursion in all 4 directions
        // can only go in a direction IF !outOfBounds & index can be replaced & probability says so
        if (left.isInGrid(length, width) && canReplace(map[left.getY()][left.getX()], id, empty) && probabilityOfEnd(max, count)) {
            buildStructureBlob(left, id, max, count+1);
        }
        if (right.isInGrid(length, width) && canReplace(map[right.getY()][right.getX()], id, empty) && probabilityOfEnd(max, count)) {
            buildStructureBlob(right, id, max, count+1);
        }
        if (down.isInGrid(length, width) && canReplace(map[down.getY()][down.getX()], id, empty) && probabilityOfEnd(max, count)) {
            buildStructureBlob(down, id, max, count+1);
        }
        if (up.isInGrid(length, width) && canReplace(map[up.getY()][up.getX()], id, empty) && probabilityOfEnd(max, count)) {
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

   /*
    @description: Wrapper method for recursive replace. Replaces all connected same-ID characters with a new ID.
    @parameters: Coord tgt is the target coordinate, char replaceID is the character to replace with.
    @returns: boolean indicating success of replacement (false if target coordinate is not EMPTY)
   */
   public boolean replace (Coord tgt, char replaceID) {
        if (map[tgt.getY()][tgt.getX()] == EMPTY) {
            return false;
        } else if (!charIsAllowed(replaceID)) { // illegal character
            return false;
        } else {
            replace (tgt, map[tgt.getY()][tgt.getX()], replaceID);
            return true;
        }
   }

   /*
    @description: replaces all connected same-ID characters with a new ID.
    @parameters: Coord tgt is the target coordinate, char tgtID is the character to be replaced, char replaceID is the character to replace with.
    */
   private void replace (Coord tgt, char tgtID, char replaceID) {
        int x = tgt.getX();
        int y = tgt.getY();    
    
        // new coordinates to represent each adjacent element
        Coord left = new Coord (x+1, y);
        Coord right = new Coord (x-1, y);
        Coord up = new Coord (x, y-1);
        Coord down = new Coord (x, y+1);

        if (map[tgt.getY()][tgt.getX()] == tgtID) {
            map[tgt.getY()][tgt.getX()] = replaceID;
            if (left.isInGrid(length, width) && map[left.getY()][left.getX()] == tgtID) {
                replace(left, tgtID, replaceID);      
            }
            if (right.isInGrid(length, width) && map[right.getY()][right.getX()] == tgtID) {
                replace(right, tgtID, replaceID);
            }
            if (down.isInGrid(length, width) && map[down.getY()][down.getX()] == tgtID) {
                replace(down, tgtID, replaceID);
            }
            if (up.isInGrid(length, width) && map[up.getY()][up.getX()] == tgtID) {
                replace(up, tgtID, replaceID);
            }
        }
   }

   /*
    @description: erases structure at target coordinate by replacing all connected same-ID characters with EMPTY character.
    @returns: boolean indicating success of erasure (false if target coordinate is already EMPTY)
   */
   public boolean erase (Coord tgt) {
        if (map[tgt.getY()][tgt.getX()] == EMPTY) {
            return false;
        } else {
            char tgtID = map[tgt.getY()][tgt.getX()];
            replace (tgt, tgtID, EMPTY);
            return true;
        }
   }

   /*
   @description:  finds topmost and leftmost (with topmost dominant) point of with a matching char to input on the map 2D array. Returns point as a Coord object.
   @parameters: char input is the character to be found.
   */
   public Coord find (char input) {
        if (!charIsAllowed(input)) { // illegal character
            return null;
        }
        // loop down rows first (y), then across columns (x)
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                // if match found
                if (this.map[i][j] == input) {
                    return new Coord (j, i); // x is j, y is i
                }
            }
        }
        return null;
   }

   /*
    @description: returns area of connected blob of elements of the same element, adjacent to inputted coordinate point.
    @parameters: Coord point is the starting point of the blob.
    @returns: int, the area of the connected blob.
   */
   public int areaOf(Coord point) {
        char[][] map2 = new char[this.length][this.width];
        // copying map into map2
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                map2[i][j] = this.map[i][j];
            }
        }
        // by using a separate copy of map, we preserve the original map while using the recursive areaOf algorithm that modifies the map.
        char tgt = map2[point.getY()][point.getX()];
        if (tgt == EMPTY) {
            return 0; // area of empty space is 0
        }

        int area = areaOf(point, tgt, map2);
        return area;
   }

   /*
   @description: recursive method that calculates area of connected blob of elements of the same element, adjacent to inputted coordinate point.
   @parameters: Coord point is the starting point of the blob, char tgt is the target character to be matched.
   @returns: int blobCount, the area of the connected blob from all branching recursive calls summed up.
   */
   private int areaOf (Coord point, char tgt, char[][] map2) {
        int blobCount = 0;
        int x = point.getX();
        int y = point.getY();   
        // new coordinates to represent each adjacent element
        Coord left = new Coord (x+1, y);
        Coord right = new Coord (x-1, y);
        Coord up = new Coord (x, y-1);
        Coord down = new Coord (x, y+1);

        if (map2[point.getY()][point.getX()] == tgt) { // redundant except for first case when we input target
            blobCount++;
            map2[point.getY()][point.getX()] = TEMPORARY_COUNTER; // marking as counted
        
            // recursive calls in all 4 directions
            if (right.isInGrid(this.length, this.width) && map2[right.getY()][right.getX()] == tgt) {
                blobCount += areaOf(right, tgt, map2);
            }
            if (left.isInGrid(this.length, this.width) && map2[left.getY()][left.getX()] == tgt) {
                blobCount += areaOf(left, tgt, map2);
            }
            if (down.isInGrid(this.length, this.width) &&  map2[down.getY()][down.getX()] == tgt) {
                blobCount += areaOf(down, tgt, map2);
            }
            if (up.isInGrid(this.length, this.width) && map2[up.getY()][up.getX()] == tgt) {
                blobCount += areaOf(up, tgt, map2);
            }      
        }
        return blobCount;
   }

   /*
    @description: prints the map to standard output.
   */
   public void printMap() {
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.map[i][j] + " ");
            }
            System.out.println();
        }
   }
}

 