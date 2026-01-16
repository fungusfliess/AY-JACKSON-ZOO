

import Structure_Subsystem.*;

public abstract class Amphibian extends Animal {

    protected String currentStage;
    protected int stage2Age;
    protected int stage3Age;
    protected boolean canWalk;
    protected boolean canBreathUnderwater;

    public static final String STAGE_LARVA = "Larva";
    public static final String STAGE_JUVENILE = "Juvenile";
    public static final String STAGE_ADULT = "Adult";

    // Description: constructor for amphibians (template-based)
    public Amphibian(Animal parent) {
        super(parent);
    }

    // Description: full constructor
    public Amphibian(char habitatId, String name, String specie, String preferedInteraction, String gender,
                     int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, specie, preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);
    }

    // =========================
    // SHARED AMPHIBIAN METHODS
    // =========================

    public Egg reproduce() {
        if (this.getGender().equalsIgnoreCase("Female") &&
            this.getHappiness() >= (LOW_STAT * MAX_STAT) &&
            this.getAge() >= this.getAdultAge() &&
            this.getHunger() <= (LOW_STAT * this.getMaxHunger())) {

            Egg egg = new Egg(this);
            return egg;
        }
        return null;
    }
    
    @Override
    public String saveToString() {
        return super.saveToString() + "\n" + currentStage;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nStage 2 Age: " + stage2Age +
               "\nStage 3 Age: " + stage3Age +
               "\nCurrent Stage: " + currentStage +
               "\nCan Walk: " + canWalk +
               "\nCan Breath Underwater: " + canBreathUnderwater;
    }
}
