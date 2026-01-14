import Animal_Subsystem.Animal;
import Animal_Subsystem.LivingCondition;
import Land_Subsystem.Land;
import Person_Subsystem.Visitor;

public class Habitat extends Structure{
    private double spaceLeft;
    private int numAnimals;
    private int maxAnimals;
    private LivingCondition climate;
    private Animal[] animals;

    //needs to be fixed after onProperty
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

    public boolean suitableHabitat(Animal animal){
        
    }


}
