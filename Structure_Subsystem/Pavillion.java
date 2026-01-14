import Animal_Subsystem.Animal;
import Animal_Subsystem.LivingCondition;

public class Pavillion extends Habitat{
    public static int LEARNING_PER_DISPLAY = 35;
    public static int MAINTENANCE_RATE_PER_UNIT = 30;

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

    public static Pavillion loadFromString(String fromFile, Land onProperty) {
        String[] fields = fromFile.split("\n");
        int index = 0;

        String name = fields[index];
        char structureID = fields[index++].charAt(0);
        int area = Integer.parseInt(fields[index++]);
        int timeBetweenMaintenance = Integer.parseInt(fields[index++]);
        int daysSinceLastMaintenance = Integer.parseInt(fields[index++]);
        int maxAnimals = Integer.parseInt(fields[index++]);
        // fix the climate crisis later
        return new Pavillion(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty, maxAnimals);
    }
    
    public double calculateMaintenanceCost(){
        return this.getArea() * MAINTENANCE_RATE_PER_UNIT;
    }

    public boolean addAnimal(Animal animal){
        if(animal.isSuitable(this) && getNumAnimals() > getMaxAnimals()){
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

