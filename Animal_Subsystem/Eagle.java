package Animal_Subsystem;

import Structure_Subsystem.*;

public class Eagle extends Bird {

    private static final LivingCondition EAGLE_CONDITION =
        new LandCondition(15, 55, "Mountainous", 85, 25, true, 30, 12);

    private static final int WEIGHT_GAIN_PER_YEAR = 3;
    private static final int HUNGER_GAIN_PER_YEAR = 5;

    public Eagle(Animal parent) {
        super(parent);
        setupStats();
    }

    public Eagle(char habitatId, String name, String preferedInteraction, String gender,
                 int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Eagle", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(80);
        setTypeFoods(new String[]{"Meat", "Fish"});
        setLifeExpectancy(30);
        setFlexibility(0.4);
        setLivingCondition(EAGLE_CONDITION);
        setTotalDailyInteractions(3);
        setAdultAge(5);
        setRequiredArea(30.0);
        flyingAge = 3;
        canFly = false;
    }

    // =========================
    // REQUIRED ABSTRACT GETTERS
    // =========================

    public int getMaxHunger() {
        return 80;
    }

    public String[] getTypeFoods() {
        String[] foods = {"Meat", "Fish"};
        return foods;
    }

    public int getLifeExpectancy() {
        return 30;
    }

    public double getFlexibility() {
        return 0.4;
    }

    public LivingCondition getLivingCondition() {
        return EAGLE_CONDITION;
    }

    public int getTotalDailyInteractions() {
        return 3;
    }

    public int getAdultAge() {
        return 5;
    }

    public double getRequiredArea() {
        return 30.0;
    }

    // =========================
    // METHODS
    // =========================

    public void updateAge() {
        setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
        setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);

        if (getAge() >= flyingAge) {
            canFly = true;
        }
    }

    public String description() {
        return super.description() +
               "Eagles are large birds of prey that live in mountainous regions. " +
               "They require high perching areas and open airspace for flight.";
    }
}
