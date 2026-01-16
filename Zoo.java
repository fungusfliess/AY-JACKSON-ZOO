import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Animal_Subsystem.Animal;
import Animal_Subsystem.LivingCondition;
import Person_Subsystem.Employee;
import Person_Subsystem.Person;
import Person_Subsystem.Visitor;
import Structure_Subsystem.Habitat;

public class Zoo {
    //CONSTANTS 
    private static final String PERSON_FILE = "person.txt";
    private static final int MAX_STAFF = 200;
    private static final int MAX_VISITORS = 2000;
    
    //FIELDS
    private Employee[] staffList = new Employee[MAX_STAFF];
    private Visitor[] visitorList = new Visitor[MAX_VISITORS];
    private int numEmployees = 0;
    private int numVisitors = 0;

    
    int numberOfAnimals;
    private Animal[] animals;
    private LivingCondition[] livingConditions;
    private Habitat[] habitats;
    private Land zooLand;

    public Zoo(int numberOfAnimals) {
        this.numberOfAnimals = numberOfAnimals;
    }
    public Zoo(String file) {
        
        // Load animals, habitats, and living conditions from files
    }


    // LAND METHODS
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

    public Structure searchByNumberAnimalsAndSize (int numAnimals, int size) {
        return zooLand.searchByNumberAnimalsAndSize(numAnimals, size);
    }

    public Habitat searchHabitatMostAnimalsAndLivingConditions (String condition) {
        return zooLand.searchHabitatMostAnimalsAndLivingConditions(condition);
    }

    public void sortStructuresByDaysSinceLastMaintenance (int numToSort) {
        zooLand.sortByDaysSinceLastMaintenance(numToSort);
    }

    public void sortStructuresByLeastAnimals () {
        zooLand.sortByLeastAnimals();
    }

    public void printMap() {
        zooLand.printMap();
    }

    public void sortStructureBySize () {
        zooLand.sortBySmallestToLargest();
    }

    public void sortBySizeAndTimeBetweenMaintenance () {
        zooLand.sortBySizeAndTimeBetweenMaintenance();
    }

    public void sortByMostAnimals () {
        zooLand.sortByMostAnimals();
    }
 
    public void printAllStructuresNeedingMaintenance () {
        zooLand.printAllStructuresNeedingMaintenance();
    }

    public void printAllStructureInfo () {
        zooLand.printAllStructureInfo();
    }

    public void maintainAll () {
        zooLand.maintainAll();
    }

    public void maintain(Structure input) {
        input.maintenance();
    } 



    public String toString() {
        return "This zoo has " + numberOfAnimals + " animals.\n" ;
        return "This zoo has " + numEmployees + "employees\n"; 
        return "This zoo has " + numVisitors + "visitors\n"; 
    }

   
    /*
        @description: checks whether a Person with the given personID exists in the current Zoo system
        @param personID the unique ID to search for
        @return true if a matching employee or visitor is found, false otherwise
    */
    public boolean personIDExists(String personID){
        if (personID == null){return false;}
    
        for (int i=0;i<numEmployees;i++){
            if (staffList[i]!=null && personID.equals(staffList[i].getPersonID())){
                return true;
            }
        }
    
        for (int i=0;i<numVisitors;i++){
            if (visitorList[i]!=null && personID.equals(visitorList[i].getPersonID())){
                return true;
            }
        }
    
        return false;
    }
    
    /*
        @description: adds a new Person object to the Zoo system (Employee -> staffList, Visitor -> visitorList)
        @param p the Person object to add
        @return true if the person was added successfully, false otherwise (null, duplicate ID, or array full)
    */
    public boolean addPerson(Person p){
        if (p == null || p.getPersonID() == null || personIDExists(p.getPersonID())){return false;}
    
        if (p instanceof Employee) {
            if (numEmployees >= staffList.length){return false;}
            staffList[numEmployees] = (Employee) p;
            numEmployees++;
            return true;
        }
    
        if (p instanceof Visitor) {
            if (numVisitors >= visitorList.length){return false;}
            visitorList[numVisitors] = (Visitor) p;
            numVisitors++;
            return true;
        }
    
        return false;
    }
    
    /*
        @description: deactivates a Person from the Zoo using their unique ID
        @param personID the unique ID of the person to deactivate
        @return true if a matching person was found and deactivated, false otherwise
    */
    public boolean deactivatePersonByID(String personID){
        if (personID == null) return false;
    
        for (int i = 0; i < numEmployees; i++) {
            if (staffList[i] != null && personID.equals(staffList[i].getPersonID())) {
                staffList[i].deactivate();
                return true;
            }
        }
    
        for (int i = 0; i < numVisitors; i++) {
            if (visitorList[i] != null && personID.equals(visitorList[i].getPersonID())) {
                visitorList[i].deactivate();
                return true;
            }
        }
    
        return false;
    }
    
    /*
        @description: finds and returns a Person using their unique ID
        @param personID the unique ID of the person to search for
        @return the matching Person object if found, otherwise null
    */
    public Person searchByPersonID(String personID) {
        if (personID == null) return null;
    
        for (int i = 0; i < numEmployees; i++) {
            if (staffList[i] != null && personID.equals(staffList[i].getPersonID())) {
                return staffList[i];
            }
        }
    
        for (int i = 0; i < numVisitors; i++) {
            if (visitorList[i] != null && personID.equals(visitorList[i].getPersonID())) {
                return visitorList[i];
            }
        }
        return null;
    }
    
    /*
        @description: finds and returns an Employee using their unique ID AND earnings value
        @param personID the employee's unique ID
        @param earnings the earnings value to match
        @return the matching Employee object if found, otherwise null
    */
    public Employee searchByPersonIDAndEarnings(String personID, double earnings) {
        if (personID == null) return null;
    
        for (int i = 0; i < numEmployees; i++) {
            Employee e = staffList[i];
            if (e != null && personID.equals(e.getPersonID()) && e.getEarnings() == earnings) {
                return e;
            }
        }
    
        return null;
    }
    
    /*
        @description: checks whether the visitor role label matches the visitor's age
        @param role the role string ("CHILD", "ADULT", "SENIOR")
        @param age  the visitor's age
        @return true if role matches the age range, false otherwise
    */
    private static boolean ageMatchesVisitorRole(String role, int age) {
        switch (role) {
            case "CHILD":  return age <= CHILD_MAX_AGE;
            case "ADULT":  return age >= ADULT_MIN_AGE && age < SENIOR_MIN_AGE;
            case "SENIOR": return age >= SENIOR_MIN_AGE;
            default:       return false;
        }
    }
    
    /*
        @description: displays info for all Visitors currently in the Zoo
    */
    public void displayAllVisitors() {
        System.out.println("=== ALL VISITORS (" + numVisitors + ") ===");
        for (int i = 0; i < numVisitors; i++) {
            if (visitorList[i] != null) {
                System.out.println(visitorList[i]);
                System.out.println("----------------------------------------");
            }
        }
    }
        
    /*
        @description: displays info for all Employees currently in the Zoo
    */
    public void displayAllEmployees() {
        System.out.println("=== ALL EMPLOYEES (" + numEmployees + ") ===");
        for (int i = 0; i < numEmployees; i++) {
            if (staffList[i] != null) {
                System.out.println(staffList[i]);
                System.out.println("----------------------------------------");
            }
        }
    }
    
    /*
        @description: writes all Person records to person.txt in correct format
                    - first line: total number of persons
                    - then all employees, then all visitors (one field per line)
    */
    private void savePersons() {
        try (PrintWriter out = new PrintWriter(new FileWriter(PERSON_FILE))) {
            out.println(numEmployees + numVisitors);

            for (int i = 0; i < numEmployees; i++) {
                if (staffList[i] != null) out.print(staffList[i].saveToString());
            }

            for (int i = 0; i < numVisitors; i++) {
                if (visitorList[i] != null) out.print(visitorList[i].saveToString());
            }

        } catch (IOException e) {
            System.out.println("ERROR failed to save persons: " + e.getMessage());
        }
    }
    
}
