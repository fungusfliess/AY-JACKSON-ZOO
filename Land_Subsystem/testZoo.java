package Land_Subsystem;
import Structure_Subsystem.*;
import java.util.*;
import java.io.*;

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

    public int searchStructureIdxByName (String name) {
        return zooLand.searchIdxByName(name);
    }

    public int searchStructureIdxByID (char id) {
        return zooLand.searchIdxByID(id);
    }

    public Structure searchStructureByID (char id) {
        int idx = zooLand.searchIdxByID(id);
        if (idx == -1) {
            return null;
        }
        return zooLand.getStructureAtIdx(idx);
    }

    /*
    @description: Searches for a Structure in the land subsystem by both its ID and name.
    @params: char id represents the ID of the Structure to search for.
    @returns: Structure object if found with matching ID and name, null otherwise.
    */
    public Structure searchStructureByIDAndName (char id, String name) {
        int idx = zooLand.searchIdxByID(id);
        // if not found, then searchIdxByID returns -1
        if (idx == -1) {
            return null;
        }
        // Structure is found, now check name
        Structure temp = zooLand.getStructureAtIdx(idx);
        // if name is equal, return Structure, else return null
        if (temp.getName().equals(name)) {
            return temp;
        }
        return null;
    }


}