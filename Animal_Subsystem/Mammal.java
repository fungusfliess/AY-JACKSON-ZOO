package Animal_Subsystem;
import Structure_Subsystem.*;

public class Mammal extends Animal {
    
    // Description: constructor for mammals
    public Mammal(Animal parent) {
        super(parent);

        setMaxHunger(maxHunger(parent.getSpecie()));
        setTypeFoods(new String[]{"Milk"});
        setLifeExpectancy(lifeExpectancy(parent.getSpecie()));
        setFlexibility(flexibility(parent.getSpecie()));
        setLivingCondition(livingCondition(parent.getSpecie()));
        setTotalDailyInteractions(totalDailyInteractions(parent.getSpecie()));  
        setAdultAge(adultAge(parent.getSpecie()));
    }
    public Mammal(String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight) {

        super(name, specie, preferedInteraction, gender, happiness, cleanliness, hunger, age, weight);

        setMaxHunger(maxHunger(specie));
        setTypeFoods(typeFoods(specie));
        setLifeExpectancy(lifeExpectancy(specie));
        setFlexibility(flexibility(specie));
        setLivingCondition(livingCondition(specie));
        setTotalDailyInteractions(totalDailyInteractions(specie)); 
        setAdultAge(adultAge(specie));
    }

    // SETTING SPECIE BASED FIELDS
    public static int maxHunger(String specie) {
        if (specie.equalsIgnoreCase("Unicorn")) {
            return 150;
        } else if (specie.equalsIgnoreCase("Cabybara")) {
            return 80;
        }
        return -1;
    }
    public static String[] typeFoods(String specie) {
        if (specie.equalsIgnoreCase("Unicorn")) {
            String[] foods = {"Grass", "Hay", "Fruits"};
            return foods;
        } else if (specie.equalsIgnoreCase("Cabybara")) {
            String[] foods = {"Grass", "Vegetables"};
            return foods;
        }
        return new String[0];
    }

    public static int lifeExpectancy(String specie) {
        if (specie.equalsIgnoreCase("Unicorn")) {
            return 30;
        } else if (specie.equalsIgnoreCase("Cabybara")) {
            return 10;
        }
        return -1;
    }
    public static double flexibility(String specie) {
        if (specie.equalsIgnoreCase("Unicorn")) {
            return 0.4;
        } else if (specie.equalsIgnoreCase("Cabybara")) {
            return 0.7;
        }
        return -1;
    }
    public static LivingCondition livingCondition(String specie) {
        if (specie.equalsIgnoreCase("Unicorn")) {
            LivingCondition unicornLivingCondition = new LivingCondition();
            return unicornLivingCondition;
        } else if (specie.equalsIgnoreCase("Cabybara")) {
            LivingCondition cabybaraLivingCondition = new LivingCondition();
            return cabybaraLivingCondition;
        }
        return null;
    }
    public static int totalDailyInteractions(String specie) {
        if (specie.equalsIgnoreCase("Unicorn")) {
            return 3;
        } else if (specie.equalsIgnoreCase("Cabybara")) {
            return 5;
        }
        return -1;
    }
    public static int adultAge(String specie) {
        if (specie.equalsIgnoreCase("Unicorn")) {
            return 3;
        } else if (specie.equalsIgnoreCase("Cabybara")) {
            return 2;
        }
        return -1;
    }

    // GETTERS

    public int getMaxHunger() {
        return Mammal.maxHunger(this.getSpecie());
    }
    public String[] getTypeFoods() {
        return Mammal.typeFoods(this.getSpecie());
    }
    public int getLifeExpectancy() {
        return Mammal.lifeExpectancy(this.getSpecie());
    }
    public double getFlexibility() {
        return Mammal.flexibility(this.getSpecie());
    } 
    public LivingCondition getLivingCondition() {
        return Mammal.livingCondition(this.getSpecie());
    }
    public int getTotalDailyInteractions() {
        return Mammal.totalDailyInteractions(this.getSpecie());
    }
    public int getAdultAge() {
        return Mammal.adultAge(this.getSpecie());
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
                "Adulthood Age: " + getAdultAge() + "\n";

    }

    // Description: creates a new animal of the same type as its parent.
    //  This method is different for animals that produce offspring as eggs.
    //  Returns null if the animal does not have the requirements to reproduce.
    public Animal[] reproduce(Habitat habitat, String name) {
        if (this.getGender().equalsIgnoreCase("Female") && 
            this.getHappiness() >= (LOW_STAT * MAX_STAT) && 
            this.getAge() >= this.getAdultAge() &&  
            this.getHunger() <= (LOW_STAT * this.getMaxHunger())) {
                if (this.isSuitable(habitat)) {
                Mammal babyMammal = new Mammal(this);
                babyMammal.setName(name);
                if (Math.random() < 0.5) {
                    babyMammal.setGender("Male"); // 50% chance male
                } else {
                    babyMammal.setGender("Female"); // 50% chance female
                }
                habitat.addAnimal(babyMammal);
                return new Animal[]{babyMammal} ;
            }

        }
    }

    // Description: method that updates the age of the animal
    public void updateAge() {
        if (getDaysPassed() % 365 == 0) {
            int newAge = getAge() + 1;
            setAge(newAge);
            if (this.getSpecie().equalsIgnoreCase("Unicorn") && this.getAge() > 1) {
                setWeight(getWeight() + 15);
            } else if (this.getSpecie().equalsIgnoreCase("Cabybara")) {
                setWeight(getWeight() + 5);
            }
        }
    }
}
