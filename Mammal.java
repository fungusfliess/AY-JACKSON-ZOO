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
    public Animal reproduce(String name) {
        if (canReproduce()) {
                
            Mammal baby = createOffspring();
            baby.setName(name);
            if (Math.random() < 0.5) {
                baby.setGender("Male");
            } else {
                baby.setGender("Female");
            }
            System.out.println(name + " has been born!");
            return baby;
            
        } 
        System.out.println("reproduction failed");
        return null;
    }

    // =========================
    // SPECIES HOOKS
    // =========================

    // Each species knows how to make its own baby
    public abstract Mammal createOffspring();
}
