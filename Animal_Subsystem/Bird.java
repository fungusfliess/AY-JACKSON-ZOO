package Animal_Subsystem;
import Structure_Subsystem.*;

public class Bird extends Animal{
    private boolean canFly;
    private int flyingAge;
    private boolean hasNest;
    
    // Description: constructor for bird
    public Bird (Animal parent) {
        super(parent);
        
        setMaxHunger(maxHunger(parent.getSpecie()));
        setTypeFoods(typeFoods(parent.getSpecie()));
        setLifeExpectancy(lifeExpectancy(parent.getSpecie()));
        setFlexibility(flexibility(parent.getSpecie()));
        setLivingCondition(livingCondition(parent.getSpecie()));
        setTotalDailyInteractions(totalDailyInteractions(parent.getSpecie()));
        setAdultAge(adultAge(parent.getSpecie()));
        this.canFly = canFly(parent.getSpecie());
        this.flyingAge = flyingAge(parent.getSpecie());  
        this.hasNest = false;

    }
    public Bird(String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight) {

        super(name, specie, preferedInteraction, gender, happiness, cleanliness, hunger, age, weight);

        setMaxHunger(maxHunger(specie));
        setTypeFoods(typeFoods(specie));
        setLifeExpectancy(lifeExpectancy(specie));
        setFlexibility(flexibility(specie));
        setLivingCondition(livingCondition(specie));
        setTotalDailyInteractions(totalDailyInteractions(specie)); 
        setAdultAge(adultAge(specie));
        this.canFly = canFly(specie);
        this.flyingAge = flyingAge(specie);  
        this.hasNest = false;
    }

    // SETTING SPECIE BASED FIELDS
    public static int maxHunger(String specie) {
        if (specie.equalsIgnoreCase("Eagle")) {
            return 150;
        } else if (specie.equalsIgnoreCase("Cockatoo")) {
            return 80;
        }
        return -1;
    }
    public static String[] typeFoods(String specie) {
        if (specie.equalsIgnoreCase("Eagle")) {
            String[] foods = {"Meat", "Fish"};
            return foods;
        } else if (specie.equalsIgnoreCase("Cockatoo")) {
            String[] foods = {"Seeds", "Fruits"};
            return foods;
        }
        return new String[0];
    }

    public static int lifeExpectancy(String specie) {
        if (specie.equalsIgnoreCase("Eagle")) {
            return 30;
        } else if (specie.equalsIgnoreCase("Cockatoo")) {
            return 10;
        }
        return -1;
    }
    public static double flexibility(String specie) {
        if (specie.equalsIgnoreCase("Eagle")) {
            return 0.4;
        } else if (specie.equalsIgnoreCase("Cockatoo")) {
            return 0.7;
        }
        return -1;
    }
    public static LivingCondition livingCondition(String specie) {
        if (specie.equalsIgnoreCase("Eagle")) {
            LivingCondition eagleLivingCondition = new LivingCondition();
            return eagleLivingCondition;
        } else if (specie.equalsIgnoreCase("Cockatoo")) {
            LivingCondition cockatooLivingCondition = new LivingCondition();
            return cockatooLivingCondition;
        }
        return null;
    }
    public static int totalDailyInteractions(String specie) {
        if (specie.equalsIgnoreCase("Eagle")) {
            return 3;
        } else if (specie.equalsIgnoreCase("Cockatoo")) {
            return 5;
        }
        return -1;
    }
    private static boolean canFly(String specie) {
        if (specie.equalsIgnoreCase("Eagle")) {
            return true;
        } else if (specie.equalsIgnoreCase("Cockatoo")) {
            return true;
        }
        return false;
    }
    private static int flyingAge(String specie) {
        if (specie.equalsIgnoreCase("Eagle")) {
            return 3;
        } else if (specie.equalsIgnoreCase("Cockatoo")) {
            return 2;
        }
        return 0;
    }
    private static int adultAge(String specie) {
        if (specie.equalsIgnoreCase("Eagle")) {
            return 5;
        } else if (specie.equalsIgnoreCase("Cockatoo")) {
            return 3;
        }
        return -1;
    }

    // GETTERS

    public int getMaxHunger() {
        return Bird.maxHunger(this.getSpecie());
    }
    public String[] getTypeFoods() {
        return Bird.typeFoods(this.getSpecie());
    }
    public int getLifeExpectancy() {
        return Bird.lifeExpectancy(this.getSpecie());
    }
    public double getFlexibility() {
        return Bird.flexibility(this.getSpecie());
    } 
    public LivingCondition getLivingCondition() {
        return Bird.livingCondition(this.getSpecie());
    }
    public int getTotalDailyInteractions() {
        return Bird.totalDailyInteractions(this.getSpecie());
    }
    public boolean getCanFly() {
        return canFly;
    }   
    public int getFlyingAge() {
        return flyingAge;
    }
    public int getAdultAge() {
        return Bird.adultAge(this.getSpecie());
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
    public void canFly(boolean canFly) {
        this.canFly = canFly;
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
                "Life Expectancy: " + getLifeExpectancy() + "\n" +
                "can fly: " + getCanFly() + "\n";

    }

    // Description: creates a new animal of the same type as its parent.
    //  This method is different for animals that produce offspring as eggs.
    //  Returns null if the animal does not have the requirements to reproduce.
    public Egg reproduce(Habitat habitat) {
        if (this.getGender().equalsIgnoreCase("Female") && 
            this.getHappiness() >= (LOW_STAT * MAX_STAT) && 
            this.getAge() >= this.getAdultAge() &&  
            this.getHunger() <= (LOW_STAT * this.getMaxHunger()) &&
            this.hasNest) {

            Egg egg = new Egg(this);
            return egg;
        }
        return null;
    }

    // Description: method that updates the age of the animal
    public void updateAge() {
        setAge(getAge() + 1);
        if (this.getSpecie().equalsIgnoreCase("Eagle")) {
            setWeight(getWeight() + 15);
            setMaxHunger(getMaxHunger() + 5);
        } else if (this.getSpecie().equalsIgnoreCase("Cockatoo")) {
            setWeight(getWeight() + 5);
            setMaxHunger(getMaxHunger() + 2);
        }
        if (this.getAge() >= this.getFlyingAge()) {
            this.canFly = true;
        }
    }
}
