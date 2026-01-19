/*
   File Name: Eagle.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Eagle is a concrete class representing eagles in the zoo.
                Eagles are birds of prey that live in mountainous regions.
*/
public class Eagle extends Bird {

    // Eagles prefer mountainous terrain with lower temperatures
    private static final LivingCondition EAGLE_CONDITION =
        new LandCondition(15, 55, "Mountainous", 85, 25, true, 30, 12);

    private static final int WEIGHT_GAIN_PER_YEAR = 3;
    private static final int HUNGER_GAIN_PER_YEAR = 5;

    /* @description: Template constructor creates baby eagle from parent
    */
    public Eagle(Animal parent) {
        super(parent);
        setupStats();
    }

    /* @description: Full constructor creates eagle with all specified attributes
    */
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
        setFlyingAge(3);
        setCanFly(false);
    }

    // =========================
    //  GETTERS
    // =========================

    @Override
    public int getMaxHunger() {
        return 80;
    }

    public String[] getTypeFoods() {
        String[] foods = {"Meat", "Fish"};
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
        return EAGLE_CONDITION;
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
        return 30.0;
    }

    // =========================
    // METHODS
    // =========================

    @Override
    public void updateAge() {
        setWeight(getWeight() + getAge()*WEIGHT_GAIN_PER_YEAR);
        setMaxHunger(getMaxHunger() + getAge()*HUNGER_GAIN_PER_YEAR);

        if (getAge() >= getFlyingAge()) {
            setCanFly(true);
        }
    }

    @Override
    public String description() {
        return super.description() +
               "Eagles are large birds of prey that live in mountainous regions. " +
               "They require high perching areas and open airspace for flight.";
    }
}
