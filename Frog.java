public class Frog extends Amphibian {

    private static final LivingCondition FROG_CONDITION =
        new WaterCondition(20, 85, "Wetland", 6.8, 60, 18, true, 0.1);
    private static final int WEIGHT_GAIN_PER_YEAR = 2;
    private static final int HUNGER_GAIN_PER_YEAR = 3;


    public Frog(Animal parent) {
        super(parent);
        setupStats();
    }

    public Frog(char habitatId, String name, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Frog", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(50);
        setTypeFoods(new String[]{"Flies", "Worms"});
        setLifeExpectancy(5);
        setFlexibility(0.4);
        setLivingCondition(FROG_CONDITION);
        setTotalDailyInteractions(3);
        setAdultAge(5);
        setRequiredArea(10.0);

        stage2Age = 2;
        stage3Age = 4;
        canWalk = false;
        canBreathUnderwater = true;
    }

    // ===== GETTERS =====
    @Override
    public int getMaxHunger() {
        return 50;
    }

    @Override
    public String[] getTypeFoods() {
        String[] foods = {"Flies", "Worms"};
        return foods;
    }

    @Override
    public int getLifeExpectancy() {
        return 5;
    }

    @Override
    public double getFlexibility() {
        return 0.4;
    }

    @Override
    public LivingCondition getLivingCondition() {
        return FROG_CONDITION;
    }

    @Override
    public int getTotalDailyInteractions() {
        return 3;
    }

    @Override
    public int getAdultAge() {
        return 5;
    }

    @Override
    public double getRequiredArea() {
        return 10.0;
    }

    // ===== METHODS =====
    @Override
    public void updateAge() {
        setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
        setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);

        if (getAge() > stage3Age) {
            currentStage = STAGE_ADULT;
            canWalk = true;
            canBreathUnderwater = false;
        } else if (getAge() > stage2Age) {
            currentStage = STAGE_JUVENILE;
            canWalk = true;
            canBreathUnderwater = true;
        } else {
            currentStage = STAGE_LARVA;
            canWalk = false;
            canBreathUnderwater = true;
        }
    }

    @Override
    public String description() {
        return super.description() +
               "Frogs are amphibians that live in wetlands and undergo metamorphosis from larva to adult.";
    }
}
