/*
   File Name: Visitor.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Visitor is an abstract subclass of Person that represents guests visiting the zoo. It stores
                visit-related data such as balance, learning level, visit duration, attractions visited, and
                learning history. Visitor subclasses (Adult, Child, Senior) override calculateTicketCost()
                and may provide an end-of-visit summary.
*/

public abstract class Visitor extends Person {
    //CONSTANTS 
    public static final int MAX_ITEMS = 20;
    public static final int MAX_LEARNING_HISTORY_SIZE = 20;

    public static final int MIN_ADULT_AGE = 18;
    public static final int MIN_SENIOR_AGE = 65;

    public static final double ADULT_BASE_PRICE = 10.00;
    public static final double CHILD_BASE_PRICE = 5.00;

    public static final int FREE_CHILD_AGE_MAX = 3;
    public static final int FREE_SENIOR_AGE_MIN = 80;

    //FIELDS 
    private double balance;
    private int learningLevel;
    private int visitDuration;
    private int attractionsVisited;

    private int learningHistorySize;
    private String[] learningHistory;

    private int numItems;
    private Item[] itemInventory;

    private double amountSpent;

    //CONSTRUCTOR
    /*
    @description: declares and initializes Visitor object
    @param age           the visitor's age (handled in Person)
    @param personID      unique ID for the visitor
    @param firstName     the visitor's first name
    @param lastName      the visitor's last name
    @param balance       money available for tickets/items (if negative, set to 0)
    @param learningLevel learning level (if negative, set to 0)
    @param visitDuration visit duration (if negative, set to 0)
    */
    public Visitor(int age, String personID, String firstName, String lastName, double balance, int learningLevel, int visitDuration) {
        super(age, personID, firstName, lastName);
        
        this.balance = Math.max(0.0, balance);
        this.learningLevel = Math.max(0, learningLevel);
        this.visitDuration = Math.max(0, visitDuration);

        this.attractionsVisited = 0;
        this.amountSpent = 0.0;

        this.learningHistorySize = 0;
        this.learningHistory = new String[MAX_LEARNING_HISTORY_SIZE];

        this.numItems = 0;
        this.itemInventory = new Item[MAX_ITEMS];
    }

    //ACCESSOR
    public double getBalance(){
        return balance;
    }

    public int getLearningLevel(){
        return learningLevel;
    }

    public int getAttractionsVisited(){
        return attractionsVisited;
    }

    public int getNumItems(){
        return numItems;
    }

    public int getLearningHistorySize() {
        return learningHistorySize;
    }

    public double getAmountSpent() {
        return amountSpent;
    }
 
    public String[] getLearningHistory() {
        return learningHistory;
    }

    public int getVisitDuration(){
        return visitDuration;
    }
    
    //MUTATOR
    /*
    @description: increases the visitor's learning level by a positive amount
    @param add the amount to increase learning level by (must be > 0)
    */
    public void addLeavingLevel(int add) {
        if (add>0) {
            learningLevel+=add;
        }
    }

    /*
    @description: adds a learning fact to the visitor's learning history (if there is space)
    @param fact the fact to add (must be non-null and not empty)
    */
    public void addLearningFact(String fact) {
        if (fact == null) return;

        if (fact.isEmpty()) return;
        if (learningHistorySize==MAX_LEARNING_HISTORY_SIZE){return;}
        learningHistory[learningHistorySize] = fact;
        learningHistorySize++;
    }

    //OTHER METHODS 
    /*
    @description: calculates and returns the admission ticket cost for this visitor
                 (subclasses override this with their own pricing rules)
    @return the ticket cost for this visitor
    */
    public abstract double calculateTicketCost();

    /*
    @description: attempts to buy a ticket using this visitor's balance
    @return true if ticket purchase was successful, false otherwise
    */
    public boolean buyTicket() {
        double cost = calculateTicketCost();
        if (cost < 0) {
            cost = 0;
        }

        if (balance >= cost) {
            recordPurchase(cost); //subtracts from balance + adds to amountSpent
            return true;
        }
        return false;
    }

    /*
    @description: records a purchase by decreasing balance and increasing amountSpent
    @param amount purchase amount (must be > 0)
    */
    public void recordPurchase(double amount) {
        if (amount > 0) {
            amountSpent += amount;
            balance -= amount;
            if (balance < 0) balance = 0;
        }
    }

    /*
    @description: checks if the visitor can afford an item based on current balance
    @param item the item to check
    @return true if visitor can afford it, false otherwise
    */
    public boolean canAffordItem(Item item) {
        if (item == null){return false;}
        return item.getPrice() <= balance;
    }

    /*
    @description: adds an item to the visitor's inventory (does NOT change balance)
    @param item the item to add
    @return true if the item was added, false otherwise (null item or inventory full)
    */
    public boolean addItem(Item item) {
        if (item == null || numItems >= itemInventory.length){return false;}

        itemInventory[numItems] = item;
        numItems++;
        return true;
    }

    /*
    @description: purchases an item by checking affordability, recording the purchase, and adding it to inventory
    @param item the item to purchase
    @return true if purchase was successful, false otherwise
    */
    public boolean purchaseItem(Item item) {
        if (item == null) return false;
        if (!canAffordItem(item)) return false;
        if (!addItem(item)) return false;

        recordPurchase(item.getPrice());
        return true;
    }

    /*
    @description: ends the visitor's day in the Zoo system (default behavior: deactivate visitor)
    */
    public void passDay(){
        this.deactivate();
    }

   /*
    @description: visits a structure and updates visitor learning/attraction count if valid
    @param structureID the ID of the structure to visit
    @return true if the visit occurred, false otherwise
    */ 
    public boolean visit(String structureID){
        if (structureID == null || structureID.isEmpty()){return false;}  
        Structure s = Zoo.getStructureByID(structureID);

        if (s == null) return false;
        if (s.isDemolished()) return false;

        s.updateVisitorLearning(this);
        attractionsVisited++;
        return true;
    }

    /*
    @description: returns this Visitor in file format (role + base fields + visitor fields)
    @return a string formatted for writing to person.txt
    */
    @Override
    public String saveToString() {
    return getRole() + "\n" +
            getPersonID() + "\n" +
            getFirstName() + "\n" +
            getLastName() + "\n" +
            getAge() + "\n" +
            balance + "\n" +
            learningLevel + "\n" + 
            visitDuration + "\n";
    }

}



