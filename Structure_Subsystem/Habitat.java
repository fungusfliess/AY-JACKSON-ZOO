import Animal_Subsystem.Animal;
import Animal_Subsystem.LivingCondition;
import Land_Subsystem.Land;
import Person_Subsystem.Visitor;

public abstract class Habitat extends Structure{
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

    public double getSpaceLeft(){
        return spaceLeft; 
    }
    
    public LivingCondition getClimate(){
        return climate;
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

    public boolean removeAnimal(Animal animal){
        int idx = findAnimalIdx(animal);
        Animal temp;
        if(idx != -1){
            for (int i = idx; i < numAnimals - 1; i++) {
            animals[i] = animals[i + 1];
            }
            animal.leaveHabitat();
            animals[numAnimals - 1] = null;
            numAnimals--;   
            return true;
        }
        else{
            return false;
        }
    }

    public int findAnimalIdx(Animal animal){
        int missing = - 1; 
        for(int i = 0; i < numAnimals; i++){
            if(animals[i] == animal){
                return i;
            }
        }
        return missing;
    }


    public abstract String saveToString();

}
