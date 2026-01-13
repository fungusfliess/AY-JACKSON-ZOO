/*
   File Name: Visitor.java
   Name: Elizabeth Wang
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: 
*/

import Structure_Subsystem.Item;
import Structure_Subsystem.Structure;

public abstract class Visitor extends Person {

    public static final int MAX_ITEMS = 20;
    public static final int MAX_LEARNING_HISTORY_SIZE = 20;

    public static final int MIN_ADULT_AGE = 18;
    public static final int MIN_SENIOR_AGE = 65;

    public static final double ADULT_BASE_PRICE = 10.00;
    public static final double CHILD_BASE_PRICE = 5.00;

    public static final int FREE_CHILD_AGE_MAX = 3;
    public static final int FREE_SENIOR_AGE_MIN = 80;

    private double balance;
    private int learningLevel;
    private int visitDuration;
    private int attractionsVisited;

    private int learningHistorySize;
    private String[] learningHistory;

    private int numItems;
    private Item[] itemInventory;

    private double amountSpent;

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

    public double getBalance(){return balance;}
    public int getLearningLevel(){return learningLevel;}
    public int getAttractionsVisited(){return attractionsVisited;}
    public int getNumItems(){return numItems;}

    public void addLeavingLevel(int add) {
        if (add>0) {
            learningLevel+=add;
        }
    }

    public void addLearningFact(String fact) {
        if (fact == null) return;

        if (fact.isEmpty()) return;
        ensureLearningHistoryCapacity(learningHistorySize + 1);
        learningHistory[learningHistorySize] = fact;
        learningHistorySize++;
    }


    public abstract double calculateTicketCost();

    public boolean buyTicket() {
        double cost = calculateTicketCost();
        if (cost < 0) {
            cost = 0;
        }

        if (balance >= cost) {
            recordPurchase(cost); // subtracts from balance + adds to amountSpent
            return true;
        }
        return false;
    }


    public void recordPurchase(double amount) {
        if (amount > 0) {
            amountSpent += amount;
            balance -= amount;
            if (balance < 0) balance = 0;
        }
    }

    public boolean canAffordItem(Item item) {
        if (item == null){return false;}
        return item.getPrice() <= balance;
    }

    public boolean addItem(Item item) {
        if (item == null){return false;{}

        ensureItemInventoryCapacity(numItems + 1);
        itemInventory[numItems] = item;
        numItems++;
        return true;
    }

    public void passDay(){
        this.deactivate();
    }

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


}
