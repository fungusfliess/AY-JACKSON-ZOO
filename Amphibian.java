public abstract class Amphibian extends Animal {

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

    public Egg reproduce() {
        if (canReproduce()) {

            Egg egg = new Egg(this);
            return egg;
        }
        return null;
    }

     
    @Override
    public String saveToString() {
        return super.saveToString() + "|" + currentStage;
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
