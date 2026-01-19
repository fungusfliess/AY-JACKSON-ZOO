/*
   File Name: Capybara.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Capybara is a concrete class representing capybaras in the zoo.
                Capybaras are large rodents that live in wet grassland environments.
*/
public class Capybara extends Mammal {

    // CONSTANTS
    // Capybaras prefer wet grasslands with high humidity and water access
    private static final LivingCondition CAPYBARA_CONDITION =
        new LandCondition(26, 75, "Wet Grassland", 30, 2, true, 80, 5);

    private static final int WEIGHT_GAIN_PER_YEAR = 8;
    private static final int HUNGER_GAIN_PER_YEAR = 5;
    
    // CONSTRUCTORS
    
    /* @description: Template constructor creates baby capybara from parent
    */
    public Capybara(Animal parent) {
        super(parent);
        setupStats();
    }

    /* @description: Full constructor creates capybara with all specified attributes
    */
    public Capybara(char habitatId, String name, String preferedInteraction, String gender,
                    int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, "Capybara", preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        setupStats();
        updateAge();
    }

    /* description: sets up the initial stats for the Capybara
    */

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

    @Override
    public int getMaxHunger() {
        return 80;
    }
    
    /* description: returns the types of food the Capybara eats
    */

    @Override
    public String[] getTypeFoods() {
        String[] foods = {"Grass", "Vegetables"};
        return foods;
    }
    
    /* description: returns the life expectancy of the Capybara
    */

    @Override
    public int getLifeExpectancy() {
        return 10;
    }

    public double getFlexibility() {
        return 0.7;
    }

    /* description: returns the living condition of the Capybara
    */

    @Override
    public LivingCondition getLivingCondition() {
        return CAPYBARA_CONDITION;
    }

    public int getTotalDailyInteractions() {
        return 5;
    }

    /* description: returns the adult age of the Capybara
    */

    @Override
    public int getAdultAge() {
        return 2;
    }

    /* description: returns the required area for the Capybara
    */
    @Override
    public double getRequiredArea() {
        return 30.0;
    }
    

    // ===== METHODS =====

    public Mammal createOffspring() {
    return new Capybara(this);
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
               "Capybaras are calm, semi-aquatic mammals that require access to water and social interaction.";
    }
    
}
