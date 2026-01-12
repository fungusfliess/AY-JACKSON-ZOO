import java.io.*;
public class GiftShop extends Shop{
    public final static int LEARNING_PER_PURCHASE = 12;
    public final static double MAINTENANCE_COST_PER_AREA = 10;

    public GiftShop(String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty, String[] animalFacts, Item[] menu){
      super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, animalFacts, menu);
    }

    public static GiftShop loadFromString(String fromFile){
        String[] fields = fromFile.split("\n");
        int numFacts = Integer.parseInt(fields[0]);
        String[] facts = new String[numFacts];
        for(int i = 0; i < numFacts; i++){
            facts[i] = fields[i+1];
        }
        String name = fields[numFacts + 1];
        char structureID = (fields[numFacts + 2]).charAt(0);
        int area = Integer.parseInt(fields[numFacts + 3]);
        int timeBetweenMaintenance = Integer.parseInt(fields[numFacts + 4]);
        int timeSinceLastMaintenance = Integer.parseInt(fields[numFacts + 5]);

        return GiftShop(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance);
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

    
}
