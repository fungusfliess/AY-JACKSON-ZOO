import Animal_Subsystem.Animal;
import Animal_Subsystem.LivingCondition;
import Animal_Subsystem.WaterCondition;

public class Enclosure extends Habitat{
    public static int LEARNING_PER_DISPLAY = 30;
    public static int MAINTENANCE_RATE_PER_UNIT = 26;
    
    private String species; 

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

    public static Enclosure loadFromString(String fromFile, Land onProperty) {
        String[] fields = fromFile.split("\n");
        int index = 0;
        int spaceLeft = Integer.parseInt(fields[index]);
        String species = fields[index++];
        
        String climateType = fields[index++];
        double temp = Double.parseDouble(fields[index++]);
        double humidity = Double.parseDouble(fields[index++]);
        String region = fields[index++];

        LivingCondition climate;
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

        String name = fields[index++];
        char structureID = fields[index++].charAt(0);
        int area = Integer.parseInt(fields[index++]);
        int timeBetweenMaintenance = Integer.parseInt(fields[index++]);
        int daysSinceLastMaintenance = Integer.parseInt(fields[index++]);
        int maxAnimals = Integer.parseInt(fields[index++]);
        // fix the climate crisis later
        return new Enclosure(species, name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, maxAnimals, climate);
    }
    
    public double calculateMaintenanceCost(){
        return this.getArea() * MAINTENANCE_RATE_PER_UNIT;
    }

    public boolean addAnimal(Animal animal){
        if(animal.isSuitable(this) && getNumAnimals() > getMaxAnimals() && (animal.getSpecie()).equals(species)){
            modifySpaceLeft(-(animal.getRequiredArea()));
            setNumAnimals(getNumAnimals() + 1);
        }
    }
    
    public void updateVisitorLearning(Visitor toUpdate){
        toUpdate.addLearningLevel(LEARNING_PER_DISPLAY);
        displayHabitatAnimalInfo();
    }

    public String saveToString(){
        //Do this after talking to jerry about files
    }
}
