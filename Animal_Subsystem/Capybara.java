package Animal_Subsystem;

import Structure_Subsystem.*;

public class Capybara extends Mammal {

    private static final LivingCondition CAPYBARA_CONDITION =
        new LandCondition(26, 75, "Wet Grassland", 30, 2, true, 80, 5);

    public Capybara(Animal parent) {
        super(parent);
        setupStats();
    }

    public Capybara(char habitatId, String name, String preferedInteraction, String gender,
                    int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Capybara", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    private void setupStats() {
        setMaxHunger(80);
        setTypeFoods(new String[]{"Grass", "Vegetables"});
        setLifeExpectancy(10);
        setFlexibility(0.7);
        setLivingCondition(CAPYBARA_CONDITION);
        setTotalDailyInteractions(5);
        setAdultAge(2);
        setRequiredArea(30.0);
    }

    // ===== ABSTRACT GETTERS =====

    public int getMaxHunger() {
        return 80;
    }

    public String[] getTypeFoods() {
        String[] foods = {"Grass", "Vegetables"};
        return foods;
    }

    public int getLifeExpectancy() {
        return 10;
    }

    public double getFlexibility() {
        return 0.7;
    }

    public LivingCondition getLivingCondition() {
        return CAPYBARA_CONDITION;
    }

    public int getTotalDailyInteractions() {
        return 5;
    }

    public int getAdultAge() {
        return 2;
    }

    public double getRequiredArea() {
        return 30.0;
    }
    

    // ===== METHODS =====

    protected Mammal createOffspring() {
    return new Capybara(this);
    }

    public void updateAge() {
        setWeight(getWeight() + 5);

        if (getAge() >= getAdultAge()) {
            drinksMilk = false;
        }
    }

    public String description() {
        return super.description() +
               "Capybaras are calm, semi-aquatic mammals that require access to water and social interaction.";
    }
    
}
