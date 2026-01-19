/*
    File Name: Land.java
    Name: Jason Liu
    Class: ICS4U1-23
    Date: Jan 7, 2025
    Description: Land represents the physical land of the zoo, managing the map and structures within the zoo.
*/


public class Land {
    
    // FIELDS
    private int currentNumStructures;
    private int maxNumStructures;
    private Map landMap;
    private Structure[] structureList;

    // CONSTANTS
    public static final char EMPTY = '.'; // represents empty space on grid

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
    @description: Accessor for Structure at given index.
    @params: int idx is the index of the Structure to access.
    @returns: Structure at given index, or null if index is out of bounds.
    */
    public Structure getStructureAtIdx (int idx) {
        if (idx < 0 || idx >= currentNumStructures) {
            return null;
        }
        return structureList[idx];
    }


    // /*
    // @description: Saves land's map and list of structures to a file.
    // @params: String filename represents the name of the file to save to.
    // @returns: boolean indicating success.
    // */
    // public boolean saveToFile (String filename) {
    //     try {
    //         BufferedWriter out = new BufferedWriter(new FileWriter(filename));
    //         // write landMap into file.
    //         out.write(this.landMap.saveToString());

    //         // use out.write("\n") to create new lines because out.newLine() may cause formatting issues on different OS. (I think, I read this from a Google search.)
    //         out.write("\n");
    //         out.write(this.maxNumStructures + "\n");
    //         out.write(this.currentNumStructures + "\n");
    //         out.write("\n");
    //         // writing Structure info into the file.
    //         for (int i = 0; i < this.currentNumStructures; i++) {
    //             out.write(this.structureList[i].saveToString());
    //             out.write("\n");
                
    //             // any minor adjustments to formatting can be done here. (wait until Structure class is done)
    //         }
             
    //         out.close();
    //         return true;
    //     } catch (IOException iox) {
    //         System.out.println("Error writing to file: " + filename);
    //         return false;
    //     }
    // }

    /*
    @description: Saves land's map and list of structures to a String so that Zoo can save it into a file.
                Adapted from saveToFile, to be more OO.
    @returns: properly formatted String.
    */
    public String saveLandToString () {
        // add everything to a String, formatted accordingly
        String str = "";
        str += this.landMap.saveToString() + "\n";
        str += this.maxNumStructures + "\n";
        str += this.currentNumStructures + "\n";
        str += "\n";
        for (int i = 0; i < this.currentNumStructures; i++) {
            str += this.structureList[i].saveToString();
            str += "\n";
            // any minor adjustments to formatting can be done here. (wait until Structure class is done)
        }
        return str;
    }

    // /*
    // @description: Loads land's map and list of structures from a file.
    // @params: String filename represents the name of the file to load from.
    // @returns: boolean indicating success.

    // NOTE: This will OVERWRITE ANY EXISTING DATA in the Land object.
    // */
    // public boolean loadFromFile (String filename) {
    //     String input, sumString;
        
    //     try {
    //         BufferedReader in = new BufferedReader(new FileReader(filename));
    //         // reading landMap from file
    //         sumString = "";
    //         // if input is an empty line, stop reading. Check null to avoid errors. 
    //         // This will still read the empty line, so the next line is the beginning of the next section.
    //         while ((input = in.readLine()) != null && !input.isEmpty()) {
    //             sumString += input + "\n";
    //         }
    //         // pass this formatted String to Map.loadFromString to create landMap.
    //         this.landMap = Map.loadFromString(sumString);
            
    //         // reading currentNumStructures
    //         input = in.readLine();
    //         if (input != null) {
    //             this.maxNumStructures = Integer.parseInt(input);
    //         }
    //         input = in.readLine();
    //         if (input != null) {
    //             this.currentNumStructures = Integer.parseInt(input);
    //         }
    //         // creating new array of Structure
    //         this.structureList = new Structure[this.maxNumStructures];

    //         in.readLine();
    //         // reading Structure info from the file.
    //         String structureType = "";
            
    //         // for loop through array of Structures.
    //         for (int i = 0; i < this.currentNumStructures; i++) {
    //             sumString = "";
    //             input = in.readLine();
    //             if (input != null) {
    //                 structureType = input;
    //             } else {
    //                 return false;
    //             }
    //             // if input is an empty line, stop reading. Check null to avoid errors. This will still read the empty line.
    //             while ((input = in.readLine()) != null && !input.isEmpty()) {
    //                 sumString += input + "\n";
    //             }

    //             // based on the structureType, call the appropriate loadFromString method.
    //             System.out.println(structureType);
    //             switch (structureType) {
    //                 case "GiftShop":
    //                     this.structureList[i] = GiftShop.loadFromString(sumString, this);
    //                     break;
    //                 case "Restaurant":
    //                     this.structureList[i] = Restaurant.loadFromString(sumString, this);
    //                     break;
    //                 case "Pavillion":
    //                     this.structureList[i] = Pavillion.loadFromString(sumString, this);
    //                     break;
    //                 case "Enclosure":
    //                     this.structureList[i] = Enclosure.loadFromString(sumString, this);
    //                     break;
    //                 case "Maze":
    //                     this.structureList[i] = Maze.loadFromString(sumString, this);
    //                     break;
    //                 case "Park":
    //                     this.structureList[i] = Park.loadFromString(sumString, this);
    //                     break;
    //                 // add more cases as more instantiatable Structure types are created.
    //                 default:
    //                     System.out.println("Error: Unknown Structure type: " + structureType + "\nfound in file: " + filename + ". ");
    //                     return false;
    //             }

    //         }
    //         in.close();
    //         return true;
    //     } catch (IOException iox) {
    //         System.out.println("Error reading from file: " + filename);
    //     }
    //     return false;
    // }


    /*
    @description: Loads land's map and list of structures from a String. 
                Adapted from loadFromFile, to be more OO. 
    @params: String info represents the String from which to load in data.
    @returns: boolean indicating success.

    NOTE: This will OVERWRITE ANY EXISTING DATA in the Land object.
    */
    public boolean loadLandFromString (String info) {
        String[] rowStrings = info.split("\n");

        String sumString = "";
        int idx = 0;
        // if input is an empty line, stop reading. Check null to avoid errors. 
        // This will still read the empty line, so the next line is the beginning of the next section.
        while (!(idx > rowStrings.length-1) && rowStrings[idx] != null && !rowStrings[idx].isEmpty()) {
            sumString += rowStrings[idx] + "\n";
            idx++;
        }
        // pass this formatted String to Map.loadFromString to create landMap.
        this.landMap = Map.loadFromString(sumString);

        idx++;
        // reading maxNumStructures and currentNumStructures
        if (!(idx > rowStrings.length-1) && rowStrings[idx] != null && !rowStrings[idx].isEmpty()) {
            this.maxNumStructures = Integer.parseInt(rowStrings[idx]);
        } else {
            return false;
        }
        idx++;
        if (!(idx > rowStrings.length-1) && rowStrings[idx] != null && !rowStrings[idx].isEmpty()) {
            this.currentNumStructures = Integer.parseInt(rowStrings[idx]);
        } else {
            return false;
        }
        idx+= 2;

         // creating new array of Structure
        this.structureList = new Structure[this.maxNumStructures];

        
        // reading Structure info from the file.
        String structureType = "";
        
        // for loop through array of Structures.
        for (int i = 0; i < this.currentNumStructures; i++) {
            sumString = "";
            if (rowStrings[idx] != null) {
                structureType = rowStrings[idx];
                idx++;
            } else {
                return false;
            }
            // if input is an empty line, stop reading. Check null to avoid errors. This will still read the empty line.
            while (!(idx > rowStrings.length-1) && rowStrings[idx] != null && !rowStrings[idx].isEmpty()) {
                sumString += rowStrings[idx] + "\n";
                idx++;
            }
            idx += 1;

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
                    System.out.println("Error: Unknown Structure type: " + structureType + " found in file. ");
                    return false;
            }
        }
        return true;
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
        return -1; // if not found
    }
    
    /*
    @description: Searches for a Structure in the structureList by its ID.
    @params: char structureID represents the ID of the Structure to search for.
    @returns: int index of the Structure in the structureList array, or -1 if not found.
    */
    public int searchIdxByID (char structureID) {
        if (!charIsAllowed(structureID)) {return -1;}
        for (int i = 0; i < currentNumStructures; i++) {
            if (structureList[i].getStructureID() == structureID) {
                return i;
            }
        }
        return -1;
    }

    /*
    @description: Searches for a Structure in the land subsystem by both its ID and name.
    @params: char id represents the ID of the Structure to search for.
    @returns: Structure object if found with matching ID and name, null otherwise.
    */
    public Structure searchStructureByIDAndName (char id, String name) {
        int idx = this.searchIdxByID(id);
        // if not found, then searchIdxByID returns -1
        if (idx == -1) {
            return null;
        }
        // Structure is found, now check name
        Structure temp = this.getStructureAtIdx(idx);
        // if name is equal, return Structure, else return null
        if (temp.getName().equals(name)) {
            return temp;
        }
        return null;
    }

    /*
    @description: Searches for a Structure with matching parameters 
    @params: numAnimals and size represents user desired number of Animals and size
    @returns: returns Structure object, null if not found
    */

    public Structure searchByNumberAnimalsAndSize(int numAnimals, int size){
        this.sortByAnimalsAndSize();
        Structure current;
        int top = currentNumStructures -1, bottom = 0, middle, index = -1;
        boolean found = false;
        while(top >= bottom && !found){
            middle = (top + bottom)/2; 
            current = structureList[middle];
            if(current.getNumAnimals() == numAnimals && current.getArea() == size){
                index = middle;
                found = true;
            }else if(current.getNumAnimals() < numAnimals || (current.getNumAnimals() == numAnimals && current.getArea() < size)){
                bottom = middle + 1;
            }else{
                top = middle -1;
            }
        }
        return getStructureAtIdx(index);
    }

    /*
    @description: Searches for Habitat with matching climate and most Animals
    @params: climate represents user desired LivingCondition 
    @returns: returns Structure object 
    */

    public Habitat searchMostAnimalsAndLivingCondition(LivingCondition climate){
        Habitat mostAnimals = null;
        Structure current;
        Habitat currentHabitat;
        for(int i = 0; i < currentNumStructures; i++){
            current = structureList[i];
            // must be habitat, or else not considered.
            if (current instanceof Habitat) {
                currentHabitat = (Habitat) current;
                // look for most animals
                if (currentHabitat.getClimate() == climate) {
                    if (mostAnimals == null || currentHabitat.compareToNumAnimals(mostAnimals) > 0) {
                        mostAnimals = currentHabitat;
                    }   
                }

            }
        }
        return mostAnimals;
    }

    /*
    @description: Finds the habitat with the most animals that satisfies the inputted livingCondition
    @params: LivingCondition condition2 is the LivingCondition these habitats must match. If null, then it'll be ignored.
    @returns: Habitat with the most animals that satisfies a specific LivingCondition, null if not found.
    */
    public Habitat searchHabitatMostAnimalsAndLivingConditions (LivingCondition condition2) {
        Habitat mostAnimals = null;
        Habitat currentHabitat = null;
        // finding largest num of animals
        for (int i = 0; i < currentNumStructures; i++) {
            // must be a habitat
            if (structureList[i] instanceof Habitat) {
                currentHabitat = (Habitat)structureList[i];
                if (condition2 == null || currentHabitat.getClimate().compareTo(condition2) == 0) { // must have a specific living condition, if it was null then we ignore it and do not compare based off of it. 
                    // if more animals that current mostAnimals
                    if ((mostAnimals == null || (currentHabitat.getNumAnimals() > mostAnimals.getNumAnimals()))) {
                        mostAnimals = currentHabitat;
                    }
                }  
            }
        }
        return mostAnimals;
    }

    /*
    @description: Searches for a Structure in the structureList by its ID using binary search.
    @params: char tgtIdx represents the ID of the Structure to search for.
    @returns: Structure object if found, or null if not found.
    */
    public Structure binarySearchByID (char tgtIdx) {
        // sort by ID first
        this.sortByID();
        Structure tgt = null;
        int left = 0, right = currentNumStructures-1;
        int mid; 
        boolean found = false;
        // binary search
        while (!found && (left <= right)) {
            mid = (left + right)/2;
            if (structureList[mid].getStructureID() == tgtIdx) { // if found
                tgt = structureList[mid];
                found = true;
            } else if (structureList[mid].getStructureID() < tgtIdx) { // if target is to the right
                left = mid+1;
            } else if (structureList[mid].getStructureID() > tgtIdx) { // if target is to the left
                right = mid-1;
            }
        }
        return tgt;
    }

    // SORT
    /*
    @description: Sorts array of Structures based on their ID in ascending order.
    */
    public void sortByID () {
        // bubble sort
        Structure temp;
        // early termination variable
        boolean swapped = true;
        for (int i = 0; i < currentNumStructures-1 && swapped; i++) {
            swapped = false;
            for (int j = 0; j < currentNumStructures-i-1; j++) {
                
                // if this structure's ID is greater in terms of ASCII code than the one after it, swap
                if (structureList[j].compareToID(structureList[j+1]) > 0) {
                    temp = structureList[j];
                    structureList[j] = structureList[j+1];
                    structureList[j+1] = temp;
                    swapped = true;
                }
            }
        }
    }

    /*
    @description: Sorts array of Structures based on # of Animals, from most to least. Leaves non-habitat Structures in their original order, but pushed back.
    */
    public void sortByMostAnimals () {
        // if a structure is not a Habitat, disregard it entirely. 
        int blankIdx;
        Structure temp;
        for (int i = 1; i < currentNumStructures; i++) {
            blankIdx = i;
            temp = structureList[i];
            // insertion sort
            if (temp instanceof Habitat) { // only pushes forward if it is a habitat, leaving non-habitats in order. 
                // stopping conditions: pushed all the way to index of 0, or the Habitat in front of temp is greater in terms of number of animals. 
                // Will push if the Structure to the left is NOT a Habitat 
                while (!(blankIdx == 0 || ((structureList[blankIdx-1] instanceof Habitat) && (((Habitat)structureList[blankIdx-1]).getNumAnimals() < ((Habitat)temp).getNumAnimals())))) {
                    structureList[blankIdx] = structureList[blankIdx-1];
                    blankIdx--;
                }
            }
            structureList[blankIdx] = temp;
        }
    }

    /*
    @description: Sorts array of Structures based on how urgently they need maintenance
    @params: int numToSort is the number of Structures the user wnats sorted. 
    */
    public void sortByDaysSinceLastMaintenance(int numToSort) {

        // if input is negative or too large, then sort the entire array.
        if (numToSort < 0 || numToSort > currentNumStructures) {
            numToSort = currentNumStructures;
        }
        Structure temp;
        int highest;
        // selection sort
        for (int i = 0; i < numToSort; i++) {

            // find highest num of days without maintenance
            highest = i;
            for (int j = i+1; j < currentNumStructures; j++) {
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
    @description: Sorts array of Structures based on # of Animals, from least to most
    */

    public void sortByLeastAnimals(){
        Structure temp;
        boolean sorted = false;
        for(int i = currentNumStructures - 1; i > 0 && !sorted; i--){
            sorted = true;
            for(int j = 0; j < i; j++){
                if(structureList[j].compareToNumAnimals(structureList[j + 1]) > 0){
                    temp = structureList[j];
                    structureList[j] = structureList[j + 1];
                    structureList[j + 1] = temp;
                    sorted = false; 
                }
            }
        }
    }

    /*
    @description: Sorts array of Structures based on # of Animals, from least to most, 
    and size from least to most if # of Animals is the same
    */

    public void sortByAnimalsAndSize(){
        Structure temp;
        int blankIndex; 
        for(int i = 1; i < currentNumStructures; i++){
            temp = structureList[i];
            blankIndex = i;
            while(blankIndex > 0 && (structureList[blankIndex - 1].compareToNumAnimals(temp) > 0 
            || (structureList[blankIndex - 1].compareToNumAnimals(temp) == 0 
            && structureList[blankIndex - 1].compareToSize(temp) > 0))){
                structureList[blankIndex] = structureList[blankIndex - 1];
                blankIndex--;
            }
            structureList[blankIndex] = temp;
        }
    }


    /*
    @description: sorts the array of Structure based on each Structure's area, from smallest to largest. 
    */
    public void sortBySmallestToLargest () {
        // bubble sort
        Structure temp;
        // early termination boolean
        boolean swapped = true;
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
        boolean swapped = true;
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

    public void printAllHabitatInfo () {
        for (int i = 0; i < currentNumStructures; i++) {
            if (structureList[i] instanceof Habitat) {
                System.out.println(((Habitat)structureList[i]).toString());
                System.out.println();
            }
            
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
    public boolean createEnclosure (Coord corner1, int maxRadiusOfBuild, String species, String name, char structureID, int timeBetweenMaintenance, int maxAnimal, LivingCondition climate) {
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
        structureList[currentNumStructures] = new Enclosure(species, name, structureID, area, timeBetweenMaintenance, 0, this, maxAnimal, climate);
        currentNumStructures++;
        System.out.println("Area of Enclosure: " + area); 
        return true;
    }

    /*
    @description: creates a Pavillion for the zoo. Creates and stores an instance of the class, as well as the structure on the map.
    @params: corner1 is to tell where to start building the blob structure. maxRadiusOfBuild is the maximum radius of sprawl of the blob generation. name, char, capacity, condition are parameters for the Pavillion constructor.
    @returns: boolean representing success. Will be unsuccessful if the Structure array is full, or the blob build area has obstacles.
    */
    public boolean createPavillion (Coord corner1, int maxRadiusOfBuild, String name, char structureID, int timeBetweenMaintenance, int capacity, LivingCondition condition) {
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
        structureList[currentNumStructures] = new Pavillion(name, structureID, area, timeBetweenMaintenance, 0, this, capacity, condition);
        currentNumStructures++;
        System.out.println("Area of Pavillion: " + area); 
        return true;
    }

    /*
    @description: creates a park for the zoo. Creates and stores an instance of the class, as well as a structure on the map.
    @params: corner1 is to tell where to start building the blob structure. maxRadiusOfBuild is the maximum radius of sprawl of the blob generation. name, char, capacity, condition are parameters for the Park constructor.
    @returns: boolean representing success. Will be unsuccessful if the Structure array is full, or the blob build area has obstacles.
    */
    public boolean createPark (Coord corner1, int maxRadiusOfBuild, String name, char structureID, int timeBetweenMaintenance) {
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
        structureList[currentNumStructures] = new Park(name, structureID, area, timeBetweenMaintenance, 0, this);
        currentNumStructures++;
        return true;
    }

    /*
    @description: creates a Maze for the zoo. Creates and stores an instance of the class, as well as the structure on the map.
    @params: corner1 is to tell where to start the maze structure (top left corner). structureID, timeBetweenMaintenance, mazeLayout are parameters for the Maze constructor.
    @returns: boolean representing success. Will be unsuccessful if the Structure array is full, or the maze build area has obstacles.
    */
    public boolean createMaze (Coord corner1, String name, char structureID, int timeBetweenMaintenance, char[][] mazeLayout) {
        // if array is full
        if (currentNumStructures == maxNumStructures) {
            return false;
        }
        Coord corner2 = new Coord(corner1.getX() + mazeLayout[0].length-1, corner1.getY() + mazeLayout.length-1); 
        // if physical building cannot be built
        if (!landMap.buildStructureRectangular(corner1, corner2, structureID)) {
            return false;
        }
        // calculating area of the Structure.
        int area = landMap.areaOf(corner1);
        structureList[currentNumStructures] = new Maze(name, structureID, area, timeBetweenMaintenance, 0, this, mazeLayout);
        currentNumStructures++;
        return true;
    }

    /*
    @description: removes a Structure from the structureList array and demolishes it from the map.
    @params: int tgtIdx is the index of the Structure to be removed.
    @returns: boolean representing success. Will be unsuccessful if the Structure's demolish method returns false.
    */
    public boolean removeStructureFromList (int tgtIdx) {
        // if demolish is unsuccessful (also calls demolish on the Structure)
        char tempID = structureList[tgtIdx].getStructureID();
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
        landMap.erase(landMap.find(tempID));
        return true;
    }

    /*
    @description: checks if a character is allowed to be placed on the land map.
    @params: char in is the character to check.
    @returns: boolean representing if the character is allowed.
    */
    public boolean charIsAllowed (char in) {
        return landMap.charIsAllowed(in);
    }

    /*
    @description: prints the land map to standard output
    */
    public void printMap() {
        landMap.printMap();
    }
}
