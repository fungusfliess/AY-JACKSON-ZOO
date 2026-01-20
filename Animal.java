/*
   File Name: Animal.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Animal is an abstract base class representing all animals in the zoo.
                It includes common fields like name, species, age, and stats for happiness,
                cleanliness, and hunger, as well as methods for daily updates and interactions.
*/
public abstract class Animal {

    // =========================
    // CONSTANTS
    // =========================
    public static final int MAX_STAT = 100;
    public static final int LOW_STAT = 30;

    private static final double DAILY_HUNGER_DECREASE = 0.10;
    private static final double DAILY_CLEANLINESS_DECREASE = 0.05;
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
    private int maxHunger;
    private String[] typeFoods;
    private int lifeExpectancy;
    private double flexibility;
    private LivingCondition livingCondition;
    private int totalDailyInteractions;
    private int adultAge;
    private double requiredArea;

    // =========================
    // CONSTRUCTORS
    // =========================

    /* @description: Creates a baby animal based on parent template with default initial stats
       @param parent the parent animal to copy species and interaction preferences from
    */
    public Animal(Animal parent) {
        this.habitatId = Land.EMPTY;
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

    /* @description: Creates an animal with all specified attributes
       @param habitatId the ID of the habitat where the animal lives
       @param name the name of the animal
       @param specie the species of the animal
       @param preferedInteraction the animal's preferred type of interaction
       @param gender the gender of the animal (Male/Female)
       @param happiness the happiness level (0-100)
       @param cleanliness the cleanliness level (0-100)
       @param hunger the hunger level (0-maxHunger)
       @param age the age of the animal in years
       @param weight the weight of the animal in kilograms
    */
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

    public char getHabitatId() {
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

    public void setDaysPassed(int days) {
        this.daysPassed = days;
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

    /* @description: Feeds the animal if the food type is acceptable
       @param food the type of food being offered
       @param amount the amount of hunger to restore
       @return true if the animal ate the food, false otherwise
    */
    public boolean eat(String food, int amount) {
        if (food == null || amount <= 0 || typeFoods == null) {
            return false;
        }

        for (int i = 0; i < typeFoods.length; i++) {
            if (typeFoods[i] != null && food.equalsIgnoreCase(typeFoods[i])) {
                hunger = hunger + amount;
                if (hunger > maxHunger) {
                    hunger = maxHunger;
                }
                return true;
            }
        }

        return false;
    }

    /* @description: Calculates the happiness of the animal based on cleanliness, hunger,
                     interactions, and preferred interactions. Happiness is averaged across
                     four factors: cleanliness, hunger satisfaction, daily interactions, and
                     preferred interaction count.
    */
    public void calculateHappiness() {
        double avg = 0.0;

        // Factor 1: Cleanliness as a percentage of max stat
        avg += (double) cleanliness / MAX_STAT;
        if (maxHunger > 0) avg += (double) hunger / maxHunger;
        if (totalDailyInteractions > 0)
            avg += (double) Math.min(numDailyInteractions, totalDailyInteractions) / totalDailyInteractions;
        avg += numPreferredInteractions;

        happiness = (int) ((avg / 4.0) * MAX_STAT);
        happiness = Math.max(0, Math.min(MAX_STAT, happiness));
    }

    /* @description: Performs an interaction with the animal, tracking if it's the preferred type
       @param interaction the type of interaction being performed
    */
    public void interact(String interaction) {
        if (interaction == null) return;

        // Only count interactions up to the daily limit
        if (numDailyInteractions < totalDailyInteractions) {
            numDailyInteractions++;
            if (interaction.equalsIgnoreCase(preferedInteraction)) {
                numPreferredInteractions++;
            }
        }
    }

    /* @description: Checks if the animal is suitable for a given habitat based on living
                     conditions and space requirements. Uses flexibility to determine how
                     closely the habitat must match the animal's preferred conditions.
       @param habitat the habitat to check suitability for
       @return true if the habitat is suitable, false otherwise
    */
    public boolean isSuitable(Habitat habitat) {
        if (habitat == null || livingCondition == null || habitat.getClimate() == null) {
            return false;
        }

        // Check if living conditions match within flexibility tolerance and space is sufficient
        return livingCondition.compareTo(habitat.getClimate()) >= (1-flexibility)
                && habitat.enoughSpace(requiredArea);
    }

    /* @description: Simulates the passing of a day for the animal, updating its state.
                     Resets daily interaction counters, decreases hunger and cleanliness,
                     and increments age annually.
    */
    public void passDay() {
        daysPassed++;

        // Reset daily interaction counters
        numDailyInteractions = 0;
        numPreferredInteractions = 0;

        // Decrease hunger and cleanliness naturally over time
        hunger -= maxHunger*DAILY_HUNGER_DECREASE;
        cleanliness -= MAX_STAT*DAILY_CLEANLINESS_DECREASE;
        if (daysPassed % DAYS_PER_YEAR == 0) {
            age++;
            updateAge();
        }
    }

    /* @description: Checks if the animal's hunger is below the low threshold
       @return true if hunger is low, false otherwise
    */
    public boolean lowHunger() {
        return maxHunger <= 0 || hunger <= (LOW_STAT * maxHunger) / 100;
    }

    /* @description: Checks if the animal's cleanliness is below the low threshold
       @return true if cleanliness is low, false otherwise
    */
    public boolean lowCleansiness() {
        return cleanliness <= LOW_STAT;
    }

    /* @description: Checks if the animal's happiness is below the low threshold
       @return true if happiness is low, false otherwise
    */
    public boolean lowHappiness() {
        return happiness <= LOW_STAT;
    }

    /* @description: Removes the animal from its current habitat
    */
    public void leaveHabitat() {
        habitatId = Land.EMPTY;
    }

    /* @description: Checks if the animal has reached adult age
       @return true if the animal is an adult, false otherwise
    */
    public boolean isAdult() {
        return age >= getAdultAge();
    }

    /* @description: Checks if the animal can reproduce based on gender, happiness, age, and hunger
       @return true if the animal can reproduce, false otherwise
    */
    public boolean canReproduce() {
        return this.getGender().equalsIgnoreCase("Female") &&
            this.getHappiness() >= (LOW_STAT * MAX_STAT) &&
            isAdult() &&
            this.getHunger() <= (LOW_STAT * this.getMaxHunger());
    }

    // =========================
    // ABSTRACT
    // =========================
    public abstract void updateAge();

    

    // =========================
    // SAVE / STRING
    // =========================

    /* @description: Converts the animal's data to a pipe-delimited string for file storage
       @return a string representation of the animal's data
    */
    public String saveToString() {
        return specie + "|" +
            habitatId + "|" +
            name + "|" +
            preferedInteraction + "|" +
            gender + "|" +
            happiness + "|" +
            cleanliness + "|" +
            hunger + "|" +
            age + "|" +
            weight + "|" +
            daysPassed;
    }


    /* @description: Returns a brief description of the animal with basic info
       @return a string with name, species, and age
    */
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
