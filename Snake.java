/*
   File Name: Snake.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Snake is a concrete class representing snakes in the zoo.
                Snakes are reptiles that live in grassland environments.
*/
public class Snake extends Reptile {

    // Snakes prefer warm grasslands with moderate conditions
    private static final LivingCondition SNAKE_CONDITION =
        new LandCondition(26, 50, "Grassland", 40, 6, true, 45, 6);

    private static final int WEIGHT_GAIN_PER_YEAR = 4;
    private static final int HUNGER_GAIN_PER_YEAR = 2;

    /* @description: Template constructor creates baby snake from parent
    */
    public Snake(Animal parent) {
        super(parent);
        setupStats();
    }

    /* @description: Full constructor creates snake with all specified attributes
    */
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
        setTimeToShed(30);
    }

    // ===== ABSTRACT GETTERS =====

    @Override
    public int getMaxHunger() {
        return 50;
    }

    @Override
    public String[] getTypeFoods() {
        String[] foods = {"Rats", "Birds"};
        return foods;
    }

    @Override
    public int getLifeExpectancy() {
        return 20;
    }

    @Override
    public double getFlexibility() {
        return 0.7;
    }

    @Override
    public LivingCondition getLivingCondition() {
        return SNAKE_CONDITION;
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
        return 20.0;
    }

    // ===== REPTILE METHODS =====

    @Override
    public int getInitialTimeToShed() {
        return 15;
    }

    // ===== METHODS =====

    @Override
    public void updateAge() {
        setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
        setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);
        setTimeToShed(getTimeToShed()-1);
    }

    @Override
    public String description() {
        return super.description() +
               "Snakes are legless reptiles that live in grasslands and hunt small animals such as rats and birds.";
    }
}
