/*
   File Name: Amphibian.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Amphibian is an abstract class representing amphibians in the zoo.
                Amphibians have life stages (Larva, Juvenile, Adult) and can walk
                and breathe underwater depending on their stage.
*/
public abstract class Amphibian extends Animal {

    //FIELDS
    private String currentStage;
    private int stage2Age;
    private int stage3Age;
    private boolean canWalk;
    private boolean canBreathUnderwater;

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
    // GETTERS (ACCESSORS)
    // =========================

    public String getCurrentStage() {
        return currentStage;
    }

    public int getStage2Age() {
        return stage2Age;
    }

    public int getStage3Age() {
        return stage3Age;
    }

    public boolean canWalk() {
        return canWalk;
    }

    public boolean canBreathUnderwater() {
        return canBreathUnderwater;
    }

    // =========================
    // SETTERS (MUTATORS)
    // =========================

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public void setStage2Age(int stage2Age) {
        this.stage2Age = stage2Age;
    }

    public void setStage3Age(int stage3Age) {
        this.stage3Age = stage3Age;
    }

    public void setCanWalk(boolean canWalk) {
        this.canWalk = canWalk;
    }

    public void setCanBreathUnderwater(boolean canBreathUnderwater) {
        this.canBreathUnderwater = canBreathUnderwater;
    }

    // =========================
    // SHARED AMPHIBIAN METHODS
    // =========================

    /* @description: Creates an egg if the amphibian can reproduce
       @return an Egg object if reproduction is successful, null otherwise
    */
    public Egg reproduce() {
        if (canReproduce()) {

            Egg egg = new Egg(this);
            return egg;
        }
        return null;
    }

    /* @description: Returns a string representation of the Amphibian object for saving to a file
       @return pipe-delimited string with amphibian data including current stage
    */
     
    @Override
    public String saveToString() {
        return super.saveToString() + "|" + currentStage;
    }

    /* description: returns a string representation of the Amphibian object, including its stages and abilities 
    */

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
