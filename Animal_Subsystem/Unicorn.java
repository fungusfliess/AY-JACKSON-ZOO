package Animal_Subsystem;

import Structure_Subsystem.*;

public class Unicorn extends Mammal {

    private static final LivingCondition UNICORN_CONDITION =
        new LandCondition(18, 60, "Enchanted Forest", 45, 4, true, 70, 8);

    private static final int WEIGHT_GAIN_PER_YEAR = 15;
    private static final int HUNGER_GAIN_PER_YEAR = 6;
    public Unicorn(Animal parent) {
        super(parent);
        setupStats();
    }

    public Unicorn(char habitatId, String name, String preferedInteraction, String gender,
                   int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Unicorn", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(120);
        setTypeFoods(new String[]{"Grass", "Hay", "Fruits"});
        setLifeExpectancy(30);
        setFlexibility(0.4);
        setLivingCondition(UNICORN_CONDITION);
        setTotalDailyInteractions(3);
        setAdultAge(3);
        setRequiredArea(50.0);
    }

    // ===== ABSTRACT GETTERS =====

    public int getMaxHunger() {
        return 120;
    }

    public String[] getTypeFoods() {
        String[] foods = {"Grass", "Hay", "Fruits"};
        return foods;
    }

    public int getLifeExpectancy() {
        return 30;
    }

    public double getFlexibility() {
        return 0.4;
    }

    public LivingCondition getLivingCondition() {
        return UNICORN_CONDITION;
    }

    public int getTotalDailyInteractions() {
        return 3;
    }

    public int getAdultAge() {
        return 3;
    }

    public double getRequiredArea() {
        return 50.0;
    }

    // ===== METHODS =====

    protected Mammal createOffspring() {
        return new Unicorn(this);
    }

    public void updateAge() {
        setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
        setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);

        if (getAge() >= getAdultAge()) {
            drinksMilk = false;
        }
    }

    public String description() {
        return super.description() +
               "Unicorns are magical mammals that thrive in enchanted forests and require pristine habitats.";
    }
}
