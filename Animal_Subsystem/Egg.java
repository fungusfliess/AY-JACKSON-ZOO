package Animal_Subsystem;
import Structure_Subsystem.*;

public class Egg {
    private int hatchTime;
    private Animal parent;

    public Egg(Animal parent) {
        this.hatchTime = 0;
        this.parent = parent;
        
        if (parent.getSpecie().equalsIgnoreCase("Shark")) {
            this.hatchTime = 90; // Sharks hatch in 90 days
        } else if (parent.getSpecie().equalsIgnoreCase("Sunfish")) {
            this.hatchTime = 60; // Sunfish hatch in 60 days
        }
    }

    // GETTERS
    public int getHatchTime() {
        return hatchTime;
    }
    public Animal getParent() {
        return parent;
    }

    // SETTERS
    public void setHatchTime(int hatchTime) {
        this.hatchTime = hatchTime;
    }   
    public void setParent(Animal parent) {
        this.parent = parent;
    }


    // METHODS
    public void passTime(int daysPassed) {
        hatchTime -= daysPassed;
    }

    public boolean readyToHatch() {
        return hatchTime <= 0;
    }   

    public Animal hatch(Habitat habitat, String name) {
        if (readyToHatch()) {
            if (parent instanceof Fish && parent.isSuitable(habitat)) {
                Fish babyFish = new Fish(parent);
                babyFish.setName(name);
                if (Math.random() < 0.5) {
                    babyFish.setGender("Male"); // 50% chance male
                } else {
                    babyFish.setGender("Female"); // 50% chance female
                }
                habitat.addAnimal(babyFish);
                return babyFish;
            }
        }
        return null;
    }

}
