/*
   File Name: Structure.java
   Name: Arianna Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Structure represents a building or facility within the zoo. It includes fields for name, ID, area, maintenance cost, and other properties related to the structure's condition and usage.
*/
import java.io.*;
public abstract class GiftShop extends Shop{

    //CONSTANTS
    public final static int LEARNING_PER_PURCHASE = 12;
    public final static double MAINTENANCE_RATE_PER_UNIT = 10;

    //CONSTRUCTOR

    /*
        @description: declares and initializes GiftShop object
        @param name: the name of the gift shop
        @param structureID: unique ID for the gift shop
        @param area: the area of the gift shop
        @param timeBetweenMaintenance: the time between maintenance cycles
        @param daysSinceLastMaintenance: the number of days since last maintenance
        @param onProperty: the land on which this gift shop is located
        @param animalFacts: facts about animals in the zoo
        @param menu: items available for purchase in the gift shop
    */
    public GiftShop(String name,
    char structureID,
    int area, 
    int timeBetweenMaintenance, 
    int daysSinceLastMaintenance, 
    Land onProperty, 
    String[] animalFacts, 
    Item[] menu){
      super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, animalFacts, menu);
    }

    /*
     * @description: loads a gift shop from a string
     * @param fromFile: the string to load the gift shop from
     * @param onProperty: the land on which this gift shop is located
     * @return: the loaded gift shop
    */
    public static GiftShop loadFromString(String fromFile, Land onProperty) {
        String[] fields = fromFile.split("\n");
        int index = 0;

        //initializes subclass-specific fields

        int numItems = Integer.parseInt(fields[index]);
        Item[] menu = new Item[numItems];

        String itemName;
        double price;

        for (int i = 0; i < numItems; i++) {
            itemName = fields[index++];
            price = Double.parseDouble(fields[index++]);
            menu[i] = new Item(itemName, price);
        }

        int numFacts = Integer.parseInt(fields[index++]);
        String[] facts = new String[numFacts];

        for (int i = 0; i < numFacts; i++) {
            facts[i] = fields[index++];
        }

        //initializes superclass fields

        String name = fields[index++];
        char structureID = fields[index++].charAt(0);
        int area = Integer.parseInt(fields[index++]);
        int timeBetweenMaintenance = Integer.parseInt(fields[index++]);
        int daysSinceLastMaintenance = Integer.parseInt(fields[index++]);

        //returns new GiftShop object

        return new GiftShop(
            name,
            structureID,
            area,
            timeBetweenMaintenance,
            daysSinceLastMaintenance,
            onProperty,
            facts,
            menu
        );
    }

    /* 
     * @description: calculates the maintenance cost of the gift shop
     * @return: the maintenance cost of the gift shop
    */
    public double calculateMaintenanceCost(){
        return this.getArea() * MAINTENANCE_RATE_PER_UNIT;
    }

    /* 
     @description: updates the visitor's learning history with an animal fact
     @param toUpdate: the visitor to update
    */
    public void updateVisitorLearning(Visitor toUpdate){

        //increases learning level and adds a random animal fact to visitor's learning history

        toUpdate.addLearningLevel(LEARNING_PER_PURCHASE);
        toUpdate.addLearningFact((this.getAnimalFacts()).pickRandomFact());

        //displays latest fact in Visitor's learning history

        displayAnimalFact(toUpdate);
    }

    /* 
        @description: allows a visitor to purchase an item
        @param buyer: the visitor purchasing the item
        @param product: the item being purchased
        @return: true if the purchase was successful, false otherwise
    */
    public boolean buy(Visitor buyer, Item product){

        //checks if buyer can afford item
        if(buyer.canAffordItem(product)){

            //processes purchase
            buyer.recordPurchase(product.getPrice());
            buyer.addItem(product);
            updateVisitorLearning(buyer);
            return true;
        } else {
           return false;
        }        
    }

    /* 
        @description: saves the gift shop to a string
        @return: the gift shop as a string
    */
    public String saveToString(){
        String saveToString;

        saveToString = "GiftShop\n" ;

        // saves subclass-specific fields
        saveToString += (getMenu()).length + "\n";
        for (int i = 0; i < (getMenu()).length; i++){
            saveToString += (getMenu())[i].getName()+ "\n" + (getMenu())[i].getPrice() + "\n";
        }
        int animalLength = (getAnimalFacts()).getLength();
        saveToString += animalLength;
        for (int j = 0; j < animalLength; j++){
            saveToString += (getAnimalFacts()).getAnimalFact(j) + "\n";
        }
      
        // saves superclass fields
        saveToString += getName() 
        + "\n" + getStructureID() 
        + "\n" + getArea() 
        + "\n" + getTimeBetweenMaintenance() 
        + "\n" + getDaysSinceLastMaintenance() 
        + "\n";

        //returns the complete save string
        return saveToString;

   }
   
}
