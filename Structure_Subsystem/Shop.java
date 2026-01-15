/*
   File Name: Shop.java
   Name: Arianna Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Shop represents a shop within the zoo. 
*/
import java.util.*;
import Land_Subsystem.Land;
import Person_Subsystem.*;
public abstract class Shop extends Structure{

   public static Scanner sc = new Scanner(System.in);

   //FIELDS
   private Item[] menu;
   private AnimalFacts animalFacts;
   
   //CONSTRUCTOR
   /*
     @description: declares and initializes Shop object
     @param name                the name of the shop
     @param structureID         unique ID for the shop
     @param area                the area of the sho
     @param timeBetweenMaintenance  the time between maintenance cycles
     @param daysSinceLastMaintenance  the number of days since last maintenance
     @param onProperty          the land on which this shop is located
     @param animalFacts         array of animal facts available at the shop
     @param menu                array of items available for purchase at the shop
   */
   public Shop(String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty, String[] animalFacts, Item[] menu){
      super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty);
      this.menu = menu;
      this.animalFacts = new AnimalFacts(animalFacts);     
   }
   
   //ACCESSORS
   public AnimalFacts getAnimalFacts(){
      return animalFacts;
   }

   public Item[] getMenu(){
      return menu;
   }
   
   //OTHER METHODS

   /* 
    * @description: calculates the maintenance cost of the shop
    * @return: the maintenance cost of the shop
   */
   public abstract double calculateMaintenanceCost(); 

   /* 
    @description: updates the visitor's learning history with an animal fact
    @param toUpdate: the visitor to update
   */
   public abstract void updateVisitorLearning(Visitor toUpdate);
   
   /* 
    @description: processes the purchase of an item by a visitor
    @param buyer: the visitor buying the item
    @param product: the item being purchased
    @return: true if the purchase was successful, false otherwise
   */
   public abstract boolean buy(Visitor buyer, Item product);
   
   /* 
    @description: sets structure as demolished
    @return: returns true
   */
   public boolean demolish(){
      this.setDemolished(false);  
      return true; 
   }
     
   /* 
    @description: displays the shop menu and processes purchases
    @param visitor: the visitor interacting with the shop
   */
   public void displayMenu(Visitor visitor){

      //Display menu items
      
      System.out.println("Store Menu:\n");
      for(int i = 0; i < menu.length; i++){
            System.out.println((i + 1) + ". " + menu[i]);
      }
      String line = "";

      //Prompt user to buy items until they choose to quit

      System.out.println("Enter # to buy item or type quit to leave");
      while(!line.equalsIgnoreCase("quit")){
            try{
                line = sc.nextLine();
                buy(visitor, menu[Integer.parseInt(line) - 1]);
            }catch(NumberFormatException e){
            }catch(ArrayIndexOutOfBoundsException f){
                System.out.print("Product does not exist.");
            }
      }     
      System.out.println("Bye!");

   }
   
   /*
   * @description: displays the animal fact learned by the visitor   
   * @param visitor: the visitor who learned the fact
   */
   protected void displayAnimalFact(Visitor visitor){
      System.out.println((visitor.getLearningHistory())[visitor.getLearningHistorySize() - 1]);
   }

   /* 
    @description: saves the shop to a string
    @return: the shop as a string
   */
   public abstract String saveToString();
}