/*
   File Name: Unicorn.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Unicorn is a concrete class representing unicorns in the zoo.
                Unicorns are mythical mammals that live in enchanted forest environments.
*/
public class Unicorn extends Mammal {

    // Unicorns prefer enchanted forests with moderate conditions
    private static final LivingCondition UNICORN_CONDITION =
        new LandCondition(18, 60, "Enchanted Forest", 45, 4, true, 70, 8);

    private static final int WEIGHT_GAIN_PER_YEAR = 15;
    private static final int HUNGER_GAIN_PER_YEAR = 6;
    
    /* @description: Template constructor creates baby unicorn from parent
    */
    public Unicorn(Animal parent) {
        super(parent);
        setupStats();
    }

    /* @description: Full constructor creates unicorn with all specified attributes
    */
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

    @Override
    public int getMaxHunger() {
        return 120;
    }

    @Override
    public String[] getTypeFoods() {
        String[] foods = {"Grass", "Hay", "Fruits"};
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
        return UNICORN_CONDITION;
    }

    @Override
    public int getTotalDailyInteractions() {
        return 3;
    }

    @Override
    public int getAdultAge() {
        return 3;
    }

    @Override
    public double getRequiredArea() {
        return 50.0;
    }

    // ===== METHODS =====

    @Override
    public Mammal createOffspring() {
        return new Unicorn(this);
    }

    @Override
    public void updateAge() {
        setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
        setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);

        if (getAge() >= getAdultAge()) {
            drinksMilk = false;
        }
    }

    @Override
    public String description() {
        return super.description() +
               "Unicorns are magical mammals that thrive in enchanted forests and require pristine habitats.";
    }
}
