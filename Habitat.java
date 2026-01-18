/*
   File Name: Habitat.java
   Name: Arianna Liu
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Habitat represents a living space for animals within the zoo. 
*/

public abstract class Habitat extends Structure{

    //FIELDS
    private double spaceLeft;
    private int numAnimals;
    private int maxAnimals;
    private LivingCondition climate;
    private Animal[] animals;

    //CONSTRUCTOR
    /* @description: declares and initializes Habitat object
        @param name                the name of the habitat
        @param structureID         unique ID for the habitat  
        @param area                the area of the habitat
        @param timeBetweenMaintenance  the time between maintenance cycles 
        @param daysSinceLastMaintenance  the number of days since last maintenance
        @param onProperty          the land on which this habitat is located
        @param maxAnimals          the maximum number of animals the habitat can hold
        @param climate             the living condition of the habitat
    */
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

    public Animal[] getAnimals(){
        return animals;
    }

    //MUTATORS

    public void setNumAnimals(int num){
        numAnimals = num;
    }

    //OTHER METHODS

    /* 
        @description: adds an animal to the habitat
        @param animal: the animal to add
        @return: true if the animal was added, false otherwise
    */
    public abstract double calculateMaintenanceCost();

    /* 
        @description: adds an animal to the habitat
        @param animal: the animal to add
        @return: true if the animal was added, false otherwise
    */
    public abstract boolean addAnimal(Animal animal);

    /* 
        @description: updates the visitor's learning history with an animal fact
        @param visitor: the visitor to update
    */
    public abstract void updateVisitorLearning(Visitor visitor);

    /* 
        @description: sets structure as demolished if possible
        @return: returns true if all animals have been removed, false otherwise
    */
    public boolean demolish(){
        if (numAnimals == 0 ){
            this.setDemolished(true);
            return true;
        }
        System.out.println("Demolition is not possible! There are still " + numAnimals + " animals remaining. ");
        return false;
    }

    /*
        @description: checks if there is enough space for an animal
        @param area: the area required for the animal
        @return: true if there is enough space, false otherwise
    */
    public boolean enoughSpace(double area){
        return spaceLeft - area >= 0;
    }

    /*
        @description: modifies the space left in the habitat
        @param amount: the amount to modify the space left by
    */
    public void modifySpaceLeft(double amount){
        spaceLeft += amount;
    }

    /*
        @description: displays information about the habitat and its animals
    */
    public void displayHabitatAnimalInfo(){
        System.out.println("Habitat " + getName() + ":\n" + climate);
        for(int i = 0; i < numAnimals; i++){
            System.out.println(animals[i]); 
        }
    }

    /* 
        @description: removes an animal from the habitat
        @param animal: the animal to remove
        @return: true if the animal was removed, false otherwise
    */
    public boolean removeAnimal(Animal animal){
        int idx = findAnimalIdx(animal);
        Animal temp;

        //removes animal from array and shifts remaining animals down
        
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

            //returns false if animal not found

            return false;
        }
    }

    /* 
        @description: finds the index of an animal in the habitat
        @param animal: the animal to find
        @return: the index of the animal, or -1 if not found
    */
    public int findAnimalIdx(Animal animal){
        int missing = - 1; 
        for(int i = 0; i < numAnimals; i++){
            if(animals[i] == animal){
                return i;
            }
        }
        return missing;
    }

    /* 
        @description: saves the habitat to a string
        @return: the habitat as a string
    */
    public abstract String saveToString();

}
