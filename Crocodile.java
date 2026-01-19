/*
   File Name: Crocodile.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Crocodile is a concrete class representing crocodiles in the zoo.
                Crocodiles are large reptiles that live in river delta environments.
*/
public class Crocodile extends Reptile {

    // Crocodiles prefer warm, humid river deltas with specific water conditions
    private static final LivingCondition CROCODILE_CONDITION =
        new WaterCondition(28, 80, "River Delta", 7.2, 180, 26, true, 0.5);

    //CONSTANTS
    private static final int WEIGHT_GAIN_PER_YEAR = 12;
    private static final int HUNGER_GAIN_PER_YEAR = 4;

    /* @description: Template constructor creates baby crocodile from parent
    */
    public Crocodile(Animal parent) {
        super(parent);
        setupStats();
    }

    /* @description: Full constructor creates crocodile with all specified attributes
    */
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
        setTimeToShed(30); 
    }

    // ===== GETTERS =====

    @Override
    public int getMaxHunger() {
        return 120;
    }

    @Override
    public String[] getTypeFoods() {
        String[] foods = {"Meat", "Fish"};
        return foods;
    }

    @Override
    public int getLifeExpectancy() {
        return 50;
    }

    @Override
    public double getFlexibility() {
        return 0.4;
    }

    @Override
    public LivingCondition getLivingCondition() {
        return CROCODILE_CONDITION;
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
        return 50.0;
    }

    // ===== REPTILE METHODs =====

    @Override
    public int getInitialTimeToShed() {
        return 30;
    }

    // ===== METHODS =====

    public void updateAge() {
        setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
        setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);
        setTimeToShed(getTimeToShed()-1);
    }

    public String description() {
        return super.description() +
               "Crocodiles are large aquatic reptiles that live in river deltas and require both land and water.";
    }
}
