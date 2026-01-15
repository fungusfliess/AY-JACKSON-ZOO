/*
   File Name: Enclosure.java
   Name: Arianna Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Enclosure represents a living space for animals within the zoo.
*/
import Animal_Subsystem.*;
import Land_Subsystem.Land;
import Person_Subsystem.Visitor;

public class Enclosure extends Habitat{

    //CONSTANTS
    public static int LEARNING_PER_DISPLAY = 30;
    public static int MAINTENANCE_RATE_PER_UNIT = 26;

    //FIELDS  
    private String species; 

    //CONSTRUCTOR
    /* 
     @description: declares and initializes Enclosure object
     @param species             the species of animal the enclosure is for
     @param name                the name of the enclosure
     @param structureID         unique ID for the enclosure  
     @param area                the area of the enclosure
     @param timeBetweenMaintenance  the time between maintenance cycles 
     @param daysSinceLastMaintenance  the number of days since last maintenance
     @param onProperty          the land on which this enclosure is located
     @param maxAnimals          the maximum number of animals the enclosure can hold
     @param climate             the living condition of the enclosure
    */

    public Enclosure(String species,
        String name, 
        char structureID, 
        int area, 
        int timeBetweenMaintenance, 
        int daysSinceLastMaintenance,
        Land onProperty,
        int maxAnimals,
        LivingCondition climate){
            super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, maxAnimals, climate);
            this.species = species;
    }

    /* 
     * @description: loads an enclosure from a string
     * @param fromFile: the string to load the enclosure from
     * @param onProperty: the land on which this enclosure is located
     * @return: the loaded enclosure
    */
    public static Enclosure loadFromString(String fromFile, Land onProperty) {
        String[] fields = fromFile.split("\n");
        int index = 0;

        //initializes subclass-specific fields
        
        String species = fields[index];
        
        String climateType = fields[index++];
        double temp = Double.parseDouble(fields[index++]);
        double humidity = Double.parseDouble(fields[index++]);
        String region = fields[index++];

        LivingCondition climate;

        //checks which subclass of LivingCondition to create
        if(climateType.equals("LAND")){
            double soilCompaction = Double.parseDouble(fields[index++]);
            double landSlope = Double.parseDouble(fields[index++]);
            boolean hasWaterSource = Boolean.parseBoolean(fields[index++]);
            double vegetationDensity = Double.parseDouble(fields[index++]);
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

        //returns new Enclosure object

        return new Enclosure(species, name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, maxAnimals, climate);
    }

    /* 
     * @description: calculates the maintenance cost of the enclosure
     * @return: the maintenance cost of the enclosure
    */
    public double calculateMaintenanceCost(){
        return this.getArea() * MAINTENANCE_RATE_PER_UNIT;
    }

    /* 
     @description: adds an animal to the enclosure
     @param animal: the animal to add
     @return: true if the animal was added, false otherwise
    */
    public boolean addAnimal(Animal animal){
        
        // checks if animal is suitable for enclosure and if there is space
        if(animal.isSuitable((this)) && getNumAnimals() > getMaxAnimals() && (animal.getSpecie()).equals(species)){
            
            // adds animal to enclosure, updates space and number of animals
            modifySpaceLeft(-(animal.getRequiredArea()));
            animals[getNumAnimals()] = animal;
            setNumAnimals(getNumAnimals() + 1);
            return true;
        }else{
            return false;
        }
    }
    
    /* 
     @description: updates the visitor's learning history with animal info from the enclosure
     @param toUpdate: the visitor to update
    */
    public void updateVisitorLearning(Visitor toUpdate){
        toUpdate.addLearningLevel(LEARNING_PER_DISPLAY);
        displayHabitatAnimalInfo();
    }

    /* 
        @description: saves the enclosure to a string
        @return: the enclosure as a string
    */
    public String saveToString(){

        return "Enclosure\n"
            + species
            + getClimate() + "\n"
            + getName() + "\n"
            + getStructureID() + "\n"
            + getArea() + "\n"
            + getTimeBetweenMaintenance() + "\n"
            + getDaysSinceLastMaintenance() + "\n"
            + getMaxAnimals() + "\n";
    }
}
