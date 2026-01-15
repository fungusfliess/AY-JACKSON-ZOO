package Animal_Subsystem;

import Structure_Subsystem.*;

public class Sunfish extends Fish {

    private static final LivingCondition SUNFISH_CONDITION =
        new WaterCondition(22, 70, "Pelagic Zone", 8, 160, 23, false, 34);

    private static final int WEIGHT_GAIN_PER_YEAR = 6;
    private static final int HUNGER_GAIN_PER_YEAR = 4;

    public Sunfish(Animal parent) {
        super(parent);
        setupStats();
    }

    public Sunfish(char habitatId, String name, String preferedInteraction, String gender,
                   int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Sunfish", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(80);
        setTypeFoods(new String[]{"Algae", "Flakes"});
        setLifeExpectancy(10);
        setFlexibility(0.7);
        setLivingCondition(SUNFISH_CONDITION);
        setTotalDailyInteractions(5);
        setAdultAge(3);
        setRequiredArea(80.0);
        amountEggs = 10;
    }

    // ===== GETTERS =====

    @Override
    public int getMaxHunger() {
        return 80;
    }
    @Override
    public String[] getTypeFoods() {
        String[] foods = {"Algae", "Flakes"};
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
        return SUNFISH_CONDITION;
    }
    @Override
    public int getTotalDailyInteractions() {
        return 5;
    }
    @Override
    public int getAdultAge() {
        return 3;
    }
    @Override
    public double getRequiredArea() {
        return 80.0;
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
               "Sunfish are large, slow-moving fish that inhabit open ocean zones and feed on algae.";
    }
}
