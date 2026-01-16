import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Animal_Subsystem.*;
import Structure_Subsystem.Habitat;

public class Zoo {
    //CONSTANTS 
    private static final String PERSON_FILE = "person.txt";
    private static final int MAX_STAFF = 200;
    private static final int MAX_VISITORS = 2000;
    private static final int MAX_ANIMALS = 200;
    private static final int MAX_EGGS = 100;
    
    //FIELDS
    private Employee[] staffList = new Employee[MAX_STAFF];
    private Visitor[] visitorList = new Visitor[MAX_VISITORS];
    private int numEmployees = 0;
    private int numVisitors = 0;
    private int numAnimals = 0;
    private int numEggs = 0;

    
    int numberOfAnimals;
    private Animal[] animals;
    private Egg[] eggs;
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

    // =========================
    // ANIMAL METHODS
    // =========================
    // =========================
    // SAVE ANIMALS
    // =========================

    public void saveAnimals(String file) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        // save number of animals
        bw.write(numAnimals + "");
        bw.newLine();

        // save each animal
        for (int i = 0; i < numAnimals; i++) {
            bw.write(animals[i].saveToString());
            bw.newLine();
        }

        bw.close();
    }


    // =========================
    // LOAD ANIMALS
    // =========================

    public void loadAnimals(Scanner input) {

        numAnimals = Integer.parseInt(input.nextLine());

        for (int i = 0; i < numAnimals; i++) {

            // ===== READ COMMON STATE =====
            char habitatId = input.nextLine().charAt(0);
            String name = input.nextLine();
            String specie = input.nextLine();
            String preferedInteraction = input.nextLine();
            String gender = input.nextLine();
            int happiness = Integer.parseInt(input.nextLine());
            int cleanliness = Integer.parseInt(input.nextLine());
            int hunger = Integer.parseInt(input.nextLine());
            int age = Integer.parseInt(input.nextLine());
            double weight = Double.parseDouble(input.nextLine());

            // ===== CREATE CORRECT SPECIES =====
            Animal animal = null;

            if (specie.equalsIgnoreCase("Unicorn")) {
                animal = new Unicorn(habitatId, name, preferedInteraction, gender,
                        happiness, cleanliness, hunger, age, weight);
            }
            else if (specie.equalsIgnoreCase("Capybara")) {
                animal = new Capybara(habitatId, name, preferedInteraction, gender,
                        happiness, cleanliness, hunger, age, weight);
            }
            else if (specie.equalsIgnoreCase("Eagle")) {
                animal = new Eagle(habitatId, name, preferedInteraction, gender,
                        happiness, cleanliness, hunger, age, weight);
            }
            else if (specie.equalsIgnoreCase("Cockatoo")) {
                animal = new Cockatoo(habitatId, name, preferedInteraction, gender,
                        happiness, cleanliness, hunger, age, weight);
            }
            else if (specie.equalsIgnoreCase("Snake")) {
                animal = new Snake(habitatId, name, preferedInteraction, gender,
                        happiness, cleanliness, hunger, age, weight);
            }
            else if (specie.equalsIgnoreCase("Crocodile")) {
                animal = new Crocodile(habitatId, name, preferedInteraction, gender,
                        happiness, cleanliness, hunger, age, weight);
            }
            else if (specie.equalsIgnoreCase("Frog")) {
                animal = new Frog(habitatId, name, preferedInteraction, gender,
                        happiness, cleanliness, hunger, age, weight);
            }
            else if (specie.equalsIgnoreCase("Axolotl")) {
                animal = new Axolotl(habitatId, name, preferedInteraction, gender,
                        happiness, cleanliness, hunger, age, weight);
            }
            else if (specie.equalsIgnoreCase("Shark")) {
                animal = new Shark(habitatId, name, preferedInteraction, gender,
                        happiness, cleanliness, hunger, age, weight);
            }
            else if (specie.equalsIgnoreCase("Sunfish")) {
                animal = new Sunfish(habitatId, name, preferedInteraction, gender,
                        happiness, cleanliness, hunger, age, weight);
            }

            animals[i] = animal;
        }
    }

    // =========================
    // ANIMAL METHODS
    // =========================

    // RELOCATE ANIMAL
    public boolean relocateAnimal(Habitat habitat, Animal animal) {
        Habitat originalHabitat = (Habitat)searchStructureByID(animal.getHabitatId());
        originalHabitat.removeAnimal();
        animal.leaveHabitat();
        if (animal.isSuitable(habitat)) {
            animal.setHabitatId(habitat.getHabitatId()); 
        }
    } 
    // ADD ANIMAL
    public boolean addAnimal(Habitat habitat, Animal animal) {
        animals[numAnimals] = animal;
        if (animal.isSuitable(habitat)) {
            animal.setHabitatId(habitat.getStructureID());
            habitat.addAnimal(animal);
        }
    }

    // =========================
    // FIND ANIMAL METHODS
    // =========================

    /**
     * Description: Finds and returns the first animal with the given name and species.
     * If multiple animals match, the first instance is returned.
     * Parameters:
     *   name   - name of the animal
     *   specie - species of the animal
     * Return:
     *   matching Animal or null if not found
     */
    public Animal findAnimal(String name, String specie) {
        for (int i = 0; i < numAnimals; i++) {
            if (animals[i].getName().equalsIgnoreCase(name) &&
                animals[i].getSpecie().equalsIgnoreCase(specie)) {
                return animals[i];
            }
        }
        return null;
    }

    /**
     * Description: Finds and returns the first animal with the given name.
     * If multiple animals have the same name, the first instance is returned.
     * Parameters:
     *   name - name of the animal
     * Return:
     *   matching Animal or null if not found
     */
    public Animal findAnimal(String name) {
        for (int i = 0; i < numAnimals; i++) {
            if (animals[i].getName().equalsIgnoreCase(name)) {
                return animals[i];
            }
        }
        return null;
    }

    // =========================
    // LISTING METHODS
    // =========================

    /**
     * Description: Prints all animals that match the given species.
     * Parameters:
     *   specie - species to search for
     */
    public void listAllSameSpecie(String specie) {
        for (int i = 0; i < numAnimals; i++) {
            if (animals[i].getSpecie().equalsIgnoreCase(specie)) {
                System.out.println(animals[i]);
            }
        }
    }

    // =========================
    // EGG METHODS
    // =========================

    /**
     * Description: Adds an egg to the incubator if there is space.
     * Parameters:
     *   egg - egg to add
     * Return:
     *   true if added successfully, false otherwise
     */
    public boolean addEgg(Egg egg) {
        if (numEggs < MAX_EGGS) {
            eggs[numEggs] = egg;
            numEggs++;
            return true;
        }
        egg.setIndex(numEggs);
        return false;
    }
    /**
     * Description: Removes an egg from the incubator at the given index.
     * Parameters:
     *   index - index of the egg to remove
     * Return:
     *   true if the egg was removed, false if index was invalid
     */
    public boolean removeEgg(int index) {

        if (index < 0 || index >= numEggs) {
            return false;
        }

        for (int i = index; i < numEggs - 1; i++) {
            eggs[i] = eggs[i + 1];
        }

        eggs[numEggs - 1] = null;
        numEggs--;

        return true;
    }
    // =========================
    // DISPLAY LOW STAT METHODS
    // =========================

    /**
     * Description: Displays names of all animals with low hunger.
     */
    public void displayAnimalsLowHunger() {
        for (int i = 0; i < numAnimals; i++) {
            if (animals[i].lowHunger()) {
                System.out.println(animals[i].getName());
            }
        }
    }

    /**
     * Description: Displays names of all animals with low happiness.
     */
    public void displayAnimalsLowHappiness() {
        for (int i = 0; i < numAnimals; i++) {
            if (animals[i].lowHappiness()) {
                System.out.println(animals[i].getName());
            }
        }
    }

    /**
     * Description: Displays names of all animals with low cleanliness.
     */
    public void displayAnimalsLowCleansiness() {
        for (int i = 0; i < numAnimals; i++) {
            if (animals[i].lowCleansiness()) {
                System.out.println(animals[i].getName());
            }
        }
    }

    // =========================
    // SORTING METHODS
    // =========================

    /**
     * Description: Sorts animals alphabetically by name, then by species if names match.
     * Sorting Algorithm: Insertion Sort
     */
    public void sortAnimalsByNameThenSpecie() {
        for (int i = 1; i < numAnimals; i++) {
            Animal key = animals[i];
            int j = i - 1;

            while (j >= 0) {
                int nameCompare =
                    animals[j].getName().compareToIgnoreCase(key.getName());

                if (nameCompare > 0 ||
                (nameCompare == 0 &&
                    animals[j].getSpecie().compareToIgnoreCase(key.getSpecie()) > 0)) {
                    animals[j + 1] = animals[j];
                    j--;
                } else {
                    break;
                }
            }
            animals[j + 1] = key;
        }
    }

    /**
     * Description: Sorts animals by age from youngest to oldest.
     * Sorting Algorithm: Selection Sort
     */
    public void sortAnimalsByAge() {
        for (int i = 0; i < numAnimals - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < numAnimals; j++) {
                if (animals[j].getAge() < animals[minIndex].getAge()) {
                    minIndex = j;
                }
            }

            Animal temp = animals[i];
            animals[i] = animals[minIndex];
            animals[minIndex] = temp;
        }
    }

    /**
     * Description: Sorts animals by happiness from lowest to highest.
     * Sorting Algorithm: Bubble Sort
     */
    public void sortAnimalsByHappiness() {
        for (int i = 0; i < numAnimals - 1; i++) {
            for (int j = 0; j < numAnimals - 1 - i; j++) {
                if (animals[j].getHappiness() > animals[j + 1].getHappiness()) {
                    Animal temp = animals[j];
                    animals[j] = animals[j + 1];
                    animals[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Description: Sorts animals by hunger from most hungry to least hungry.
     * Sorting Algorithm: Bubble Sort
     */
    public void sortAnimalsByHunger() {
        for (int i = 0; i < numAnimals - 1; i++) {
            for (int j = 0; j < numAnimals - 1 - i; j++) {
                if (animals[j].getHunger() < animals[j + 1].getHunger()) {
                    Animal temp = animals[j];
                    animals[j] = animals[j + 1];
                    animals[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Description: Sorts animals by cleanliness from lowest to highest.
     * Sorting Algorithm: Bubble Sort
     */
    public void sortAnimalsByCleanliness() {
        for (int i = 0; i < numAnimals - 1; i++) {
            for (int j = 0; j < numAnimals - 1 - i; j++) {
                if (animals[j].getCleanliness() > animals[j + 1].getCleanliness()) {
                    Animal temp = animals[j];
                    animals[j] = animals[j + 1];
                    animals[j + 1] = temp;
                }
            }
        }
    }
}
