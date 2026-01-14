package Person_Subsystem;
/*
   File Name: Visitor.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Visitor represents anyone entering the zoo as a guest. It adds visit-related fields like balance (money available) and learning 
                level, plus tracking for visit actions (ex: attractions visited / learning history). Visitor subclasses override ticket-cost 
                logic and provide an end-of-visit summary.
*/

import Zoo;
import Structure_Subsystem.Item;
import Structure_Subsystem.Structure;

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
    public Visitor(int age, String personID, String firstName, String lastNamedouble balance, int learningLevel, int visitDuration) {
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
        ensureLearningHistoryCapacity(learningHistorySize + 1);
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

    //WORK IN PROGRESS (visit a structure by ID)
   /*
    @description: visits a structure and updates visitor learning/attraction count if valid
    @param structureID the ID of the structure to visit
    @return true if the visit occurred, false otherwise
    */ 
    public boolean visit(String structureID){
        if (structureID == null || structureID.isEmpty()){return false;}   //ADD OR DEMOLISHED to reutnr false condition 
        //CALL specifif mehods for visit, called by user  
        Structure s = Zoo.getStructureByID(structureID);

        if (structure == null) return false;
        if (structure.isDemolished()) return false;

        structure.updateVisitorLearning(this);
        attractionsVisited++;
        return true;
    }
}



