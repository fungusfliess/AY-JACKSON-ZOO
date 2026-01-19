/*
   File Name: Zoo.java
   Names: Arianna Liu, Jerry Ning, Jason Liu, Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Zoo is the main management class for the zoo system.
                It manages structures, animals, persons, and handles file I/O operations.
*/
import java.io.*;

public class Zoo {

    //CONSTANTS 
    public static final String ZOO_CONSTRUCTOR_FILE = "zoo.txt";
    public static final String ANIMAL_FILE = "animal.txt";
    public static final String EGG_FILE = "eggs.txt";
    public static final String PERSON_FILE = "persons.txt";
    public static final String LAND_FILE = "land.txt";
    public static final String ADMIN_PIN = "0000";
    public static final int MAX_NUM_STRUCTURES = 40; 
    public static final int length = 20;
    public static final int width = 50;
    public static final String QUIT = "quit";
    public static final char[][] MAZE_DEFAULT_SHAPE = {{'.', '.'}, {'N', 'N'}};
    // {
    //                          {'.', '.', '.', '.', '.', '.', 'N', '.', 'N', '.', '.', '.', '.', '.', '.', }, 
    //                          {'.', '.', '.', '.', 'N', 'X', 'N', '.', 'N', '.', 'N', 'N', 'N', 'N', 'N', }, 
    //                          {'.', 'N', 'N', 'N', 'N', 'N', 'N', '.', '.', '.', 'N', 'N', 'N', 'N', 'N', }, 
    //                          {'.', 'N', '.', '.', '.', '.', '.', '.', 'N', '.', '.', '.', '.', '.', '.', }, 
    //                          {'.', 'N', '.', 'N', '.', 'N', '.', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', }, 
    //                          {'.', 'N', '.', 'N', '.', 'N', '.', '.', 'N', 'N', '.', '.', '.', '.', '.', }, 
    //                          {'.', 'N', 'N', 'N', '.', 'N', '.', '.', '.', '.', '.', '.', '.', '.', '.', }, 
    //                          {'.', '.', '.', '.', '.', 'N', '.', '.', '.', 'N', '.', '.', '.', '.', '.', }, 
    //                          {'.', 'N', 'N', '.', 'N', 'N', 'N', 'N', 'N', 'N', 'N', '.', 'N', 'N', 'N', }, 
    //                          {'.', '.', '.', '.', '.', '.', '.', 'N', 'N', '.', 'N', '.', '.', '.', '.', }, 
    //                          {'.', 'N', '.', 'N', 'N', 'N', '.', '.', '.', '.', 'N', 'N', 'N', '.', 'N', }, 
    //                          {'.', 'N', '.', 'N', '.', 'N', 'N', 'N', '.', 'N', 'N', '.', 'N', '.', '.', }, 
    //                          {'.', 'N', '.', 'N', '.', 'N', '.', '.', '.', '.', '.', '.', 'N', '.', 'N', }, 
    //                          {'.', 'N', '.', 'N', '.', 'N', '.', 'N', 'N', 'N', 'N', '.', 'N', '.', '.', }, 
    //                          {'.', 'N', 'N', 'N', '.', 'N', '.', '.', '.', '.', 'N', '.', 'N', '.', 'N', }, 
    //                          {'.', '.', '.', '.', '.', 'N', '.', 'N', '.', '.', 'N', '.', '.', '.', '.', }, };;

    //RANDOM 
    public int getNumVisitors() { return numVisitors; }
public int getNumEmployees() { return numEmployees; }

    //FIELDS
    private Employee[] employeeList;
    private Visitor[] visitorList;

    private int maxEmployee;
    private int maxVisitor;
    private int maxAnimal;
    private int maxEggs;
    
    private int numEmployees;
    private int numVisitors;
    private int numAnimals;
    private int numEggs; 

    private double zooBalance;

    
    private Animal[] zooAnimals;
    private Egg[] incubator;
    private Map map = new Map(length, width);
    private Land zooLand = new Land(MAX_NUM_STRUCTURES, map);
    

    /*
    @description: constructs and initializes the Zoo by reading configuration values
                  from ZOO_CONSTRUCTOR_FILE, allocating arrays, and loading all saved data
                  (persons, animals, land/structures, and map).
    @postcondition: Zoo fields are initialized and load methods are called so the Zoo is ready to use.
    */
    public Zoo(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(ZOO_CONSTRUCTOR_FILE));
            double balance = Double.parseDouble(br.readLine());
            int animals = Integer.parseInt(br.readLine());
            int eggs = Integer.parseInt(br.readLine());
            int employees = Integer.parseInt(br.readLine());
            int visitors = Integer.parseInt(br.readLine());
            
            
            this.zooBalance = balance;
            this.maxAnimal = animals;
            this.maxEggs = eggs;
            this.maxEmployee = employees;
            this.maxVisitor = visitors; 
            
            this.zooAnimals = new Animal[maxAnimal];
            this.incubator = new Egg[maxEggs];
            this.employeeList = new Employee[maxEmployee];
            this.visitorList = new Visitor[maxVisitor];
            
            br.close();
         
        }catch(IOException e){
            System.out.println("Error reading file.");
        }
        loadPersons();
        loadLandFromFile(LAND_FILE);
        loadAnimals(ANIMAL_FILE);
        loadEggs(EGG_FILE);

        System.out.println("Zoo Successfully Loaded!");
    }
   
    

    /*
    @description: saves the Zoo system state to files by saving persons, animals, eggs,
                  and land/structures, and then writing configuration values back to the
                  ZOO_CONSTRUCTOR_FILE.
    @postcondition: persistent files are updated to reflect the Zoo's current state.
    */
    public void saveZoo(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(ZOO_CONSTRUCTOR_FILE, false));
            bw.write(String.valueOf(zooBalance));
            bw.newLine();
            bw.write("" + maxAnimal);
            bw.newLine();
            bw.write("" + maxEggs);
            bw.newLine();
            bw.write("" + maxEmployee);
            bw.newLine();
            bw.write("" + maxVisitor);
            System.out.println("after saving zoo info, before saving the 3 files.");
            savePersons();
            System.out.println("after savePersons");
            saveAnimals(ANIMAL_FILE);
            System.out.println("after saveAnimals");
            saveLandToFile(LAND_FILE);
            System.out.println("Zoo Successfully Saved!");
            bw.close();
        }catch(IOException e){
            System.out.println("Error saving zoo: " + e.getMessage());
        }
    }
    
    /*
    @description: advances the Zoo simulation forward by a specified number of days by
                  repeatedly calling passDay().
    @param days the number of days to simulate (if days <= 0, no changes occur)
    @postcondition: employees, visitors, and land have progressed forward by "days" days.
    */
    public void passTime(int days){
        for(int i = 0; i < days; i++){
            passDay();
        }
    }

    /*
    @description: advances the Zoo simulation forward by exactly one day by calling passDay()
                  on all active employees and visitors and updating the zooLand state.
    @postcondition: the Zoo state reflects one full day of progression.
    */
    public void passDay(){
        for(int i = 0; i < numEmployees; i++){
            (employeeList[i]).passDay();
        }
        for(int i = 0; i < numVisitors; i++){
            (visitorList[i]).passDay();
        }
        for(int i = 0; i < numAnimals; i++){
            (zooAnimals[i]).passDay();
        }
        for(int i = 0; i < numEggs; i++){
            (incubator[i]).passDay();
        }
        for(int i = 0; i < numEggs; i++){
            if (incubator[i].readyToHatch()) {
                System.out.println("egg" + i + " is ready to hatch!");
            }
        }

        int index = (int)(Math.random() * numAnimals);
        if (zooAnimals[index].canReproduce()) {
            System.out.println(zooAnimals[index].getName() + " is about to have a baby! help deliver its baby!");
        }
        zooLand.passDay();
    }

    

    // LAND METHODS
    //
    public boolean createGiftShop(Coord c1,Coord c2,String name,char id,int timeBetweenMaintenance,String[] facts,Item[] menu) {
        if (c1 == null || c2 == null || name == null || facts == null || menu == null) {
            return false;
        }
        return zooLand.createGiftShop(c1,c2,name,id,timeBetweenMaintenance,facts,menu);
    }
    
    //
    public boolean createRestaurant(Coord c1, Coord c2, String name, char id,
                                    int timeBetweenMaintenance, String[] facts, Item[] menu) {
        if (c1 == null || c2 == null || name == null || facts == null || menu == null) {
            return false;
        }
        return zooLand.createRestaurant(c1, c2, name, id, timeBetweenMaintenance, facts, menu);
    }
    
    // 
    public boolean createEnclosure(Coord c1, int area, String species, String name, char id,
                               int timeBetweenMaintenance, int maxAnimals, LivingCondition condition) {
        if (c1 == null || species == null || name == null || condition == null) {
            return false;
        }
        return zooLand.createEnclosure(c1, area, species, name, id,
                                    timeBetweenMaintenance, maxAnimals, condition);
                                    
    }

    //
    public boolean createPavillion(Coord c1, int area, String name, char id,
                                int timeBetweenMaintenance, int maxAnimals, LivingCondition condition) {

        if (c1 == null || name == null || condition == null) {
            return false;
        }

        return zooLand.createPavillion(c1, area, name, id,
                                    timeBetweenMaintenance, maxAnimals, condition);
    }
    
    //
    public boolean createPark(Coord c1, int area, String name, char id, int timeBetweenMaintenance) {

        if (c1 == null || name == null) {
            return false;
        }

        return zooLand.createPark(c1, area, name, id, timeBetweenMaintenance);
    }

    //
    public boolean createMaze(Coord c1, String name, char id,
                          int timeBetweenMaintenance /*layout: replaced by default */) {

        if (c1 == null || name == null) {
            return false;
        }

        return zooLand.createMaze(c1, name, id, timeBetweenMaintenance, /*layout */ MAZE_DEFAULT_SHAPE);
    }

    public boolean removeStructure (char id) {
        return zooLand.removeStructureFromList(zooLand.searchIdxByID(id));
    }


    public boolean loadLandFromFile (String filename) { 
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String total = "";
            String input;
            while ((input = br.readLine()) != null) {
                total += input + "\n";
            }
            
            br.close();
            System.out.println("Reading Land file " + filename + " was successful!");
            if (zooLand.loadLandFromString(total)) {
                System.out.println("Interpreting file successful!");
                return true;
            } else {
                System.out.println("Error during file interpretation! ");
                return false;
            }
        } catch (IOException iox) {
            System.out.println("Error loading file: " + filename);
            return false;
        }
    }

    public boolean saveLandToFile (String filename) {
        String toSave =  zooLand.saveLandToString();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(toSave);
            bw.close();
            System.out.println("Saving to " + filename + " was successful!");
            return true;
        } catch (IOException iox) {
            System.out.println("Error occurred when trying to save to file " + filename + " !");
            return false;
        }
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

    public Habitat searchHabitatMostAnimalsAndLivingConditions (LivingCondition condition) {
        return zooLand.searchHabitatMostAnimalsAndLivingConditions(condition);
    }

    public void sortBySizeAndMostAnimals () {
        zooLand.sortByAnimalsAndSize();
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

    public void printAllHabitatInfo () {
        zooLand.printAllHabitatInfo();
    }

    public void maintainAll () {
        zooLand.maintainAll();
    }

    public void maintain(Structure input) {
        input.maintenance();
    } 

    public double getBalance() {
        return zooBalance;
    }


    public String toString() {
        return "This zoo has " + numAnimals + " animals.\n" +
                "This zoo has " + numEmployees + "employees\n" + 
                "This zoo has " + numVisitors + "visitors\n"; 
    }

   
    /*
        @description: checks whether a Person with the given personID exists in the current Zoo system
        @param personID the unique ID to search for
        @return true if a matching employee or visitor is found, false otherwise
    */
    public boolean personIDExists(String personID){
        if (personID == null){return false;}
    
        for (int i=0;i<numEmployees;i++){
            if (employeeList[i]!=null && personID.equals(employeeList[i].getPersonID())){
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
        @description: adds a new Person object to the Zoo system (Employee -> employeeList, Visitor -> visitorList)
        @param p the Person object to add
        @return true if the person was added successfully, false otherwise (null, duplicate ID, or array full)
    */
    public boolean addPerson(Person p){
        if (p == null || p.getPersonID() == null || personIDExists(p.getPersonID())){return false;}
    
        if (p instanceof Employee) {
            if (numEmployees >= employeeList.length){return false;}
            employeeList[numEmployees] = (Employee) p;
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
            if (employeeList[i] != null && personID.equals(employeeList[i].getPersonID())) {
                employeeList[i].deactivate();
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
            if (employeeList[i] != null && personID.equals(employeeList[i].getPersonID())) {
                return employeeList[i];
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
            Employee e = employeeList[i];
            if (e != null && personID.equals(e.getPersonID()) && (Math.abs(e.getEarnings() - earnings) < 0.0001)) {
                return e;
            }
        }
    
        return null;
    }

    /*
    @description: sorts all visitors alphabetically by last name, then by first name
    @algorithm: uses bubble sort; compares visitors using compareToByName
    */
    public void sortVisitorByName(){
        for (int i = 0; i < numVisitors - 1; i++){
            for (int j = 0; j < numVisitors - i - 1; j++){
        
                if (visitorList[j].compareToByName(visitorList[j + 1]) > 0){
                    Visitor temp = visitorList[j];
                    visitorList[j] = visitorList[j + 1];
                    visitorList[j + 1] = temp;
                }
            }
        }
    }
    
    /*
    @description: sorts all employees from highest to lowest total earnings
    @algorithm: uses selection sort; compares employees using compareToByEarnings
    */
    public void sortEmployeesByEarnings(){
        for (int i = 0; i < numEmployees - 1; i++){
            int maxIdx = i;
        
            for (int j = i + 1; j < numEmployees; j++){
                if (employeeList[j].compareToByEarnings(employeeList[maxIdx]) < 0){
                    maxIdx = j;
                }
            }
        
            if (maxIdx != i){
                Employee temp = employeeList[i];
                employeeList[i] = employeeList[maxIdx];
                employeeList[maxIdx] = temp;
            }
        }
    }

    /*
    @description: sorts employees by years of experience (highest first),
                    and by hourly wage if experience is equal
    @algorithm: uses bubble sort; compares experience first, then wage
    @postcondition: employeeList is reordered by experience and wage in descending order
    */
    public void sortEmployeesByExperienceAndWage(){
        for (int i = 0; i < numEmployees - 1; i++){
            for (int j = 0; j < numEmployees - i - 1; j++){
        
                int cmpExp = employeeList[j].compareToByExperience(employeeList[j + 1]);
        
                if (cmpExp > 0 || (cmpExp == 0 && employeeList[j].compareToByWage(employeeList[j + 1]) > 0)){
        
                    Employee temp = employeeList[j];
                    employeeList[j] = employeeList[j + 1];
                    employeeList[j + 1] = temp;
                }
            }
        }
    }
        
    /*
        @description: checks whether the visitor role label matches the visitor's age
        @param role the role string ("CHILD", "ADULT", "SENIOR")
        @param age  the visitor's age
        @return true if role matches the age range, false otherwise
    */
    private static boolean ageMatchesVisitorRole(String role, int age) {
        switch (role) {
            case "CHILD":
                return age < Visitor.MIN_ADULT_AGE;
            case "ADULT":
                return age >= Visitor.MIN_ADULT_AGE && age < Visitor.MIN_SENIOR_AGE;
            case "SENIOR":
                return age >= Visitor.MIN_SENIOR_AGE;
            default:
                return false;
        }
    }
    
    /*
        @description: displays info for all Visitors currently in the Zoo
    */
    public void displayAllVisitors() {
        System.out.println("=== ALL VISITORS (" + numVisitors + ") ===");
        if (numVisitors == 0) {
            System.out.println("No visitors loaded.");
            return;
        }
        
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
        if (numEmployees == 0) {
            System.out.println("No employees loaded.");
            return;
        }
        
        for (int i = 0; i < numEmployees; i++) {
            if (employeeList[i] != null) {
                System.out.println(employeeList[i]);
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
        try (BufferedWriter out = new BufferedWriter(new FileWriter(PERSON_FILE))) {

        // total number of people
        out.write(Integer.toString(numEmployees + numVisitors));
        out.newLine();

        // save employees
        for (int i = 0; i < numEmployees; i++) {
            if (employeeList[i] != null) {
                out.write(employeeList[i].saveToString());
                out.newLine();
            }
        }

        // save visitors
        for (int i = 0; i < numVisitors; i++) {
            if (visitorList[i] != null) {
                out.write(visitorList[i].saveToString());
                out.newLine();
            }
        }
        out.close(); 

        } catch (IOException e) {
            System.out.println("ERROR failed to save persons: " + e.getMessage());
        }
    }

    /*
    @description: loads all Person records from persons.txt using the assignment file format
                    (role + base fields + fixed role-specific fields)
    */
    public void loadPersons() {
        try (BufferedReader br = new BufferedReader(new FileReader(PERSON_FILE))) {
    
        int n = Integer.parseInt(next(br)); 
    
        for (int i = 0; i < n; i++) {
    
            String role = next(br);
    
            String personID = next(br);
            String firstName = next(br);
            String lastName = next(br);
    
            int age = Integer.parseInt(next(br));
            if (age < 0) age = 0;
    
            Person p = null;
    
            if (role.equals("ZOOKEEPER")) {
                double hourlyWage = Double.parseDouble(next(br));
                int yearsExp = Integer.parseInt(next(br));
                int certLevel = Integer.parseInt(next(br));
                double hoursWorked = Double.parseDouble(next(br));
                double earnings = Double.parseDouble(next(br));

                p = new ZooKeeper(age, personID, firstName, lastName,
                                hourlyWage, yearsExp, certLevel,
                                hoursWorked, earnings);

            
            } else if (role.equals("SHOPSTAFF")) {
                double hourlyWage = Double.parseDouble(next(br));
                int yearsExp = Integer.parseInt(next(br));

                double hoursWorked = Double.parseDouble(next(br));
                double earnings = Double.parseDouble(next(br));

                p = new ShopStaff(age, personID, firstName, lastName,
                                hourlyWage, yearsExp,
                                hoursWorked, earnings);
            }            
            else if (role.equals("ADULT")) {
                if (!ageMatchesVisitorRole("ADULT", age)) {
                    System.out.println("[WARN] Age " + age + " does not match ADULT for '" + personID + "'. Skipping.");
                    continue;
                }

                double balance = Double.parseDouble(next(br));
                int learningLevel = Integer.parseInt(next(br));
                int visitDuration = Integer.parseInt(next(br));
                double preferredBudgetLimit = Double.parseDouble(next(br));
    
                p = new Adult(age, personID, firstName, lastName,
                            Math.max(0.0, balance),
                            Math.max(0, learningLevel),
                            Math.max(0, visitDuration),
                            Math.max(0.0, preferredBudgetLimit));
            }    
            else if (role.equals("CHILD")) {
    
                double balance = Double.parseDouble(next(br));
                int learningLevel = Integer.parseInt(next(br));
                int visitDuration = Integer.parseInt(next(br));
                boolean strollerNeeded = Boolean.parseBoolean(next(br));
                String guardianID = next(br);
    
                if (!ageMatchesVisitorRole("CHILD", age)) {
                    System.out.println("[WARN] Age " + age + " does not match CHILD for '" + personID + "'. Skipping.");
                    continue;
                }
    
                p = new Child(age, personID, firstName, lastName,
                            Math.max(0.0, balance),
                            Math.max(0, learningLevel),
                            Math.max(0, visitDuration),
                            strollerNeeded,
                            guardianID);
            }
            else if (role.equals("SENIOR")) {
                // If your file includes SENIOR, use:
                // balance, learningLevel, visitDuration, preferredBudgetLimit, requiresAccessibilitySupport
    
                double balance = Double.parseDouble(next(br));
                int learningLevel = Integer.parseInt(next(br));
                int visitDuration = Integer.parseInt(next(br));
                double preferredBudgetLimit = Double.parseDouble(next(br));
                boolean requiresSupport = Boolean.parseBoolean(next(br));
    
                if (!ageMatchesVisitorRole("SENIOR", age)) {
                    System.out.println("[WARN] Age " + age + " does not match SENIOR for '" + personID + "'. Skipping.");
                    continue;
                }
    
                p = new Senior(age, personID, firstName, lastName,
                                Math.max(0.0, balance),
                                Math.max(0, learningLevel),
                                Math.max(0, visitDuration),
                                Math.max(0.0, preferredBudgetLimit),
                                requiresSupport);
            }
    
            // Add to system
            if (p != null) {
                boolean ok = addPerson(p);
                if (!ok) System.out.println("[WARN] Could not add '" + personID + "' (array full?).");
            }
        }
        br.close();
    
        } catch (FileNotFoundException e) {
            System.out.println("[INFO] " + PERSON_FILE + " not found yet (first run is ok).");
        } catch (Exception e) {
            System.out.println("[ERROR] loadPersons failed: " + e.getMessage());
        }
    }
 
    /*
    @description: reads the next non-empty line (skips blank lines)
    */
    private static String next(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
        line = line.trim();
        if (!line.isEmpty()) return line;
        }
        return null;
    }

    // =========================
    // ANIMAL METHODS
    // =========================
    // =========================
    // SAVE ANIMALS
    // =========================

    public void saveAnimals(String filename) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

        for (int i = 0; i < numAnimals; i++) {
            if (zooAnimals[i] != null) {
                bw.write(zooAnimals[i].saveToString());
                bw.newLine();
            }
        }

    }
}



    // =========================
    // LOAD ANIMALS
    // =========================

    public void loadAnimals(String filename) {
        numAnimals = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                int i = 0;

                String specie = data[i++];

                char habitatId = (data[i++]).charAt(0);
                String name = data[i++];
                String preferredInteraction = data[i++];
                String gender = data[i++];
                int happiness = Integer.parseInt(data[i++]);
                int cleanliness = Integer.parseInt(data[i++]);
                int hunger = Integer.parseInt(data[i++]);
                int age = Integer.parseInt(data[i++]);
                double weight = Double.parseDouble(data[i++]);
                int daysPassed = Integer.parseInt(data[i++]);

                Animal animal = null;

                if (specie.equalsIgnoreCase("Unicorn")) {
                    animal = new Unicorn(habitatId, name, preferredInteraction, gender,
                            happiness, cleanliness, hunger, age, weight);

                } else if (specie.equalsIgnoreCase("Capybara")) {
                    animal = new Capybara(habitatId, name, preferredInteraction, gender,
                            happiness, cleanliness, hunger, age, weight);

                } else if (specie.equalsIgnoreCase("Eagle")) {
                    Eagle eagle = new Eagle(habitatId, name, preferredInteraction, gender,
                            happiness, cleanliness, hunger, age, weight);
                    eagle.setHasNest(Boolean.parseBoolean(data[i++]));
                    animal = eagle;

                } else if (specie.equalsIgnoreCase("Cockatoo")) {
                    Cockatoo cockatoo = new Cockatoo(habitatId, name, preferredInteraction, gender,
                            happiness, cleanliness, hunger, age, weight);
                    cockatoo.setHasNest(Boolean.parseBoolean(data[i++]));
                    animal = cockatoo;

                } else if (specie.equalsIgnoreCase("Snake")) {
                    Snake snake = new Snake(habitatId, name, preferredInteraction, gender,
                            happiness, cleanliness, hunger, age, weight);
                    snake.setTimeToShed(Integer.parseInt(data[i++]));
                    animal = snake;

                } else if (specie.equalsIgnoreCase("Crocodile")) {
                    Crocodile croc = new Crocodile(habitatId, name, preferredInteraction, gender,
                            happiness, cleanliness, hunger, age, weight);
                    croc.setTimeToShed(Integer.parseInt(data[i++]));
                    animal = croc;

                } else if (specie.equalsIgnoreCase("Frog")) {
                    animal = new Frog(habitatId, name, preferredInteraction, gender,
                            happiness, cleanliness, hunger, age, weight);

                } else if (specie.equalsIgnoreCase("Axolotl")) {
                    animal = new Axolotl(habitatId, name, preferredInteraction, gender,
                            happiness, cleanliness, hunger, age, weight);

                } else if (specie.equalsIgnoreCase("Shark")) {
                    animal = new Shark(habitatId, name, preferredInteraction, gender,
                            happiness, cleanliness, hunger, age, weight);

                } else if (specie.equalsIgnoreCase("Sunfish")) {
                    animal = new Sunfish(habitatId, name, preferredInteraction, gender,
                            happiness, cleanliness, hunger, age, weight);
                }
                
                Habitat habitat = (Habitat)searchStructureByID(habitatId);
                if (habitat != null && animal != null) {
                    animal.setDaysPassed(daysPassed);
                    addAnimal(habitat, animal);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading animals: " + e.getMessage());
        }
    }



    // =========================
    // ANIMAL METHODS
    // =========================

    // RELOCATE ANIMAL
    public boolean relocateAnimal(Habitat newHabitat, Animal animal) {

        if (animal == null || newHabitat == null) {
            return false;
        }

        if (!animal.isSuitable(newHabitat)) {
            System.out.println("Relocation failed: habitat unsuitable.");
            return false;
        }

        Habitat originalHabitat =
            (Habitat) searchStructureByID(animal.getHabitatId());

        if (originalHabitat != null) {
            originalHabitat.removeAnimal(animal);
        }

        animal.leaveHabitat();
        animal.setHabitatId(newHabitat.getStructureID());
        newHabitat.addAnimal(animal);

        return true;
    }

    // ADD ANIMAL
    public boolean addAnimal(Habitat habitat, Animal animal) {

        if (animal == null || habitat == null) {
            return false;
        }

        if (numAnimals >= zooAnimals.length) {
            return false;
        }

        if (!animal.isSuitable(habitat)) {
            System.out.println("Add animal failed: habitat unsuitable.");
            return false;
        }

        zooAnimals[numAnimals] = animal;
        numAnimals++;

        animal.setHabitatId(habitat.getStructureID());
        habitat.addAnimal(animal);

        return true;
    }

    public void animalsReadyToReproduce() {
        for (int i = 0; i < numAnimals; i++) {
            if (zooAnimals[i].canReproduce()) {
                System.out.println(zooAnimals[i].getName() + " the " + zooAnimals[i].getSpecie() + " can reproduce");
            }
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
            if (zooAnimals[i].getName().equalsIgnoreCase(name) &&
                zooAnimals[i].getSpecie().equalsIgnoreCase(specie)) {
                return zooAnimals[i];
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
            if (zooAnimals[i].getName().equalsIgnoreCase(name)) {
                return zooAnimals[i];
            }
        }
        return null;
    }

    // =========================
    // ANIMAL DISPLAYING METHODS
    // =========================
    /**
     * Description: Displays information for all animals currently in the Zoo.
     *              Uses each Animal's toString() method.
     */
    public void displayAllAnimals() {
        System.out.println("=== ALL ANIMALS (" + numAnimals + ") ===");

        if (numAnimals == 0) {
            System.out.println("No animals in the zoo.");
            return;
        }

        for (int i = 0; i < numAnimals; i++) {
            if (zooAnimals[i] != null) {
                System.out.println(zooAnimals[i]);
                System.out.println("----------------------------------------");
            }
        }
    }
    // =========================
    // DISPLAY LOW STAT METHODS
    // =========================

    /**
     * Description: Displays names of all animals with low hunger.
     */
    public void displayAnimalsLowHunger() {
        for (int i = 0; i < numAnimals; i++) {
            if (zooAnimals[i].lowHunger()) {
                System.out.println(zooAnimals[i].getName());
            }
        }
    }

    /**
     * Description: Displays names of all animals with low happiness.
     */
    public void displayAnimalsLowHappiness() {
        for (int i = 0; i < numAnimals; i++) {
            if (zooAnimals[i].lowHappiness()) {
                System.out.println(zooAnimals[i].getName());
            }
        }
    }

    /**
     * Description: Displays names of all animals with low cleanliness.
     */
    public void displayAnimalsLowCleansiness() {
        for (int i = 0; i < numAnimals; i++) {
            if (zooAnimals[i].lowCleansiness()) {
                System.out.println(zooAnimals[i].getName());
            }
        }
    }

    /**
     * Description: Prints all animals that match the given species.
     * Parameters:
     *   specie - species to search for
     */
    public void listAllSameSpecie(String specie) {
        for (int i = 0; i < numAnimals; i++) {
            if (zooAnimals[i].getSpecie().equalsIgnoreCase(specie)) {
                System.out.println(zooAnimals[i]);
            }
        }
    }

    // =========================
    // EGG METHODS
    // =========================
    /**
     * Description: Displays all eggs currently in the incubator.
     *              Uses each Egg's toString() method.
     */
    public void displayIncubator() {
        System.out.println("=== INCUBATOR (" + numEggs + " eggs) ===");

        if (numEggs == 0) {
            System.out.println("No eggs in the incubator.");
            return;
        }

        for (int i = 0; i < numEggs; i++) {
            if (incubator[i] != null) {
                System.out.println("Egg #" + i);
                System.out.println(incubator[i]);
                System.out.println("----------------------------------------");
            }
        }
    }

    /**
     * Description: Adds an egg to the incubator if there is space.
     * Parameters:
     *   egg - egg to add
     * Return:
     *   true if added successfully, false otherwise
     */
    public boolean addEgg(Egg egg) {
        if (numEggs < maxEggs) {
            incubator[numEggs] = egg;
            numEggs++;
            return true;
        }
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
            incubator[i] = incubator[i + 1];
        }

        incubator[numEggs - 1] = null;
        numEggs--;

        return true;
    }
    

    // Description: hatches the egg from incubator
    public Animal hatchEgg(Habitat habitat, int index, String name) {

        if (habitat == null || incubator[index] == null || name == null) {
            return null;
        }

        Animal baby = incubator[index].hatch(name);

        if (baby == null) {
            return null;
        }

        boolean removed = removeEgg(index);
        if (!removed) {
            return null;
        }

        boolean added = addAnimal(habitat, baby);
        if (!added) {
            return null;
        }

        return baby;
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
            Animal key = zooAnimals[i];
            int j = i - 1;

            while (j >= 0) {
                int nameCompare =
                    zooAnimals[j].getName().compareToIgnoreCase(key.getName());

                if (nameCompare > 0 ||
                (nameCompare == 0 &&
                    zooAnimals[j].getSpecie().compareToIgnoreCase(key.getSpecie()) > 0)) {
                    zooAnimals[j + 1] = zooAnimals[j];
                    j--;
                } else {
                    break;
                }
            }
            zooAnimals[j + 1] = key;
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
                if (zooAnimals[j].getAge() < zooAnimals[minIndex].getAge()) {
                    minIndex = j;
                }
            }

            Animal temp = zooAnimals[i];
            zooAnimals[i] = zooAnimals[minIndex];
            zooAnimals[minIndex] = temp;
        }
    }

    /**
     * Description: Sorts animals by happiness from lowest to highest.
     * Sorting Algorithm: Bubble Sort
     */
    public void sortAnimalsByHappiness() {
        for (int i = 0; i < numAnimals - 1; i++) {
            for (int j = 0; j < numAnimals - 1 - i; j++) {
                if (zooAnimals[j].getHappiness() > zooAnimals[j + 1].getHappiness()) {
                    Animal temp = zooAnimals[j];
                    zooAnimals[j] = zooAnimals[j + 1];
                    zooAnimals[j + 1] = temp;
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
                if (zooAnimals[j].getHunger() < zooAnimals[j + 1].getHunger()) {
                    Animal temp = zooAnimals[j];
                    zooAnimals[j] = zooAnimals[j + 1];
                    zooAnimals[j + 1] = temp;
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
                if (zooAnimals[j].getCleanliness() > zooAnimals[j + 1].getCleanliness()) {
                    Animal temp = zooAnimals[j];
                    zooAnimals[j] = zooAnimals[j + 1];
                    zooAnimals[j + 1] = temp;
                }
            }
        }
    }

    // EGG METHODS
    /**
     * Description:
     * Attempts to hatch the given egg. If the egg successfully hatches,
     * the egg is removed from the incubator and the newborn animal is returned.
     */
    public void saveEggs(String file) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            bw.write(Integer.toString(numEggs));
            bw.newLine();
            bw.newLine();

            for (int i = 0; i < numEggs; i++) {
                bw.write(incubator[i].saveToString());
                bw.newLine();
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving eggs.");
        }
    }

    //========================================
    public void loadEggs(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = br.readLine();
            if (line == null) {
                br.close();
                return;
            }

            int count = Integer.parseInt(line.trim());
            numEggs = 0;

            for (int i = 0; i < count; i++) {

                br.readLine(); // skip blank line

                String parentName = br.readLine();
                String species = br.readLine();
                int hatchTime = Integer.parseInt(br.readLine());

                Animal parent = findAnimal(parentName, species);

                // 🔹 Fallback: create dummy parent if not found
                if (parent == null) {
                    parent = createDummyParent(species);
                }

                Egg egg = new Egg(parent);
                egg.setHatchTime(hatchTime);

                addEgg(egg);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error loading eggs.");
        }
    }

    // 
    private Animal createDummyParent(String specie) {

        char habitatId = Land.EMPTY; // placeholder
        String name = "Unknown_" + specie;
        String preferredInteraction = "None";
        String gender;

        if (Math.random() < 0.5) {
            gender = "Male";
        } else {
            gender = "Female";
        }

        int happiness = Animal.MAX_STAT / 2;
        int cleanliness = Animal.MAX_STAT / 2;
        int hunger = Animal.MAX_STAT / 2;
        int age = 0;
        double weight = 0;

        Animal animal = null;

        if (specie.equalsIgnoreCase("Unicorn")) {

            animal = new Unicorn(habitatId, name, preferredInteraction, gender,
                    happiness, cleanliness, hunger, age, weight);

        } else if (specie.equalsIgnoreCase("Capybara")) {

            animal = new Capybara(habitatId, name, preferredInteraction, gender,
                    happiness, cleanliness, hunger, age, weight);

        } else if (specie.equalsIgnoreCase("Eagle")) {

            Eagle eagle = new Eagle(habitatId, name, preferredInteraction, gender,
                    happiness, cleanliness, hunger, age, weight);
            eagle.setHasNest(false);
            animal = eagle;

        } else if (specie.equalsIgnoreCase("Cockatoo")) {

            Cockatoo cockatoo = new Cockatoo(habitatId, name, preferredInteraction, gender,
                    happiness, cleanliness, hunger, age, weight);
            cockatoo.setHasNest(false);
            animal = cockatoo;

        } else if (specie.equalsIgnoreCase("Snake")) {

            Snake snake = new Snake(habitatId, name, preferredInteraction, gender,
                    happiness, cleanliness, hunger, age, weight);
            snake.setTimeToShed(0);
            animal = snake;

        } else if (specie.equalsIgnoreCase("Crocodile")) {

            Crocodile croc = new Crocodile(habitatId, name, preferredInteraction, gender,
                    happiness, cleanliness, hunger, age, weight);
            croc.setTimeToShed(0);
            animal = croc;

        } else if (specie.equalsIgnoreCase("Frog")) {

            animal = new Frog(habitatId, name, preferredInteraction, gender,
                    happiness, cleanliness, hunger, age, weight);

        } else if (specie.equalsIgnoreCase("Axolotl")) {

            animal = new Axolotl(habitatId, name, preferredInteraction, gender,
                    happiness, cleanliness, hunger, age, weight);

        } else if (specie.equalsIgnoreCase("Shark")) {

            animal = new Shark(habitatId, name, preferredInteraction, gender,
                    happiness, cleanliness, hunger, age, weight);

        } else if (specie.equalsIgnoreCase("Sunfish")) {

            animal = new Sunfish(habitatId, name, preferredInteraction, gender,
                    happiness, cleanliness, hunger, age, weight);
        }

        return animal;
    }

    //==============================================
    public Animal hatchEgg(int index, String name) {

        if (incubator[index] == null) {
            return null;
        }

        Animal baby = incubator[index].hatch(name);

        if (baby != null) {
            removeEgg(index); // remove by reference, not index
        }

        return baby;
    }

    public Egg getEggAtIndex(int index) {
        if (index < 0 || index >= numEggs) {
            return null;
        }
        return incubator[index];
    }

}
