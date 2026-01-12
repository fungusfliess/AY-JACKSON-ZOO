import Structure_Subsystem.*;

public class Fish extends Animal{
    private int amountEggs;
    
    // Description: constructor for fish
    public Fish(Animal parent) {
        super(parent);

        setMaxHunger(maxHunger(parent.getSpecie()));
        setTypeFoods(typeFoods(parent.getSpecie()));
        setLifeExpectancy(lifeExpectancy(parent.getSpecie()));
        setFlexibility(flexibility(parent.getSpecie()));
        setLivingCondition(livingCondition(parent.getSpecie()));
        setTotalDailyInteractions(totalDailyInteractions(parent.getSpecie()));  
        this.amountEggs = amountEggs(parent.getSpecie());
    }
    public Fish(String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight) {

        super(name, specie, preferedInteraction, gender, happiness, cleanliness, hunger, age, weight);

        setMaxHunger(maxHunger(specie));
        setTypeFoods(typeFoods(specie));
        setLifeExpectancy(lifeExpectancy(specie));
        setFlexibility(flexibility(specie));
        setLivingCondition(livingCondition(specie));
        setTotalDailyInteractions(totalDailyInteractions(specie)); 

        this.amountEggs = amountEggs(specie);
    }

    // SETTING SPECIE BASED FIELDS
    public static int maxHunger(String specie) {
        if (specie.equalsIgnoreCase("Shark")) {
            return 150;
        } else if (specie.equalsIgnoreCase("Sunfish")) {
            return 80;
        }
        return -1;
    }
    public static String[] typeFoods(String specie) {
        if (specie.equalsIgnoreCase("Shark")) {
            String[] foods = {"Meat", "Fish"};
            return foods;
        } else if (specie.equalsIgnoreCase("Sunfish")) {
            String[] foods = {"Algae", "Flakes"};
            return foods;
        }
        return new String[0];
    }

    public static int lifeExpectancy(String specie) {
        if (specie.equalsIgnoreCase("Shark")) {
            return 30;
        } else if (specie.equalsIgnoreCase("Sunfish")) {
            return 10;
        }
        return -1;
    }
    public static double flexibility(String specie) {
        if (specie.equalsIgnoreCase("Shark")) {
            return 0.4;
        } else if (specie.equalsIgnoreCase("Sunfish")) {
            return 0.7;
        }
        return -1;
    }
    public static LivingCondition livingCondition(String specie) {
        if (specie.equalsIgnoreCase("Shark")) {
            LivingCondition sharkLivingCondition = new LivingCondition();
            return sharkLivingCondition;
        } else if (specie.equalsIgnoreCase("Sunfish")) {
            LivingCondition sunfishLivingCondition = new LivingCondition();
            return sunfishLivingCondition;
        }
        return null;
    }
    public static int totalDailyInteractions(String specie) {
        if (specie.equalsIgnoreCase("Shark")) {
            return 3;
        } else if (specie.equalsIgnoreCase("Sunfish")) {
            return 5;
        }
        return -1;
    }
    private static int amountEggs(String specie) {
        if (specie.equalsIgnoreCase("Shark")) return 2;
        if (specie.equalsIgnoreCase("Sunfish")) return 10;
        return 0;
    }

    // GETTERS

    public int getMaxHunger() {
        return Fish.maxHunger(this.getSpecie());
    }
    public String[] getTypeFoods() {
        return Fish.typeFoods(this.getSpecie());
    }
    public int getLifeExpectancy() {
        return Fish.lifeExpectancy(this.getSpecie());
    }
    public double getFlexibility() {
        return Fish.flexibility(this.getSpecie());
    } 
    public LivingCondition getLivingCondition() {
        return Fish.livingCondition(this.getSpecie());
    }
    public int getTotalDailyInteractions() {
        return Fish.totalDailyInteractions(this.getSpecie());
    }
    public int getAmountEggs() {
        return amountEggs;
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
    public void setAmountEggs(int amountEggs) {
        this.amountEggs = amountEggs;
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
                "Amount of Eggs: " + getAmountEggs() + "\n";

    }

    // Description: creates a new animal of the same type as its parent.
    //  This method is different for animals that produce offspring as eggs.
    //  Returns null if the animal does not have the requirements to reproduce.
    public Egg[] reproduce(Habitat habitat) {
        if (this.getGender().equalsIgnoreCase("Female") && 
            this.getHappiness() >= (LOW_STAT * MAX_STAT) && 
            this.getAge() >= 2 &&  
            this.getHunger() <= (LOW_STAT * this.getMaxHunger())) {

            Egg[] eggs = new Egg[amountEggs]; 
            for (int i = 0; i < amountEggs; i++) {
                eggs[i] = new Egg(this);
            }
            return eggs;
        }
        return null;
    }

    // Description: method that updates the age of the animal
    public void updateAge() {
        if (getDaysPassed() % 365 == 0) {
            int newAge = getAge() + 1;
            setAge(newAge);
            if (this.getSpecie().equalsIgnoreCase("Shark")) {
                setWeight(getWeight() + 15);
                setMaxHunger(getMaxHunger() + 5);
            } else if (this.getSpecie().equalsIgnoreCase("Sunfish")) {
                setWeight(getWeight() + 5);
                setMaxHunger(getMaxHunger() + 2);
            }
        }
    }
}
