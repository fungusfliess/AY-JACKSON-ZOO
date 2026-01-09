public class GiftShop extends Shop{
    public final static int LEARNING_PER_PURCHASE = 12;
    public final static double MAINTENANCE_COST_PER_AREA = 10;

    public GiftShop(String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty, String[] animalFacts, Item[] menu){
      super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, animalFacts, menu);
    }
    public double calculateMaintenanceCost(){
        return this.getArea() * MAINTENANCE_COST_PER_AREA;
    }
    public void updateVisitorLearning(Visitor toUpdate){
        toUpdate.addLearningLevel(LEARNING_PER_PURCHASE);
        toUpdate.addLearningFact((this.getAnimalFacts()).pickRandomFact());
        displayAnimalFact(toUpdate);
    }
   
    public boolean buy(Visitor buyer, Item product){
        if(buyer.canAffordItem(product)){}
            deductMoney(buyer, product.getPrice());
            buyer.addItem(product);
            updateVisitorLearning(buyer);
            return true;
        } else {
           return false;
        }        
    }

    // loadFromString stuff
}
