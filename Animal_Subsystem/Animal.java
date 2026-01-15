package Animal_Subsystem;
import Structure_Subsystem.*;

public abstract class Animal {

    public static final int MAX_STAT = 100;
    public static final int LOW_STAT = 30;

    // COMMON FIELDS
    private String habitatId;
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

    // SPECIE BASED FIELDS
    private int maxHunger;
    private String[] typeFoods;
    private int lifeExpectancy;
    private double flexibility;
    private LivingCondition livingCondition;
    private int totalDailyInteractions;
    private int adultAge;
    private double requiredArea;

    
    public Animal(Animal parent) {
        this.name = "";
        this.specie = parent.getSpecie();
        this.preferedInteraction = parent.getPreferedInteraction();
        this.gender = "";
        this.happiness = 50;
        this.cleanliness = 50;
        this.hunger = 50;
        this.age = 0;
        this.weight = 0.0;
        numDailyInteractions = 0;
        daysPassed = 0;
        numPreferredInteractions = 0;

    }

    public Animal(String habitatId, String name, String specie, String preferedInteraction, String gender, int happiness, int cleanliness, int hunger, int age, double weight) {
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
        numDailyInteractions = 0;
        daysPassed = 0;
        numPreferredInteractions = 0;
    }
    // METHODS

    // COMMON FIELDS GETTERS AND SETTERS

    // GETTERS
    public String getHabitatId() {
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
    

    //SETTERS

    public void setHabitatId(String habitatId) {
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
    public void setPreferedInteraction (String preferedInteraction) {
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
    

    // SPECIE BASED FIELDS GETTERS AND SETTERS
    // SETTERS
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
    // ABSTRACT GETTERS
    public abstract int getMaxHunger();
    public abstract String[] getTypeFoods();
    public abstract int getLifeExpectancy();
    public abstract double getFlexibility();
    public abstract LivingCondition getLivingCondition();
    public abstract int getTotalDailyInteractions();
    public abstract int getAdultAge();
    public abstract double getRequiredArea();


    // NON-ABSTRACT METHODS
    // feeds the animal with given food, if possible
    public boolean eat(String food, int amount) {
        if (this.getTypeFoods() != null) {
            for (int i =0; i <this.typeFoods.length; i++) {
                if (food.equalsIgnoreCase(this.typeFoods[i])) {
                    this.hunger += amount;
                    if (this.hunger > this.maxHunger) {
                        this.hunger = this.maxHunger;
                    }
                    return true;
                }
            }
        } 
        return false;
    }

    // updates the animal's happiness based on cleanliness, hunger, and interactions.
    public void calculateHappiness() {
        double average =0;
        average += this.cleanliness/MAX_STAT;
        average += this.hunger/this.maxHunger;
        average += Math.min(this.numDailyInteractions, this.totalDailyInteractions)/Math.max(this.numDailyInteractions, this.totalDailyInteractions);
        average += numPreferredInteractions;
        this.happiness = (int) ((average/4) * MAX_STAT);
    }

    // interacts with the animal given the name of said interaction.
    public void interact(String interaction) {
        if (numDailyInteractions < totalDailyInteractions) {
            numDailyInteractions++;
            if (interaction.equalsIgnoreCase(this.preferedInteraction)) {
                numPreferredInteractions++;
            }
        }
    }

    // moves animal to a new habitat if possible
    public boolean relocate(Habitat newHabitat) {
        return relocateAnimal();

    }

    // checks to see if the animal is suitable for the given habitat
    public boolean isSuitable(Habitat habitat) {
        if (this.livingCondition.compareTo(habitat.getLivingCondition()) >= this.flexibility && habitat.enoughSpace(requiredArea)) {
            return true;
        }
        return false;
    }

    // Description: simulates a day passing for animals
    public void passDay() {
        daysPassed++;
        numDailyInteractions = 0;
        numPreferredInteractions = 0;
        hunger -= 10;
        if (hunger < 0) {
            hunger = 0;
        }
        cleanliness -= 5;
        if (cleanliness < 0) {
            cleanliness = 0;
        }
        // if a year has passed, update age
        if (getDaysPassed() % 365 == 0) {
            setAge(getAge() + 1);
            updateAge();
        }
    }

    // Description: determines if the animals hunger is low
    public boolean lowHunger() {
        if (this.hunger <= (LOW_STAT * this.maxHunger) / 100) {
            return true;
        }
        return false;
    }

    // Description: determines if the animals cleanliness is low
    public boolean lowCleansiness() {
        if (this.cleanliness <= LOW_STAT) {
            return true;
        }
        return false;
    }

    // Description: determines if the animals happiness is low
    public boolean lowHappiness() {
        if (this.happiness <= LOW_STAT) {
            return true;
        }
        return false;
    }

    public String toString() {
        return  "Habitat Id: " + habitatId + "\n" +
                "Name: " + name + "\n" +
                "Specie: " + specie + "\n" +
                "Age: " + age + "\n" +
                "Prefered Interaction: " + preferedInteraction + "\n" +
                "Happiness: " + happiness + "\n" +
                "Cleanliness: " + cleanliness+ "\n" +
                "Hunger: " + hunger + "/" + getMaxHunger() + "\n" +
                "Gender: " + gender + "\n" +
                "Weight: " + weight + "\n" +
                "Adulthood Age: " + adultAge + "\n" +
                "Interactions Today: " + numDailyInteractions + "/" + totalDailyInteractions + "\n" +
                "Days passed since last birthday: " + daysPassed + "\n" +
                "Preferred Interactions Today: " + numPreferredInteractions + "\n" +
                "Max Hunger: " + maxHunger + "\n" +
                "Type of Foods: " + String.join(", ", typeFoods) + "\n" +
                "Life Expectancy: " + lifeExpectancy + "\n" +
                "Flexibility: " + flexibility + "\n" +
                "Living Condition: " + livingCondition.toString() + "\n" +
                "Total Daily Interactions: " + totalDailyInteractions + "\n";
    }

    // ABSTRACT METHODS

    // Description: abstract method that formats all information of the animal
    

    // Description: abstract helper method that updates the age of the animal
    public abstract void updateAge();
    

}
