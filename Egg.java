public class Egg {
    private String specie;
    private int hatchTime;
    private Animal animalClass;

    public Egg(String specie, Animal animalClass) {
        this.specie = specie;
        this.hatchTime = 0;
        this.animalClass = animalClass;
        
        if (specie.equalsIgnoreCase("Shark")) {
            this.hatchTime = 90; // Sharks hatch in 90 days
        } else if (specie.equalsIgnoreCase("Sunfish")) {
            this.hatchTime = 60; // Sunfish hatch in 60 days
        }
    }

    // GETTERS
    public String getSpecie() {
        return specie; 
    }
    public int getHatchTime() {
        return hatchTime;
    }
    public Animal getAnimalClass() {
        return animalClass;
    }

    // SETTERS
    public void setSpecie(String specie) {
        this.specie = specie;
    }
    public void setHatchTime(int hatchTime) {
        this.hatchTime = hatchTime;
    }   
    public void setAnimalClass(Animal animalClass) {
        this.animalClass = animalClass;
    }

    // METHODS
    public void passTime(int daysPassed) {
        hatchTime -= daysPassed;
    }

    public boolean readyToHatch() {
        return hatchTime <= 0;
    }   

    public Animal hatch() {
        if (readyToHatch()) {
            if (animalClass instanceof Fish) {
                new Fish
            }
            return animalClass;
        }
        return null;
    }

}
