public abstract class Shop extends Structure{
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
   }
     
   public void displayMenu(){
      System.out.print(menu);
   }
   
   public void deductMoney(Visitor buyer, double cost){
      return buyer.recordPurchase(cost);
   }
   
   //tell elizabeth to make this accessor later
   protected void displayAnimalFact(Visitor visitor){
      System.out.println((visitor.getLearningHistory)[visitor.getLearningHistorySize() - 1]);
   }
}