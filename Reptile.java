/*
   File Name: Reptile.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Reptile is an abstract class representing reptiles in the zoo.
                Reptiles shed their skin periodically.
*/
public abstract class Reptile extends Animal {

    private int timeToShed;

    // Description: constructor for reptiles (template-based)
    public Reptile(Animal parent) {
        super(parent);
    }

    // Description: full constructor
    public Reptile(char habitatId, String name, String specie, String preferedInteraction, String gender,
                   int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, specie, preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);
    }

    // Accessor
    public int getTimeToShed() {
        return timeToShed;
    }
    // Mutator
    public void setTimeToShed(int timeToShed) {
        this.timeToShed = timeToShed;
    }

    // =========================
    // SHARED REPTILE METHODS
    // =========================

    /* @description: Reptiles reproduce via eggs
       @return an Egg object if reproduction is successful, null otherwise
    */
    public Egg reproduce() {
        if (canReproduce()) {

            return new Egg(this);
        }
        return null;
    }

    /* @description: Allows the reptile to shed its skin if ready, fully restoring cleanliness
    */
    public void shedSkin() {
        if (timeToShed <= 0) {
            System.out.println(getName() + " the " + getSpecie() + " has shed its skin!");
            timeToShed = getInitialTimeToShed();
            setCleanliness(MAX_STAT); // Shedding fully cleans the reptile
        } else {
            System.out.println(getName() + " the " + getSpecie() + " is not ready to shed its skin yet.");
        }
    }

    @Override
    public String saveToString() {
        return super.saveToString() + "|" + timeToShed;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nTime to Shed: " + timeToShed + " days";
    }

    // =========================
    // SPECIES HOOKS
    // =========================

    public abstract int getInitialTimeToShed();
}
