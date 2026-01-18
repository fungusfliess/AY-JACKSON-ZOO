public class Axolotl extends Amphibian {

    private static final LivingCondition AXOLOTL_CONDITION =
        new WaterCondition(16, 70, "Freshwater", 7.4, 150, 15, true, 0);
    private static final int WEIGHT_GAIN_PER_YEAR = 1;
    private static final int HUNGER_GAIN_PER_YEAR = 2;

    public Axolotl(Animal parent) {
        super(parent);
        setupStats();
    }

    public Axolotl(char habitatId, String name, String preferedInteraction, String gender,
                   int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Axolotl", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(20);
        setTypeFoods(new String[]{"Insects", "Small Fish"});
        setLifeExpectancy(15);
        setFlexibility(0.7);
        setLivingCondition(AXOLOTL_CONDITION);
        setTotalDailyInteractions(5);
        setAdultAge(3);
        setRequiredArea(8.0);

        setStage2Age(1);
        setStage3Age(2);
        setCanWalk(false);
        setCanBreathUnderwater(true);
    }

    // ===== GETTERS =====

    @Override
    public int getMaxHunger() {
        return 20;
    }

    @Override
    public String[] getTypeFoods() {
        String[] foods = {"Insects", "Small Fish"};
        return foods;
    }

    @Override
    public int getLifeExpectancy() {
        return 15;
    }

    @Override
    public double getFlexibility() {
        return 0.7;
    }
    
    @Override
    public LivingCondition getLivingCondition() {
        return AXOLOTL_CONDITION;
    }

    public int getTotalDailyInteractions() {
        return 5;
    }

    @Override
    public int getAdultAge() {
        return 3;
    }

    @Override
    public double getRequiredArea() {
        return 8.0;
    }

    // ===== METHODS =====

    @Override
    public void updateAge() {
        if (!isAdult()) {
            setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
            setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);
        }

        if (getAge() > getStage3Age()) {
            setCurrentStage(STAGE_ADULT);
            setCanWalk(true);
            setCanBreathUnderwater(false);

        } else if (getAge() > getStage2Age()) {
            setCurrentStage(STAGE_JUVENILE);
            setCanWalk(true);
            setCanBreathUnderwater(true);

        } else {
            setCurrentStage(STAGE_LARVA);
            setCanWalk(false);
            setCanBreathUnderwater(true);
        }

    }

    public String description() {
        return super.description() +
               "Axolotls are amphibians that remain in their larval stage and live fully underwater.";
    }
}
