import java.io.*;
public class GiftShop extends Shop{
    public final static int LEARNING_PER_PURCHASE = 12;
    public final static double MAINTENANCE_COST_PER_AREA = 10;

    public GiftShop(String name, char structureID, int area, int timeBetweenMaintenance, int daysSinceLastMaintenance, Land onProperty, String[] animalFacts, Item[] menu){
      super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, animalFacts, menu);
    }

    public static GiftShop loadFromString(String fromFile) {
        String[] fields = fromFile.split("\n");
        int index = 0;

        int numItems = Integer.parseInt(fields[index++]);
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

        String name = fields[index++];
        char structureID = fields[index++].charAt(0);
        int area = Integer.parseInt(fields[index++]);
        int timeBetweenMaintenance = Integer.parseInt(fields[index++]);
        int daysSinceLastMaintenance = Integer.parseInt(fields[index++]);

        return new GiftShop(
            name,
            structureID,
            area,
            timeBetweenMaintenance,
            daysSinceLastMaintenance,
            menu,
            facts
        );
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
