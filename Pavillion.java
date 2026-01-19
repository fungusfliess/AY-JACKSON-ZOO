/*
   File Name: Pavillion.java
   Name: Arianna Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Pavillion represents a pavillion habitat within the zoo.
*/

public class Pavillion extends Habitat{

    //CONSTANTS
    public static int LEARNING_PER_DISPLAY = 35;
    public static int MAINTENANCE_RATE_PER_UNIT = 30;

    //CONSTRUCTOR
    /*
     @description: declares and initializes Pavillion object
     @param name                the name of the pavillion
     @param structureID         unique ID for the pavillion  
     @param area                the area of the pavillion
     @param timeBetweenMaintenance  the time between maintenance cycles 
     @param daysSinceLastMaintenance  the number of days since last maintenance
     @param onProperty          the land on which this pavillion is located
     @param maxAnimals          the maximum number of animals the pavillion can hold
     @param climate             the living condition of the pavillion

    */
    public Pavillion(String name, 
        char structureID, 
        int area, 
        int timeBetweenMaintenance, 
        int daysSinceLastMaintenance,
        Land onProperty,
        int maxAnimals,
        LivingCondition climate){
            super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, maxAnimals, climate);
    }

    /* 
     * @description: loads a pavillion from a string
     * @param fromFile: the string to load the pavillion from
     * @param onProperty: the land on which this pavillion is located
     * @return: the loaded pavillion
    */
    public static Pavillion loadFromString(String fromFile, Land onProperty) {
        String[] fields = fromFile.split("\n");
        int index = 0;
        
        //initializes subclass-specific fields

        String climateType = fields[index++];
        double temp = Double.parseDouble(fields[index++]);
        double humidity = Double.parseDouble(fields[index++]);
        String region = fields[index++];

        LivingCondition climate;

        //checks which subclass of LivingCondition to create

        if(climateType.equals("LAND")){
            double soilCompaction = Double.parseDouble(fields[index++]);
            double landSlope = Double.parseDouble(fields[index++]);
            double vegetationDensity = Double.parseDouble(fields[index++]);
            boolean hasWaterSource = Boolean.parseBoolean(fields[index++]);
            int amountStructures = Integer.parseInt(fields[index++]);

            climate = new LandCondition(temp, humidity, region, soilCompaction, landSlope, hasWaterSource, vegetationDensity, amountStructures);
        }else{
            double waterAcidity = Double.parseDouble(fields[index++]);
            double waterHardness = Double.parseDouble(fields[index++]);
            double waterTemp = Double.parseDouble(fields[index++]);
            boolean hasLand = Boolean.parseBoolean(fields[index++]);
            double waterSalinity = Double.parseDouble(fields[index++]);

            climate = new WaterCondition(waterTemp, humidity, region, waterAcidity, waterHardness, temp, hasLand, waterSalinity);

        }

        //initializes superclass fields

        String name = fields[index++];
        char structureID = fields[index++].charAt(0);
        int area = Integer.parseInt(fields[index++]);
        int timeBetweenMaintenance = Integer.parseInt(fields[index++]);
        int daysSinceLastMaintenance = Integer.parseInt(fields[index++]);
        int maxAnimals = Integer.parseInt(fields[index++]);

        //returns new Pavillion object

        return new Pavillion(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, maxAnimals, climate);
    }
    
    /* 
     @description: calculates the maintenance cost of the pavillion
     @return: the maintenance cost of the pavillion
    */
    public double calculateMaintenanceCost(){
        return this.getArea() * MAINTENANCE_RATE_PER_UNIT;
    }

    /* 
     @description: adds an animal to the pavillion
     @param animal: the animal to add
     @return: true if the animal was added, false otherwise
    */
    public boolean addAnimal(Animal animal){

        //checks if animal is suitable for pavillion and if there is space
        if(animal.isSuitable(this) && getNumAnimals() < getMaxAnimals()){

            // adds animal to pavillion, updates space and number of animals
            modifySpaceLeft(-(animal.getRequiredArea()));
            (getAnimals())[getNumAnimals()] = animal;
            setNumAnimals(getNumAnimals() + 1);
            return true;
        }else{
            return false;
        }
    }
    
    /* 
     @description: updates the visitor's learning history with pavillion animal info
     @param toUpdate: the visitor to update
    */
    public void updateVisitorLearning(Visitor toUpdate){
        toUpdate.addLearningLevel(LEARNING_PER_DISPLAY);
        displayHabitatAnimalInfo();
    }

    /* 
        @description: saves the pavillion to a string
        @return: the pavillion as a string
    */
    public String saveToString(){

        return "Pavillion\n" 
            + getClimateString() 
            + getName() + "\n"
            + getStructureID() + "\n"
            + getArea() + "\n"
            + getTimeBetweenMaintenance() + "\n"
            + getDaysSinceLastMaintenance() + "\n"
            + getMaxAnimals() + "\n"; 
    }

    public String toString(){
        return "Pavillion Name: " + getName() + "\nStructure ID: " + getStructureID() + "\nArea: " + getArea() + "\nMaintenance Cost: " + calculateMaintenanceCost() + "\nTime Between Maintenance: " + getTimeBetweenMaintenance() + "\nDays Since Last Maintenance: " + getDaysSinceLastMaintenance();
    }
}

