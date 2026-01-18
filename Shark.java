public class Shark extends Fish {

    private static final LivingCondition SHARK_CONDITION =
        new WaterCondition(18, 75, "Ocean", 8.1, 180, 20, false, 35);

    private static final int WEIGHT_GAIN_PER_YEAR = 10;
    private static final int HUNGER_GAIN_PER_YEAR = 6;

    public Shark(Animal parent) {
        super(parent);
        setupStats();
    }

    public Shark(char habitatId, String name, String preferedInteraction, String gender,
                 int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Shark", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(150);
        setTypeFoods(new String[]{"Meat", "Fish"});
        setLifeExpectancy(30);
        setFlexibility(0.4);
        setLivingCondition(SHARK_CONDITION);
        setTotalDailyInteractions(3);
        setAdultAge(5);
        setRequiredArea(150.0);
        setAmountEggs(2);
    }

    // ===== GETTERS =====

    @Override
    public int getMaxHunger() {
        return 150;
    }
    @Override
    public String[] getTypeFoods() {
        String[] foods = {"Meat", "Fish"};
        return foods;
    }
    @Override
    public int getLifeExpectancy() {
        return 30;
    }
    @Override
    public double getFlexibility() {
        return 0.4;
    }
    @Override
    public LivingCondition getLivingCondition() {
        return SHARK_CONDITION;
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
        return 150.0;
    }

    // ===== METHODS =====

    @Override
    public void updateAge() {
        setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
        setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);
    }

    @Override
    public String description() {
        return super.description() +
               "Sharks are large predatory fish that live in oceans and require vast open water habitats.";
    }
}
