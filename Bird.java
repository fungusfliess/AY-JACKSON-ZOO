/*
   File Name: Bird.java
   Name: Jerry Ning
   Class: ICS4U1-23
   Date: Jan 7, 2025
   Description: Bird is an abstract class representing birds in the zoo.
                Birds can fly (depending on age) and may have nests.
*/
public abstract class Bird extends Animal {

    private boolean canFly;
    private int flyingAge;
    private boolean hasNest;

    // Description: constructor for birds (template-based)
    public Bird(Animal parent) {
        super(parent);
        this.hasNest = false;
    }

    // Description: full constructor
    public Bird(char habitatId, String name, String specie, String preferedInteraction, String gender,
                int happiness, int cleanliness, int hunger, int age, double weight) {

        super(habitatId, name, specie, preferedInteraction, gender,
              happiness, cleanliness, hunger, age, weight);

        this.hasNest = false;
    }

    // =========================
    // GETTERS (ACCESSORS)
    // =========================

    public boolean canFly() {
        return canFly;
    }

    public int getFlyingAge() {
        return flyingAge;
    }
    public boolean getHasNest() {
        return hasNest;
    }

    // =========================
    // SETTERS (MUTATORS)
    // =========================

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public void setFlyingAge(int flyingAge) {
        this.flyingAge = flyingAge;
    }

    public void setHasNest(boolean hasNest) {
        this.hasNest = hasNest;
    }

    // =========================
    // SHARED BIRD METHODS
    // =========================

    /* @description: Creates an egg if the bird can reproduce and has a nest
       @return an Egg object if reproduction is successful, null otherwise
    */
    public Egg reproduce() {
        if (canReproduce()) {

            Egg egg = new Egg(this);
            return egg;
        }
        return null;
    }

    /* @description: Overrides base canReproduce to also require a nest
       @return true if the bird can reproduce and has a nest, false otherwise
    */
    @Override
    public boolean canReproduce() {
        return super.canReproduce() && hasNest;
    }

    /* @description: Builds a nest for the bird to enable reproduction
    */
    public void buildNest() {
        this.hasNest = true;
    }
    
    @Override
    public String saveToString() {
        return super.saveToString() + "|" + hasNest;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nCan Fly: " + this.canFly +
               "\nFlying Age: " + this.flyingAge +
               "\nHas Nest: " + this.hasNest;
    }
}
