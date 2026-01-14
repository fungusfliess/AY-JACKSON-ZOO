package Land_Subsystem;
import Structure_Subsystem.*;
import java.io.*;

import Animal_Subsystem.LivingCondition;

public class Land {
    
    // FIELDS
    private int currentNumStructures;
    private int maxNumStructures;
    private Map landMap;
    private Structure[] structureList;


    // CONSTRUCTOR
    public Land(int maxNumStructures, Map landMap) {
        this.maxNumStructures = maxNumStructures;
        this.landMap = landMap;
        this.currentNumStructures = 0;
        this.structureList = new Structure[maxNumStructures];
    }

    // ACCESSOR MUTATORS
    public int getCurrentNumStructures() {
        return currentNumStructures;
    }
    public void setCurrentNumStructures(int currentNumStructures) {
        this.currentNumStructures = currentNumStructures;
    }
    public int getMaxNumStructures() {
        return maxNumStructures;
    }
    public void setMaxNumStructures(int maxNumStructures) {
        this.maxNumStructures = maxNumStructures;
    }


    /*
    @description: Saves land's map and list of structures to a file.
    @params: String filename represents the name of the file to save to.
    @returns: boolean indicating success.
    */
    public boolean saveToFile (String filename) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            // write landMap into file.
            out.write(this.landMap.saveToString());

            // use out.write("\n") to create new lines because out.newLine() may cause formatting issues on different OS. (I think, I read this from a Google search.)
            out.write("\n");
            out.write(this.maxNumStructures + "\n");
            out.write(this.currentNumStructures + "\n");
            out.write("\n");
            // writing Structure info into the file.
            for (int i = 0; i < this.currentNumStructures; i++) {
                out.write(this.structureList[i].saveToString());
                // any minor adjustments to formatting can be done here. (wait until Structure class is done)
            }
             
            out.close();
            return true;
        } catch (IOException iox) {
            System.out.println("Error writing to file: " + filename);
            return false;
        }
    }

    /*
    @description: Loads land's map and list of structures from a file.
    @params: String filename represents the name of the file to load from.
    @returns: boolean indicating success.

    NOTE: This will OVERWRITE ANY EXISTING DATA in the Land object.
    */
    public boolean loadFromFile (String filename) {
        String input, sumString;
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            // reading landMap from file
            sumString = "";
            // if input is an empty line, stop reading. Check null to avoid errors. 
            // This will still read the empty line, so the next line is the beginning of the next section.
            while ((input = in.readLine()) != null && !input.isEmpty()) {
                sumString += input + "\n";
            }
            // pass this formatted String to Map.loadFromString to create landMap.
            this.landMap = Map.loadFromString(sumString);
            
            // reading currentNumStructures
            input = in.readLine();
            if (input != null) {
                this.maxNumStructures = Integer.parseInt(input);
            }
            input = in.readLine();
            if (input != null) {
                this.currentNumStructures = Integer.parseInt(input);
            }
            // creating new array of Structure
            this.structureList = new Structure[this.maxNumStructures];

            in.skip(1);
            // reading Structure info from the file.
            String structureType;
            
            // I originally intended this to be a while loop until null but since the save file already has a numStructures field, a for loop is more appropriate.
            for (int i = 0; i < this.currentNumStructures; i++) {
                sumString = "";
                input = in.readLine();
                if (input != null) {
                    structureType = input;
                }
                // if input is an empty line, stop reading. Check null to avoid errors. This will still read the empty line.
                while ((input = in.readLine()) != null && !input.isEmpty()) {
                    sumString += input + "\n";
                }

                // based on the structureType, call the appropriate loadFromString method.
                switch (structureType) {
                    case "GiftShop":
                        this.structureList[i] = GiftShop.loadFromString(sumString, this);
                        break;
                    case "Restaurant":
                        this.structureList[i] = Restaurant.loadFromString(sumString, this);
                        break;
                    case "Pavillion":
                        this.structureList[i] = Pavillion.loadFromString(sumString, this);
                        break;
                    case "Enclosure":
                        this.structureList[i] = Enclosure.loadFromString(sumString, this);
                        break;
                    case "Maze":
                        this.structureList[i] = Maze.loadFromString(sumString, this);
                        break;
                    case "Park":
                        this.structureList[i] = Park.loadFromString(sumString, this);
                        break;
                    // add more cases as more instantiatable Structure types are created.
                    default:
                        System.out.println("Error: Unknown Structure type: " + structureType + "\nfound in file: " + filename + ". ");
                        return false;
                }

            }
            in.close();
            return true;
        } catch (IOException iox) {
            System.out.println("Error reading from file: " + filename);
        }
        return false;
    }

    // MANAGE STRUCTURE ARRAY
    // SEARCH
    /*
    @description: Searches for a Structure in the structureList by its name.
    @params: String name represents the name of the Structure to search for.
    @returns: int index of the Structure in the structureList array, or -1 if not found.
    */
    public int searchIdxByName (String name) {
        for (int i = 0; i < currentNumStructures; i++) {
            if (structureList[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
    
    public int searchIdxByID (char structureID) {
        for (int i = 0; i < currentNumStructures; i++) {
            if (structureList[i].getStructureID() == structureID) {
                return i;
            }
        }
        return -1;
    }

    // SORT

    /*
    @description: Sorts array of Structures based on how urgently they need maintenance
    @params: int numToSort is the number of Structures the user wnats sorted. 
    */
    public void sortByDaysSinceLastMaintenance(int numToSort) {

        // if input is negative, then sort the entire array.
        if (numToSort < 0) {
            numToSort = currentNumStructures;
        }
        Structure temp;
        int highest;
        // selection sort
        for (int i = 0; i < numToSort && i < currentNumStructures; i++) {

            // find highest num of days without maintenance
            highest = 0;
            for (int j = 0; j < currentNumStructures; j++) {
                if (structureList[j].compareToSinceLastMaintenance(structureList[highest]) > 0) {
                    highest = j;
                }
            }

            // swap
            temp = structureList[i];
            structureList[i] = structureList[highest];
            structureList[highest] = temp;

        }
    }

    /*
    
    ARIANNA IS DOING SORTBYANIMALS AND SORTBYSIZEANDANIMALS HERE

    */

    /*
    @description: sorts the array of Structure based on each Structure's area, from smallest to largest. 
    */
    public void sortBySmallestToLargest () {
        // bubble sort
        Structure temp;
        // early termination boolean
        boolean swapped = false;
        for (int i = 0; i < currentNumStructures-1 && swapped; i++) {
            swapped = false;
            for (int j = 0; j < currentNumStructures-i-1; j++) {
                
                // if this structure is larger than the one after it, swap
                if (structureList[j].compareToSize(structureList[j+1]) > 0) {
                    temp = structureList[j];
                    structureList[j] = structureList[j+1];
                    structureList[j+1] = temp;
                    swapped = true;
                }
            }
        }
    }

    /*
    @description: Sorts by smallest to largest based on size (dominant attribute), and if size is equal then sort by time between maintenance, longer goes in front.
    */
    public void sortBySizeAndTimeBetweenMaintenance () {   
        // bubble sort
        Structure temp;
        // early termination boolean
        boolean swapped = false;
        for (int i = 0; i < currentNumStructures-1 && swapped; i++) {
            swapped = false;
            for (int j = 0; j < currentNumStructures-i-1; j++) {
                
                // if this structure is larger than the one after it, swap
                if ((structureList[j].compareToSize(structureList[j+1]) > 0) || ((structureList[j].compareToSize(structureList[j+1]) == 0) && (structureList[j].compareToSinceLastMaintenance(structureList[j+1]) < 0))) {
                    temp = structureList[j];
                    structureList[j] = structureList[j+1];
                    structureList[j+1] = temp;
                    swapped = true;
                }
            }
        }
    }

    /*
    @description: prints all Structure information in standard output.
    */
    public void printAllStructureInfo () {
        for (int i = 0; i < currentNumStructures; i++) {
            System.out.println(String.format("Name: %s%nID: %c%nArea: %d%n", structureList[i].getName(), structureList[i].getStructureID(), structureList[i].getArea()));
        }
    }

    /*
    @description: prints the ID of all Structures that require maintenance
    */
    public void printAllStructuresNeedingMaintenance () {
        for (int i = 0; i < currentNumStructures; i++) {
            if (structureList[i].needsMaintenance()) {
                System.out.println(structureList[i].getStructureID());
            }
        }
    }

    /*
    @description: maintains all Structures
    */
    public void maintainAll () {
        for (int i = 0; i < currentNumStructures; i++) {
            structureList[i].maintenance();
        }
    }

    /*
    @description: passes day for all Structures
    */
    public void passDay () {
        for (int i = 0; i < currentNumStructures; i++) {
            structureList[i].passDay();
        }
    }

    // CREATING STRUCTURES

    /*
    @description: creates a gift shop for the zoo. Creates and stores an instance of the class, as well as the structure on the map. 
    @params: corner1, corner2 are to tell where to build the rectangular structure. name, char, timeBetweenMaintenance, animalFactStrings, menu, are inputs for the GiftShop constructor.
    @returns: boolean representing success. Will be unsuccessful if the Structure array is full, or the rectangular build area has obstacles.
    */
    public boolean createGiftShop (Coord corner1, Coord corner2, String name, char structureID, int timeBetweenMaintenance, String[] animalFactStrings, Item[] menu) {
        // if array is full
        if (currentNumStructures == maxNumStructures) {
            return false;
        }
        // if physical building cannot be built
        if (!landMap.buildStructureRectangular(corner1, corner2, structureID)) {
            return false;
        }
        // calculating area of the Structure.
        int area = landMap.areaOf(corner1);
        structureList[currentNumStructures] = new GiftShop(name, structureID, area, timeBetweenMaintenance, 0, this, animalFactStrings, menu);
        currentNumStructures++;
        return true;
    }

    /*
    @description: creates a Restaurant for the zoo. Creates and stores an instance of the class, as well as the structure on the map. 
    @params: corner1, corner2 are to tell where to build the rectangular structure. name, char, timeBetweenMaintenance, animalFactStrings, menu, are inputs for the Restaurant constructor.
    @returns: boolean representing success. Will be unsuccessful if the Structure array is full, or the rectangular build area has obstacles.
    */    
    public boolean createRestaurant (Coord corner1, Coord corner2, String name, char structureID, int timeBetweenMaintenance, String[] animalFactStrings, Item[] menu) {
        // if array is full
        if (currentNumStructures == maxNumStructures) {
            return false;
        }
        // if physical building cannot be built
        if (!landMap.buildStructureRectangular(corner1, corner2, structureID)) {
            return false;
        }
        // calculating area of the Structure.
        int area = landMap.areaOf(corner1);
        structureList[currentNumStructures] = new Restaurant(name, structureID, area, timeBetweenMaintenance, 0, this, animalFactStrings, menu);
        currentNumStructures++;
        return true;
    }
    
    /*
    @description: creates an Enclosure for the zoo. Creates and stores an instance of the class, as well as the structure on the map.
    @params: corner1 is to tell where to build the circular structure. maxRadiusOfBuild is the maximum radius of the circular structure. name, char, timeBetweenMaintenance, animalFactStrings, menu, are inputs for the Enclosure constructor.
    @returns: boolean representing success. Will be unsuccessful if the Structure array is full, or the circular build area has obstacles.
    */
    public boolean createEnclosure (Coord corner1, int maxRadiusOfBuild, String name, char structureID, int timeBetweenMaintenance, String[] animalFactStrings, Item[] menu) {
        // if array is full
        if (currentNumStructures == maxNumStructures) {
            return false;
        }
        // if physical building cannot be built
        if (!landMap.buildStructureBlob(corner1, structureID, maxRadiusOfBuild)) {
            return false;
        }
        // calculating area of the Structure.
        int area = landMap.areaOf(corner1);
        structureList[currentNumStructures] = new Enclosure(name, structureID, area, timeBetweenMaintenance, 0, this, animalFactStrings, menu);
        currentNumStructures++;
        return true;
    }

    public boolean createPavillion (Coord corner1, int maxRadiusOfBuild, String name, char structureID, int capacity, LivingCondition condition) {
        // if array is full
        if (currentNumStructures == maxNumStructures) {
            return false;
        }
        // if physical building cannot be built
        if (!landMap.buildStructureBlob(corner1, structureID, maxRadiusOfBuild)) {
            return false;
        }
        // calculating area of the Structure.
        int area = landMap.areaOf(corner1);
        structureList[currentNumStructures] = new Pavillion(name, structureID, area, capacity, this, condition);
        currentNumStructures++;
        return true;
    }

    /*
    @description: removes a Structure from the structureList array and demolishes it from the map.
    @params: int tgtIdx is the index of the Structure to be removed.
    @returns: boolean representing success. Will be unsuccessful if the Structure's demolish method returns false.
    */
    public boolean removeStructureFromList (int tgtIdx) {
        if (!structureList[tgtIdx].demolish()) {
            return false;
        } 
        // shift all Structures after it down to fill in the gap. 
        for (int i = tgtIdx; i < currentNumStructures-1; i++) {
            structureList[i] = structureList[i+1];
        }
        
        structureList[currentNumStructures-1] = null;

        // update currentNumStructures
        currentNumStructures --;
        // remove from map by finding any coordinate with structureID and recursively erasing it from there. 
        landMap.erase(landMap.find(structureList[tgtIdx].getStructureID()));
        return true;
    }
}
