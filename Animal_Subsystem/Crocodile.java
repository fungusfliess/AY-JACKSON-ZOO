package Animal_Subsystem;

import Structure_Subsystem.*;

public class Crocodile extends Reptile {

    private static final LivingCondition CROCODILE_CONDITION =
        new WaterCondition(28, 80, "River Delta", 7.2, 180, 26, true, 0.5);

    public Crocodile(Animal parent) {
        super(parent);
        setupStats();
    }

    public Crocodile(char habitatId, String name, String preferedInteraction, String gender,
                     int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Crocodile", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(120);
        setTypeFoods(new String[]{"Meat", "Fish"});
        setLifeExpectancy(50);
        setFlexibility(0.4);
        setLivingCondition(CROCODILE_CONDITION);
        setTotalDailyInteractions(3);
        setAdultAge(5);
        setRequiredArea(50.0);
        timeToShed = 30;
    }

    // ===== ABSTRACT GETTERS =====

    public int getMaxHunger() {
        return 120;
    }

    public String[] getTypeFoods() {
        String[] foods = {"Meat", "Fish"};
        return foods;
    }

    public int getLifeExpectancy() {
        return 50;
    }

    public double getFlexibility() {
        return 0.4;
    }

    public LivingCondition getLivingCondition() {
        return CROCODILE_CONDITION;
    }

    public int getTotalDailyInteractions() {
        return 3;
    }

    public int getAdultAge() {
        return 5;
    }

    public double getRequiredArea() {
        return 50.0;
    }

    // ===== REPTILE HOOK =====

    protected int getInitialTimeToShed() {
        return 30;
    }

    // ===== METHODS =====

    public void updateAge() {
        setWeight(getWeight() + 15);
        setMaxHunger(getMaxHunger() + 5);
        timeToShed--;
    }

    public String description() {
        return super.description() +
               "Crocodiles are large aquatic reptiles that live in river deltas and require both land and water.";
    }
}
