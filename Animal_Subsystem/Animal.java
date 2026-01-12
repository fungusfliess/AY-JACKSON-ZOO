package Animal_Subsystem;
public abstract class Animal {

    public static final int MAX_STAT = 100;
    public static final int LOW_STAT = 30;

    // COMMON FIELDS
    private Habitat habitat;
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

    public Animal(String name, String specie, String preferedInteraction, String gender, int happiness, int cleanliness, int hunger, int age, double weight) {
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
    public Habitat getHabitat() {
        return habitat;
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

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
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


    // NON-ABSTRACT METHODS
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

    // ABSTRACT METHODS

    // Description: abstract method that formats all information of the animal
    public abstract String toString();

    // Description: abstract helper method that updates the age of the animal
    public abstract void updateAge();

}
