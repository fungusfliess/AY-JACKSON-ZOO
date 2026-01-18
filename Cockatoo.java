public class Cockatoo extends Bird {

    private static final LivingCondition COCKATOO_CONDITION =
        new LandCondition(24, 65, "Tropical Forest", 50, 5, true, 80, 15);

    private static final int WEIGHT_GAIN_PER_YEAR = 1;
    private static final int HUNGER_GAIN_PER_YEAR = 4;

    public Cockatoo(Animal parent) {
        super(parent);
        setupStats();
    }

    public Cockatoo(char habitatId, String name, String preferedInteraction, String gender,
                    int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Cockatoo", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(50);
        setTypeFoods(new String[]{"Seeds", "Fruits"});
        setLifeExpectancy(10);
        setFlexibility(0.7);
        setLivingCondition(COCKATOO_CONDITION);
        setTotalDailyInteractions(5);
        setAdultAge(3);
        setRequiredArea(15.0);
        setFlyingAge(2);
        setCanFly(false);
    }

    // ===== GETTERS =====

    @Override
    public int getMaxHunger() {
        return 50;
    }

    @Override
    public String[] getTypeFoods() {
        String[] foods = {"Seeds", "Fruits"};
        return foods;
    }

    @Override
    public int getLifeExpectancy() {
        return 10;
    }

    @Override
    public double getFlexibility() {
        return 0.7;
    }

    @Override
    public LivingCondition getLivingCondition() {
        return COCKATOO_CONDITION;
    }

    @Override
    public int getTotalDailyInteractions() {
        return 5;
    }

    public int getAdultAge() {
        return 3;
    }

    @Override
    public double getRequiredArea() {
        return 15.0;
    }

    // ===== METHODS =====
    
    @Override
    public void updateAge() {
        setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
        setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);

        if (getAge() >= getFlyingAge()) {
            setCanFly(true);
        }
    }

    @Override
    public String description() {
        return super.description() +
               "Cockatoos are social birds that live in tropical forests and require frequent interaction.";
    }
}
