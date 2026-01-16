/*
   File Name: Restaurant.java
   Name: Arianna Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Restaurant represents a restaurant within the zoo.
*/
import Land_Subsystem.*;

public class Restaurant extends Shop{

    //CONSTANTS
    public final static int LEARNING_PER_PURCHASE = 20;
    public final static double MAINTENANCE_RATE_PER_UNIT = 12;
    public final static String EATING_MESSAGE = "Meal Eaten: yummy!!";

    //CONSTRUCTOR
    /*
     @description: declares and initializes Restaurant object
     @param name                the name of the restaurant
     @param structureID         unique ID for the restaurant
     @param area                the area of the restaurant
     @param timeBetweenMaintenance  the time between maintenance cycles
     @param daysSinceLastMaintenance  the number of days since last maintenance
     @param onProperty          the land on which this restaurant is located
     @param animalFacts         array of animal facts available at the restaurant
     @param menu                array of items available for purchase at the restaurant
    */
    public Restaurant(String name, 
    char structureID, 
    int area, 
    int timeBetweenMaintenance, 
    int daysSinceLastMaintenance, 
    Land onProperty, 
    String[] animalFacts, 
    Item[] menu){
      super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, animalFacts, menu);
    }

    //OTHER METHODS

    /*
     * @description: loads a restaurant from a string
     * @param fromFile: the string to load the restaurant from
     * @param onProperty: the land on which this restaurant is located
     * @return: the loaded restaurant
    */
    public static Restaurant loadFromString(String fromFile, Land onProperty){
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

        // returns new Restaurant object
        return new Restaurant(
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
     * @description: calculates the maintenance cost of the restaurant
     * @return: the maintenance cost of the restaurant
    */
    public double calculateMaintenanceCost(){
        return this.getArea() * MAINTENANCE_RATE_PER_UNIT;
    }

    /* 
     @description: updates the visitor's learning history with an animal fact
     @param toUpdate: the visitor to update
    */
    public void updateVisitorLearning(Visitor toUpdate){
        toUpdate.addLearningLevel(LEARNING_PER_PURCHASE);
        toUpdate.addLearningFact((this.getAnimalFacts()).pickRandomFact());
        displayAnimalFact(toUpdate);
    }
   
    /* 
     @description: processes the purchase of an item by a visitor
     @param buyer: the visitor buying the item
     @param product: the item being purchased
     @return: true if the purchase was successful, false otherwise
    */
    public boolean buy(Visitor buyer, Item product){

        //check if buyer can afford item

        if(buyer.canAffordItem(product)){

            //records purchase and adds item to visitor's inventory

            buyer.recordPurchase(product.getPrice());
            buyer.addItem(product);
            updateVisitorLearning(buyer);
            System.out.println(EATING_MESSAGE + "\n");
            return true;
        } else {
           return false;
        }        
    }

    /*
     * @description: saves the restaurant to a string
     * @return: the restaurant as a string
    */
    public String saveToString(){
        String saveToString;

        saveToString = "Restaurant\n" ;

        //saves subclass-specific fields

        saveToString += (getMenu()).length + "\n";
        for (int i = 0; i < (getMenu()).length; i++){
            saveToString += ((getMenu())[i]).getName()+ "\n" + ((getMenu())[i]).getPrice() + "\n";
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
        return saveToString;

   }

}