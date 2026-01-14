import Animal_Subsystem.Animal;
import Animal_Subsystem.LivingCondition;
import Land_Subsystem.Land;

public class Habitat extends Structure{
    private double spaceLeft;
    private int numAnimals;
    private int maxAnimals;
    private LivingCondition climate;
    private Animal[] animals;

    public Habitat(String name, 
    char structureID, 
    int area, 
    int timeBetweenMaintenance, 
    int daysSinceLastMaintenance,
    Land onProperty,
    int maxAnimals,
    LivingCondition climate){
        super(name, structureID, area, timeBetweenMaintenance, daysSinceLastMaintenance, onProperty);
        this.maxAnimals = maxAnimals;
        this.climate = climate;
        animals = new Animal[maxAnimals];
        spaceLeft = area;    
        numAnimals = 0; 
    }

    public abstract double calculateMaintenanceCost();

    public abstract boolean addAnimal(Animal animal);

    public abstract void updateVisitorLearning(Visitor visitor);

    public boolean demolish(){
        if (numAnimals == 0 ){
            System.out.println("Demolition is not possible! There are still " + numAnimals + " animals remaining. ");
            this.setDemolished(true);
            return true;
        }return false;
    }

    public boolean modifySpaceLeft(double amount){
        if (spaceLeft - amount > 0){
            spaceLeft += amount;
            return true;
        }return false;
    }

    public void displayHabitatAnimalInfo(){
        System.out.println("Habitat " + getName() + ":\n" + climate);
        for(int i = 0; i < numAnimals; i++){
            System.out.println(animals[i]); 
        }
    }

    public abstract String saveToString();

}
