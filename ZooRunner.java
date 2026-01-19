/*
   File Name: ZooRunner.java
   Names: Arianna Liu, Jerry Ning, Jason Liu, Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: ZooRunner is the main runner class for the zoo management system.
                It provides menu interfaces for admins, employees, and visitors.
*/
import java.util.Scanner;

public class ZooRunner {

    //CONSTANTS
    private static final String QUIT = "quit";
    private static final String ADMIN_PIN = "0000";

    //FIELDS
    private static Scanner sc = new Scanner(System.in);
    private static Zoo zoo = new Zoo();

    //MAIN METHOD
        public static void main(String[] args) {
        welcomeMenu();
    }

/*
@description: displays the main menu for the Zoo system and routes the user to
                Admin, Employee, or Visitor menus, or prints the Zoo map.
                Continues looping until the user types QUIT.
@postcondition: when the user quits, the Zoo is saved using saveZoo().
*/
    public static void welcomeMenu() {
        boolean quit = false;
        System.out.println("Welcome To The Zoo's Main Menu!\n");
    
        // ===== MAIN MENU LOOP =====
        while (!quit) {
            System.out.println("(Type quit to quit)\n"
                    + "Enter # To Access Specific Menu: "
                    + "\n1 - Admin Menu"
                    + "\n2 - Employee Menu"
                    + "\n3 - Visitor Menu"
                    + "\n4 - Display Map");
    
            String input = sc.nextLine().trim();
            
            // QUIT CHECK 
            if (input.equalsIgnoreCase(QUIT)) {
                System.out.println("See You Next Time!");
                quit = true;
                break;
            }
    
            try {

                // SWITCH CASE
                switch (Integer.parseInt(input)) {
                    case 1: adminMenu(); break;
                    case 2:
                        System.out.println("Enter Employee ID: ");
                        Person u1 = zoo.searchByPersonID(sc.nextLine());
                        if (u1 instanceof Employee) employeeMenu((Employee) u1);
                        else System.out.println("Employee Does Not Exist.");
                        break;
                    case 3:
                        System.out.println("Enter Visitor ID: ");
                        Person u2 = zoo.searchByPersonID(sc.nextLine());
                        if (u2 instanceof Visitor) visitorMenu((Visitor) u2);
                        else System.out.println("Visitor Does Not Exist.");
                        break;
                    case 4:
                        zoo.printMap();
                        break;
                    default:
                        System.out.println("Sorry, that is not a valid option!\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Sorry, that is not a valid option!\n");
            }
        }
    
        // SAVE ZOO ON EXIT
        zoo.saveZoo();
    }    

/*
@description: handles the Admin login flow (PIN check) and then displays the Admin menu.
                Allows the admin to run management commands until the user types QUIT.
@postcondition: returns to the welcome menu when the admin quits.
*/
    public static void adminMenu() {

    boolean quit = false;
    boolean login = false;
    String input;

    // ===== LOGIN LOOP =====
    while (!quit && !login) {
        System.out.print("(Type quit to return)\nEnter Admin PIN: ");
        input = sc.nextLine().trim();

        if (input.equalsIgnoreCase(QUIT)) {
            return;
        } else if (input.equals(ADMIN_PIN)) {
            login = true;
        } else {
            System.out.println("PIN ENTERED WRONG.");
        }
    }

    // ===== ADMIN MENU LOOP =====
    while (login && !quit) {
        System.out.println("""
            Admin Menu:
            (Type quit to return)
            1  - Pass Time
            2  - Display Zoo Balance
            3  - Search Person By ID
            4  - Search Person By ID & Earnings
            5  - Sort Visitors By Name
            6  - Sort Employees By Earnings
            7  - Sort Employees By Experience & Wage
            8  - Display All Employees
            9  - Display All Visitors
            10 - Create Gift Shop
            11 - Create Restaurant
            12 - Create Pavillion
            13 - Create Enclosure
            14 - Create Park
            15 - Create Maze
            16 - Remove Structure
            17 - Maintain All Structures
            18 - Add Animal
            19 - Relocate Animal
            20 - List Animals By Species
            21 - Add Egg
            22 - Display Animals With Low Happiness
            23 - Display Animals With High Hunger
            24 - Display Animals With Low Cleanliness
            25 - Sort Animals By Name Then Species
            26 - Sort Animals By Age
            27 - Sort Animals By Happiness
            28 - Sort Animals By Hunger
            29 - Sort Animals By Cleanliness
            30 - Display All Animals
            31 - Display Incubator
            """);

        input = sc.nextLine().trim();

        if (input.equalsIgnoreCase(QUIT)) {
            return;
        }

        // ===== ADMIN COMMAND SWITCH =====
        try {
            switch (Integer.parseInt(input)) {

                case 1: 
                    System.out.print("Enter days: ");
                    zoo.passTime(Integer.parseInt(sc.nextLine()));
                    break;
                

                case 2:
                    System.out.println("Zoo Balance: $" + zoo.getBalance());
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    Person p = zoo.searchByPersonID(sc.nextLine());
                    System.out.println(p != null ? p : "Person not found.");
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter earnings: ");
                    double earn = Double.parseDouble(sc.nextLine());
                    Employee e = zoo.searchByPersonIDAndEarnings(id, earn);
                    System.out.println(e != null ? e : "No match found.");
                    break;

                case 5:
                    zoo.sortVisitorByName();
                    break;
                case 6:
                    zoo.sortEmployeesByEarnings();
                    break;
                case 7:
                    zoo.sortEmployeesByExperienceAndWage();
                    break;
                case 8:
                    zoo.displayAllEmployees();
                    break;
                case 9:
                    zoo.displayAllVisitors();
                    break;

                // ===== STRUCTURES =====
                case 10:
                    buildGiftShop();
                    break;
                case 11:
                    buildRestaurant();
                    break;
                case 12:
                    buildPavillion();
                    break;
                case 13:
                    buildEnclosure();
                    break;
                case 14:
                    buildPark();
                    break;
                case 15:
                    buildMaze();
                    break;

                case 16:
                    System.out.print("Enter Structure ID: ");
                    System.out.println(zoo.removeStructure(sc.nextLine().charAt(0)));
                    break;

                case 17:
                    zoo.maintainAll();
                    break;

                // ===== ANIMALS =====
                case 18:
                    addAnimalUI();
                    break;
                case 19:
                    relocateAnimalUI();
                    break;

                case 20:
                    System.out.print("Enter species: ");
                    zoo.listAllSameSpecie(sc.nextLine());
                    break;

                case 21:
                    addEggMenu();
                    break;
                case 22:
                    zoo.displayAnimalsLowHappiness();
                    break;
                case 23:
                    zoo.displayAnimalsLowHunger();
                    break;
                case 24:
                    zoo.displayAnimalsLowCleansiness();
                    break;

                case 25:
                    zoo.sortAnimalsByNameThenSpecie();
                    break;
                case 26:
                    zoo.sortAnimalsByAge();
                    break;
                case 27:
                    zoo.sortAnimalsByHappiness();
                    break;
                case 28:
                    zoo.sortAnimalsByHunger();
                    break;
                case 29:
                    zoo.sortAnimalsByCleanliness();
                    break;

                case 30:
                    zoo.displayAllAnimals();
                    break;
                case 31:
                    zoo.displayIncubator();
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input.");
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
    public static void employeeMenu(Employee employee){
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
                + "11 - Deliver animals baby\n"
                + "12 - Print All Habitats\n"
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
                            System.out.println(zoo.searchByNumberAnimalsAndSize(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine())));
                            break;
                     
                        case 2: 
                            buildLivingCondition();
                            break;

                        case 3:
                            zoo.sortStructureBySize();
                            System.out.println("Sorting Complete.");
                            break;
                        
                        case 4:
                            zoo.sortBySizeAndTimeBetweenMaintenance();
                            System.out.println("Sorting Complete.");
                            break;
                        case 5:
                            zoo.sortBySizeAndMostAnimals();
                            System.out.println("Sorting Complete.");
                            break;
                        case 6:
                            zoo.printAllStructuresNeedingMaintenance();
                            break;
                        case 7: 
                            System.out.print("Enter Structure ID: ");
                            zoo.maintain(zoo.searchStructureByID(sc.nextLine().charAt(0))); 
                            break;
                        case 8:
                            zoo.maintainAll();
                            break;
                        case 9:
                            zoo.printAllStructureInfo();
                            break;
                        case 10:
                            addPersonMenu(zoo);
                            break;
                        case 11:
                          deliverOffspringMenu(zoo);
                          break;
                        case 12: 
                            zoo.printAllHabitatInfo();
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

    // ===========================
    // Animal menus
    // ===========================
    public static void deliverOffspringMenu(Zoo zoo) {

        boolean delivered = false;

        while (!delivered) {

            zoo.animalsReadyToReproduce();

            System.out.println("Enter name of the animal whose offspring you will deliver (or 'quit'):");
            String parentName = sc.nextLine();
            if (parentName.equalsIgnoreCase("quit")) {
                return;
            }

            System.out.println("Enter species of the animal:");
            String specie = sc.nextLine();

            Animal parent = zoo.findAnimal(parentName, specie);

            if (parent == null) {
                System.out.println("Animal not found. Try again.");
                continue;
            }

            System.out.println("Enter name for the newborn:");
            String babyName = sc.nextLine();
            if (babyName == null || babyName.trim().isEmpty()) {
                System.out.println("Baby name cannot be empty.");
                continue;
            }

            // =========================
            // MAMMAL BIRTH
            // =========================
            if (parent instanceof Mammal) {

                Mammal mammal = (Mammal) parent;
                Animal baby = mammal.reproduce(babyName);

                if (baby == null) {
                    System.out.println("This mammal cannot give birth right now.");
                    continue;
                }

                System.out.println("Enter habitat ID for the newborn:");
                char habitatId = sc.nextLine().charAt(0);

                Habitat habitat = (Habitat) zoo.searchStructureByID(habitatId);
                if (habitat == null) {
                    System.out.println("Invalid habitat.");
                    continue;
                }

                if (zoo.addAnimal(habitat, baby)) {
                    System.out.println(babyName + " has been born successfully!");
                    delivered = true;
                } else {
                    System.out.println("Failed to add baby to habitat.");
                }
            }

            // =========================
            // EGG-LAYING ANIMALS
            // =========================
            
            else {

                boolean eggAdded = false;

                // =========================
                // FISH (MULTIPLE EGGS)
                // =========================
                if (parent instanceof Fish) {

                    Egg[] eggs = ((Fish) parent).reproduce();

                    if (eggs == null || eggs.length == 0) {
                        System.out.println("This fish cannot reproduce right now.");
                        continue;
                    }

                    for (int i = 0; i < eggs.length; i++) {

                        if (zoo.addEgg(eggs[i])) {
                            System.out.println("Egg added to incubator.");
                            eggAdded = true;
                        } else {
                            System.out.println("Incubator full. Remaining eggs discarded.");
                            break;
                        }
                    }

                    if (eggAdded) {
                        delivered = true;
                    }
                }

                // =========================
                // REPTILES / AMPHIBIANS / BIRDS (SINGLE EGG)
                // =========================
                Egg egg = null;
                if (parent instanceof Reptile) {
                    egg = ((Reptile)parent).reproduce();
                } else if (parent instanceof Bird) {
                    egg = ((Bird)parent).reproduce();
                }
                else if (parent instanceof Reptile) {
                    egg = ((Amphibian)parent).reproduce();
                }

                if (egg == null) {
                    System.out.println("This animal cannot reproduce right now.");
                    continue;
                }

                if (zoo.addEgg(egg)) {
                    System.out.println("Egg added to incubator.");
                    delivered = true;
                } else {
                    System.out.println("Incubator is full. Reproduction failed");
                }

            }
        }
    }

    //==========================================
    private static void addAnimalUI() {

        System.out.print("Enter habitat ID: ");
        char habitatId = sc.nextLine().charAt(0);

        Structure s = zoo.searchStructureByID(habitatId);
        if (!(s instanceof Habitat)) {
            System.out.println("Invalid habitat.");
            return;
        }
        Habitat habitat = (Habitat) s;

        System.out.print("Enter animal name: ");
        String name = sc.nextLine();

        System.out.print("Enter species: ");
        String specie = sc.nextLine();

        System.out.print("Enter preferred interaction: ");
        String interaction = sc.nextLine();

        System.out.print("Enter gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter happiness (0–100): ");
        int happiness = Integer.parseInt(sc.nextLine());

        System.out.print("Enter cleanliness (0–100): ");
        int cleanliness = Integer.parseInt(sc.nextLine());

        System.out.print("Enter hunger (0–100): ");
        int hunger = Integer.parseInt(sc.nextLine());

        System.out.print("Enter age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Enter weight: ");
        double weight = Double.parseDouble(sc.nextLine());

        Animal animal = null;

        switch (specie.toLowerCase()) {

            case "capybara":
                animal = new Capybara(
                    habitatId, name, interaction, gender,
                    happiness, cleanliness, hunger, age, weight
                );
                break;

            case "unicorn":
                animal = new Unicorn(
                    habitatId, name, interaction, gender,
                    happiness, cleanliness, hunger, age, weight
                );
                break;

            case "eagle":
                animal = new Eagle(
                    habitatId, name, interaction, gender,
                    happiness, cleanliness, hunger, age, weight
                );
                break;

            case "cockatoo":
                animal = new Cockatoo(
                    habitatId, name, interaction, gender,
                    happiness, cleanliness, hunger, age, weight
                );
                break;

            case "shark":
                animal = new Shark(
                    habitatId, name, interaction, gender,
                    happiness, cleanliness, hunger, age, weight
                );
                break;

            case "sunfish":
                animal = new Sunfish(
                    habitatId, name, interaction, gender,
                    happiness, cleanliness, hunger, age, weight
                );
                break;

            case "snake":
                animal = new Snake(
                    habitatId, name, interaction, gender,
                    happiness, cleanliness, hunger, age, weight
                );
                break;

            case "axolotl":
                animal = new Axolotl(
                    habitatId, name, interaction, gender,
                    happiness, cleanliness, hunger, age, weight
                );
                break;

            default:
                System.out.println("Unknown species.");
                return;
        }

        if (zoo.addAnimal(habitat, animal)) {
            System.out.println("Animal successfully added.");
        } else {
            System.out.println("Failed to add animal.");
        }
    }



//==========================================

/* description: relocates an animal to a new habitat by prompting the user for necessary fields
*/

private static void relocateAnimalUI() {

    System.out.print("Enter animal name: ");
    String name = sc.nextLine();

    System.out.print("Enter species: ");
    String species = sc.nextLine();

    Animal animal = zoo.findAnimal(name, species);

    if (animal == null) {
        System.out.println("Animal not found.");
        return;
    }

    System.out.print("Enter new habitat ID: ");
    char habitatId = sc.nextLine().charAt(0);

    Structure s = zoo.searchStructureByID(habitatId);
    if (!(s instanceof Habitat)) {
        System.out.println("Invalid habitat.");
        return;
    }
    Habitat newHabitat = (Habitat) s;

    boolean success = zoo.relocateAnimal(newHabitat, animal);

    if (success) {
        System.out.println("Animal relocated successfully.");
    } else {
        System.out.println("Relocation failed.");
    }
}

// =============================================
// =============== EGG MENU ===============
// ================================================
public static void addEggMenu() {

    System.out.print("Enter parent animal name: ");
    String name = sc.nextLine();

    System.out.print("Enter parent animal species: ");
    String specie = sc.nextLine();

    Animal parent = zoo.findAnimal(name, specie);

    if (parent == null) {
        System.out.println("Parent animal not found.");
        return;
    }

    Egg egg = new Egg(parent);

    if (zoo.addEgg(egg)) {
        System.out.println("Egg added to incubator.");
    } else {
        System.out.println("Incubator full. Egg not added.");
    }
}

//==========================================
public static void removeEggMenu() {

    System.out.print("Enter egg index to remove: ");

    try {
        int index = Integer.parseInt(sc.nextLine());

        if (zoo.removeEgg(index)) {
            System.out.println("Egg removed.");
        } else {
            System.out.println("Invalid egg index.");
        }

    } catch (NumberFormatException e) {
        System.out.println("Invalid input.");
    }
}

//==========================================
public static void hatchEggMenu() {

    try {
        System.out.print("Enter egg index: ");
        int eggIndex = Integer.parseInt(sc.nextLine());

        System.out.print("Enter habitat ID: ");
        char habitatId = sc.nextLine().charAt(0);

        Structure s = zoo.searchStructureByID(habitatId);
        if (!(s instanceof Habitat)) {
            System.out.println("That structure is not a habitat.");
            return;
        }

        Habitat habitat = (Habitat) s;

        System.out.print("Enter baby animal name: ");
        String babyName = sc.nextLine();

        Animal baby = zoo.hatchEgg(habitat, eggIndex, babyName);

        if (baby != null) {
            System.out.println("Hatching successful!");
            System.out.println(baby);
        } else {
            System.out.println("Hatching failed.");
        }

    } catch (NumberFormatException e) {
        System.out.println("Invalid input.");
    }
}

// ==================================================================
// ========= menu for building a living condition =================
// ==================================================================
    private static LivingCondition buildLivingCondition() {

        System.out.println("Select Living Condition Type:");
        System.out.println("1 - Land");
        System.out.println("2 - Water");

        int choice = Integer.parseInt(sc.nextLine());

        System.out.print("Enter temperature: ");
        double temp = Double.parseDouble(sc.nextLine());

        System.out.print("Enter humidity: ");
        double hum = Double.parseDouble(sc.nextLine());

        System.out.print("Enter region: ");
        String region = sc.nextLine();

        if (choice == 1) {

            System.out.print("Enter soil compaction: ");
            double soilCompaction = Double.parseDouble(sc.nextLine());

            System.out.print("Enter slope: ");
            double slope = Double.parseDouble(sc.nextLine());

            System.out.print("Has water? (true/false): ");
            boolean hasWater = Boolean.parseBoolean(sc.nextLine());

            System.out.print("Enter vegetation amount: ");
            double vegetation = Double.parseDouble(sc.nextLine());

            System.out.print("Enter number of structures: ");
            int structures = Integer.parseInt(sc.nextLine());

            return new LandCondition(
                temp,
                hum,
                region,
                soilCompaction,
                slope,
                hasWater,
                vegetation,
                structures
            );
        }

        if (choice == 2) {

            System.out.print("Enter acidity: ");
            double acidity = Double.parseDouble(sc.nextLine());

            System.out.print("Enter hardness: ");
            double hardness = Double.parseDouble(sc.nextLine());

            System.out.print("Enter water temperature: ");
            double waterTemp = Double.parseDouble(sc.nextLine());

            System.out.print("Has land? (true/false): ");
            boolean hasLand = Boolean.parseBoolean(sc.nextLine());

            System.out.print("Enter salinity: ");
            double salinity = Double.parseDouble(sc.nextLine());

            return new WaterCondition(
                temp,
                hum,
                region,
                acidity,
                hardness,
                waterTemp,
                hasLand,
                salinity
            );
        }

        System.out.println("Invalid living condition type.");
        return null;
    }
// ======================================================
// COMMON UI HELPERS (ZooRunner ONLY)
// ======================================================

    private static char readStructureID() {
        System.out.print("Enter structure ID: ");
        return sc.nextLine().charAt(0);
    }

    /* description: reads structure name by prompting the user for necessary fields
     */

    private static String readStructureName() {
        System.out.print("Enter name: ");
        return sc.nextLine();
    }

    /* description: reads maintenance time by prompting the user for necessary fields
    */

    private static int readMaintenanceTime() {
        System.out.print("Enter time between maintenance: ");
        return Integer.parseInt(sc.nextLine());
    }

    /* description: reads coordinates by prompting the user for necessary fields
    */

    private static Coord readCoord() {
        System.out.print("Enter Coord (x y): ");
        int x = Integer.parseInt(sc.nextLine());
        int y = Integer.parseInt(sc.nextLine());
        return new Coord(x, y);
    }

    /* description: reads facts by prompting the user for necessary fields
    */

    private static String[] readFacts() {
        System.out.print("Enter number of facts: ");
        int n = Integer.parseInt(sc.nextLine());
        String[] facts = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Fact " + (i + 1) + ": ");
            facts[i] = sc.nextLine();
        }
        return facts;
    }

    /* description: reads menu items by prompting the user for necessary field
    */

    private static Item[] readMenuItems() {
        System.out.print("Enter number of items: ");
        int n = Integer.parseInt(sc.nextLine());
        Item[] menu = new Item[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Item name: ");
            String name = sc.nextLine();
            System.out.print("Item price: ");
            double price = Double.parseDouble(sc.nextLine());
            menu[i] = new Item(name, price);
        }
        return menu;
    }

// ======================================================
// STRUCTURE BUILD METHODS
// ======================================================

    /* description: builds a gift shop by prompting the user for necessary fields
    */

    private static void buildGiftShop() {

        char id = readStructureID();
        String name = readStructureName();
        int time = readMaintenanceTime();
        Coord c1 = readCoord();
        Coord c2 = readCoord();
        String[] facts = readFacts();
        Item[] menu = readMenuItems();

        boolean success = zoo.createGiftShop(c1, c2, name, id, time, facts, menu);

        if (success) System.out.println("Gift Shop created.");
        else System.out.println("Gift Shop creation failed.");
    }

    /* description: builds a restaurant by prompting the user for necessary fields
    */

    private static void buildRestaurant() {

        char id = readStructureID();
        String name = readStructureName();
        int time = readMaintenanceTime();
        Coord c1 = readCoord();
        Coord c2 = readCoord();
        String[] facts = readFacts();
        Item[] menu = readMenuItems();

        boolean success = zoo.createRestaurant(c1, c2, name, id, time, facts, menu);

        if (success) System.out.println("Restaurant created.");
        else System.out.println("Restaurant creation failed.");
    }

    /* description: builds an enclosure by prompting the user for necessary fields
    */

    private static void buildEnclosure() {

        char id = readStructureID();
        String name = readStructureName();

        System.out.print("Enter species: ");
        String species = sc.nextLine();

        System.out.print("Enter area: ");
        int area = Integer.parseInt(sc.nextLine());

        int time = readMaintenanceTime();

        System.out.print("Enter max animals: ");
        int maxAnimals = Integer.parseInt(sc.nextLine());

        Coord c1 = readCoord();
        LivingCondition condition = buildLivingCondition();

        boolean success = zoo.createEnclosure(
            c1, area, species, name, id, time, maxAnimals, condition
        );

        if (success) {
            System.out.println("Enclosure created.");
        } else {
            System.out.println("Enclosure creation failed.");
        }
    }

    /* description: builds a pavillion by prompting the user for necessary fields
    */

    private static void buildPavillion() {

        char id = readStructureID();
        String name = readStructureName();

        System.out.print("Enter area: ");
        int area = Integer.parseInt(sc.nextLine());

        int time = readMaintenanceTime();

        System.out.print("Enter max animals: ");
        int maxAnimals = Integer.parseInt(sc.nextLine());

        Coord c1 = readCoord();
        LivingCondition condition = buildLivingCondition();

        boolean success = zoo.createPavillion(
            c1, area, name, id, time, maxAnimals, condition
        );

        if (success) System.out.println("Pavillion created.");
        else System.out.println("Pavillion creation failed.");
    }

    private static void buildPark() {

        char id = readStructureID();
        String name = readStructureName();

        System.out.print("Enter area: ");
        int area = Integer.parseInt(sc.nextLine());

        int time = readMaintenanceTime();
        Coord c1 = readCoord();

        boolean success = zoo.createPark(c1, area, name, id, time);

        if (success) System.out.println("Park created.");
        else System.out.println("Park creation failed.");
    }

    private static void buildMaze() {

        char id = readStructureID();
        String name = readStructureName();
        int time = readMaintenanceTime();
        Coord c1 = readCoord();

        boolean success = zoo.createMaze(c1, name, id, time);

        if (success) System.out.println("Maze created.");
        else System.out.println("Maze creation failed.");
    }


    // ============================================
    // ================ people menus ==============
    // ============================================
    
    /*
    @description: displays the Visitor menu and allows a visitor to visit structures,
                  view their learning summary / leave the zoo, and sort structures.
                  Continues looping until the user types QUIT.
    @param visitor the logged-in Visitor using the menu
    @postcondition: returns to the welcome menu when the visitor quits.
    */
    public static void visitorMenu(Visitor visitor){
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
                            visitor.visit(zoo.searchStructureByID(sc.nextLine().charAt(0)));  
                            break;
                        case 2: 
                            System.out.println(visitor.leaveZoo()); 
                            break;   
                        case 3:
                            zoo.sortStructuresByLeastAnimals();
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

    /* description: displays the Add Person menu and allows the admin/employee to add a person
       to the zoo (visitor or employee). Prompts for all necessary fields.
       @param zoo: the zoo to add the person to
    */
    public static void addPersonMenu(Zoo zoo) {

        Person p = null;

        System.out.println("Enter person type (child / adult / zookeeper / shopstaff) or 'quit':");
        String personType = sc.nextLine().toLowerCase();

        if (personType.equals("quit")) {
            return;
        }

        System.out.println("Enter person ID:");
        String id = sc.nextLine();

        System.out.println("Enter first name:");
        String firstName = sc.nextLine();

        System.out.println("Enter last name:");
        String lastName = sc.nextLine();

        System.out.println("Enter age:");
        int age = Integer.parseInt(sc.nextLine());

        // =========================
        // VISITORS
        // =========================
        if (personType.equals("child")) {

            System.out.println("Enter balance:");
            double balance = Double.parseDouble(sc.nextLine());

            System.out.println("Enter learning level:");
            int learning = Integer.parseInt(sc.nextLine());

            System.out.println("Enter visit duration (days):");
            int duration = Integer.parseInt(sc.nextLine());

            System.out.println("Stroller needed? (true/false):");
            boolean stroller = Boolean.parseBoolean(sc.nextLine());

            System.out.println("Enter guardian ID:");
            String guardianId = sc.nextLine();

            p = new Child(age, id, firstName, lastName, balance, learning, duration, stroller, guardianId);
        }

        else if (personType.equals("adult")) {

            System.out.println("Enter balance:");
            double balance = Double.parseDouble(sc.nextLine());

            System.out.println("Enter learning level:");
            int learning = Integer.parseInt(sc.nextLine());

            System.out.println("Enter visit duration (days):");
            int duration = Integer.parseInt(sc.nextLine());

            System.out.println("Enter preferred budget limit:");
            double budget = Double.parseDouble(sc.nextLine());

            p = new Adult(age, id, firstName, lastName, balance, learning, duration, budget);
        }

        // =========================
        // EMPLOYEES
        // =========================
        else if (personType.equals("zookeeper")) {

            System.out.println("Enter hourly wage:");
            double wage = Double.parseDouble(sc.nextLine());

            System.out.println("Enter years of experience:");
            int experience = Integer.parseInt(sc.nextLine());

            System.out.println("Enter certification level:");
            int cert = Integer.parseInt(sc.nextLine());

            System.out.println("Enter hours worked (starting):");
            double hoursWorked = Double.parseDouble(sc.nextLine());

            System.out.println("Enter starting earnings:");
            double earnings = Double.parseDouble(sc.nextLine());

            p = new ZooKeeper(age, id, firstName, lastName, wage, experience, cert,
                            hoursWorked, earnings);

        }
        else if (personType.equals("shopstaff")) {

            System.out.println("Enter hourly wage:");
            double wage = Double.parseDouble(sc.nextLine());

            System.out.println("Enter years of experience:");
            int experience = Integer.parseInt(sc.nextLine());

            System.out.println("Enter hours worked (starting):");
            double hoursWorked = Double.parseDouble(sc.nextLine());

            System.out.println("Enter starting earnings:");
            double earnings = Double.parseDouble(sc.nextLine());

            p = new ShopStaff(age, id, firstName, lastName, wage, experience,
                            hoursWorked, earnings);
        }

        else {
            System.out.println("Invalid person type.");
            return;
        }

        // =========================
        // ADD TO ZOO
        // =========================
        if (zoo.addPerson(p)) {
            System.out.println("Person added successfully.");
        } else {
            System.out.println("Zoo has reached max capacity or ID already exists.");
        }
    }
}
