/*
   File Name: Fish.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Fish is an abstract class representing fish in the zoo.
                Fish live in aquatic environments and can lay eggs.
*/
public abstract class Fish extends Animal {

    private int amountEggs;

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
    // GETTER (ACCESSOR)
    // =========================

    public int getAmountEggs() {
        return amountEggs;
    }

    // =========================
    // SETTER (MUTATOR)
    // =========================

    public void setAmountEggs(int amountEggs) {
        this.amountEggs = amountEggs;
    }

    // =========================
    // SHARED FISH METHODS
    // =========================

    /* @description: Creates multiple eggs if the fish can reproduce
       @return an array of Egg objects equal to amountEggs, null if reproduction fails
    */
    public Egg[] reproduce() {
        if (canReproduce()) {

            Egg[] eggs = new Egg[amountEggs];
            // Create each egg individually
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
