package Animal_Subsystem;

import Structure_Subsystem.*;

public class Snake extends Reptile {

    private static final LivingCondition SNAKE_CONDITION =
        new LandCondition(26, 50, "Grassland", 40, 6, true, 45, 6);

    private static final int WEIGHT_GAIN_PER_YEAR = 4;
    private static final int HUNGER_GAIN_PER_YEAR = 2;

    public Snake(Animal parent) {
        super(parent);
        setupStats();
    }

    public Snake(char habitatId, String name, String preferedInteraction, String gender,
                 int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Snake", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(50);
        setTypeFoods(new String[]{"Rats", "Birds"});
        setLifeExpectancy(20);
        setFlexibility(0.7);
        setLivingCondition(SNAKE_CONDITION);
        setTotalDailyInteractions(5);
        setAdultAge(3);
        setRequiredArea(20.0);
        timeToShed = 15;
    }

    // ===== ABSTRACT GETTERS =====

    public int getMaxHunger() {
        return 50;
    }

    public String[] getTypeFoods() {
        String[] foods = {"Rats", "Birds"};
        return foods;
    }

    public int getLifeExpectancy() {
        return 20;
    }

    public double getFlexibility() {
        return 0.7;
    }

    public LivingCondition getLivingCondition() {
        return SNAKE_CONDITION;
    }

    public int getTotalDailyInteractions() {
        return 5;
    }

    public int getAdultAge() {
        return 3;
    }

    public double getRequiredArea() {
        return 20.0;
    }

    // ===== REPTILE HOOK =====

    protected int getInitialTimeToShed() {
        return 15;
    }

    // ===== METHODS =====

    public void updateAge() {
        setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
        setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);
        timeToShed--;
    }

    public String description() {
        return super.description() +
               "Snakes are legless reptiles that live in grasslands and hunt small animals such as rats and birds.";
    }
}
