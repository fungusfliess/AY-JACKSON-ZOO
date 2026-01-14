package Animal_Subsystem;
import Structure_Subsystem.*;

public class Reptile extends Animal{
    private int timeToShed;
    public static final LivingCondition crocodileLivingCondition = new WaterCondition(28, 80, "River Delta", 7.2, 180, 26, true, 0.5);
    public static final LivingCondition snakeLivingCondition = new LandCondition(26, 50, "Grassland", 40, 6, true, 45, 6);

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
    public Reptile(String habitatId,String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight) {
        super(habitatId, name, specie, preferedInteraction, gender, happiness, cleanliness, hunger, age, weight);

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
            return crocodileLivingCondition;
        } else if (specie.equalsIgnoreCase("Snake")) {
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
    public static int adultAge(String specie) {
        if (specie.equalsIgnoreCase("Crocodile")) {
            return 5;
        } else if (specie.equalsIgnoreCase("Snake")) {
            return 3;
        }
        return -1;
    }
    public static int timeToShed(String specie) {
        if (specie.equalsIgnoreCase("Crocodile")) {
            return 30;
        } else if (specie.equalsIgnoreCase("Snake")) {
            return 15;
        }
        return -1;
    }   

    // GETTERS

    public int getMaxHunger() {
        return Reptile.maxHunger(this.getSpecie());
    }
    public String[] getTypeFoods() {
        return Reptile.typeFoods(this.getSpecie());
    }
    public int getLifeExpectancy() {
        return Reptile.lifeExpectancy(this.getSpecie());
    }
    public double getFlexibility() {
        return Reptile.flexibility(this.getSpecie());
    } 
    public LivingCondition getLivingCondition() {
        return Reptile.livingCondition(this.getSpecie());
    }
    public int getTotalDailyInteractions() {
        return Reptile.totalDailyInteractions(this.getSpecie());
    }
    public int getAdultAge() {
        return Reptile.adultAge(this.getSpecie());
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
        return super.toString() + 
                "\nTime to Shed: " + this.timeToShed + " days";
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
        this.timeToShed--;
        
    }

    public void shedSkin() {
        if (timeToShed <= 0) {
            System.out.println(getName() + " the " + getSpecie() + " has shed its skin!");
            // Reset time to shed based on specie
            this.timeToShed = timeToShed(this.getSpecie());
            setCleanliness(MAX_STAT);
        } else {
            System.out.println(getName() + " the " + getSpecie() + " is not ready to shed its skin yet.");
        }
    }
}
