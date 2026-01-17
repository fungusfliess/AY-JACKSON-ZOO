import java.util.Scanner;

public class ZooRunner {

    private static final String QUIT = "quit";
    private static final String ADMIN_PIN = "0000";
    private static Scanner sc = new Scanner(System.in);
    private static Zoo zoo = new Zoo();

    public static void main(String[] args) {
      
        
        welcomeMenu();
        // boolean quit = false;

        // while (!quit) {
        //     System.out.println("""
        //         === ZOO SYSTEM ===
        //         1 - Admin Menu
        //         2 - Employee Menu
        //         3 - Visitor Menu
        //         4 - Display Map
        //         quit - Exit
        //         """);

        //     String input = sc.nextLine().trim();

        //     switch (input.toLowerCase()) {
        //         case "1" -> adminMenu(zoo);
        //         case "2" -> employeeMenu(zoo);
        //         case "3" -> visitorMenu(zoo);
        //         case "4" -> zoo.printMap();
        //         case "quit" -> {
        //             zoo.saveZoo();
        //             quit = true;
        //         }
        //         default -> System.out.println("Invalid option.");
        //     }
        // }
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
          input = sc.nextLine();

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
              9  - Create Gift Shop
              10 - Create Restaurant
              11 - Create Pavillion
              12 - Create Enclosure
              13 - Create Park
              14 - Create Maze
              15 - Remove Structure
              16 - Maintain All Structures
              17 - Add Animal
              18 - Relocate Animal
              19 - List Animals By Species
              20 - Add Egg
              21 - Display Animals With Low Happiness
              22 - Display Animals With High Hunger
              23 - Display Animals With Low Cleanliness
              24 - Sort Animals By Name Then Species
              25 - Sort Animals By Age
              26 - Sort Animals By Happiness
              27 - Sort Animals By Hunger
              28 - Sort Animals By Cleanliness
              """);

          input = sc.nextLine();

          if (input.equalsIgnoreCase(QUIT)) {
              return;
          }

          try {
              switch (Integer.parseInt(input)) {

                  case 1 -> {
                      System.out.print("Enter days: ");
                      zoo.passTime(Integer.parseInt(sc.nextLine()));
                  }

                  case 2 -> System.out.println("Zoo Balance: $" + zoo.getBalance());

                  case 3 -> {
                      System.out.print("Enter ID: ");
                      Person p = zoo.searchByPersonID(sc.nextLine());
                      System.out.println(p != null ? p : "Person not found.");
                  }

                  case 4 -> {
                      System.out.print("Enter ID: ");
                      String id = sc.nextLine();
                      System.out.print("Enter earnings: ");
                      double earn = Double.parseDouble(sc.nextLine());
                      Employee e = zoo.searchByPersonIDAndEarnings(id, earn);
                      System.out.println(e != null ? e : "No match found.");
                  }

                  case 5 -> zoo.sortVisitorByName();
                  case 6 -> zoo.sortEmployeesByEarnings();
                  case 7 -> zoo.sortEmployeesByExperienceAndWage();
                  case 8 -> zoo.displayAllEmployees();

                  // ===== STRUCTURES =====
                  case 9 -> buildGiftShop();
                  case 10 -> buildRestaurant();
                  case 11 -> buildPavillion();
                  case 12 -> buildEnclosure();
                  case 13 -> buildPark();
                  case 14 -> buildMaze();

                  case 15 -> {
                      System.out.print("Enter Structure ID: ");
                      System.out.println(zoo.removeStructure(sc.nextLine().charAt(0))
                              ? "Removed."
                              : "Removal failed.");
                  }

                  case 16 -> zoo.maintainAll();

                  // ===== ANIMALS =====
                  case 17 -> addAnimalUI();
                  case 18 -> relocateAnimalUI();

                  case 19 -> {
                      System.out.print("Enter species: ");
                      zoo.listAllSameSpecie(sc.nextLine());
                  }

                  case 20 -> addEggMenu();
                  case 21 -> zoo.displayAnimalsLowHappiness();
                  case 22 -> zoo.displayAnimalsLowHunger();
                  case 23 -> zoo.displayAnimalsLowCleansiness();

                  case 24 -> zoo.sortAnimalsByNameThenSpecie();
                  case 25 -> zoo.sortAnimalsByAge();
                  case 26 -> zoo.sortAnimalsByHappiness();
                  case 27 -> zoo.sortAnimalsByHunger();
                  case 28 -> zoo.sortAnimalsByCleanliness();

                  default -> System.out.println("Invalid option.");
              }

          } catch (Exception e) {
              System.out.println("Invalid input.");
          }
      }
  }



// ======================================================
// COMMON UI HELPERS (ZooRunner ONLY)
// ======================================================

private static char readStructureID() {
    System.out.print("Enter structure ID: ");
    return sc.nextLine().charAt(0);
}

private static String readStructureName() {
    System.out.print("Enter name: ");
    return sc.nextLine();
}

private static int readMaintenanceTime() {
    System.out.print("Enter time between maintenance: ");
    return Integer.parseInt(sc.nextLine());
}

private static Coord readCoord() {
    System.out.print("Enter Coord (x y): ");
    int x = Integer.parseInt(sc.nextLine());
    int y = Integer.parseInt(sc.nextLine());
    return new Coord(x, y);
}

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

    if (success) System.out.println("Enclosure created.");
    else System.out.println("Enclosure creation failed.");
}

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

    boolean success = zoo.createMaze(c1, name, id, time, null);

    if (success) System.out.println("Maze created.");
    else System.out.println("Maze creation failed.");
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

// ==================================================================
    // ========= Animal menus =================
    // ==================================================================
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
    String species = sc.nextLine();

    Animal animal = zoo.findAnimal(name, species);

    if (animal == null) {
        System.out.println("Animal not found.");
        return;
    }

    boolean success = zoo.addAnimal(habitat, animal);

    if (success) {
        System.out.println("Animal successfully added to habitat.");
    } else {
        System.out.println("Failed to add animal.");
    }
}

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

// ===============
// EGG STUFF
// ==================
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

//
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

//
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
                            zoo.sortStructureBySize();
                            System.out.println("Sorting Complete.");
                            break;
                        
                        case 4:
                            zoo.sortBySizeAndTimeBetweenMaintenance();
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
                            zoo.maintain(zoo.searchStructureByID(sc.nextLine().charAt(0))); 
                            break;
                        case 8:
                            zoo.maintainAll();
                            break;
                        case 9:
                            zoo.printAllStructureInfo();
                            break;
                        case 10:
                            if (addPerson()) {
                                System.out.println("Person Added.");
                            } else {
                                System.out.println("Zoo has reached max capacity OR input was invalid, Person could not be added!");
                            }
                            break;
                        case 11:
                          deliverOffspringMenu(zoo);
                        default:
                            System.out.println("Sorry, that is not a valid option!\n");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Sorry, that is not a valid option!\n");
                }
            }
        }
    }

    //
    public static void deliverOffspringMenu(Zoo zoo, Scanner sc) {

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

            Egg egg = parent.reproduce();

            if (egg == null) {
                System.out.println("This animal cannot reproduce right now.");
                continue;
            }

            if (zoo.addEgg(egg)) {
                System.out.println("Egg created successfully.");
                delivered = true;
            } else {
                System.out.println("Incubator is full. Egg discarded.");
            }
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
}
