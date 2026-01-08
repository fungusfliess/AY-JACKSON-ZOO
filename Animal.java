public abstract class Animal {

    public static final int MAX_STAT = 100;
    public static final int LOW_STAT = 30;

    private LivingCondition livingCondition;
    private Habitat habitat;

    private int maxHunger;
    private String name;
    private String specie;
    private String preferredInteraction;
    private int numPreferredInteractions;

    private String[] typeFoods;

    private int happiness;
    private int cleanliness;
    private int hunger;

    private int numDailyInteractions;
    private int totalDailyInteractions;

    private int lifeExpectancy;
    private int livingConditionId;
    private int habitatId;

    private int age;
    private double flexibility;
    private boolean rehomed;

    private String gender;
    private int weight;
    private int daysPassed;

    public Animal(String name, String specie, String preferedInteraction, String gender,
                  int happiness, int cleanliness, int hunger, int totalDailyInteractions, int age) {
        this.name = name;
        this.specie = specie;

    }
    // METHODS

    // feeds the animal with given food, if possible
    public boolean eat(String food, int amount) {

    }

    // updates the animal's happiness based on cleanliness, hunger, and interactions.
    public void calculateHappiness() {

    }

    // interacts with the animal given the name of said interaction.
    public void interact(String interaction) {

    }

    // moves animal to a new habitat if possible
    public boolean relocate(Habitat newHabitat) {

    }

    // checks to see if the animal is suitable for the given habitat
    public boolean isSuitable(Habitat habitat) {

    }

    // Description: simulates a day passing for animals
    public void passDay() {

    }

    // Description: determines if the animals hunger is low
    public boolean lowHunger() {

    }

    // Description: determines if the animals cleanliness is low
    public boolean lowCleansiness() {

    }

    // Description: determines if the animals happiness is low
    public boolean lowHappiness() {

    }

    // Description: abstract method that formats all information of the animal
    public abstract String toString();

    // Description: abstract method that creates a new animal of the same type as its parent.
                //  This method is different for animals that produce offspring as eggs.
                //  Returns null if the animal does not have the requirements to reproduce.
    public abstract Animal reproduce(Habitat habitat);

    // Description: abstract helper method that updates the age of the animal
    public abstract void updateAge();

}
