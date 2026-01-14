public class testZoo {
    // CONSTANTS
    public static final String ANIMAL_FILE = "animals.txt";
    public static final String CONDITION_FILE = "livingConditions.txt";
    public static final String EGG_FILE = "eggs.txt";
    public static final String PERSON_FILE = "persons.txt";
    public static final String LAND_FILE = "land.txt";

    private Land zooLand;

    public boolean loadLandFromFile () { 
        return zooLand.loadFromFile(LAND_FILE);
    }

    public boolean saveLandToFile () {
        return zooLand.saveToFile(LAND_FILE);
    }

    public int searchIdxByName (String name) {
        return zooLand.searchIdxByName(name);
    }

    public int searchIdxByID (char id) {
        return zooLand.searchIdxByID(id);
    }

    
}