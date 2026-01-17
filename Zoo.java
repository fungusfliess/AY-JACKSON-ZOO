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
    private Land zooLand;
    private Map map;

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
        loadAnimals();
        loadStructures();
        loadMap();
        map = zooLand.getMap();
        System.out.println("Zoo Successfully Loaded!");
    }
   
    /*
    @description: displays the main menu for the Zoo system and routes the user to
                  Admin, Employee, or Visitor menus, or prints the Zoo map.
                  Continues looping until the user types QUIT.
    @postcondition: when the user quits, the Zoo is saved using saveZoo().
    */
    public void welcomeMenu() {
        boolean quit = false;
        System.out.println("Welcome To The Zoo's Main Menu!\n");
    
        while (!quit) {
            System.out.println("(Type quit to quit)\n"
                    + "Enter # To Access Specific Menu: "
                    + "\n1 - Admin Menu"
                    + "\n2 - Employee Menu"
                    + "\n3 - Visitor Menu"
                    + "\n4 - Display Map");
    
            String input = sc.nextLine().trim();
    
            if (input.equalsIgnoreCase(QUIT)) {
                System.out.println("See You Next Time!");
                quit = true;
                break;
            }
    
            try {
                switch (Integer.parseInt(input)) {
                    case 1: adminMenu(); break;
                    case 2:
                        System.out.println("Enter Employee ID: ");
                        Person u1 = searchByPersonID(sc.nextLine());
                        if (u1 instanceof Employee) employeeMenu((Employee) u1);
                        else System.out.println("Employee Does Not Exist.");
                        break;
                    case 3:
                        System.out.println("Enter Visitor ID: ");
                        Person u2 = searchByPersonID(sc.nextLine());
                        if (u2 instanceof Visitor) visitorMenu((Visitor) u2);
                        else System.out.println("Visitor Does Not Exist.");
                        break;
                    case 4:
                        printMap();
                        break;
                    default:
                        System.out.println("Sorry, that is not a valid option!\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Sorry, that is not a valid option!\n");
            }
        }
    
        saveZoo();
    }    

    /*
    @description: handles the Admin login flow (PIN check) and then displays the Admin menu.
                  Allows the admin to run management commands until the user types QUIT.
    @postcondition: returns to the welcome menu when the admin quits.
    */
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
                + "14 -  Create Maze\n"
                + "15 -  Remove Structure\n"
                + "16 -  Maintain All Structures\n"
                + "17 -  Add Animal\n"
                + "18 -  Relocate Animal\n"
                + "19 -  List All Animals Of Specified Species\n"
                + "20 -  Add Egg\n"
                + "21 -  Display Animals With Low Happiness\n"
                + "22 -  Display Animals With High Hunger\n"
                + "23 -  Display Animals With Low Cleansiness\n" 
                + "24 -  Sort Animals By Name, Then Species\n"
                + "25 -  Sort Animals By Age\n"
                + "26 -  Sort Animals By Happiness\n"
                + "27 -  Sort Animals By Hunger\n"
                + "28 -  Sort Animals By Cleanliness\n"
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
                            if(createStructure("GiftShop")){        
                            }else{
                                System.out.println("Could Not Build Structure Due to Insuffient Space.");
                            }
                            break;
                        case 10: 
                            if(createStructure("Restaurant")){        
                            }else{
                                System.out.println("Could Not Build Structure Due to Insuffient Space.");
                            }
                            break;
                        case 11: 
                            if(createStructure("Pavillion")){        
                            }else{
                                System.out.println("Could Not Build Structure Due to Insuffient Space.");
                            }
                            break;
                        case 12: 
                            if(createStructure("Enclosure")){        
                            }else{
                                System.out.println("Could Not Build Structure Due to Insuffient Space.");
                            }
                            break;
                        case 13: 
                            if(createStructure("Park")){        
                            }else{
                                System.out.println("Could Not Build Structure Due to Insuffient Space.");
                            }
                            break;
                        case 14: 
                            if(createStructure("Maze")){        
                            }else{
                                System.out.println("Could Not Build Structure Due to Insuffient Space.");
                            }
                            break;
                        case 15:
                            System.out.print("Enter Structure ID");
                            if(removeStructure(sc.nextLine().charAt(0))){
                                System.out.println("Removal Successful.");
                            }else{
                                System.out.println("Removal Unseccesful.");
                            }
                            break;
                        case 16: 
                            System.out.println("Maintaining All Structures...");
                            maintainAll();
                            break;
                        case 17:
                            System.out.println("input name of the animal");
                            String name = sc.nextLine();
                            System.out.println("input specie of the animal");
                            String specie = sc.nextLine();
                            Animal animal = findAnimal(name,specie);
                            if(addAnimal(animal)){
                                System.out.println("Animal Successfully Added.");
                            }else{
                                System.out.println("Animal Failed To Be Added.");
                            }
                            break;
                        case 18:
                            System.out.println("input name of the animal");
                            String name = sc.nextLine();
                            System.out.println("input specie of the animal");
                            String specie = sc.nextLine();
                            Animal animal = findAnimal(name,specie);
                            System.out.println("input habitat id");
                            char habitatId = sc.nextLine().charAt(0);
                            Habitat habitat = (Habitat)searchStructureByID(habitatId);
                            if(relocateAnimal(habitat, animal)){
                                System.out.println("Relocation Successful!");
                            }else{
                                System.out.println("Relocation Failed.");
                            }
                            break;
                        case 19:
                            System.out.print("Enter Species: ");
                            listAllSameSpecie(sc.nextLine());
                            break;
                        case 20:
                            if(addEgg()){
                                System.out.println("Egg Successfully Added.");
                            }else{
                                System.out.println("Egg Failed To Be Added.");
                            }
                            break;
                        case 21:
                            displayAnimalsLowHappiness();
                            break;
                        case 22:
                            displayAnimalsLowHunger();
                            break;
                        case 23: 
                            displayAnimalsLowCleansiness();
                            break;
                        case 24:
                            sortAnimalsByNameThenSpecie();
                            break;
                        case 25:
                            sortAnimalsByAge();
                            break;
                        case 26:
                            sortAnimalsByHappiness();
                            break;
                        case 27:
                            sortAnimalsByHunger();
                            break;
                        case 28: 
                            sortAnimalsByCleanliness();
                            break;
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
    
    /*
    @description: displays the Employee menu and allows an employee to run structure-related
                  commands (search, sort, maintain, print) and add persons (if allowed).
                  Continues looping until the user types QUIT.
    @param employee the logged-in Employee using the menu
    @postcondition: returns to the welcome menu when the employee quits.
    */
    public void employeeMenu(Employee employee){
        boolean quit = false;
        String input;

   
        while(!quit){
            System.out.println("Employee Menu:\n"
                + "(Type quit to return to Main Menu)\n"
                + "Enter # To Run Command:\n"
                + "1  -  Search Structure By Number Of Animals And Size\n"
                + "2  -  Search Habitat By Living Condition & With Most Animals\n"
                + "3  -  Sort Structures By Size\n"
                + "4  -  Sort Structures By Size, Then Time Between Maintenance\n"
                + "5  -  Sort Structures By Size, Then Number Of Animals\n"
                + "6  -  Print Structures Needing Maintenace\n"
                + "7  -  Maintain Structure\n" 
                + "8  -  Maintain All Structures\n"
                + "9  -  Print All Structures\n"
                + "10 -  Add Person\n"
                );
   
            input = sc.nextLine();
   
            if(input.equalsIgnoreCase(QUIT)){
                System.out.println("Returning Back To Main Menu...\n");
                quit = true;
            }else{
                try{
                    switch(Integer.parseInt(input)){
                        case 1:
                            System.out.println("Enter Number Of Animals And Size");
                            System.out.println(searchByNumberAnimalsAndSize(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine())));
                            break;
                     
                        case 2: 
                            System.out.println(searchHabitatMostAnimalsAndLivingConditions(LivingCondition.createLivingCondition()));
                            break;

                        case 3:
                            sortStructureBySize();
                            System.out.println("Sorting Complete.");
                            break;
                        
                        case 4:
                            sortBySizeAndTimeBetweenMaintenance();
                            System.out.println("Sorting Complete.");
                            break;
                        case 5:
                            sortBySizeAndMostAnimals();
                            System.out.println("Sorting Complete.");
                            break;
                        case 6:
                            printAllStructuresNeedingMaintenance();
                            break;
                        case 7: 
                            System.out.print("Enter Structure ID: ");
                            maintain(searchStructureByID(sc.nextLine().charAt(0))); 
                            break;
                        case 8:
                            maintainAll();
                            break;
                        case 9:
                            printAllStructureInfo();
                            break;
                        case 10:
                            Person p = inputPersonFromUser();
                            if (addPerson(p)) {
                                System.out.println("Person Added.");
                            } else {
                                System.out.println("Zoo has reached max capacity OR input was invalid, Person could not be added!");
                            }
                            break;
                        default:
                            System.out.println("Sorry, that is not a valid option!\n");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Sorry, that is not a valid option!\n");
                }
            }
        }
    
    
    /*
    @description: displays the Visitor menu and allows a visitor to visit structures,
                  view their learning summary / leave the zoo, and sort structures.
                  Continues looping until the user types QUIT.
    @param visitor the logged-in Visitor using the menu
    @postcondition: returns to the welcome menu when the visitor quits.
    */
    public void visitorMenu(Visitor visitor){
        boolean quit = false;
        String input;
   
        while(!quit){
            System.out.println("Visitor Menu:\n"
                + "(Type quit to return to Main Menu)\n"
                + "Enter # To Run Command:\n"
                + "1  -  Visit Structure\n"
                + "2  -  Learning Summary\n"
                + "3  -  Sort Structures From Least To Most Animals()\n"
            );
            
            input = sc.nextLine();
   
            if(input.equalsIgnoreCase(QUIT)){
                System.out.println("Returning Back To Main Menu...\n");
                quit = true;
            }else{
                try{   
                    switch(Integer.parseInt(input)){
                        case 1:
                            System.out.print("Enter Structure ID: ");
                            visitor.visit(searchStructureByID(sc.nextLine().charAt(0)));  
                            break;
                        case 2: 
                            System.out.println(visitor.leaveZoo()); 
                            break;   
                        case 3:
                            sortStructuresByLeastAnimals();
                            break;
                        default:
                            System.out.println("Sorry, that is not a valid option!\n");
                    }        
   
                }catch(NumberFormatException e){
                    System.out.println("Sorry, that is not a valid option!\n");
                }
            }
        }
    }

    /*
    @description: saves the Zoo system state to files by saving persons, animals, eggs,
                  and land/structures, and then writing configuration values back to the
                  ZOO_CONSTRUCTOR_FILE.
    @postcondition: persistent files are updated to reflect the Zoo's current state.
    */
    public void saveZoo(){
        savePersons();
        saveAnimals();
        saveEggs();
        saveLand();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(ZOO_CONSTRUCTOR_FILE, false));
            bw.write(String.valueOf(zooBalance));
            bw.newLine();;
            bw.write("" + maxAnimal);
            bw.newLine();
            bw.write("" + maxEggs);
            bw.newLine();
            bw.write("" + maxEmployee);
            bw.close();
            bw.write("" + maxVisitor);
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
        zooLand.passDay();
    }

    public boolean createStructure(String type){
        int numItems;
        Item[] menu;
        String itemName;
        double price;

        int numFacts;
        String[] facts;

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

        
        if(type.equals("GiftShop")){

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
            

        }else if(type.equals("Restaurant")){
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
            

        }else if(type.equals("Enclosure")){
            System.out.print("Enter species: ");
            String species = sc.nextLine();
            
            //initializes superclass fields

            System.out.print("Enter structure name: ");
            name = sc.nextLine();

            System.out.print("Enter time between maintenance: ");
            timeBetweenMaintenance = Integer.parseInt(sc.nextLine());

            System.out.print("Enter maximum animals: ");
            maxAnimals = Integer.parseInt(sc.nextLine());
            return zooLand.createEnclosure(c1, area, species, name, id, timeBetweenMaintenance, maxAnimals, LivingCondition.createLivingCondition());

        }else if(type.equals("Pavillion")){

            //initializes superclass fields

            System.out.print("Enter structure name: ");
            name = sc.nextLine();

            System.out.print("Enter time between maintenance: ");
            timeBetweenMaintenance = Integer.parseInt(sc.nextLine());

            System.out.print("Enter maximum animals: ");
            maxAnimals = Integer.parseInt(sc.nextLine());
            return zooLand.createPavillion(c1, area, name, id, timeBetweenMaintenance, maxAnimals, LivingCondition.createLivingCondition());

        }else if(type.equals("Maze")){

            System.out.print("Enter structure name: ");
            name = sc.nextLine();

            System.out.print("Enter time between maintenance: ");
            timeBetweenMaintenance = Integer.parseInt(sc.nextLine());

            return zooLand.createMaze(c1, name, id, timeBetweenMaintenance, null);
            

        }else if(type.equals("Park")){
            
            System.out.print("Enter structure name: ");
            name = sc.nextLine();

            System.out.print("Enter time between maintenance: ");
            timeBetweenMaintenance = Integer.parseInt(sc.nextLine());

            return zooLand.createPark(c1, area, name, id, timeBetweenMaintenance);
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

    public Habitat searchHabitatMostAnimalsAndLivingConditions (LivingCondition condition) {
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
    @description: prompts the user for role, personal information, and role-specific fields,
                  then creates and returns the appropriate Person subclass
    @return a newly created Person object if successful, or null if input is invalid/cancelled
    */
    public Person inputPersonFromUser() {
        try {
            System.out.print("Enter role (ZOOKEEPER, SHOPSTAFF, ADULT, SENIOR, CHILD): ");
            String role = sc.nextLine().trim().toUpperCase();
            if (role.equalsIgnoreCase(QUIT)) return null;

            System.out.print("Enter person ID: ");
            String personID = sc.nextLine().trim();
            if (personIDExists(personID)) {
                System.out.println("ID already exists.");
                return null;
            }

            System.out.print("Enter first name: ");
            String firstName = sc.nextLine().trim();

            System.out.print("Enter last name: ");
            String lastName = sc.nextLine().trim();

            System.out.print("Enter age: ");
            int age = Integer.parseInt(sc.nextLine().trim());
            if (age < 0) age = 0;

            // Early visitor age validation (right after role + age)
            if (role.equals("ADULT") || role.equals("SENIOR") || role.equals("CHILD")) {
                if (!ageMatchesVisitorRole(role, age)) {
                    System.out.println("Age " + age + " does not match role " + role + ".");
                    return null;
                }
            }

            // ===== EMPLOYEES =====
            if (role.equals("ZOOKEEPER")) {
                System.out.print("Enter hourly wage: ");
                double wage = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter years of experience: ");
                int exp = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Enter certification level: ");
                int cert = Integer.parseInt(sc.nextLine().trim());

                return new ZooKeeper(age, personID, firstName, lastName, wage, exp, cert);
            }

            if (role.equals("SHOPSTAFF")) {
                System.out.print("Enter hourly wage: ");
                double wage = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter years of experience: ");
                int exp = Integer.parseInt(sc.nextLine().trim());

                return new ShopStaff(age, personID, firstName, lastName, wage, exp);
            }

            // ===== ADULT =====
            if (role.equals("ADULT")) {
                System.out.print("Enter balance: ");
                double balance = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter learning level: ");
                int learning = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Enter visit duration (days): ");
                int duration = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Enter preferred budget limit: ");
                double limit = Double.parseDouble(sc.nextLine().trim());

                return new Adult(age, personID, firstName, lastName, balance, learning, duration, limit);
            }

            // ===== SENIOR =====
            if (role.equals("SENIOR")) {
                System.out.print("Enter balance: ");
                double balance = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter learning level: ");
                int learning = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Enter visit duration (days): ");
                int duration = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Enter preferred budget limit: ");
                double limit = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Requires accessibility support? (true/false): ");
                boolean support = Boolean.parseBoolean(sc.nextLine().trim());

                return new Senior(age, personID, firstName, lastName, balance, learning, duration, limit, support);
            }

            // ===== CHILD =====
            if (role.equals("CHILD")) {
                System.out.print("Enter balance: ");
                double balance = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter learning level: ");
                int learning = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Enter visit duration (days): ");
                int duration = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Stroller needed? (true/false): ");
                boolean stroller = Boolean.parseBoolean(sc.nextLine().trim());

                System.out.print("Enter guardian ID: ");
                String guardian = sc.nextLine().trim();

                return new Child(age, personID, firstName, lastName, balance, learning, duration, stroller, guardian);
            }

            System.out.println("Invalid role entered.");
            return null;

        } catch (Exception e) {
            System.out.println("Invalid input. Person could not be created.");
            return null;
        }
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
    
            // -------- Employees --------
            if (role.equals("ZOOKEEPER") || role.equals("SHOPSTAFF")) {
                double hourlyWage = Double.parseDouble(next(br));
                int yearsExp = Integer.parseInt(next(br));
                int thirdField = Integer.parseInt(next(br)); // certLevel or placeholder
    
                if (hourlyWage < 0 || yearsExp < 0) {
                    System.out.println("[WARN] Invalid employee fields for '" + personID + "'. Skipping.");
                    continue;
                }
    
                if (role.equals("ZOOKEEPER")) {
                    p = new ZooKeeper(age, personID, firstName, lastName, hourlyWage, yearsExp, thirdField);
                } else {
                    p = new ShopStaff(age, personID, firstName, lastName, hourlyWage, yearsExp);
                }
            }
    
            // -------- Adult --------
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
    
            // -------- Child --------
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
    
            // -------- Senior --------
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
        if (numEggs < maxEggs) {
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
