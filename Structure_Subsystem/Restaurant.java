import Person_Subsystem.Visitor;

public class Restaurant extends Shop{
    public final static int LEARNING_PER_PURCHASE = 20;
    public final static double MAINTENANCE_RATE_PER_UNIT = 12;
    public final static String EATING_MESSAGE = "Meal Eaten: yummy!!";

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

    public static Restaurant loadFromString(String fromFile, Land onProperty){
        String[] fields = fromFile.split("\n");
        int index = 0;

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

        String name = fields[index++];
        char structureID = fields[index++].charAt(0);
        int area = Integer.parseInt(fields[index++]);
        int timeBetweenMaintenance = Integer.parseInt(fields[index++]);
        int daysSinceLastMaintenance = Integer.parseInt(fields[index++]);

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

    public double calculateMaintenanceCost(){
        return this.getArea() * MAINTENANCE_RATE_PER_UNIT;
    }
    public void updateVisitorLearning(Visitor toUpdate){
        toUpdate.addLearningLevel(LEARNING_PER_PURCHASE);
        toUpdate.addLearningFact((this.getAnimalFacts()).pickRandomFact());
        displayAnimalFact(toUpdate);
    }
   
    public boolean buy(Visitor buyer, Item product){
        if(buyer.canAffordItem(product)){}
            buyer.recordPurchase(product.getPrice());
            buyer.addItem(product);
            updateVisitorLearning(buyer);
            System.out.println(EATING_MESSAGE + "\n");
            return true;
        } else {
           return false;
        }        
    }

    public String saveToString(){
        String saveToString;

        saveToString = "Restaurant\n" ;

        saveToString = menu.length + "\n";
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