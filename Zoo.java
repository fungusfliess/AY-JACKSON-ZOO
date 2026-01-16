import java.io.*;
import java.util.*;


public class Zoo {

    public static Scanner sc = new Scanner(System.in);

    //CONSTANTS 
    public static final String ZOO_CONSTRUCTOR_FILE = "zoo.txt";
    public static final String ANIMAL_FILE = "animal.txt";
    public static final String EGG_FILE = "eggs.txt";
    public static final String PERSON_FILE = "persons.txt";
    public static final String LAND_FILE = "land.txt";
    public static final String ADMIN_PIN = "0000";

    public static final String QUIT = "quit";
    
    //FIELDS
    private Employee[] staffList;
    private Visitor[] visitorList;

    private int maxPerson;
    private int maxAnimal;
    private int maxEggs;
    
    private int numPerson; 
    private int numEmployees;
    private int numVisitors;
    private int numAnimals;
    private int numEggs; 

    private double zooBalance;

    
    private Animal[] animals;
    private Egg[] eggs;
    private Land zooLand;
    private Map map;

    public Zoo(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(ZOO_CONSTRUCTOR_FILE));
            double balance = Double.parseDouble(br.readLine());
            int animals = Integer.parseInt(br.readLine());
            int eggs = Integer.parseInt(br.readLine());
            int person = Integer.parseInt(br.readLine());
            
            
            this.zooBalance = balance;
            this.maxAnimal = animals;
            this.maxEggs = eggs;
            this.maxPerson = person;
            
            this.zooAnimals = new Animal[maxAnimal];
            this.incubator = new Egg[maxEggs];
            this.persons = new Person[maxPerson];
            
            br.close();
         
        }catch(IOException e){
            System.out.println("Error reading file.");
        }
        loadPersons();
        loadAnimals();
        loadStructures();
        loadMap();
        map = zooLand.getMap();
        System.out.println("Zoo Successfully Loaded!");
    }
   
    public void welcomeMenu(){
        boolean quit = false;
        String input; 
        Person user;
        System.out.println("Welcome To The Zoo's Main Menu!\n");
        do{
            try{
                System.out.println("(Type quit to quit)\n" 
                + "Enter # To Access Specific Menu: "
                + "\n1 - Admin Menu"
                + "\n2 - Employee Menu"
                + "\n3 - Visitor Menu"
                + "\n4 - Display Map");
            
                input = sc.nextLine();
                
                switch(Integer.parseInt(input)){
                    case 1: 
                        adminMenu();
                        break;
                    case 2:
                        System.out.println("Enter Employee ID: ");
                        input = sc.nextLine();
                        user = searchPersonByID(input);
                        if(user != null && user instanceof Employee){
                    
                            employeeMenu((Employee)user);
                            
                        }else{
                            System.out.println("Employee Does Not Exist. ");
                        }  
                        break;  
                    case 3:
                        System.out.println("Enter Visitor ID: ");
                        input = sc.nextLine();
                        user = searchPersonByID(input);
                        if(user != null && user instanceof Visitor){
                    
                            visitorMenu((Visitor)user);
                        
                        }else{
                            System.out.println("Visitor Does Not Exist. ");
                        }
                        break;
                    case 4:
                        displayMap();
                        break;
                    default: 
                        System.out.println("Sorry, that is not a valid option! \n");
                        break;
                }
            }catch(NumberFormatException e){
                if(input.equalsIgnoreCase(QUIT)){
                    System.out.println("See You Next Time!");
                    quit = true;
                }else{
                System.out.println("Sorry, that is not a valid option! \n");
                }
            }        
        }while(quit!=true);      
    }
   
    public void adminMenu(){
        Person search;
        boolean quit = false;
        boolean login = false;
        String input;
   
        while(!quit && !login){
            System.out.println("(Type quit to return to Main Menu)\nEnter Admin PIN: ");
            input = sc.nextLine();
   
            if(input.equalsIgnoreCase(QUIT)){
                System.out.println("Returning To Main Menu...");
                quit = true;
            }else if(input.equals(ADMIN_PIN)){
                login = true;
            }else{
                System.out.println("PIN ENTERED WRONG.");
            }
        }
   
        while(login && !quit){
            System.out.println("Admin Menu:\n"
                + "(Type quit to return to Main Menu)\n"
                + "Enter # To Run Command:\n"
                + "1  -  Pass Time\n"
                + "2  -  Display Zoo Balance\n"
                + "3  -  Search Person By ID\n"
                + "4  -  Search Person By ID & Earnings\n"
                + "5  -  Sort Visitors By Name Alphabetically\n"
                + "6  -  Sort Employees From Highest To Lowest Earnings\n"
                + "7  -  Sort Employees By Experience, Then By Wage\n"
                + "8  -  Display All Employees\n"
                + "9  -  Create Gift Shop\n"
                + "10 -  Create Restaurant\n"
                + "11 -  Create Pavillion\n"
                + "12 -  Create Enclosure\n"
                + "13 -  Create Park\n"
                + "14 -  Create Maze\n";
            );
   
            input = sc.nextLine();
   
            if(input.equalsIgnoreCase(QUIT)){
                System.out.println("Returning To Main Menu...");
                quit = true;
            }else{
                try{
                    switch(Integer.parseInt(input)){
                        case 1:
                            System.out.print("Enter Days: ");
                            passTime(Integer.parseInt(sc.nextLine()));
                            break;
                        case 2: 
                            System.out.println("Zoo Balance: $" + zooBalance);
                            break;
                        case 3:
                            System.out.print("Enter ID: ");
                            input = sc.nextLine();
                            search = searchByPersonID(input);
                            if(search != null){
                                System.out.println(search);
                            }else{
                                System.out.println("Person Does Not Exist.");
                            }
                            break;
                        case 4:
                            System.out.print("Enter ID: ");
                            input = sc.nextLine();
                            System.out.println("Enter Earnings: ");
                            search = searchByPersonIDAndEarnings(input, sc.nextDouble());

                            sc.nextLine();
                            if(search != null){
                                System.out.println(search);
                            }else{
                                System.out.println("Matching Person Could Not Be Found.");
                            }
                            break;
                        case 5:
                            sortVisitorByName();
                            break; 
                        case 6:
                            sortEmployeesByEarnings();
                            break;
                        case 7:
                            sortEmployeesByExperienceAndWage();
                            break;
                        case 8:
                            displayAllEmployees();
                            break;
                        case 9:
                            System.out.print()
                        default: 
                            System.out.println("Sorry, that is not a valid option!\n");
                            break;
                    }
   
                }catch(NumberFormatException e){
                    System.out.println("Sorry, that is not a valid option/input!\n");
                }
            }
        }
    }
   
    public void employeeMenu(Employee employee){
        boolean quit = false;
        String input;
   
        System.out.println("Employee Menu:\n");
   
        while(!quit){
            System.out.println("(Type quit to return to Main Menu)\n"
                + "Enter # To Run Command:\n"
                + /*add menu*/);
   
            input = sc.nextLine();
   
            if(input.equalsIgnoreCase(QUIT)){
                System.out.println("Returning Back To Main Menu...\n");
                quit = true;
            }else{
                try{
                    switch(Integer.parseInt(input)){
                        case 1:
                     
                        case 2: // add menu
                     
                        default:
                            System.out.println("Sorry, that is not a valid option!\n");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Sorry, that is not a valid option!\n");
                }
            }
        }
    
   
    public void visitorMenu(Visitor visitor){
        boolean quit = false;
        String input;
   
        System.out.println("Visitor Menu:\n");
   
        while(!quit){
            System.out.println("(Type quit to return to Main Menu)\n"
                + "Enter # To Run Command:\n"
                + /* add menu */);
   
            input = sc.nextLine();
   
            if(input.equalsIgnoreCase(QUIT)){
                System.out.println("Returning Back To Main Menu...\n");
                quit = true;
            }else{
                try{   
                    switch(Integer.parseInt(input)){
                        case 1:
                            //add menu         
                        default:
                            System.out.println("Sorry, that is not a valid option!\n");
                    }        
   
                }catch(NumberFormatException e){
                    System.out.println("Sorry, that is not a valid option!\n");
                }
            }
        }
    }
    
    public void passTime(int days){
        for(int i = 0; i < days; i++){
            passDay();
        }
    }

    public void passDay(){
        for(int i = 0; i < numEmployees; i++){
            ((getStaffList())[i]).passDay();
        }
        for(int i = 0; i < numVisitors; i++){
            ((getVisitorList())[i]).passDay();
        }
        zooLand.passDay();
    }

    public boolean createStructure(String type){
        int numItems;
        Item[] menu;
        String itemName;
        double price;

        int numFacts;
        String[] facts;

        String climateType;
        double temp;
        double humidity;
        String region;
        LivingCondition climate;

        int maxAnimals; 

        String name;
        char structureID;    
        int area = 0;
        int timeBetweenMaintenance;

        Coord c1 = null;
        Coord c2 = null;
        char id;

        System.out.print("Enter New ID: ");
        id = sc.nextLine().charAt(0);

        
        if(type.equals("GiftShop") || type.equals("Restaurant")){
            System.out.print("Enter X and Y for Coordinate 1: ");
            c1 = new Coord(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
            System.out.print("Enter Desired Size: ");
            area = Integer.parseInt(sc.nextLine());

        }else {
            System.out.print("Enter X and Y for Coordinate 1: ");
            c1 = new Coord(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
            System.out.print("Enter X and Y for Coordinate 2: ");
            c2 = new Coord(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));    
        }

        
        if(type == "GiftShop"){

            System.out.print("Enter number of items: ");
            numItems = Integer.parseInt(sc.nextLine());
            menu = new Item[numItems];

            for(int i = 0; i < numItems; i++){
                System.out.print("Enter item name: ");
                itemName = sc.nextLine();

                System.out.print("Enter item price: ");
                price = Double.parseDouble(sc.nextLine());

                menu[i] = new Item(itemName, price);
            }

            System.out.print("Enter number of facts: ");
            numFacts = Integer.parseInt(sc.nextLine());
            facts = new String[numFacts];

            for(int i = 0; i < numFacts; i++){
                System.out.print("Enter fact: ");
                facts[i] = sc.nextLine();
            }

            //initializes superclass fields

            System.out.print("Enter structure name: ");
            name = sc.nextLine();

            System.out.print("Enter time between maintenance: ");
            timeBetweenMaintenance = Integer.parseInt(sc.nextLine());
            
            return zooLand.createGiftShop(c1, c2, name, id, timeBetweenMaintenance, facts, menu );
            

        }else if(type == "Restaurant"){
            System.out.print("Enter number of items: ");
            numItems = Integer.parseInt(sc.nextLine());
            menu = new Item[numItems];

            for(int i = 0; i < numItems; i++){
                System.out.print("Enter item name: ");
                itemName = sc.nextLine();

                System.out.print("Enter item price: ");
                price = Double.parseDouble(sc.nextLine());

                menu[i] = new Item(itemName, price);
            }

            System.out.print("Enter number of facts: ");
            numFacts = Integer.parseInt(sc.nextLine());
            facts = new String[numFacts];

            for(int i = 0; i < numFacts; i++){
                System.out.print("Enter fact: ");
                facts[i] = sc.nextLine();
            }

            //initializes superclass fields

            System.out.print("Enter structure name: ");
            name = sc.nextLine();

            System.out.print("Enter time between maintenance: ");
            timeBetweenMaintenance = Integer.parseInt(sc.nextLine());

            return zooLand.createRestaurant(c1, c2, name, id, timeBetweenMaintenance, facts, menu );
            

        }else if(type == "Enclosure"){
            System.out.print//come back here
            System.out.print("Enter climate type (LAND or WATER): ");
            climateType = sc.nextLine();

            System.out.print("Enter temperature: ");
            temp = Double.parseDouble(sc.nextLine());

            System.out.print("Enter humidity: ");
            humidity = Double.parseDouble(sc.nextLine());

            System.out.print("Enter region: ");
            region = sc.nextLine();

            //checks which subclass of LivingCondition to create

            if(climateType.equals("LAND")){
                System.out.print("Enter soil compaction: ");
                double soilCompaction = Double.parseDouble(sc.nextLine());

                System.out.print("Enter land slope: ");
                double landSlope = Double.parseDouble(sc.nextLine());

                System.out.print("Has water source (true/false): ");
                boolean hasWaterSource = Boolean.parseBoolean(sc.nextLine());

                System.out.print("Enter vegetation density: ");
                double vegetationDensity = Double.parseDouble(sc.nextLine());

                System.out.print("Enter number of nearby structures: ");
                int amountStructures = Integer.parseInt(sc.nextLine());

                climate = new LandCondition(
                    temp,
                    humidity,
                    region,
                    soilCompaction,
                    landSlope,
                    hasWaterSource,
                    vegetationDensity,
                    amountStructures
                );
            }else{
                System.out.print("Enter water acidity: ");
                double waterAcidity = Double.parseDouble(sc.nextLine());

                System.out.print("Enter water hardness: ");
                double waterHardness = Double.parseDouble(sc.nextLine());

                System.out.print("Enter water temperature: ");
                double waterTemp = Double.parseDouble(sc.nextLine());

                System.out.print("Has land access (true/false): ");
                boolean hasLand = Boolean.parseBoolean(sc.nextLine());

                System.out.print("Enter water salinity: ");
                double waterSalinity = Double.parseDouble(sc.nextLine());

                climate = new WaterCondition(
                    waterTemp,
                    humidity,
                    region,
                    waterAcidity,
                    waterHardness,
                    temp,
                    hasLand,
                    waterSalinity
                );
            }

            //initializes superclass fields

            System.out.print("Enter structure name: ");
            name = sc.nextLine();

            System.out.print("Enter time between maintenance: ");
            timeBetweenMaintenance = Integer.parseInt(sc.nextLine());

            System.out.print("Enter maximum animals: ");
            maxAnimals = Integer.parseInt(sc.nextLine());
            return zooLand.createPavillion(c1, area, name, id, maxAnimals, climate);

        }else if(type == "Pavillion"){
            System.out.print("Enter climate type (LAND or WATER): ");
            climateType = sc.nextLine();

            System.out.print("Enter temperature: ");
            temp = Double.parseDouble(sc.nextLine());

            System.out.print("Enter humidity: ");
            humidity = Double.parseDouble(sc.nextLine());

            System.out.print("Enter region: ");
            region = sc.nextLine();

            //checks which subclass of LivingCondition to create

            if(climateType.equals("LAND")){
                System.out.print("Enter soil compaction: ");
                double soilCompaction = Double.parseDouble(sc.nextLine());

                System.out.print("Enter land slope: ");
                double landSlope = Double.parseDouble(sc.nextLine());

                System.out.print("Has water source (true/false): ");
                boolean hasWaterSource = Boolean.parseBoolean(sc.nextLine());

                System.out.print("Enter vegetation density: ");
                double vegetationDensity = Double.parseDouble(sc.nextLine());

                System.out.print("Enter number of nearby structures: ");
                int amountStructures = Integer.parseInt(sc.nextLine());

                climate = new LandCondition(
                    temp,
                    humidity,
                    region,
                    soilCompaction,
                    landSlope,
                    hasWaterSource,
                    vegetationDensity,
                    amountStructures
                );
            }else{
                System.out.print("Enter water acidity: ");
                double waterAcidity = Double.parseDouble(sc.nextLine());

                System.out.print("Enter water hardness: ");
                double waterHardness = Double.parseDouble(sc.nextLine());

                System.out.print("Enter water temperature: ");
                double waterTemp = Double.parseDouble(sc.nextLine());

                System.out.print("Has land access (true/false): ");
                boolean hasLand = Boolean.parseBoolean(sc.nextLine());

                System.out.print("Enter water salinity: ");
                double waterSalinity = Double.parseDouble(sc.nextLine());

                climate = new WaterCondition(
                    waterTemp,
                    humidity,
                    region,
                    waterAcidity,
                    waterHardness,
                    temp,
                    hasLand,
                    waterSalinity
                );
            }

            //initializes superclass fields

            System.out.print("Enter structure name: ");
            name = sc.nextLine();

            System.out.print("Enter time between maintenance: ");
            timeBetweenMaintenance = Integer.parseInt(sc.nextLine());

            System.out.print("Enter maximum animals: ");
            maxAnimals = Integer.parseInt(sc.nextLine());
            return zooLand.createPavillion(c1, area, name, id, maxAnimals, climate);

        }else if(type == "Maze"){

        }else{
            
        }

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

        if (numAnimals >= animals.length) {
            return false;
        }

        if (!animal.isSuitable(habitat)) {
            System.out.println("Add animal failed: habitat unsuitable.");
            return false;
        }

        animals[numAnimals] = animal;
        numAnimals++;

        animal.setHabitatId(habitat.getStructureID());
        habitat.addAnimal(animal);

        return true;
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

    /**
     * Description:
     * Attempts to hatch the given egg. If the egg successfully hatches,
     * the egg is removed from the incubator and the newborn animal is returned.
     */
    public Animal hatchEgg(Egg egg, String name) {

        if (egg == null) {
            return null;
        }

        Animal baby = egg.hatch(name);

        if (baby != null) {
            removeEgg(egg); // remove by reference, not index
        }

        return baby;
    }
}
