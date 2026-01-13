package Animal_Subsystem;
import Structure_Subsystem.*;

public class Reptile extends Animal{
    private int timeToShed;

    // Description: constructor for reptile
    public Reptile (Animal parent) {
        super(parent);
        
        setMaxHunger(maxHunger(parent.getSpecie()));
        setTypeFoods(typeFoods(parent.getSpecie()));
        setLifeExpectancy(lifeExpectancy(parent.getSpecie()));
        setFlexibility(flexibility(parent.getSpecie()));
        setLivingCondition(livingCondition(parent.getSpecie()));
        setTotalDailyInteractions(totalDailyInteractions(parent.getSpecie()));
        setAdultAge(adultAge(parent.getSpecie()));
        this.timeToShed = timeToShed(parent.getSpecie());

    }
    public Reptile(String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight) {
        super(name, specie, preferedInteraction, gender, happiness, cleanliness, hunger, age, weight);

        setMaxHunger(maxHunger(specie));
        setTypeFoods(typeFoods(specie));
        setLifeExpectancy(lifeExpectancy(specie));
        setFlexibility(flexibility(specie));
        setLivingCondition(livingCondition(specie));
        setTotalDailyInteractions(totalDailyInteractions(specie)); 
        setAdultAge(adultAge(specie));
        updateAge();
    }

    // SETTING SPECIE BASED FIELDS
    public static int maxHunger(String specie) {
        if (specie.equalsIgnoreCase("Frog")) {
            return 50;
        } else if (specie.equalsIgnoreCase("Axolotl")) {
            return 20;
        }
        return -1;
    }
    public static String[] typeFoods(String specie) {
        if (specie.equalsIgnoreCase("Crocodile")) {
            String[] foods = {"Meat", "Fish"};
            return foods;
        } else if (specie.equalsIgnoreCase("Snake")) {
            String[] foods = {"Rats", "Birds"};
            return foods;
        }
        return new String[0];
    }

    public static int lifeExpectancy(String specie) {
        if (specie.equalsIgnoreCase("Crocodile")) {
            return 50;
        } else if (specie.equalsIgnoreCase("Snake")) {
            return 20;
        }
        return -1;
    }
    public static double flexibility(String specie) {
        if (specie.equalsIgnoreCase("Crocodile")) {
            return 0.4;
        } else if (specie.equalsIgnoreCase("Snake")) {
            return 0.7;
        }
        return -1;
    }
    public static LivingCondition livingCondition(String specie) {
        if (specie.equalsIgnoreCase("Crocodile")) {
            LivingCondition crocodileLivingCondition = new LivingCondition();
            return crocodileLivingCondition;
        } else if (specie.equalsIgnoreCase("Snake")) {
            LivingCondition snakeLivingCondition = new LivingCondition();
            return snakeLivingCondition;
        }
        return null;
    }
    public static int totalDailyInteractions(String specie) {
        if (specie.equalsIgnoreCase("Crocodile")) {
            return 3;
        } else if (specie.equalsIgnoreCase("Snake")) {
            return 5;
        }
        return -1;
    }
    private static int adultAge(String specie) {
        if (specie.equalsIgnoreCase("Crocodile")) {
            return 5;
        } else if (specie.equalsIgnoreCase("Snake")) {
            return 3;
        }
        return -1;
    }
    // GETTERS

    public int getMaxHunger() {
        return Amphibian.maxHunger(this.getSpecie());
    }
    public String[] getTypeFoods() {
        return Amphibian.typeFoods(this.getSpecie());
    }
    public int getLifeExpectancy() {
        return Amphibian.lifeExpectancy(this.getSpecie());
    }
    public double getFlexibility() {
        return Amphibian.flexibility(this.getSpecie());
    } 
    public LivingCondition getLivingCondition() {
        return Amphibian.livingCondition(this.getSpecie());
    }
    public int getTotalDailyInteractions() {
        return Amphibian.totalDailyInteractions(this.getSpecie());
    }
    public int getAdultAge() {
        return Amphibian.adultAge(this.getSpecie());
    }

    // SETTERS
    public void setMaxHunger(int maxHunger) {
        super.setMaxHunger(maxHunger);
    }
    public void setTypeFoods(String[] typeFoods) {
        super.setTypeFoods(typeFoods);
    }
    public void setLifeExpectancy(int lifeExpectancy) {
        super.setLifeExpectancy(lifeExpectancy);
    }
    public void setFlexibility(double flexibility) {
        super.setFlexibility(flexibility);
    }
    public void setLivingCondition(LivingCondition livingCondition) {
        super.setLivingCondition(livingCondition);
    }
    public void setTotalDailyInteractions(int totalDailyInteractions) {
        super.setTotalDailyInteractions(totalDailyInteractions);
    }

    // METHODS

    // Description: abstract method that formats all information of the animal
    public String toString() {
        return  "Habitat: " + getHabitat() + "\n" +
                "Name: " + getName() + "\n" +
                "Specie: " + getSpecie() + "\n" +
                "Age: " + getAge() + "\n" +
                "Prefered Interaction: " + getPreferedInteraction() + "\n" +
                "Happiness: " + getHappiness() + "\n" +
                "Cleanliness: " + getCleanliness() + "\n" +
                "Hunger: " + getHunger() + "/" + getMaxHunger() + "\n" +
                "Gender: " + getGender() + "\n" +
                "Weight: " + getWeight() + "\n" +
                "Type of Foods: " + getTypeFoods() + "\n" +
                "Life Expectancy: " + getLifeExpectancy() + "\n";
    }

    // Description: creates a new animal of the same type as its parent.
    //  This method is different for animals that produce offspring as eggs.
    //  Returns null if the animal does not have the requirements to reproduce.
    public Egg reproduce(Habitat habitat) {
        if (this.getGender().equalsIgnoreCase("Female") && 
            this.getHappiness() >= (LOW_STAT * MAX_STAT) && 
            this.getAge() >= this.getAdultAge() &&  
            this.getHunger() <= (LOW_STAT * this.getMaxHunger())) {

            Egg egg = new Egg(this);
            return egg;
        }
        return null;
    }

    // Description: method that updates the age of the animal
    public void updateAge() {
        if (this.getSpecie().equalsIgnoreCase("Crocodile")) {
            setWeight(getWeight() + 15);
            setMaxHunger(getMaxHunger() + 5);
        } else if (this.getSpecie().equalsIgnoreCase("Snake")) {
            setWeight(getWeight() + 5);
            setMaxHunger(getMaxHunger() + 2);
        }
        
    }
}
