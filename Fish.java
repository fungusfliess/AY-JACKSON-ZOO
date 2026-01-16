

import Structure_Subsystem.*;

public abstract class Fish extends Animal {

    protected int amountEggs;

    // Description: constructor for fish (template-based)
    public Fish(Animal parent) {
        super(parent);
    }

    // Description: full constructor
    public Fish(char habitatId, String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, specie, preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);
        
    }

    // =========================
    // SHARED FISH METHODS
    // =========================

    public Egg[] reproduce() {
        if (this.getGender().equalsIgnoreCase("Female") &&
            this.getHappiness() >= (LOW_STAT * MAX_STAT) &&
            this.getAge() >= this.getAdultAge() &&
            this.getHunger() <= (LOW_STAT * this.getMaxHunger())) {

            Egg[] eggs = new Egg[amountEggs];
            for (int i = 0; i < amountEggs; i++) {
                eggs[i] = new Egg(this);
            }
            return eggs;
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString() +
               "Amount of Eggs: " + amountEggs + "\n";
    }
}
