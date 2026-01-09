public class Fish extends Animal{



    // Description: constructor for fish
    public Fish(String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight) {

        super(name, specie, preferedInteraction, gender, happiness, cleanliness, hunger, age, weight);

        setMaxHunger(maxHunger(specie));
        setTypeFoods(typeFoods(specie));
        setLifeExpectancy(lifeExpectancy(specie));
        setFlexibility(flexibility(specie));
        setLivingCondition(livingCondition(specie));
        setTotalDailyInteractions(totalDailyInteractions(specie)); 

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
            return new String[]{"Meat", "Fish"};
        } else if (specie.equalsIgnoreCase("Sunfish")) {
            return new String[]{"Algae", "Flakes"};
        }
        return null;
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

    // METHODS

    // Description: abstract method that formats all information of the animal
    public String toString() {

    }

    // Description: abstract method that creates a new animal of the same type as its parent.
    //  This method is different for animals that produce offspring as eggs.
    //  Returns null if the animal does not have the requirements to reproduce.
    public Animal reproduce(Habitat habitat) {

    }

    // Description: abstract helper method that updates the age of the animal
    public void updateAge() {

    }
}
