package Animal_Subsystem;

import Structure_Subsystem.*;
import Land;
import Land_Subsystem.*;

public abstract class Animal {

    // =========================
    // CONSTANTS
    // =========================
    public static final int MAX_STAT = 100;
    public static final int LOW_STAT = 30;

    private static final int DAILY_HUNGER_DECREASE = 10;
    private static final int DAILY_CLEANLINESS_DECREASE = 5;
    private static final int DAYS_PER_YEAR = 365;

    // =========================
    // COMMON FIELDS
    // =========================
    private char habitatId;
    private String name;
    private String specie;
    private int age;
    private String preferedInteraction;
    private int happiness;
    private int cleanliness;
    private int hunger;
    private String gender;
    private double weight;

    private int numDailyInteractions;
    private int daysPassed;
    private int numPreferredInteractions;

    // =========================
    // SPECIE-BASED FIELDS
    // =========================
    protected int maxHunger;
    protected String[] typeFoods;
    protected int lifeExpectancy;
    protected double flexibility;
    protected LivingCondition livingCondition;
    protected int totalDailyInteractions;
    protected int adultAge;
    protected double requiredArea;

    // =========================
    // CONSTRUCTORS
    // =========================

    public Animal(Animal parent) {
        this.habitatId = Land.EMPTY_SPACE;
        this.name = "";
        this.specie = parent.getSpecie();
        this.preferedInteraction = parent.getPreferedInteraction();
        this.gender = "";
        this.happiness = 50;
        this.cleanliness = 50;
        this.hunger = 50;
        this.age = 0;
        this.weight = 0.0;

        this.numDailyInteractions = 0;
        this.daysPassed = 0;
        this.numPreferredInteractions = 0;
    }

    public Animal(char habitatId, String name, String specie, String preferedInteraction, String gender,
                  int happiness, int cleanliness, int hunger, int age, double weight) {

        this.habitatId = habitatId;
        this.name = name;
        this.specie = specie;
        this.preferedInteraction = preferedInteraction;
        this.gender = gender;
        this.happiness = happiness;
        this.cleanliness = cleanliness;
        this.hunger = hunger;
        this.age = age;
        this.weight = weight;

        this.numDailyInteractions = 0;
        this.daysPassed = 0;
        this.numPreferredInteractions = 0;
    }

    // =========================
    // GETTERS
    // =========================

    public int getHabitatId() {
        return habitatId;
    }

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

    public int getAge() {
        return age;
    }

    public String getPreferedInteraction() {
        return preferedInteraction;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public int getHunger() {
        return hunger;
    }

    public String getGender() {
        return gender;
    }

    public double getWeight() {
        return weight;
    }

    public int getNumDailyInteractions() {
        return numDailyInteractions;
    }

    public int getDaysPassed() {
        return daysPassed;
    }

    public int getNumPreferredInteractions() {
        return numPreferredInteractions;
    }

    // =========================
    // SETTERS
    // =========================

    public void setHabitatId(char habitatId) {
        this.habitatId = habitatId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPreferedInteraction(String preferedInteraction) {
        this.preferedInteraction = preferedInteraction;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // =========================
    // SPECIE-BASED SETTERS
    // =========================

    public void setMaxHunger(int maxHunger) {
        this.maxHunger = maxHunger;
    }

    public void setTypeFoods(String[] typeFoods) {
        this.typeFoods = typeFoods;
    }

    public void setLifeExpectancy(int lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public void setFlexibility(double flexibility) {
        this.flexibility = flexibility;
    }

    public void setLivingCondition(LivingCondition livingCondition) {
        this.livingCondition = livingCondition;
    }

    public void setTotalDailyInteractions(int totalDailyInteractions) {
        this.totalDailyInteractions = totalDailyInteractions;
    }

    public void setAdultAge(int adultAge) {
        this.adultAge = adultAge;
    }

    public void setRequiredArea(double requiredArea) {
        this.requiredArea = requiredArea;
    }

    // =========================
    // ABSTRACT SPECIES GETTERS
    // =========================
    public abstract int getMaxHunger();
    public abstract String[] getTypeFoods();
    public abstract int getLifeExpectancy();
    public abstract double getFlexibility();
    public abstract LivingCondition getLivingCondition();
    public abstract int getTotalDailyInteractions();
    public abstract int getAdultAge();
    public abstract double getRequiredArea();

    // =========================
    // BEHAVIOUR METHODS
    // =========================

    public boolean eat(String food, int amount) {
        if (food == null || amount <= 0 || typeFoods == null) return false;

        for (String f : typeFoods) {
            if (f != null && food.equalsIgnoreCase(f)) {
                hunger = Math.min(hunger + amount, maxHunger);
                return true;
            }
        }
        return false;
    }

    public void calculateHappiness() {
        double avg = 0.0;

        avg += (double) cleanliness / MAX_STAT;
        if (maxHunger > 0) avg += (double) hunger / maxHunger;
        if (totalDailyInteractions > 0)
            avg += (double) Math.min(numDailyInteractions, totalDailyInteractions) / totalDailyInteractions;
        avg += numPreferredInteractions;

        happiness = (int) ((avg / 4.0) * MAX_STAT);
        happiness = Math.max(0, Math.min(MAX_STAT, happiness));
    }

    public void interact(String interaction) {
        if (interaction == null) return;

        if (numDailyInteractions < totalDailyInteractions) {
            numDailyInteractions++;
            if (interaction.equalsIgnoreCase(preferedInteraction)) {
                numPreferredInteractions++;
            }
        }
    }

    public boolean isSuitable(Habitat habitat) {
        if (habitat == null || livingCondition == null || habitat.getLivingCondition() == null) {
            return false;
        }

        return livingCondition.compareTo(habitat.getLivingCondition()) >= flexibility
                && habitat.enoughSpace(requiredArea);
    }

    public void passDay() {
        daysPassed++;

        numDailyInteractions = 0;
        numPreferredInteractions = 0;

        hunger = Math.max(0, hunger - DAILY_HUNGER_DECREASE);
        cleanliness = Math.max(0, cleanliness - DAILY_CLEANLINESS_DECREASE);

        if (daysPassed % DAYS_PER_YEAR == 0) {
            age++;
            updateAge();
        }
    }

    public boolean lowHunger() {
        return maxHunger <= 0 || hunger <= (LOW_STAT * maxHunger) / 100;
    }

    public boolean lowCleansiness() {
        return cleanliness <= LOW_STAT;
    }

    public boolean lowHappiness() {
        return happiness <= LOW_STAT;
    }

    public void leaveHabitat() {
        habitatId = Land.EMPTY_SPACE;
    }

    // =========================
    // ABSTRACT
    // =========================
    public abstract void updateAge();

    // =========================
    // SAVE / STRING
    // =========================

    public String saveToString() {
        // used string builder to put it into savable format
        StringBuilder sb = new StringBuilder();

        sb.append(habitatId).append("\n")
          .append(name).append("\n")
          .append(specie).append("\n")
          .append(preferedInteraction).append("\n")
          .append(gender).append("\n")
          .append(happiness).append("\n")
          .append(cleanliness).append("\n")
          .append(hunger).append("\n")
          .append(age).append("\n")
          .append(weight).append("\n")
          .append(maxHunger).append("\n");

        if (typeFoods != null) {
            for (String food : typeFoods) {
                sb.append(food).append("\n");
            }
        }

        sb.append(lifeExpectancy).append("\n")
          .append(flexibility).append("\n")
          .append(livingCondition).append("\n")
          .append(totalDailyInteractions).append("\n")
          .append(adultAge).append("\n")
          .append(requiredArea);

        return sb.toString();
    }
    public String description() {
    return "Name: " + getName() + "\n" +
           "Specie: " + getSpecie() + "\n" +
           "Age: " + getAge() + "\n";
    }

    @Override
    public String toString() {
        return  "Habitat Id: " + habitatId + "\n" +
                "Name: " + name + "\n" +
                "Specie: " + specie + "\n" +
                "Gender: " + gender + "\n" +
                "Age: " + age + "\n" +
                "Weight: " + weight + "\n" +
                "Prefered Interaction: " + preferedInteraction + "\n\n" +
                "Happiness: " + happiness + "/" + MAX_STAT + "\n" +
                "Cleanliness: " + cleanliness + "/" + MAX_STAT + "\n" +
                "Hunger: " + hunger + "/" + maxHunger + "\n\n" +
                "Interactions Today: " + numDailyInteractions + "/" + totalDailyInteractions + "\n" +
                "Preferred Interactions Today: " + numPreferredInteractions + "\n" +
                "Days Passed: " + daysPassed + "\n\n" +
                "=== Species Requirements ===\n" +
                "Life Expectancy: " + lifeExpectancy + "\n" +
                "Adult Age: " + adultAge + "\n" +
                "Flexibility: " + flexibility + "\n" +
                "Living Condition: " + livingCondition + "\n" +
                "Required Area: " + requiredArea + "\n";
    }
}
