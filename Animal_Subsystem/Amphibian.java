package Animal_Subsystem;
import Structure_Subsystem.*;

public class Amphibian extends Animal{
    private String currentStage;
    private int stage2Age;
    private int stage3Age;
    private boolean canWalk;
    private boolean canBreathUnderwater;
    public static final LivingCondition frogLivingCondition = new WaterCondition(20, 85, "Wetland", 6.8, 60, 18, true, 0.1);
    public static final LivingCondition axolotlLivingCondition = new WaterCondition(16, 70, "Freshwater", 7.4, 150, 15, true, 0);

    // Description: constructor for amphibian
    public Amphibian (Animal parent) {
        super(parent);
        
        setMaxHunger(maxHunger(parent.getSpecie()));
        setTypeFoods(typeFoods(parent.getSpecie()));
        setLifeExpectancy(lifeExpectancy(parent.getSpecie()));
        setFlexibility(flexibility(parent.getSpecie()));
        setLivingCondition(livingCondition(parent.getSpecie()));
        setTotalDailyInteractions(totalDailyInteractions(parent.getSpecie()));
        setAdultAge(adultAge(parent.getSpecie()));
        setRequiredArea(requiredArea(parent.getSpecie()));
        this.stage2Age = stage2Age(parent.getSpecie());
        this.stage3Age = stage3Age(parent.getSpecie());
        this.canWalk = false;
        this.canBreathUnderwater = true;

    }
    public Amphibian(String habitatId, String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight, String currentStage) {

        super(habitatId,name, specie, preferedInteraction, gender, happiness, cleanliness, hunger, age, weight);

        setMaxHunger(maxHunger(specie));
        setTypeFoods(typeFoods(specie));
        setLifeExpectancy(lifeExpectancy(specie));
        setFlexibility(flexibility(specie));
        setLivingCondition(livingCondition(specie));
        setTotalDailyInteractions(totalDailyInteractions(specie)); 
        setAdultAge(adultAge(specie));
        setRequiredArea(requiredArea(specie));
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
        if (specie.equalsIgnoreCase("Frog")) {
            String[] foods = {"Flies", "Worms"};
            return foods;
        } else if (specie.equalsIgnoreCase("Axolotl")) {
            String[] foods = {"Insects", "Small Fish"};
            return foods;
        }
        return new String[0];
    }

    public static int lifeExpectancy(String specie) {
        if (specie.equalsIgnoreCase("Frog")) {
            return 5;
        } else if (specie.equalsIgnoreCase("Axolotl")) {
            return 15;
        }
        return -1;
    }
    public static double flexibility(String specie) {
        if (specie.equalsIgnoreCase("Frog")) {
            return 0.4;
        } else if (specie.equalsIgnoreCase("Axolotl")) {
            return 0.7;
        }
        return -1;
    }
    public static LivingCondition livingCondition(String specie) {
        if (specie.equalsIgnoreCase("Frog")) {
            return frogLivingCondition;
        } else if (specie.equalsIgnoreCase("Axolotl")) {
            return axolotlLivingCondition;
        }
        return null;
    }
    public static int totalDailyInteractions(String specie) {
        if (specie.equalsIgnoreCase("Frog")) {
            return 3;
        } else if (specie.equalsIgnoreCase("Axolotl")) {
            return 5;
        }
        return -1;
    }
    private static int adultAge(String specie) {
        if (specie.equalsIgnoreCase("Frog")) {
            return 5;
        } else if (specie.equalsIgnoreCase("Axolotl")) {
            return 3;
        }
        return -1;
    }
    private static int stage2Age(String specie) {
        if (specie.equalsIgnoreCase("Frog")) return 2;
        if (specie.equalsIgnoreCase("Axolotl")) return 1;
        return 0;
    }
    private static int stage3Age(String specie) {
        if (specie.equalsIgnoreCase("Frog")) return 4;
        if (specie.equalsIgnoreCase("Axolotl")) return 2;
        return 0;
    }
    private static double requiredArea(String specie) {
        if (specie.equalsIgnoreCase("Frog")) return 10.0;
        if (specie.equalsIgnoreCase("Axolotl")) return 8.0;
        return 0;
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
    public double getRequiredArea() {
        return (int) Amphibian.requiredArea(this.getSpecie());
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
    public void setAdultAge(int adultAge) {
        super.setAdultAge(adultAge);
    }

    // METHODS

    // Description: abstract method that formats all information of the animal
    public String toString() {
        return  super.toString() +
                "\nCurrent Stage: " + this.currentStage +
                "\nCan Walk: " + this.canWalk +
                "\nCan Breath Underwater: " + this.canBreathUnderwater;
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
        if (this.getSpecie().equalsIgnoreCase("Eagle")) {
            setWeight(getWeight() + 15);
            setMaxHunger(getMaxHunger() + 5);
        } else if (this.getSpecie().equalsIgnoreCase("Cockatoo")) {
            setWeight(getWeight() + 5);
            setMaxHunger(getMaxHunger() + 2);
        }
        if (getAge() > this.stage3Age) {
            this.currentStage = "Adult";
            this.canWalk = true;
            this.canBreathUnderwater = false;
        } else if (getAge() > this.stage2Age) {
            this.currentStage = "Juvenile";
            this.canWalk = true;
            this.canBreathUnderwater = true;
        } else {
            this.currentStage = "Larva";
            this.canWalk = false;
            this.canBreathUnderwater = true;
        }
        
    }
}
