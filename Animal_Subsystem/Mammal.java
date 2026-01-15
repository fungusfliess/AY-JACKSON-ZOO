package Animal_Subsystem;

import Structure_Subsystem.*;

public abstract class Mammal extends Animal {

    protected boolean drinksMilk = true;

    // Description: constructor for mammals (template-based)
    public Mammal(Animal parent) {
        super(parent);
    }

    // Description: full constructor
    public Mammal(char habitatId, String name, String specie, String preferedInteraction, String gender,
                  int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, specie, preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);
    }

    // =========================
    // SHARED MAMMAL METHODS
    // =========================

    @Override
    public String toString() {
        return super.toString() +
               "Drinks Milk: " + drinksMilk + "\n";
    }

    // Description: creates a new mammal offspring
    public void reproduce(Habitat habitat, String name) {
        if (this.getGender().equalsIgnoreCase("Female") &&
            this.getHappiness() >= (LOW_STAT * MAX_STAT) &&
            this.getAge() >= this.getAdultAge() &&
            this.getHunger() <= (LOW_STAT * this.getMaxHunger())) {

            if (this.isSuitable(habitat)) {
                Mammal baby = createOffspring();
                baby.setName(name);
                if (Math.random() < 0.5) {
                    baby.setGender("Male");
                } else {
                    baby.setGender("Female");
                }
                habitat.addAnimal(baby);
            }
        }
    }

    // =========================
    // SPECIES HOOKS
    // =========================

    // Each species knows how to make its own baby
    protected abstract Mammal createOffspring();
}
