import java.util.*;

import Land_Subsystem.Land;
public abstract class Shop extends Structure{
   public static Scanner sc = new Scanner(System.in);
   private Item[] menu;
   private AnimalFacts animalFacts;
   
   public Shop(String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty, String[] animalFacts, Item[] menu){
      super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty);
      this.menu = menu;
      this.animalFacts = new AnimalFacts(animalFacts);     
   }
   
   public AnimalFacts getAnimalFacts(){
      return animalFacts;
   }
   
   public abstract double calculateMaintenanceCost(); 
   
   public abstract void updateVisitorLearning(Visitor toUpdate);
   
   public abstract boolean buy(Visitor buyer, Item product);
   
   public boolean demolish(){
      this.setDemolished(false);  
      return true; 
   }
     
   public void displayMenu(){
      System.out.println("Store Menu:\n");
      for(int i = 0; i < menu.length; i++){
            System.out.println((i + 1) + ". " + menu[i]));
      }
      String line = "";
      System.out.println("Enter # to buy item or type quit to leave");
      while(!line.equalsIgnoreCase("quit")){
            try{
                line = sc.nextLine();
                Visitor.buy(Integer.parseInt(line) - 1);
            }catch(NumberFormatException e){
            }catch(ArrayIndexOutOfBoundsException f){
                System.out.print("Product does not exist.");
            }
      }     
      System.out.println("Bye!");

   }
   
   public void deductMoney(Visitor buyer, double cost){
      return buyer.recordPurchase(cost);
   }
   
   //tell elizabeth to make this accessor later
   protected void displayAnimalFact(Visitor visitor){
      System.out.println((visitor.getLearningHistory)[visitor.getLearningHistorySize() - 1]);
   }

   public String saveToString(){
      String saveToString = menu.length + "\n";
      for (int i = 0; i < menu.length; i++){
         saveToString += menu[i].getName()+ "\n" + menu[i].getPrice() + "\n";
      }
      int animalLength = animalFacts.getLength();
      saveToString += animalLength;
      for (int j = 0; j < animalLength; j++){
         saveToString += animalFacts.getAnimalFact(j) + "\n";
      }
      
      saveToString += getName() 
      + "\n" + getStructureID() 
      + "\n" + getArea() 
      + "\n" + getTimeBetweenMaintenance() 
      + "\n" + getDaysSinceLastMaintenance() 
      + "\n";
      return saveToString;

   }
}