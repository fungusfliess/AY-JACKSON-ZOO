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

    //ACCESSORS

    public int getNumAnimals(){
        return numAnimals;
    }

    public int getMaxAnimals(){
        return maxAnimals;
    }

    //MUTATORS
    public void setNumAnimals(int num){
        numAnimals = num;
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

    public boolean enoughSpace(int area){
        return spaceLeft - area >= 0;
    }

    public void modifySpaceLeft(double amount){
        spaceLeft += amount;
    }

    public void displayHabitatAnimalInfo(){
        System.out.println("Habitat " + getName() + ":\n" + climate);
        for(int i = 0; i < numAnimals; i++){
            System.out.println(animals[i]); 
        }
    }

    public abstract String saveToString();

}
